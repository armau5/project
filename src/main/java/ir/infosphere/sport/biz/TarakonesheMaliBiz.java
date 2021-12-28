package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.BaziDao;
import ir.infosphere.sport.dao.KartDao;
import ir.infosphere.sport.dao.LogeKarteBajeDao;
import ir.infosphere.sport.dao.PozDao;
import ir.infosphere.sport.dao.TarakonesheMaliDao;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.KartEntity;
import ir.infosphere.sport.entity.PozEntity;
import ir.infosphere.sport.entity.TarakonesheMaliEntity;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TarakonesheMaliBiz {
	@Autowired
	private TarakonesheMaliDao tarakonesheMaliDao;
	@Autowired
	private PozeHesabBiz pozeHesabBiz;
	@Autowired
	private KodBiz kodBiz;
	@Autowired
	private KartDao kartDao;
	
	@SuppressWarnings("unused")
	private PozDao pozDao;
	@SuppressWarnings("unused")
	private BaziDao baziDao;
	@SuppressWarnings("unused")
	private LogeKarteBajeDao logeKarteBajeDao;
	

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(TarakonesheMaliBiz.class);

	

	public void setKartDao(KartDao kartDao) {
		this.kartDao = kartDao;
	}

	public void setPozDao(PozDao pozDao) {
		this.pozDao = pozDao;
	}

	public void setBaziDao(BaziDao baziDao) {
		this.baziDao = baziDao;
	}

	public void setLogeKarteBajeDao(LogeKarteBajeDao logeKarteBajeDao) {
		this.logeKarteBajeDao = logeKarteBajeDao;
	}

	
	@Transactional
	public void createTarakonesheMaliKharidRaili(PozEntity poz,
			String shomareKart, Integer mablagh) {
		TarakonesheMaliEntity entity = new TarakonesheMaliEntity();
		entity.setHesab(pozeHesabBiz.getHesabTarafeGharardad(poz));
		entity.setZaman(new Date());
		entity.setNahveyeKharid(kodBiz.getKodEntity(Constants.NahveyeKharid,
				Constants.pozForooshgah));
		entity.setNoeTarakonesh(kodBiz.getKodEntity(Constants.NoeTarakonesh,
				Constants.ForoshReyali));
		entity.setMablagh(new Long(mablagh));
		KartEntity kart = kartDao.retrieveByProperty("shomareyeKart",
				shomareKart);
		entity.setKart(kart);
		tarakonesheMaliDao.create(entity);
	}

	
	//TODO:Check below
//	@Transactional
//	public void createTarakonesheMaliForBelit(BelitEntity belit,
//			LocalBelitBean localBelitBean) {
//		logger.info(
//				"createTarakonesheMaliForBelit/ belit.id:{} , localBelitBean.id:{}",
//				belit.getId(), localBelitBean.getId());
//		TarakonesheMaliEntity tarakonesh = new TarakonesheMaliEntity();
//		tarakonesh.setBelit(belit);
//		tarakonesh.setMablagh((long) (int) localBelitBean.getGheymat());
//		if (belit.getKart().getOzv() != null) {
//			tarakonesh.setOzv(belit.getKart().getOzv());
//		}
//		tarakonesh.setZaman(localBelitBean.getZamaneFroosh());
//
//		// here maybe need to change as written
//		// code by Hesabgar
//		// 19 khordad 1392
//		tarakonesh.setPoz(localBelitBean.getSerialePoz());
//		// ////////////////////////////
//		tarakonesh.setNahveyeKharid(kodBiz.getKodEntity(
//				Constants.NahveyeKharid, Constants.KharidAzBaje));
//		tarakonesh.setNoeTarakonesh(kodBiz.getKodEntity(
//				Constants.NoeTarakonesh, Constants.KharideBelit));
//		// code by hesabgar
//		// 8/2/92
//		// ezafe kardane shomare kart
//		tarakonesh.setKart(kartDao.retrieveByProperty("shomareyeKart",
//				localBelitBean.getShomareyeKart()));
//
//		// //////////
//		tarakonesheMaliDao.create(tarakonesh);
//		logger.info(
//				"createTarakonesheMaliForBelit/done belit.id:{} , localBelitBean.id:{}",
//				belit.getId(), localBelitBean.getId());
//	}
//
//	@Transactional
//	public void createTarakoneshhayeMaliForKarthayeBinam(
//			List<LogeKartePozeBajeBean> list) throws Exception {
//		if (list == null) {
//			return;
//		}
//
//		for (LogeKartePozeBajeBean bean : list) {
//			logger.info(bean.getShomareKart() + bean.getGheymat()
//					+ bean.getSerialePoz() + bean.getShenaseyeBazi()
//					+ bean.getZaman() + bean.isLaghvShode());
//			if (bean.isLaghvShode()) {
//				DetachedCriteria dc = DetachedCriteria
//						.forClass(LogeKarteBajeEntity.class);
//				dc.add(Restrictions.eq(
//						"kart",
//						kartDao.retrieveByProperty("shomareyeKart",
//								bean.getShomareKart())));
//				dc.add(Restrictions.eq(
//						"shenaseyePoz",
//						pozDao.retrieveByProperty("serial",
//								bean.getSerialePoz())));
//				dc.add(Restrictions.eq("zaman", bean.getZaman()));
//				dc.add(Restrictions.eq("gheymat", (long) (bean.getGheymat())));
//				if (bean.isFroosheKart()) {
//					dc.add(Restrictions.eq("noeTarakonesh", kodBiz
//							.getKodEntity(Constants.NoeTarakonesh,
//									Constants.ForoosheKarteBinam)));
//				} else {
//					dc.add(Restrictions.eq("noeTarakonesh", kodBiz
//							.getKodEntity(Constants.NoeTarakonesh,
//									Constants.KharideKarteBinam)));
//				}
//				dc.add(Restrictions.eq("bazi",
//						baziDao.retrieveByID(bean.getShenaseyeBazi())));
//				List<LogeKarteBajeEntity> logList = logeKarteBajeDao
//						.retrieveAllByCriteria(dc);
//				if (logList.size() > 0) {
//					TarakonesheMaliEntity entity = tarakonesheMaliDao
//							.retrieveByProperty("shenaseyeLogeKarteBaje",
//									logList.get(0));
//					if (entity != null) {
//						tarakonesheMaliDao.delete(entity);
//					}
//					logeKarteBajeDao.delete(logList.get(0));
//				}
//
//			} else {
//				LogeKarteBajeEntity logeKart = new LogeKarteBajeEntity();
//				TarakonesheMaliEntity tarakonesh = new TarakonesheMaliEntity();
//
//				logeKart.setGheymat((long) (int) bean.getGheymat());
//				logeKart.setShenaseyePoz(pozDao.retrieveByProperty("serial",
//						bean.getSerialePoz()));
//				logeKart.setBazi(baziDao.retrieveByID(bean.getShenaseyeBazi()));
//				logeKart.setZaman(bean.getZaman());
//
//				tarakonesh.setZaman(bean.getZaman());
//				tarakonesh.setPoz(bean.getSerialePoz());
//				tarakonesh.setNahveyeKharid(kodBiz.getKodEntity(
//						Constants.NahveyeKharid, Constants.KharidAzBaje));
//
//				if (bean.isFroosheKart()) {
//					logeKart.setNoeTarakonesh(kodBiz.getKodEntity(
//							Constants.NoeTarakonesh,
//							Constants.ForoosheKarteBinam));
//					tarakonesh.setNoeTarakonesh(kodBiz.getKodEntity(
//							Constants.NoeTarakonesh,
//							Constants.ForoosheKarteBinam));
//
//					tarakonesh.setMablagh((long) (int) bean.getGheymat());
//				} else {
//					logeKart.setNoeTarakonesh(kodBiz.getKodEntity(
//							Constants.NoeTarakonesh,
//							Constants.KharideKarteBinam));
//					tarakonesh
//							.setMablagh((long) (int) (bean.getGheymat() * (-1)));
//					tarakonesh.setNoeTarakonesh(kodBiz.getKodEntity(
//							Constants.NoeTarakonesh,
//							Constants.KharideKarteBinam));
//				}
//
//				String shomareyeKart = bean.getShomareKart();
//				KartEntity kart = kartDao.retrieveByProperty("shomareyeKart",
//						shomareyeKart);
//				if (kart != null) {
//					tarakonesh.setKart(kart);
//					tarakonesh.setOzv(kart.getOzv());
//					logeKart.setKart(kart);
//				} else {
//					throw new Exception(
//							"invalid value for shomareyeKart field of LocalBelitBean");
//				}
//
//				logeKarteBajeDao.create(logeKart);
//				tarakonesh.setShenaseyeLogeKarteBaje(logeKarteBajeDao
//						.retrieveByID(logeKart.getId()));
//				tarakonesheMaliDao.create(tarakonesh);
//			}
//		}
//	}
//
//	@Transactional
//	public void createTarakonesheMaliForForooshgahPos(Integer serial,
//			String shomareKart, Integer mablagh) {
//		logger.debug(
//				"createTarakonesheMaliForForooshgahPos/shomarekart:{},mablagh:{}",
//				shomareKart, mablagh);
//		TarakonesheMaliEntity entity = new TarakonesheMaliEntity();
//		pozBiz.getTarafeGharardad(serial);
//		// entity.setTarafeGharardad(pozBiz.getTarafeGharardad(serial));
//		entity.setZaman(new Date());
//		entity.setNahveyeKharid(kodBiz.getKodEntity(Constants.NahveyeKharid,
//				Constants.pozForooshgah));
//		entity.setNoeTarakonesh(kodBiz.getKodEntity(Constants.NoeTarakonesh,
//				Constants.kasreSharj));
//		entity.setMablagh(new Long(mablagh));
//		KartEntity kart = kartDao.retrieveByProperty("shomareyeKart",
//				shomareKart);
//		entity.setKart(kart);
//		tarakonesheMaliDao.create(entity);
//		logger.info("createTarakonesheMaliForForooshgahPos/ done");
//	}
}
