package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.RahgiriDao;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.DarkhasteKartEntity;
import ir.infosphere.sport.entity.MadreseEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.OzveBasteForooshEntity;
import ir.infosphere.sport.entity.PardakhtBelitEntity;
import ir.infosphere.sport.entity.PardakhtEntity;
import ir.infosphere.sport.entity.PardakhtSharjEntity;
import ir.infosphere.sport.entity.PardakhtSiasatEntity;
import ir.infosphere.sport.entity.RahgiriEntity;
import ir.infosphere.sport.entity.SiasatEntity;
import ir.infosphere.sport.entity.TimEntity;
import ir.infosphere.sport.entity.VaziateSandaliEntity;
import ir.infosphere.sport.util.FormatUtil;
import ir.infosphere.sport.util.PermissionUtil;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RahgiriBiz {
	@Autowired
	private RahgiriDao rahgiriDao;
	@Autowired
	private KodBiz kodBiz;
	@Autowired
	private OzvBiz ozvBiz;
	@Autowired
	private MadreseBiz madreseBiz;
	@Autowired
	private TimBiz timBiz;

	@Transactional
	public List<RahgiriEntity> getAllRahgiri() {
		List<RahgiriEntity> list = rahgiriDao.retrieveAll();
		return list;
	}

	@Transactional
	public int getAllNadideCount(OzvEntity ozv) {
		if (ozv == null)
			return 0;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(RahgiriEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		criteria.add(Restrictions.eq("portal",
				PermissionUtil.getCurrentPortal()));
		criteria.add(Restrictions.eq("dideShode", false));
		return rahgiriDao.getCountByCriteria(criteria).intValue();
	}

	@Transactional
	public List<RahgiriEntity> getLastRahgiriByOzv(OzvEntity ozv) {
		if (ozv == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(RahgiriEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		criteria.add(Restrictions.eq("portal",
				PermissionUtil.getCurrentPortal()));
		criteria.addOrder(Order.desc("tarikhZaman"));
		List<RahgiriEntity> list = rahgiriDao.retrieveAllByCriteria(criteria,
				0, getAllNadideCount(ozv) + 10);
		return list;
	}

	@Transactional
	public List<RahgiriEntity> getAllRahgiriByOzv(OzvEntity ozv) {
		if (ozv == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(RahgiriEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		criteria.add(Restrictions.eq("portal",
				PermissionUtil.getCurrentPortal()));
		criteria.addOrder(Order.desc("tarikhZaman"));
		List<RahgiriEntity> list = rahgiriDao.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public List<RahgiriEntity> getAllRahgiriByOzvNadide(OzvEntity ozv) {
		if (ozv == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(RahgiriEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		criteria.add(Restrictions.eq("portal",
				PermissionUtil.getCurrentPortal()));
		criteria.add(Restrictions.eq("dideShode", false));
		List<RahgiriEntity> list = rahgiriDao.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public List<RahgiriEntity> retrieveRahgiriByCriteria(
			DetachedCriteria criteria) {
		List<RahgiriEntity> list = rahgiriDao.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public RahgiriEntity retrieveRahgiriById(Long id) {
		return rahgiriDao.retrieveByID(id);
	}

	@Transactional
	public void create(RahgiriEntity rahgiri) {
		rahgiriDao.create(rahgiri);
	}

	@Transactional
	public void createForSiasat(SiasatEntity siasat, String kind, OzvEntity ozv, String message) {
		RahgiriEntity rahgiri = getDefaultRahgiri(PermissionUtil.getCurrentUser());
		rahgiri.setNoeAmaliat(kodBiz.getKodEntity(Constants.Rahgiri,
				Constants.Rahgiri_HagheOzviat));
		if (kind.equals(Constants.NoeMadrese))
			rahgiri.setSharheAmaliat("مدرسه - " + message);
		else if (kind.equals(Constants.NoeOzveMadrese))
			rahgiri.setSharheAmaliat("عضو مدرسه - " + message);
		else if (kind.equals(Constants.NoeTim))
			rahgiri.setSharheAmaliat("تیم - " + message);
		else if (kind.equals(Constants.NoeOzveTim))
			rahgiri.setSharheAmaliat("عضو تیم - " + message);
		else if (kind.equals(Constants.NoeSayer))
			rahgiri.setSharheAmaliat(message);
		rahgiri.setSiasat(siasat);
		rahgiri.setOzv(ozv);
		rahgiriDao.create(rahgiri);
	}
	
	@Transactional
	public void createForHagheOzviat(PardakhtSiasatEntity pardakhtSiasat,
			PardakhtEntity pardakht) {
		
		RahgiriEntity rahgiri = getDefaultRahgiri(pardakht.getOzv());
		rahgiri.setNoeAmaliat(kodBiz.getKodEntity(Constants.Rahgiri,Constants.Rahgiri_HagheOzviat));
		String sharheAmaliat = "پرداخت حق عضویت ";
		sharheAmaliat += pardakhtSiasat.getSiasat().getNoesisat().getMeghdar();
		if (pardakhtSiasat.getSiasat().getNoesisat().getMeghdar().equals(Constants.NoeTim)) {
			TimEntity tim = timBiz.getTimById(pardakhtSiasat.getMadreseTim().shortValue());
			sharheAmaliat += (tim != null) ? " - " + tim.getNameTim() : "";
		}
		else if (pardakhtSiasat.getSiasat().getNoesisat().getMeghdar().equals(Constants.NoeOzveTim)) {
			sharheAmaliat += (pardakhtSiasat.getSiasat().getSemateOzv() != null) ? " - " + pardakhtSiasat.getSiasat().getSemateOzv().getMeghdar() : "";
		}
		else if (pardakhtSiasat.getSiasat().getNoesisat().getMeghdar().equals(Constants.NoeMadrese)) {
			MadreseEntity madrese = madreseBiz.retrieveById(pardakhtSiasat.getMadreseTim());
			sharheAmaliat += (madrese != null) ? " - " + madrese.getNameMadrese() : "";
		}
		else if (pardakhtSiasat.getSiasat().getNoesisat().getMeghdar().equals(Constants.NoeOzveMadrese)) {
			sharheAmaliat += (pardakhtSiasat.getSiasat().getSemateOzv() != null) ? " - " + pardakhtSiasat.getSiasat().getSemateOzv().getMeghdar() : "";
		}
		else if (pardakhtSiasat.getSiasat().getNoesisat().getMeghdar().equals(Constants.NoeSayer)) {
			sharheAmaliat += (pardakhtSiasat.getSiasat().getGorooheKarbari() != null) ? " - " + pardakhtSiasat.getSiasat().getGorooheKarbari().getNam() : "";
		}
		sharheAmaliat += " با موفقیت انجام شد.";
		rahgiri.setSharheAmaliat(sharheAmaliat);
		rahgiri.setOzv(pardakhtSiasat.getOzv());
		rahgiri.setSiasat(pardakhtSiasat.getSiasat());
		rahgiri.setDideShode(false);
		rahgiriDao.create(rahgiri);
	}
	
	@Transactional
	public void createForSharj(PardakhtSharjEntity pardakhtSharj) {
		RahgiriEntity rahgiri = getDefaultRahgiri(pardakhtSharj.getPardakht().getOzv());
		rahgiri.setNoeAmaliat(kodBiz.getKodEntity(Constants.Rahgiri, Constants.Rahgiri_Sharjh));
		rahgiri.setSharheAmaliat("خرید شارژ به مبلغ " + FormatUtil.getPersianCommaSeparatedInteger(pardakhtSharj.getMablagh()) + " ریال با موفقیت انجام شد");
		rahgiri.setSharj(pardakhtSharj.getSharj());
		rahgiri.setOzv(pardakhtSharj.getOzv());
		rahgiri.setDideShode(false);
		rahgiriDao.create(rahgiri);
	}

	@Transactional
	public void createForDarkhasteKart(DarkhasteKartEntity darkhast,
			PardakhtEntity pardakht) {
		RahgiriEntity rahgiri = getDefaultRahgiri(pardakht.getOzv());
		rahgiri.setNoeAmaliat(kodBiz.getKodEntity(Constants.Rahgiri,
				Constants.Rahgiri_DarkhasteKart));
		rahgiri.setSharheAmaliat("درخواست کارت عضویت به صورت "
				+ darkhast.getNahveyeTahvil().getMeghdar());
		rahgiri.setOzv(darkhast.getOzv());
		rahgiri.setDarkhasteKart(darkhast);
		rahgiri.setDideShode(false);
		rahgiriDao.create(rahgiri);
	}
	
	@Transactional
	public void createForBasteForosh(OzveBasteForooshEntity ozveBaste, PardakhtEntity pardakht) {
		RahgiriEntity rahgiri = getDefaultRahgiri(pardakht.getOzv());
		rahgiri.setNoeAmaliat(kodBiz.getKodEntity(Constants.Rahgiri, Constants.Rahgiri_BasteForosh));
		rahgiri.setSharheAmaliat("خرید بسته فروش " + ozveBaste.getBasteForoosh().getNameBaste() + " با موفقیت انجام شد");
		rahgiri.setOzv(ozveBaste.getOzv());
		rahgiri.setBasteForosh(ozveBaste.getBasteForoosh());
		rahgiri.setDideShode(false);
		rahgiriDao.create(rahgiri);
	}
	
	@Transactional
	public void createForKarbarKharidar(PardakhtEntity pardakht, List<PardakhtBelitEntity> list) {
		OzvEntity karbarPardakhtKonande = pardakht.getOzv();
		boolean rahgiriRafte = false;
		for (PardakhtBelitEntity pardakhtBelit : list) {
			OzvEntity temp = getKarbarVaziateSandali(pardakhtBelit.getVaziateSandali());
			if (temp != null) {
				if (temp.equals(karbarPardakhtKonande)) {
					rahgiriRafte = true;
					break;
				}
			}
		}
		if (!rahgiriRafte)
		{
			RahgiriEntity rahgiri = getDefaultRahgiri(karbarPardakhtKonande);
			String template = getOthersTemplate(list, null, "");
			rahgiri.setOzv(karbarPardakhtKonande);
			rahgiri.setNoeAmaliat(kodBiz.getKodEntity(Constants.Rahgiri, Constants.Rahgiri_KharidBelit));
			rahgiri.setSharheAmaliat(template);
			rahgiriDao.create(rahgiri);
		}
	}
	
	private OzvEntity getKarbarVaziateSandali(VaziateSandaliEntity vaziateSandaliEntity) {
		OzvEntity ozveShenas = null;
		// BELIT BARAYE OZVE SAMANE KHARIDARI SHODE AST YA KHEYR
		if (vaziateSandaliEntity.getKart() != null) {
			if (vaziateSandaliEntity.getKart().getOzvEntity() != null)
				ozveShenas = vaziateSandaliEntity.getKart().getOzvEntity();
		}
		else if (vaziateSandaliEntity.getShomareyeMelli() != null) {
			OzvEntity temp = ozvBiz.retrieveOzvByKodeMeli(vaziateSandaliEntity.getShomareyeMelli());
			if (temp != null) {
				ozveShenas = temp;
			}
		}
		return ozveShenas;
	}
	
	@Transactional
	public void createForBelit(VaziateSandaliEntity vaziateSandaliEntity, PardakhtEntity pardakht, List<PardakhtBelitEntity> list) {
		boolean isKhodesh = false;
		OzvEntity ozveShenas = null;
		
		ozveShenas = getKarbarVaziateSandali(vaziateSandaliEntity);
		
		if (ozveShenas != null && ozveShenas.equals(pardakht.getOzv())) {
			isKhodesh = true;
		}
		
		String template = "خرید بلیط برای مسابقه " + vaziateSandaliEntity.getShenaseyeBazi().getMizban().getNameTim()
				+ " - " + vaziateSandaliEntity.getShenaseyeBazi().getMihman().getNameTim()
				+ " در موقعیت " + vaziateSandaliEntity.getMogheiyyat().getNameMogheiyyat()
				+ " کدملی / شماره کارت: " + (vaziateSandaliEntity.getKart() == null ? vaziateSandaliEntity.getShomareyeMelli() : vaziateSandaliEntity.getKart().getShomareyeKart());
		
		RahgiriEntity rahgiri = new RahgiriEntity();
		if (ozveShenas != null) {
			rahgiri = getDefaultRahgiri(pardakht.getOzv());
			if (isKhodesh == true && list.size() > 1) {
				template = getOthersTemplate(list, vaziateSandaliEntity, template);
			}
			rahgiri.setOzv(ozveShenas);
			rahgiri.setNoeAmaliat(kodBiz.getKodEntity(Constants.Rahgiri, Constants.Rahgiri_KharidBelit));
			rahgiri.setSharheAmaliat(template);
			rahgiriDao.create(rahgiri);
		}
	}
	
	private String getOthersTemplate(List<PardakhtBelitEntity> list, VaziateSandaliEntity vaziateSandaliEntity, String template) {
		template += "---- خرید بلیط برای سایرین: ";
		for (PardakhtBelitEntity pardakhtBelit : list) {
			if (!pardakhtBelit.getVaziateSandali().equals(vaziateSandaliEntity)) {
				String eachMember = "- کدملی / شماره کارت: " 
						+ (pardakhtBelit.getVaziateSandali().getKart() == null ? pardakhtBelit.getVaziateSandali().getShomareyeMelli(): pardakhtBelit.getVaziateSandali().getKart().getShomareyeKart())
						+ " در موقعیت "+ pardakhtBelit.getVaziateSandali().getMogheiyyat().getNameMogheiyyat();
				template += eachMember;
			}
		}
		return template;
	}

	public void updateDideShode(OzvEntity ozv) {
		if (ozv == null)
			return;
		DetachedCriteria criteria = DetachedCriteria.forClass(RahgiriEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		criteria.add(Restrictions.eq("dideShode", Boolean.FALSE));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		List<RahgiriEntity> list = rahgiriDao.retrieveAllByCriteria(criteria);
		for (RahgiriEntity entity : list) {
			entity.setDideShode(true);
			update(entity);
		}
	}

	private RahgiriEntity getDefaultRahgiri(OzvEntity ozv) {
		RahgiriEntity rahgiri = new RahgiriEntity();
		rahgiri.setKarbarAnjamDahandeh(ozv);
		rahgiri.setPortal(PermissionUtil.getCurrentPortal());
		rahgiri.setTarikhZaman(new Date());
		rahgiri.setDideShode(false);
		return rahgiri;
	}

	@Transactional
	public void update(RahgiriEntity rahgiri) {
		rahgiriDao.update(rahgiri);
	}

	@Transactional
	public void delete(Long id) {
		rahgiriDao.delete(rahgiriDao.retrieveByID(id));
	}
}
