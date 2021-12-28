package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.DarkhastKartDao;
import ir.infosphere.sport.dao.KartDao;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.KartEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.PardakhteDarkhasteKartEntity;
import ir.infosphere.sport.entity.PortalEntity;
import ir.infosphere.sport.entity.VaziateSandaliEntity;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class KartBiz {

	@Autowired
	private KodBiz kodBiz;
	@Autowired
	private KartDao kartDao;
	@Autowired
	private DarkhastKartDao darkhastKartDao;
	@Autowired
	private LogeVaziateKartBiz logeVaziateKartBiz;
	@Autowired
	private TarakonesheMaliBiz tarakonesheMaliBiz;
	@Autowired
	private LogePozBiz logePozBiz;
	@Autowired
	private VaziateSandaliBiz vaziateSandaliBiz;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(KartBiz.class);

	public List<KartEntity> getKartHayeOzv(OzvEntity ozv, PortalEntity portal) {
		if (ozv == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(KartEntity.class);
		criteria.add(Restrictions.eq("ozvEntity", ozv));
		criteria.add(Restrictions.eq("portalID", portal));
		return kartDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public KartEntity getKartByShomareyeKart(String shomareyeKart) {
		DetachedCriteria criteria = DetachedCriteria.forClass(KartEntity.class);
		criteria.add(Restrictions.eq("shomareyeKart", shomareyeKart));
		List<KartEntity> karts = kartDao.retrieveAllByCriteria(criteria);
		if (karts.size() == 0)
			return null;
		return karts.get(0);
	}

	public KartEntity getLastKart(OzvEntity ozv, PortalEntity portal) {
		if (ozv == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(KartEntity.class);
		criteria.add(Restrictions.eq("ozvEntity", ozv));
		criteria.add(Restrictions.eq("portalID", portal));
		List<KartEntity> list = kartDao.retrieveAllByCriteria(criteria);
		if (list.size() == 0)
			return null;
		else if (list.size() == 1)
			return list.get(0);

		KartEntity firstKart = null;
		for (KartEntity kart : list) {
			if (kart.getKarteGhabli() == null)
				firstKart = kart;
		}

		if (firstKart == null)
			return null;

		KartEntity kart = firstKart;
		KartEntity lastKart = firstKart;
		while (true) {
			kart = getNextKartID(kart, list);
			if (kart == null)
				break;
			lastKart = list.get(list.indexOf(kart));
		}
		return lastKart;
	}

	private KartEntity getNextKartID(KartEntity lastKart,
			List<KartEntity> kartList) {
		for (KartEntity kart : kartList) {
			if (kart.getKarteGhabli() != null)
				if (kart.getKarteGhabli().equals(lastKart))
					return kart;
		}
		return null;
	}

	// TODO: Check below
	// /**
	// * Prepare the List of SiahKartBeans of gheyreFaal and masdood Karts.
	// *
	// * @return the list of SiahKartBeans
	// */
	// @Transactional
	// public List<SiahKartBean> retrieveListeSiah() {
	// logger.debug(LoggerUtil.log("retrieveListeSiah"));
	//
	// List<KartEntity> masdoodKartList = kartDao.retrieveAllByProperty(
	// "masdood", Boolean.TRUE);
	// logger.info("retrieveListeSiah/ masdoodKartList:{}", masdoodKartList);
	// List<SiahKartBean> listeSiah = new ArrayList<SiahKartBean>();
	// for (KartEntity kart : masdoodKartList) {
	// if (kart.getShomareyeKart() != null && kart.getSerial() != null) {
	// listeSiah.add(new SiahKartBean(kart.getShomareyeKart(), kart
	// .getSerial(), true));
	// }
	// }
	// List<KartEntity> gheyreFaalKartList = kartDao.getGheyreFaalKart();
	// logger.info("retrieveListeSiah/ gheyreFaalKartList:{}",
	// gheyreFaalKartList);
	// for (KartEntity kart : gheyreFaalKartList) {
	// if (kart.getShomareyeKart() != null && kart.getSerial() != null) {
	// listeSiah.add(new SiahKartBean(kart.getShomareyeKart(), kart
	// .getSerial(), false));
	// }
	// }
	// return listeSiah;
	//
	// }
	//
	// /**
	// * Get number of all Kart which their "faal" field is false.
	// *
	// * @return
	// */
	// @Transactional
	// public int getAllGheyreFaalKartCount() {
	// logger.debug(LoggerUtil.log("getAllGheyreFaalKartCount"));
	//
	// return kartDao.retrieveAllByProperty("faal", false).size();
	// }
	//
	// @Transactional
	// public Integer kasreMojoodi(Integer serial, String shomareKart,
	// Integer mablagh) {
	// logger.debug(LoggerUtil.log("kasreMojjodi", shomareKart, mablagh));
	// KartEntity entity = kartDao.retrieveByProperty("shomareyeKart",
	// shomareKart);
	// logger.info("kasreMojjodi/ entry:{}", entity);
	// if (entity == null)
	// return null;
	// if (entity.getMojoodi() < mablagh || mablagh < 0)
	// return -1;
	// entity.setMojoodi(entity.getMojoodi() - mablagh);
	// kartDao.update(entity);
	// // TODO:logePozBiz.addForooshgahLog();
	// tarakonesheMaliBiz.createTarakonesheMaliForForooshgahPos(serial,
	// shomareKart, mablagh);
	// return entity.getMojoodi();
	// }
	//
	// @Transactional
	// public boolean isSiah(String shomareKart) {
	// logger.info("isSiah shomaerKart:{}", shomareKart);
	// KartEntity entity = kartDao.retrieveByProperty("shomareyeKart",
	// shomareKart);
	// if (entity == null)
	// return true;
	// if (entity.getFaal() && !entity.getMasdood())
	// return false;
	// return true;
	// }
	//
	// @Transactional
	// public Integer getMojoodi(String shomareKart) {
	// logger.debug(LoggerUtil.log("getMojoodi", shomareKart));
	// KartEntity entity = kartDao.retrieveByProperty("shomareyeKart",
	// shomareKart);
	// if (entity == null)
	// return null;
	//
	// return entity.getMojoodi();
	// }
	//
	// @Transactional
	// public SakhsiSaziBean getShakhsiSaziInfo(String serialKart) {
	// logger.debug(LoggerUtil.log("getShakhsiSaziInfo", serialKart));
	// KartEntity kart = kartDao.retrieveByProperty("serial", serialKart);
	// DarkhastKartentity darkhast = darkhastKartDao.retrieveByProperty(
	// "kart", kart);
	// if (kart == null)
	// return null;
	// return new SakhsiSaziBean(darkhast);
	//
	// }
	//
	// @Transactional
	// public Integer confirmShakhsiSazi(String serialKart, Boolean Taeed) {
	// logger.debug(LoggerUtil.log("confirmShakhsiSazi", serialKart));
	// KartEntity kart = kartDao.retrieveByProperty("serial", serialKart);
	// if (kart == null)
	// return 400;
	// logger.info("confirmShakhsiSazi/ kart:{}", kart);
	// DarkhastKartentity kartentity = darkhastKartDao.retrieveByProperty(
	// "kart", kart);
	// if (Taeed) {
	// kartentity.setMarhaleyeSodoor(kodBiz.getKodEntity(
	// Constants.MarhaleyeSodoor, Constants.ChapeBorooshoor));
	// kartentity.setVaziateMarhale(kodBiz.getKodEntity(
	// Constants.VaziateMarhale, Constants.BarresiNashode));
	// kartentity.setOnvaneKhata(null);
	// } else {
	// kartentity.setMarhaleyeSodoor(kodBiz.getKodEntity(
	// Constants.MarhaleyeSodoor, Constants.ShakhsiSazi));
	// kartentity.setVaziateMarhale(kodBiz.getKodEntity(
	// Constants.VaziateMarhale, Constants.DarayeKhata));
	// kartentity.setOnvaneKhata(kodBiz.getKodEntity(
	// Constants.OnvaneKhatayeShakhsiSazi,
	// Constants.adameShakhsiSazi));
	//
	// }
	// darkhastKartDao.update(kartentity);
	//
	// logeVaziateKartBiz.createLogeVaziateKart(kartentity);
	//
	// return 0;
	// }
	//
	// @Transactional
	// public Integer isShakhsiSazi(String serialKart) {
	// KartEntity kart = kartDao.retrieveByProperty("serial", serialKart);
	// if (kart == null)
	// return 400;
	// if (kart.getMasdood().equals(Boolean.TRUE)
	// || kart.getFaal().equals(Boolean.FALSE))
	// return 420;
	// logger.info("confirmShakhsiSazi/ kart:{}", kart);
	// DarkhastKartentity kartentity = darkhastKartDao.retrieveByProperty(
	// "kart", kart);
	// if (!kartentity.getMarhaleyeSodoor().equals(
	// kodBiz.getKodEntity(Constants.MarhaleyeSodoor,
	// Constants.ShakhsiSazi)))
	// return 410;
	// return 0;
	// }
	//
	// @Transactional
	// public Integer gheyreFaalKardan(String shomareKart) {
	// KartEntity kart = kartDao.retrieveByProperty("shomareyeKart",
	// shomareKart);
	// if (kart == null)
	// return 240;
	//
	// kart.setFaal(Boolean.FALSE);
	// DarkhastKartentity darkhasst = darkhastKartDao.retrieveByProperty(
	// "kart", kart);
	// if (!darkhasst.getMarhaleyeSodoor().equals(
	// kodBiz.getKodEntity(Constants.MarhaleyeSodoor, Constants.Faal))) {
	// return 250;
	// }
	// darkhasst.setVaziateMarhale(kodBiz.getKodEntity(
	// Constants.VaziateMarhale, Constants.DarayeKhata));
	// darkhasst.setOnvaneKhata(kodBiz.getKodEntity(
	// Constants.OnvaneKhatayeFaal, Constants.GheyreFaal));
	// darkhasst.setSharheKhata(Constants.SharheKhatayeFaal);
	// darkhastKartDao.update(darkhasst);
	// kartDao.update(kart);
	// return 0;
	//
	// }
	//
	// // code by Hesabgar
	// // 6 khordad 1392
	// public boolean isRepeatedShomareyeKart(String shomareyeKart) {
	// List<KartEntity> kartList = kartDao.retrieveAllByProperty(
	// "shomareyeKart", shomareyeKart);
	// if (kartList.size() > 0) {
	// return true;
	// } else {
	// return false;
	// }
	// }
	//
	// public KartEntity giveKart(String serialKart) {
	// return kartDao.retrieveByProperty("serial", serialKart);
	// }
	//
	// public void creatKart(KartEntity kart) {
	// kartDao.create(kart);
	// }
	//

	public void update(KartEntity kart) {
		kartDao.update(kart);
	}

	public void create(KartEntity kart) {
		kartDao.create(kart);
	}

	@Transactional
	public KartEntity creatNewKartOrAlmosana(
			PardakhteDarkhasteKartEntity pardakhteDarkhasteKartEntity) {
		OzvEntity ozv = pardakhteDarkhasteKartEntity.getOzv();
		KartEntity lastKart = getLastKart(ozv, pardakhteDarkhasteKartEntity.getPortal());

		KartEntity kart = new KartEntity();
		kart.setFaal(Boolean.TRUE);
		kart.setMasdood(Boolean.FALSE);
		kart.setOzvEntity(ozv);
		kart.setPortalID(pardakhteDarkhasteKartEntity.getPortal());
		kart.setVersion(0);

		if (lastKart == null) {
			kart.setMojoodi(0);
			kart.setKarteGhabli(null);
			kart.setVaziyyateKart(kodBiz.getKodEntity(Constants.VaziateKart,
					Constants.Jadid));
		} else {
			kart.setMojoodi(lastKart.getMojoodi());
			kart.setKarteGhabli(lastKart);
			kart.setVaziyyateKart(kodBiz.getKodEntity(Constants.VaziateKart,
					Constants.Almosana));

			lastKart.setMasdood(Boolean.TRUE);
			lastKart.setFaal(Boolean.FALSE);
			lastKart.setMojoodi(0);
			update(lastKart);

			updateBelitsForAlmosanaKart(lastKart, kart);
		}
		create(kart);
		return kart;
	}
	
	@Transactional
	private void updateBelitsForAlmosanaKart(KartEntity oldKart,
			KartEntity newKart) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(VaziateSandaliEntity.class);
		criteria.add(Restrictions.eq("kart", oldKart));
		List<VaziateSandaliEntity> vaziateSandaliEntities = vaziateSandaliBiz
				.retrieveByCriteria(criteria);
		for (VaziateSandaliEntity entity : vaziateSandaliEntities) {
			if (entity.getShenaseyeBazi().getTarikheBazi().after(new Date())) {
				entity.setKart(newKart);
				vaziateSandaliBiz.update(entity);
			}
		}
	}

}
