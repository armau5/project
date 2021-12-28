package ir.infosphere.sport.biz;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ir.infosphere.sport.bean.BolookBean;
import ir.infosphere.sport.bean.MogheiyyatBean;
import ir.infosphere.sport.bean.SandaliMaliReportBean;
import ir.infosphere.sport.bean.SandaliTedadiReportBean;
import ir.infosphere.sport.dao.VaziateSandaliDao;
import ir.infosphere.sport.entity.BaziEntity;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.DarkhasteBeliteSazmaniEntity;
import ir.infosphere.sport.entity.GheymateBaziEntity;
import ir.infosphere.sport.entity.KartEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.PardakhtBelitEntity;
import ir.infosphere.sport.entity.RadifeBolookEntity;
import ir.infosphere.sport.entity.VaziateBolookEntity;
import ir.infosphere.sport.entity.VaziateSandaliEntity;

@Component
public class VaziateSandaliBiz {

	private static String removeEventPrefix = "RemoveTicket";
	public static Integer waitMinutes = 15;

	@Autowired
	private VaziateSandaliDao vaziateSandaliDao;

	@Autowired
	private BaziBiz baziBiz;

	@Autowired
	private KartBiz kartBiz;

	@Autowired
	private KodBiz kodBiz;

	@Autowired
	private GheymateBaziBiz gheymateBaziBiz;

	@Autowired
	private VaziateBolookBiz vaziateBolookBiz;

	@Autowired
	private DarkhasteBeliteSazmaniBiz darkhasteBeliteSazmaniBiz;

	@Transactional
	public void create(VaziateSandaliEntity entity) {
		vaziateSandaliDao.create(entity);
	}

	@Transactional
	public void update(VaziateSandaliEntity entity) {
		vaziateSandaliDao.update(entity);
	}

	@Transactional
	public List<VaziateSandaliEntity> retrieveAll() {
		return vaziateSandaliDao.retrieveAll();
	}

	@Transactional
	public VaziateSandaliEntity retrieveByID(Integer id) {
		return vaziateSandaliDao.retrieveByID(id);
	}

	@Transactional
	public Boolean taghireVaziateSandaliBySeat(Integer vaziateSandaliId,
			KodEntity vaziateJadid, List<KodEntity> vaziathayeGhadim) {
		if (vaziathayeGhadim.size() == 0)
			return false;
		String kodhayeGhadim = "(";
		for (KodEntity kodeGhadim : vaziathayeGhadim) {
			kodhayeGhadim += " " + kodeGhadim.getId() + ",";
		}
		kodhayeGhadim = kodhayeGhadim.substring(0, kodhayeGhadim.length() - 1);
		kodhayeGhadim += ")";
		String q = "UPDATE `vaziatesandali` SET `vaziatesandali`.`VaziateSandali` = "
				+ vaziateJadid.getId()
				+ " WHERE `vaziatesandali`.`ShenaseyeVaziateSandali` = "
				+ vaziateSandaliId
				+ " AND `vaziatesandali`.`VaziateSandali` IN " + kodhayeGhadim;
		Query query = vaziateSandaliDao.getSessionFactory().getCurrentSession()
				.createSQLQuery(q);
		int affectedRows = query.executeUpdate();
		if (affectedRows == 1)
			return true;
		return false;
	}

	@Transactional
	public Boolean resrveSandaliBySeat(VaziateSandaliEntity seat,
			OzvEntity kharidar) {
		String q = "UPDATE `vaziatesandali` SET `vaziatesandali`.`ShenaseyeKarbareKharidar` = "
				+ kharidar.getId()
				+ ", `vaziatesandali`.`VaziateSandali` = 239, `vaziatesandali`.`TarikheReserve` = current_timestamp() WHERE `vaziatesandali`.`ShenaseyeVaziateSandali` = "
				+ seat.getId() + " AND `vaziatesandali`.`VaziateSandali` = 241";
		Query query = vaziateSandaliDao.getSessionFactory().getCurrentSession()
				.createSQLQuery(q);
		int affectedRows = query.executeUpdate();
		if (affectedRows == 1)
			return true;
		return false;
	}

	@Transactional
	public Boolean resrveSandaliByBolook(OzvEntity ozv, Integer baziId,
			Short bolookId, Boolean isMizban) {
		String q = "update `vaziatesandali`"
				+ " set `vaziatesandali`.`VaziateSandali` = 239,  `vaziatesandali`.`TarikheReserve` = current_timestamp(), `vaziatesandali`.`ShenaseyeKarbareKharidar` = "
				+ ozv.getId()
				+ " where `vaziatesandali`.`IsMizban` = "
				+ isMizban
				+ " and `vaziatesandali`.`ShenaseyeBazi` = "
				+ baziId
				+ " and `vaziatesandali`.`ShenaseyeBolook` = "
				+ bolookId
				+ " and `vaziatesandali`.`VaziateSandali` = 241 "
				+ " and `vaziatesandali`.`ShenaseyeKarbareKharidar` is NULL order by `vaziatesandali`.`ShenaseyeRadifeBolook` desc, `vaziatesandali`.`ShomareyeSandali` asc limit 1";
		Query query = vaziateSandaliDao.getSessionFactory().getCurrentSession()
				.createSQLQuery(q);
		int affectedRows = query.executeUpdate();

		if (affectedRows == 1)
			return true;
		return false;
	}

	@Transactional
	public List<VaziateSandaliEntity> retrieveAllForokhteShodeByNationalIdOrShomareyeKart(
			String kodeMelli, KartEntity kart) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(VaziateSandaliEntity.class);

		Criterion rest1 = Restrictions.eq("kart", kart);
		Criterion rest2 = Restrictions.eq("shomareyeMelli", kodeMelli);

		criteria.add(Restrictions.or(rest1, rest2));

		criteria.add(Restrictions.eq("vaziateSandali", kodBiz.getKodEntity(
				Constants.VaziateSandali, Constants.SandalieForookhteShode)));
		criteria.addOrder(Order.desc("tarikheReserve"));

		List<VaziateSandaliEntity> result = vaziateSandaliDao
				.retrieveAllByCriteria(criteria, 0, 10);
		if (result.size() == 0)
			return null;
		return result;
	}

	@Transactional
	public Boolean resrveSandaliByMogheyyat(OzvEntity ozv, Integer baziId,
			Integer MogheyyatId, Boolean isMizban) {
		String q = "update `vaziatesandali`"
				+ " set `vaziatesandali`.`VaziateSandali` = 239,  `vaziatesandali`.`TarikheReserve` = current_timestamp(), `vaziatesandali`.`ShenaseyeKarbareKharidar` = "
				+ ozv.getId()
				+ " where `vaziatesandali`.`IsMizban` = "
				+ isMizban
				+ " and `vaziatesandali`.`ShenaseyeBazi` = "
				+ baziId
				+ " and `vaziatesandali`.`ShenaseyeMogheiyyat` = "
				+ MogheyyatId
				+ " and `vaziatesandali`.`VaziateSandali` = 241 "
				+ " and `vaziatesandali`.`ShenaseyeKarbareKharidar` is NULL order by `vaziatesandali`.`ShenaseyeRadifeBolook` desc, `vaziatesandali`.`ShomareyeSandali` asc limit 1";

		Query query = vaziateSandaliDao.getSessionFactory().getCurrentSession()
				.createSQLQuery(q);
		int affectedRows = query.executeUpdate();
		if (affectedRows == 1)
			return true;
		return false;
	}

	@Transactional
	public List<VaziateSandaliEntity> retrieveByShomareMelli(Integer baziId,
			String shomareMelli) {
		BaziEntity bazi = baziBiz.getBaziById(baziId);
		DetachedCriteria criteria = DetachedCriteria
				.forClass(VaziateSandaliEntity.class);
		criteria.add(Restrictions.eq("shenaseyeBazi", bazi));
		criteria.add(Restrictions.eq("shomareyeMelli", shomareMelli));
		List<VaziateSandaliEntity> result = vaziateSandaliDao
				.retrieveAllByCriteria(criteria);
		if (result.size() == 0)
			return null;
		return result;
	}

	@Transactional
	public List<VaziateSandaliEntity> retrieveByShomareKart(Integer baziId,
			String shomareKart) {
		BaziEntity bazi = baziBiz.getBaziById(baziId);
		KartEntity kart = kartBiz.getKartByShomareyeKart(shomareKart);
		DetachedCriteria criteria = DetachedCriteria
				.forClass(VaziateSandaliEntity.class);
		criteria.add(Restrictions.eq("shenaseyeBazi", bazi));
		criteria.add(Restrictions.eq("kart", kart));
		List<VaziateSandaliEntity> result = vaziateSandaliDao
				.retrieveAllByCriteria(criteria);
		if (result.size() == 0)
			return null;
		return result;
	}

	@Transactional
	public VaziateSandaliEntity retrieveByShomareSandaliAndRadifeBolook(
			Integer baziId, Byte shomareyeSandali,
			RadifeBolookEntity radifeBolook) {
		BaziEntity bazi = baziBiz.getBaziById(baziId);
		DetachedCriteria criteria = DetachedCriteria
				.forClass(VaziateSandaliEntity.class);
		criteria.add(Restrictions.eq("shenaseyeBazi", bazi));
		criteria.add(Restrictions.eq("shomareyeSandali", shomareyeSandali));
		criteria.add(Restrictions.eq("shenaseyeRadifeBolook", radifeBolook));
		List<VaziateSandaliEntity> result = vaziateSandaliDao
				.retrieveAllByCriteria(criteria);
		if (result.size() == 0)
			return null;
		return result.get(0);
	}

	@Transactional
	public List<VaziateSandaliEntity> retrieveByCriteria(
			DetachedCriteria criteria) {
		return vaziateSandaliDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<VaziateSandaliEntity> retrieveByCriteria(
			DetachedCriteria criteria, Integer firstResult, Integer maxResult) {
		return vaziateSandaliDao.retrieveAllByCriteria(criteria, firstResult,
				maxResult);
	}

	@Transactional
	public void delete(VaziateSandaliEntity entity) {
		vaziateSandaliDao.delete(entity);
	}

	@Transactional
	public void batchCreate(String hql) {
		vaziateSandaliDao.batchCreate(hql);
	}

	@Transactional
	public Boolean reserveBelit(OzvEntity ozv, Integer baziId,
			VaziateSandaliEntity seat, BolookBean bolook,
			MogheiyyatBean mogheiyyat, Boolean isMizban) {

		Boolean reserved = false;
		List<VaziateSandaliEntity> oldReservedSandalis = getListeSandaliHayeReserveShodeOrBankRafte(
				ozv, baziId);

		if (seat != null && bolook == null && mogheiyyat == null) {
			reserved = resrveSandaliBySeat(seat, ozv);
		} else if (bolook != null && mogheiyyat == null && seat == null) {
			reserved = resrveSandaliByBolook(ozv, baziId, bolook.getId(),
					isMizban);
		} else if (mogheiyyat != null && bolook == null && seat == null) {
			reserved = resrveSandaliByMogheyyat(ozv, baziId,
					mogheiyyat.getId(), isMizban);
		}

		if (!reserved)
			return false;

		List<VaziateSandaliEntity> reservedSandalis = getListeSandaliHayeReserveShodeOrBankRafte(
				ozv, baziId);
		for (VaziateSandaliEntity vaziateSandali : reservedSandalis) {
			if (!oldReservedSandalis.contains(vaziateSandali)) {
				decreaseOrIncreaseSandalieBaghimande(vaziateSandali, false);
			}
			updateTarikheReserve(vaziateSandali);
		}

		return true;

	}

	@Transactional
	private void updateTarikheReserve(VaziateSandaliEntity vaziateSandali) {
		deleteEventForRemove(vaziateSandali);
		vaziateSandali.setTarikheReserve(new Date());
		vaziateSandaliDao.update(vaziateSandali);
		createEventForRemove(vaziateSandali);
	}

	@Transactional
	public void cancelReservedBelit(VaziateSandaliEntity entity) {
		deleteEventForRemove(entity);
		decreaseOrIncreaseSandalieBaghimande(entity, true);

		entity.setVaziateSandali(kodBiz.getKodEntity(Constants.VaziateSandali,
				Constants.SandalieAzad));
		entity.setDarkhasteBeliteSazmani(null);
		entity.setKarbareKharidar(null);
		entity.setTarikheReserve(null);
		entity.setShomareyeMelli(null);
		entity.setKart(null);
		vaziateSandaliDao.update(entity);
	}

	@Transactional
	public void decreaseOrIncreaseSandalieBaghimande(
			VaziateBolookEntity vaziateBolook, Integer number,
			Boolean isIncrease) {
		decreaseOrIncreaseSandalieBaghimandeForBolook(vaziateBolook, number,
				isIncrease);
		GheymateBaziEntity vaziateMogheiyat = gheymateBaziBiz
				.retrieveByMogheiyat(vaziateBolook.getBolook().getMogheiyyat(),
						vaziateBolook.getBazi());
		decreaseOrIncreaseSandalieBaghimandeForMogheiyat(vaziateMogheiyat,
				number, isIncrease);
	}

	@Transactional
	public void decreaseOrIncreaseSandalieBaghimande(
			VaziateSandaliEntity entity, Boolean isIncrease) {
		decreaseOrIncreaseSandalieBaghimandeForBolook(entity, isIncrease);
		decreaseOrIncreaseSandalieBaghimandeForMogheiyat(entity, isIncrease);
	}

	@Transactional
	private void decreaseOrIncreaseSandalieBaghimandeForBolook(
			VaziateSandaliEntity vaziateSandaliEntity, Boolean isIncrease) {
		VaziateBolookEntity vaziateBolookEntity = vaziateBolookBiz
				.getVaziateBolook(vaziateSandaliEntity.getShenaseyeBazi(),
						vaziateSandaliEntity.getBolook());
		String plusOrMinusSign = "-";
		if (isIncrease)
			plusOrMinusSign = "+";
		String q = "UPDATE `vaziatebolook` SET `SandalieBaghimande`= `SandalieBaghimande` "
				+ plusOrMinusSign
				+ " 1  WHERE  `ShenaseyeVaziateBolook`= "
				+ vaziateBolookEntity.getId() + "; ";
		Query query = vaziateSandaliDao.getSessionFactory().getCurrentSession()
				.createSQLQuery(q);
		query.executeUpdate();
	}

	@Transactional
	private void decreaseOrIncreaseSandalieBaghimandeForBolook(
			VaziateBolookEntity vaziateBolookEntity, Integer number,
			Boolean isIncrease) {
		String plusOrMinusSign = "-";
		if (isIncrease)
			plusOrMinusSign = "+";
		String q = "UPDATE `vaziatebolook` SET `SandalieBaghimande`= `SandalieBaghimande` "
				+ plusOrMinusSign
				+ number
				+ "  WHERE  `ShenaseyeVaziateBolook`= "
				+ vaziateBolookEntity.getId() + "; ";
		Query query = vaziateSandaliDao.getSessionFactory().getCurrentSession()
				.createSQLQuery(q);
		query.executeUpdate();
	}

	@Transactional
	private void decreaseOrIncreaseSandalieBaghimandeForMogheiyat(
			VaziateSandaliEntity vaziateSandaliEntity, Boolean isIncrease) {
		GheymateBaziEntity vaziateMogheiyat = gheymateBaziBiz
				.retrieveByMogheiyat(vaziateSandaliEntity.getBolook()
						.getMogheiyyat(), vaziateSandaliEntity
						.getShenaseyeBazi());
		String plusOrMinusSign = "-";
		if (isIncrease)
			plusOrMinusSign = "+";
		String q = "UPDATE `gheymatebazi` SET `SandalieBaghimande`= `SandalieBaghimande` "
				+ plusOrMinusSign
				+ " 1 WHERE `ShenaseyeGheymateBazi`= "
				+ vaziateMogheiyat.getId() + "; ";
		Query query = vaziateSandaliDao.getSessionFactory().getCurrentSession()
				.createSQLQuery(q);
		query.executeUpdate();
	}

	@Transactional
	private void decreaseOrIncreaseSandalieBaghimandeForMogheiyat(
			GheymateBaziEntity vaziateMogheiyat, Integer number,
			Boolean isIncrease) {
		String plusOrMinusSign = "-";
		if (isIncrease)
			plusOrMinusSign = "+";
		String q = "UPDATE `gheymatebazi` SET `SandalieBaghimande`= `SandalieBaghimande` "
				+ plusOrMinusSign
				+ number
				+ " WHERE `ShenaseyeGheymateBazi`= "
				+ vaziateMogheiyat.getId() + "; ";
		Query query = vaziateSandaliDao.getSessionFactory().getCurrentSession()
				.createSQLQuery(q);
		query.executeUpdate();
	}

	@Transactional
	public void createEventForRemove(VaziateSandaliEntity vaziateSandaliEntity) {
		GheymateBaziEntity vaziateMogheiyat = gheymateBaziBiz
				.retrieveByMogheiyat(vaziateSandaliEntity.getBolook()
						.getMogheiyyat(), vaziateSandaliEntity
						.getShenaseyeBazi());
		VaziateBolookEntity vaziateBolookEntity = vaziateBolookBiz
				.getVaziateBolook(vaziateSandaliEntity.getShenaseyeBazi(),
						vaziateSandaliEntity.getBolook());
		String q = "CREATE EVENT "
				+ VaziateSandaliBiz.removeEventPrefix
				+ vaziateSandaliEntity.getId()
				+ vaziateSandaliEntity.getTarikheReserve().getTime() / 1000
				+ " "
				+ "ON SCHEDULE AT CURRENT_TIMESTAMP + "
				+ "INTERVAL "
				+ VaziateSandaliBiz.waitMinutes
				+ " MINUTE "
				+ "ON COMPLETION NOT PRESERVE "
				+ "ENABLE "
				+ "DO BEGIN "
				+ "UPDATE `vaziatesandali` SET `VaziateSandali` = "
				+ kodBiz.getKodValue(Constants.VaziateSandali,
						Constants.SandalieAzad)
				+ ", `ShomareyeMelli`= NULL, `ShenaseyeKarbareKharidar` = NULL, `ShenaseyeKart` = NULL, `TarikheReserve` = NULL WHERE (`VaziateSandali` = "
				+ kodBiz.getKodValue(Constants.VaziateSandali,
						Constants.SandalieBeBankRafteh)
				+ " OR `VaziateSandali` = "
				+ kodBiz.getKodValue(Constants.VaziateSandali,
						Constants.SandalieReserveShode)
				+ ") AND `ShenaseyeVaziateSandali`= "
				+ vaziateSandaliEntity.getId()
				+ " AND `ShenaseyeKarbareKharidar` = "
				+ vaziateSandaliEntity.getKarbareKharidar().getId()
				+ "; "
				+ "UPDATE `vaziatebolook` SET `SandalieBaghimande`= `SandalieBaghimande` + 1  WHERE  `ShenaseyeVaziateBolook`= "
				+ vaziateBolookEntity.getId()
				+ "; "
				+ "UPDATE `gheymatebazi` SET `SandalieBaghimande`= `SandalieBaghimande` + 1 WHERE  `ShenaseyeGheymateBazi`= "
				+ vaziateMogheiyat.getId() + "; " + "END ";
		Query query = vaziateSandaliDao.getSessionFactory().getCurrentSession()
				.createSQLQuery(q);
		query.executeUpdate();
	}

	@Transactional
	public void deleteEventForRemove(VaziateSandaliEntity vaziateSandaliEntity) {
		try {
			String q = "DROP EVENT IF EXISTS "
					+ VaziateSandaliBiz.removeEventPrefix
					+ vaziateSandaliEntity.getId()
					+ vaziateSandaliEntity.getTarikheReserve().getTime() / 1000;
			Query query = vaziateSandaliDao.getSessionFactory()
					.getCurrentSession().createSQLQuery(q);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public Boolean isGhabeleHazf(VaziateSandaliEntity entity, OzvEntity ozv) {
		if (entity.getKarbareKharidar() == null)
			return false;
		if (entity.getKarbareKharidar().equals(ozv)
				&& (entity.getVaziateSandali().equals(
						kodBiz.getKodEntity(Constants.VaziateSandali,
								Constants.SandalieReserveShode)) || entity
						.getVaziateSandali().equals(
								kodBiz.getKodEntity(Constants.VaziateSandali,
										Constants.SandalieBeBankRafteh))))
			return true;
		return false;
	}

	@Transactional
	public Boolean isValidForPardakht(PardakhtBelitEntity pardakhtBelit) {
		VaziateSandaliEntity vaziat = pardakhtBelit.getVaziateSandali();
		if (!vaziat.getVaziateSandali().equals(
				kodBiz.getKodEntity(Constants.VaziateSandali,
						Constants.SandalieBeBankRafteh))
				|| !vaziat.getKarbareKharidar().equals(
						pardakhtBelit.getPardakht().getOzv())) {
			return false;
		}
		return true;
	}

	@Transactional
	public void nahayiSazieBelit(VaziateSandaliEntity vaziateSandaliEntity) {
		vaziateSandaliEntity.setVaziateSandali(kodBiz.getKodEntity(
				Constants.VaziateSandali, Constants.SandalieForookhteShode));
		if (vaziateSandaliEntity.getDarkhasteBeliteSazmani() != null) { // If
																		// this
																		// VaziateSandali
																		// is
																		// included
																		// in a
																		// DarkhasteBeliteSazmani
			DarkhasteBeliteSazmaniEntity darkhast = vaziateSandaliEntity
					.getDarkhasteBeliteSazmani();
			KodEntity kharideShode = kodBiz.getKodEntity(
					Constants.VaziateDarkhasteBeliteSazmani,
					Constants.VaziateDarkhasteBeliteSazmani_KharideShode);
			if (!darkhast.getVaziateDarkhast().equals(kharideShode)) {
				darkhast.setVaziateDarkhast(kharideShode);
				darkhasteBeliteSazmaniBiz.update(darkhast);
			}
		}
		update(vaziateSandaliEntity);
	}

	@Transactional
	public List<VaziateSandaliEntity> getListeSandaliHayeReserveShodeOrBankRafteOrFrookhteShode(
			OzvEntity ozv, Integer baziId) {
		BaziEntity bazi = baziBiz.getBaziById(baziId);

		KodEntity reserveShode = kodBiz.getKodEntity(Constants.VaziateSandali,
				Constants.SandalieReserveShode);
		KodEntity bankRafte = kodBiz.getKodEntity(Constants.VaziateSandali,
				Constants.SandalieBeBankRafteh);
		KodEntity forookhteShode = kodBiz.getKodEntity(Constants.VaziateSandali,
				Constants.SandalieForookhteShode);

		DetachedCriteria criteria = DetachedCriteria
				.forClass(VaziateSandaliEntity.class);
		criteria.add(Restrictions.eq("shenaseyeBazi", bazi));
		criteria.add(Restrictions.eq("karbareKharidar", ozv));
		/*criteria.add(Restrictions.or(
				Restrictions.eq("vaziateSandali", reserveShode),
				Restrictions.eq("vaziateSandali", bankRafte)));*/
		criteria.add(Restrictions.disjunction()
						.add(Restrictions.eq("vaziateSandali", reserveShode))
						.add(Restrictions.eq("vaziateSandali", bankRafte))
						.add(Restrictions.eq("vaziateSandali", forookhteShode)));

		List<VaziateSandaliEntity> entities = vaziateSandaliDao
				.retrieveAllByCriteria(criteria);
		return entities;
	}

	@Transactional
	public List<VaziateSandaliEntity> getListeSandaliHayeReserveShodeOrBankRafte(
			OzvEntity ozv, Integer baziId) {
		BaziEntity bazi = baziBiz.getBaziById(baziId);

		KodEntity reserveShode = kodBiz.getKodEntity(Constants.VaziateSandali,
				Constants.SandalieReserveShode);
		KodEntity bankRafte = kodBiz.getKodEntity(Constants.VaziateSandali,
				Constants.SandalieBeBankRafteh);


		DetachedCriteria criteria = DetachedCriteria
				.forClass(VaziateSandaliEntity.class);
		criteria.add(Restrictions.eq("shenaseyeBazi", bazi));
		criteria.add(Restrictions.eq("karbareKharidar", ozv));
		criteria.add(Restrictions.or(
				Restrictions.eq("vaziateSandali", reserveShode),
				Restrictions.eq("vaziateSandali", bankRafte)));


		List<VaziateSandaliEntity> entities = vaziateSandaliDao
				.retrieveAllByCriteria(criteria);
		return entities;
	}

	@Transactional
	public List<VaziateSandaliEntity> getSeatsByRow(RadifeBolookEntity row,
			BaziEntity bazi) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(VaziateSandaliEntity.class);
		criteria.add(Restrictions.eq("shenaseyeBazi", bazi));
		criteria.add(Restrictions.eq("shenaseyeRadifeBolook", row));
		criteria.addOrder(Order.asc("shomareyeSandali"));
		return retrieveByCriteria(criteria);
	}

	@Transactional
	public List<SandaliMaliReportBean> getSandaliMaliReportForBazi(
			BaziEntity bazi) {
		return vaziateSandaliDao.getSandaliMaliReportForBazi(
				bazi.getId(),
				kodBiz.getKodEntity(Constants.VaziateSandali,
						Constants.SandalieForookhteShode).getId());

	}
	
	@Transactional
	public List<SandaliMaliReportBean> getSandaliMaliReportForBaziByDate(
			BaziEntity bazi, Date azTarikh, Date TaTarikh) {
		if(bazi!=null)
		
		return vaziateSandaliDao.getSandaliMaliReportForBaziByDate(
				bazi.getId(),
				kodBiz.getKodEntity(Constants.VaziateSandali,
						Constants.SandalieForookhteShode).getId(),azTarikh, TaTarikh);
		else
			return vaziateSandaliDao.getSandaliMaliReportForBaziByDate(
					0,
					kodBiz.getKodEntity(Constants.VaziateSandali,
							Constants.SandalieForookhteShode).getId(),azTarikh, TaTarikh);

	}
	
	@Transactional
	public List<SandaliTedadiReportBean> getSandaliTedadiReportForBazi(
			BaziEntity bazi) {
		return vaziateSandaliDao.getSandaliTedadiReportForBazi(
				bazi.getId(),
				kodBiz.getKodEntity(Constants.VaziateSandali,
						Constants.SandalieForookhteShode).getId());

	}

	@Transactional
	public Boolean reserveYekSandaliBarayeKarbar(VaziateSandaliEntity seat,
			OzvEntity kharidar) {
		return reserveBelit(kharidar, seat.getShenaseyeBazi().getId(), seat,
				null, null, seat.getIsMizban());
	}

	@Transactional
	public Integer executeQuery(String inputQuery) {
		Query query = vaziateSandaliDao.getSessionFactory().getCurrentSession()
				.createSQLQuery(inputQuery);
		return query.executeUpdate();
	}
}
