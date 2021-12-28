package ir.infosphere.sport.biz;

import ir.infosphere.sport.bean.TakhfifeOzvBean;
import ir.infosphere.sport.dao.KartDao;
import ir.infosphere.sport.dao.OzvDao;
import ir.infosphere.sport.entity.AksEntity;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.GorooheKarbariEntity;
import ir.infosphere.sport.entity.GoroohhayeKarbarEntity;
import ir.infosphere.sport.entity.KartEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.NahiyeEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.OzveBasteForooshEntity;
import ir.infosphere.sport.entity.PortalEntity;
import ir.infosphere.sport.entity.ReshteyeVarzeshiEntity;
import ir.infosphere.sport.entity.SiasatTakhfifEntity;
import ir.infosphere.sport.entity.SoaleAmniatiEntity;
import ir.infosphere.sport.entity.TimEntity;
import ir.infosphere.sport.util.FormatUtil;
import ir.infosphere.sport.util.PermissionUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.image.AImage;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;

@Component
public class OzvBiz {
	@Autowired
	private OzvDao ozvDao;

	@Autowired
	private KartDao kartDao;

	@Autowired
	private FormatUtil formatUtil;

	@Autowired
	private GoroohhayeKarbarBiz goroohhayeKarbarBiz;

	@Autowired
	private OzveBasteForooshBiz ozveBasteForooshBiz;
	
	@Autowired
	private SiasatTakhfifBiz siasatTakhfifBiz;
	
	@Autowired
	private KodBiz kodBiz;
	
	@Transactional
	public boolean usernameIsRepeat(String username) {
		OzvEntity ozvEntity;
		try {
			ozvEntity = ozvDao.retrieveByProperty("nameKarbari", username);
		} catch (IllegalArgumentException e) {
			return true;
		}
		if (ozvEntity == null)
			return false;
		return true;
	}
	
	@Transactional
	public boolean IsUsernameValid(String username) {
		OzvEntity ozvEntity;
		try {
			ozvEntity = ozvDao.retrieveByProperty("nameKarbari", username);
		} catch (IllegalArgumentException e) {
			return true;
		}
		if (ozvEntity == null)
			return false;
		return true;
	}

	@Transactional
	public boolean kodemelliIsRepeat(String kodemeli) {
		OzvEntity ozvEntity = ozvDao.retrieveByProperty("kodeMeli", kodemeli);
		if (ozvEntity == null) {
			return false;
		}
		return true;
	}

	@Transactional
	public boolean passportIsRepeat(String passport) {
		OzvEntity ozvEntity = ozvDao.retrieveByProperty("shomareyeGozarname",
				passport);
		if (ozvEntity == null) {
			return false;
		}
		return true;
	}

	public boolean kodemelliIsValid(String nationalCode) {
		if (nationalCode.length() != 10)
			return false;
		int digit[] = new int[10];
		for (int i = 0; i < digit.length; i++)
			try {
				digit[i] = Integer.parseInt(nationalCode.charAt(i) + "");
			} catch (NumberFormatException e) {
				return false;
			}

		int sum = 0;
		for (int i = 0; i < digit.length - 1; i++)
			sum += (10 - i) * digit[i];

		if ((sum % 11) < 2) {
			if (digit[9] != (sum % 11))
				return false;
		} else if (digit[9] != 11 - (sum % 11))
			return false;

		return true;
	}

	@Transactional
	public boolean userHasEmail(String username) {
		OzvEntity ozvEntity = ozvDao
				.retrieveByProperty("nameKarbari", username);
		if (ozvEntity.getPosteElectronik().isEmpty())
			return false;
		return true;
	}

	public OzvEntity authenticate(String userName, String password) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OzvEntity.class);
		criteria.add(Restrictions.like("nameKarbari", userName));
		criteria.add(Restrictions.like("hasheRamzeOboor", getHash(password)));
		List<OzvEntity> list = ozvDao.retrieveAllByCriteria(criteria);
		if (!list.isEmpty())
			return list.get(0);
		return null;
	}

	@Transactional
	public OzvEntity createOzv(NahiyeEntity nahiye, AksEntity aks,
			KodEntity noeOzv, String shomareyeGozarname, String name,
			String famil, String nameHoghooghi, String kodeMeli,
			String shomareyeSabt, String shomareyeHamrah,
			String shomareyeSabet, String kodePosti, String adres,
			String posteElectronik, KodEntity jensiat, Date tarikheTavallod,
			Date tarikheSabt, String nameKarbari, String ramzeOboor,
			SoaleAmniatiEntity soaleAmniati, String hashePasokheSoaleAmniati,
			TimEntity timeMoredeAlaghe,
			ReshteyeVarzeshiEntity reshteyeVarzeshiyeMoredeAlaghe,
			KodEntity vaziateKarbar, PortalEntity portal, KodEntity keshvar) {
		OzvEntity entity = new OzvEntity();

		entity.setNahiye(nahiye);
		entity.setAks(aks);
		entity.setNoeOzv(noeOzv);
		entity.setName(name);
		entity.setFamil(famil);
		entity.setKodeMeli(kodeMeli);
		entity.setShomareyeGozarname(shomareyeGozarname);
		entity.setShomareyeHamrah(shomareyeHamrah);
		entity.setShomareyeSabet(shomareyeSabet);
		entity.setKodePosti(kodePosti);
		entity.setAdres(adres);
		entity.setPosteElectronik(posteElectronik);
		entity.setJensiat(jensiat);
		entity.setTarikheTavallod(tarikheTavallod);
		entity.setNameKarbari(nameKarbari);
		entity.setHasheRamzeOboor(getHash(ramzeOboor));
		entity.setSoaleAmniati(soaleAmniati);
		entity.setHashePasokheSoaleAmniati(hashePasokheSoaleAmniati);
		entity.setTimeMoredeAlaghe(timeMoredeAlaghe);
		entity.setReshteyeVarzeshiyeMoredeAlaghe(reshteyeVarzeshiyeMoredeAlaghe);
		entity.setTaeedeAks(vaziateKarbar);
		entity.setTarikheSabt(new Date());
		entity.setPortal(portal);
		entity.setKeshvar(keshvar);

		ozvDao.create(entity);

		return entity;
	}

	public String getHash(String plain) {
		MessageDigest algorithm = null;
		try {
			algorithm = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
		InputStream is = new ByteArrayInputStream(plain.getBytes());
		DigestInputStream dis = new DigestInputStream(is, algorithm);
		try {
			while (dis.read() != -1)
				;
		} catch (IOException ex) {
		}
		byte[] hash = algorithm.digest();
		return formatUtil.asHex(hash);
	}

	@Transactional
	public OzvEntity retrieveOzvByShomareyeKarteBaNam(String shomareyeKart) {
		DetachedCriteria criteria = DetachedCriteria.forClass(KartEntity.class);
		criteria.add(Restrictions.like("shomareyeKart", shomareyeKart));
		List<KartEntity> list = kartDao.retrieveAllByCriteria(criteria);
		if (list.isEmpty())
			return null;
		else if (!FormatUtil.nationalCodeIsValid(list.get(0).getOzvEntity()
				.getKodeMeli()))
			return null;
		else
			return list.get(0).getOzvEntity();
	}

	@Transactional
	public OzvEntity retrieveOzvByKodeMeli(String kodeMeli) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OzvEntity.class);
		criteria.add(Restrictions.like("kodeMeli", kodeMeli));
		List<OzvEntity> list = ozvDao.retrieveAllByCriteria(criteria);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Transactional
	public OzvEntity retrieveOzvByNameKarbari(String nameKarbari) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OzvEntity.class);
		criteria.add(Restrictions.like("nameKarbari", nameKarbari));
		List<OzvEntity> list = ozvDao.retrieveAllByCriteria(criteria);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<OzvEntity> getOzvhayeGorooheKarbari(GorooheKarbariEntity gorooh) {
		if (gorooh == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(OzvEntity.class);
		List<GoroohhayeKarbarEntity> listOzvHayeGorooh = goroohhayeKarbarBiz
				.getAllByGoroohKarbari(gorooh);
		if (listOzvHayeGorooh != null && listOzvHayeGorooh.size() > 0) {
			List<Integer> listOzv = new ArrayList<Integer>();
			for (GoroohhayeKarbarEntity goroohhayeKarbar : listOzvHayeGorooh) {
				listOzv.add(goroohhayeKarbar.getKarbar().getId());
			}
			criteria.add(Restrictions.in("id", listOzv));
		} else {
			criteria.add(Restrictions.isNull("id"));
		}
		return retrieveByCriteria(criteria);
	}

	@Transactional
	public void update(OzvEntity entity) {
		ozvDao.update(entity);
	}

	@Transactional
	public List<OzvEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return ozvDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<OzvEntity> retrieveByCriteria(DetachedCriteria criteria,
			int firstResult, int maxResults) {
		return ozvDao.retrieveAllByCriteria(criteria, firstResult, maxResults);
	}

	@Transactional
	public List<OzvEntity> retrieveAll() {
		return ozvDao.retrieveAll();
	}

	@Transactional
	public List<OzvEntity> retrieveAll(int firstResult, int maxResults) {
		return ozvDao.retrieveAll(firstResult, maxResults);
	}

	@Transactional
	public Integer countByCriteria(DetachedCriteria criteria) {
		return ozvDao.getCountByCriteria(criteria).intValue();
	}

	@Transactional
	public OzvEntity retrieveById(Integer id) {
		if (id == null)
			return null;
		else
			return ozvDao.retrieveByID(id);
	}

	@Transactional
	public void delete(Integer id) {
		ozvDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void delete(OzvEntity ozv) {
		ozvDao.delete(ozv);
	}

	public AImage getAks(OzvEntity ozv) throws IOException {
		AImage aks;
		String port = (Executions.getCurrent().getServerPort() == 80) ? ""
				: (":" + Executions.getCurrent().getServerPort());
		String serverAddress = Executions.getCurrent().getScheme() + "://"
				+ Executions.getCurrent().getServerName() + port
				+ Executions.getCurrent().getContextPath();
		if (ozv.getAks() == null)
			aks = new AImage(new URL(serverAddress
					+ Labels.getLabel("notFoundUserPictureAddress")));
		else
			try {
				aks = new AImage(ozv.getAks().getNameFileAks());
			} catch (Exception e) {
				aks = new AImage(new URL(serverAddress
						+ Labels.getLabel("notFoundUserPictureAddress")));
			}
		return aks;
	}

	public List<OzvEntity> getAllOzvByPortal(PortalEntity portal) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OzvEntity.class);
		criteria.add(Restrictions.eq("portal", portal));
		return ozvDao.retrieveAllByCriteria(criteria);
	}
	
	public List<TakhfifeOzvBean> getAllTakhfifHayeInKarbar(OzvEntity ozv) {
		List<TakhfifeOzvBean> listTakhfif = new ArrayList<TakhfifeOzvBean>();
		List<OzveBasteForooshEntity> listBasteHayeForosh = ozveBasteForooshBiz.getAllBasteHayeOzv(ozv);
		if (listBasteHayeForosh != null && listBasteHayeForosh.size() > 0) {
			for (OzveBasteForooshEntity item : listBasteHayeForosh) {
				List<SiasatTakhfifEntity> listSiasatHayeTakhfifBasteForosh = siasatTakhfifBiz.getAllSiasatTakhfifByBasteForosh(item.getBasteForoosh());
				if (listSiasatHayeTakhfifBasteForosh != null && listSiasatHayeTakhfifBasteForosh.size() > 0) {
					for (SiasatTakhfifEntity takhfif : listSiasatHayeTakhfifBasteForosh) {
						listTakhfif.add(new TakhfifeOzvBean(ozv, takhfif, Constants.SiasatTakhfif_BasteForsoh));
					}
				}
			}
		}
		KodEntity kodeHamegani = kodBiz.getKodEntity(Constants.SiasatTakhfif, Constants.SiasatTakhfif_Hamegani);
		List<SiasatTakhfifEntity> listTakhfifHayeHamegani = siasatTakhfifBiz.getAllSiasatTakhfifByNoeSiasat(kodeHamegani, false);
		if (listTakhfifHayeHamegani.size() > 0 && listTakhfifHayeHamegani != null)
		{
			for (SiasatTakhfifEntity takhfif : listTakhfifHayeHamegani) {
				listTakhfif.add(new TakhfifeOzvBean(ozv, takhfif, Constants.SiasatTakhfif_Hamegani));
			}
		}
		
		List<GoroohhayeKarbarEntity> listGoroohHayeKarbariInKarbar = goroohhayeKarbarBiz.getAllByOzvAndPortal(ozv, PermissionUtil.getCurrentPortal());
		if (listGoroohHayeKarbariInKarbar != null && listGoroohHayeKarbariInKarbar.size() > 0) {
			for (GoroohhayeKarbarEntity ozveGorooheKarbari : listGoroohHayeKarbariInKarbar) {
				List<SiasatTakhfifEntity> listTakhfifHayeGorooheKarbari = siasatTakhfifBiz.getAllSiasatTakhfifByGorooheKarbari(ozveGorooheKarbari.getGorooh());
				if (listTakhfifHayeGorooheKarbari != null && listTakhfifHayeGorooheKarbari.size() > 0) {
					for (SiasatTakhfifEntity takhfif : listTakhfifHayeGorooheKarbari) {
						listTakhfif.add(new TakhfifeOzvBean(ozv, takhfif, Constants.SiasatTakhfif_GoroheKarbari));
					}
				}
			}
		}
		return listTakhfif;
	}
}
