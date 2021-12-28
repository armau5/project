package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.BaziDao;
import ir.infosphere.sport.dao.BelitDao;
import ir.infosphere.sport.dao.BolookDao;
import ir.infosphere.sport.dao.KartDao;
import ir.infosphere.sport.dao.RadifeBolookDao;
import ir.infosphere.sport.entity.BaziEntity;
import ir.infosphere.sport.entity.BelitEntity;
import ir.infosphere.sport.entity.RadifeBolookEntity;
import ir.infosphere.sport.util.LoggerUtil;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BelitBiz {
	private static final Logger logger = LoggerFactory
			.getLogger(BelitBiz.class);
	@Autowired
	private BelitDao belitDao;
	@Autowired
	private TarakonesheMaliBiz tarakonesheMaliBiz;
	
	@SuppressWarnings("unused")
	private BaziDao baziDao;
	@SuppressWarnings("unused")
	private KartDao kartDao;
	@SuppressWarnings("unused")
	private RadifeBolookDao radifeBolookDao;
	@SuppressWarnings("unused")
	private BolookDao bolookDao;

	

	public void setBaziDao(BaziDao baziDao) {
		this.baziDao = baziDao;
	}

	public void setKartDao(KartDao kartDao) {
		this.kartDao = kartDao;
	}

	public void setRadifeBolookDao(RadifeBolookDao radifeBolookDao) {
		this.radifeBolookDao = radifeBolookDao;
	}

	public void setBolookDao(BolookDao bolookDao) {
		this.bolookDao = bolookDao;
	}



	/**
	 * Retrieve the Belit from CDB by its Bazi, RadifeBolook, and
	 * shomareyeSandali.
	 * 
	 * @param bazi
	 *            the Bazi of the Belit
	 * @param radifeBolook
	 *            the RadifeBolook of the Belit
	 * @param shomareyeSandali
	 *            the shomareyeSandali of the Belit
	 * @return the Belit
	 */
	@Transactional
	public BelitEntity retrieveBelit(BaziEntity bazi,
			RadifeBolookEntity radifeBolook, Short shomareyeSandali) {
		logger.debug(LoggerUtil.log("retrieveBelit:" + bazi + "..."
				+ radifeBolook + "..." + shomareyeSandali));

		DetachedCriteria criteria = DetachedCriteria
				.forClass(BelitEntity.class);
		criteria.add(Restrictions.eq("bazi", bazi));
		criteria.add(Restrictions.eq("radifeBolook", radifeBolook));
		criteria.add(Restrictions.eq("shomareyeSandali", shomareyeSandali));
		List<BelitEntity> belitList = belitDao.retrieveAllByCriteria(criteria);
		// logger.info("size of criteria: "+belitList.size());
		if (belitList.size() == 1) {
			return belitList.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public List<BelitEntity> retrieveAllBelits(BaziEntity bazi) {
		logger.debug(LoggerUtil.log("retrieveAllBelits:" + bazi));

		DetachedCriteria criteria = DetachedCriteria
				.forClass(BelitEntity.class);
		criteria.add(Restrictions.eq("bazi", bazi));
		List<BelitEntity> belitList = belitDao.retrieveAllByCriteria(criteria);
		if (belitList.size() > 0) {
			return belitList;
		} else {
			return null;
		}
	}

	
	/**
	 * 
	 * Create Belits from the list of LocalBelitBeans which are the belits sold
	 * at Baje.
	 * 
	 * @param localBelitBeanList
	 *            the list of LocalBelitBeans
	 * @throws Exception
	 *             which indicates that some value of a LocalBelitBean is
	 *             invalid, not in CDB, or null.
	 */

	// TODO: Check below function to work

	// @Transactional
	// public void createBelithayeForookhteShodeDarBaje(
	// List<LocalBelitBean> localBelitBeanList) throws Exception {
	// logger.info(LoggerUtil.log("createBelithayeForookhteShodeDarBaje",
	// localBelitBeanList));
	//
	// if (localBelitBeanList == null) {
	// return;
	// }
	// for (LocalBelitBean bean : localBelitBeanList) {
	// BaziEntity bazi = baziDao.retrieveByID(bean.getShenaseyeBazi());
	// if (bazi != null) {
	// } else {
	// logger.error("invalid value for shenaseyeBazi field of LocalBelitBean: "
	// + bean.getShenaseyeBazi());
	// continue;
	// }
	// DetachedCriteria dc = DetachedCriteria.forClass(BelitEntity.class);
	// dc.add(Restrictions.eq("radifeBolook", getRadifeBolook(bean, bazi)));
	// dc.add(Restrictions.eq("bazi", bazi));
	// dc.add(Restrictions.eq("shomareyeSandali",
	// (short) (byte) (bean.getShomareyeSandali())));
	// List<BelitEntity> belitList = belitDao.retrieveAllByCriteria(dc);
	// if (bean.getShomareyeKart() == null
	// || bean.getShomareyeKart().isEmpty()) { // Must Delete
	// if (belitList.size() > 0) {
	// TarakonesheMaliEntity tarakonesh = tarakonesheMaliDao
	// .retrieveByProperty("belit", belitList.get(0));
	// tarakonesheMaliDao.delete(tarakonesh);
	// belitDao.delete(belitList.get(0));
	// }
	// } else {
	// String shomareyeKart = bean.getShomareyeKart();
	// KartEntity kart = kartDao.retrieveByProperty("shomareyeKart",
	// shomareyeKart);
	// if (kart == null) {
	// logger.error("invalid value for shomareyeKart field of LocalBelitBean: "
	// + bean.getShomareyeKart());
	// continue;
	// }
	// if (belitList.size() > 0) { // Must Update
	// BelitEntity belit = belitList.get(0);
	// TarakonesheMaliEntity tarakonesh = tarakonesheMaliDao
	// .retrieveByProperty("belit", belitList.get(0));
	// belit.setKart(kart);
	// belit.setTimeTarafdar(getTimeTarafdar(bean, bazi));
	// belit.setBazi(bazi);
	// belitDao.update(belit);
	// tarakonesh.setKart(kart);
	// tarakonesh.setMablagh((long) bean.getGheymat());
	// tarakonesh.setOzv(kart.getOzv());
	// tarakonesh.setPoz(bean.getSerialePoz());
	// tarakonesh.setZaman(bean.getZamaneFroosh());
	// tarakonesheMaliDao.update(tarakonesh);
	// } else { // Must Create
	// BelitEntity belit = new BelitEntity();
	// belit.setBazi(bazi);
	// belit.setKart(kart);
	// RadifeBolookEntity radif = getRadifeBolook(bean, bazi);
	// if (radif == null) {
	// logger.error("invalid value for shomareyeRadif field of LocalBelitBean bolook: "
	// + bean.getShomareyeBolook()
	// + " radif: "
	// + bean.getShomareyeRadif()
	// + " sandali: "
	// + bean.getShomareyeSandali());
	// continue;
	// }
	// belit.setRadifeBolook(radif);
	// belit.setShomareyeSandali((short) (byte) bean
	// .getShomareyeSandali());
	// TimEntity timeTarafdar = getTimeTarafdar(bean, bazi);
	// belit.setTimeTarafdar(timeTarafdar);
	// belitDao.create(belit);
	// tarakonesheMaliBiz.createTarakonesheMaliForBelit(belit,
	// bean);
	// }
	// }
	// }
	// }
	//
	// /**
	// * Get timeTarafdar of a Belit from its LocalBelitBean and its Bazi.
	// *
	// * @param localBelitBean
	// * the LocalBelitBean which has data of the Belit.
	// * @param bazi
	// * the Bazi of the Belit
	// * @return the Tim value of timeTarafdar field of Belit
	// * @throws Exception
	// * which indicates that sandalieMizban value of the
	// * LocalBelitBean is invalid, not in CDB, or null.
	// */
	// @Transactional
	// private TimEntity getTimeTarafdar(LocalBelitBean localBelitBean,
	// BaziEntity bazi) throws Exception {
	// logger.debug(LoggerUtil.log("getTimeTarafdar", localBelitBean, bazi));
	//
	// Short sandalieMizban = localBelitBean.getSandaliyeMizban();
	// if (sandalieMizban == 1) {
	// return bazi.getMizban();
	// } else if (sandalieMizban == 0) {
	// return bazi.getMihman();
	// } else {
	// throw new Exception(
	// "invalid value for sandalieMizban field of LocalBelitBean");
	// }
	// }
	//
	// /**
	// * Get RadifeBolook of a Belit from its LocalBelitBean and its Bazi.
	// *
	// * @param localBelitBean
	// * the LocalBelitBean which has data of the Belit.
	// * @param bazi
	// * the Bazi of the Belit
	// * @return the RadifeBolook of the Belit
	// * @throws Exception
	// * which indicates that shomareyeBolook or shomareyeRadif value
	// * of the LocalBelitBean is invalid, not in CDB, or null.
	// */
	// @Transactional
	// private RadifeBolookEntity getRadifeBolook(LocalBelitBean localBelitBean,
	// BaziEntity bazi) throws Exception {
	// logger.debug(LoggerUtil.log("getRadifeBolook", localBelitBean, bazi));
	//
	// DetachedCriteria criteria = DetachedCriteria
	// .forClass(BolookEntity.class);
	// criteria.createAlias("mogheiyyat", "mogheiyyat");
	// criteria.add(Restrictions.eq("bakhsh", bazi.getBakhsh()));
	// criteria.add(Restrictions.eq("mogheiyyat.nameMogheiyyat",
	// localBelitBean.getMogheyiat()));
	// criteria.add(Restrictions.eq("shomareyeBolook",
	// localBelitBean.getShomareyeBolook()));
	// List<BolookEntity> bolookList = bolookDao
	// .retrieveAllByCriteria(criteria);
	//
	// if (bolookList.size() != 1) {
	// throw new Exception(
	// "invalid value for shomareyeBolook field of LocalBelitBean");
	// } else {
	// BolookEntity bolook = bolookList.get(0);
	// String shomareyeRadif = localBelitBean.getShomareyeRadif();
	// criteria = DetachedCriteria.forClass(RadifeBolookEntity.class);
	// criteria.add(Restrictions.eq("shomareyeRadif", shomareyeRadif));
	// criteria.add(Restrictions.eq("bolook", bolook));
	// List<RadifeBolookEntity> radifeBolookList = radifeBolookDao
	// .retrieveAllByCriteria(criteria);
	// if (radifeBolookList.size() == 1) {
	// return radifeBolookList.get(0);
	// } else {
	// logger.info("shomareyeRadif={{}}", shomareyeRadif);
	// logger.info("bolook.id={{}}", bolook.getId());
	// logger.info("result count={}", radifeBolookList.size());
	// throw new Exception(
	// "invalid value for shomareyeRadif field of LocalBelitBean");
	// }
	// }
	// }
}
