package ir.infosphere.sport.biz;

import ir.infosphere.sport.bean.BaziBean;
import ir.infosphere.sport.dao.BaziDao;
import ir.infosphere.sport.dao.GheymateBaziDao;
import ir.infosphere.sport.entity.BakhshEntity;
import ir.infosphere.sport.entity.BaziEntity;
import ir.infosphere.sport.entity.DoreyeMosabeghatEntity;
import ir.infosphere.sport.entity.GheymateBaziEntity;
import ir.infosphere.sport.entity.MogheiyyatEntity;
import ir.infosphere.sport.entity.TimEntity;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BaziBiz {

	@Autowired
	private BaziDao baziDao;
	@Autowired
	private GheymateBaziDao gheymateBaziDao;

	@Transactional
	public List<BaziEntity> getAllBazi() {
		return baziDao.retrieveAll();
	}

	@Transactional
	public List<BaziEntity> retrieveBazi(DetachedCriteria criteria) {
		List<BaziEntity> list = baziDao.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public BaziEntity getBaziById(Integer id) {
		return baziDao.retrieveByID(id);
	}

	@Transactional
	public List<BaziEntity> getBaziByBakhshDate(BakhshEntity bakhsh,
			Date tarikh, Time zaman) {
		if (bakhsh == null || tarikh == null || zaman == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(BaziEntity.class);
		criteria.add(Restrictions.eq("bakhsh", bakhsh));
		criteria.add(Restrictions.eq("tarikheBazi", tarikh));
		criteria.add(Restrictions.eq("zamaneBazi", zaman));
		return baziDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<BaziEntity> getBaziByTimDate(TimEntity tim, Date tarikh,
			Time zaman) {
		if (tim == null || tarikh == null || zaman == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(BaziEntity.class);
		criteria.add(Restrictions.or(Restrictions.eq("mizban", tim),
				Restrictions.eq("mihman", tim)));
		criteria.add(Restrictions.eq("tarikheBazi", tarikh));
		criteria.add(Restrictions.eq("zamaneBazi", zaman));
		return baziDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public void createBazi(BakhshEntity bakhsh, TimEntity mizban,
			TimEntity mihman, Date tarikh, Time zaman, Boolean hassasiat,
			DoreyeMosabeghatEntity doreyeMosabeghat,
			java.util.Date zamaneShorooeSyncShodan,
			java.util.Date zamanePayaneSyncShodan) {
		BaziEntity bazi = new BaziEntity();
		bazi.setBakhsh(bakhsh);
		bazi.setMizban(mizban);
		bazi.setMihman(mihman);
		bazi.setTarikheBazi(tarikh);
		bazi.setZamaneBazi(zaman);
		bazi.setBaziyeHassas(hassasiat);
		bazi.setDoreyeMosabeghat(doreyeMosabeghat); // //
		bazi.setForoosheBelitFaalAst(false); // //
		bazi.setZamaneShorooeSyncShodan(zamaneShorooeSyncShodan);
		bazi.setZamanePayaneSyncShodan(zamanePayaneSyncShodan);
		baziDao.create(bazi);
	}

	@Transactional
	public void updateBazi(Integer id, BakhshEntity bakhsh, Date tarikh,
			Time zaman, boolean hassasiat,
			java.util.Date zamaneShorooeSyncShodan,
			java.util.Date zamanePayaneSyncShodan) {
		BaziEntity bazi = baziDao.retrieveByID(id);
		bazi.setBakhsh(bakhsh);
		bazi.setTarikheBazi(tarikh);
		bazi.setZamaneBazi(zaman);
		bazi.setBaziyeHassas(hassasiat);
		bazi.setZamaneShorooeSyncShodan(zamaneShorooeSyncShodan);
		bazi.setZamanePayaneSyncShodan(zamanePayaneSyncShodan);
		baziDao.update(bazi);
	}

	@Transactional
	public void deleteBazi(int id) {
		baziDao.delete(baziDao.retrieveByID(id));
	}

	@Transactional
	public GheymateBaziEntity getGheymatByMogheiyyat(BaziEntity bazi,
			MogheiyyatEntity mogheiyyat) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(GheymateBaziEntity.class);
		criteria.add(Restrictions.eq("mogheiyyat", mogheiyyat));
		criteria.add(Restrictions.eq("bazi", bazi));
		List<GheymateBaziEntity> list = gheymateBaziDao
				.retrieveAllByCriteria(criteria);
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	@Transactional
	public void insertGheymat(BaziEntity bazi, MogheiyyatEntity mogheiyyat,
			Integer gheimatBaKarteOzviat, Integer gheimatBaKarteMelli,
			Boolean faalBoodaneKharidBaKarteOzviat,
			Boolean faalBoodaneKharidBaKarteMelli,
			Boolean faalBoodaneKharidBeTafkikeBolook) {
		GheymateBaziEntity gheymat = new GheymateBaziEntity();
		gheymat.setBazi(bazi);
		gheymat.setMogheiyyat(mogheiyyat);
		gheymat.setGheymat(gheimatBaKarteOzviat);
		gheymat.setGheymateBaKarteMelli(gheimatBaKarteMelli);
		gheymat.setForooshBaKarteOzviyyatFaalAst(faalBoodaneKharidBaKarteOzviat);
		gheymat.setForooshBaKarteMelliFaalAst(faalBoodaneKharidBaKarteMelli);
		gheymat.setForooshBeBolookFaalAst(faalBoodaneKharidBeTafkikeBolook);
		gheymat.setSandalieBaghimande(0);
		gheymateBaziDao.create(gheymat);
	}

	@Transactional
	public void updateGheymat(GheymateBaziEntity gheymat,
			Integer gheimatBaKarteOzviat, Integer gheimatBaKarteMelli,
			Boolean faalBoodaneKharidBaKarteOzviat,
			Boolean faalBoodaneKharidBaKarteMelli,
			Boolean faalBoodaneKharidBeTafkikeBolook) {
		gheymat.setGheymat(gheimatBaKarteOzviat);
		gheymat.setGheymateBaKarteMelli(gheimatBaKarteMelli);
		gheymat.setForooshBaKarteOzviyyatFaalAst(faalBoodaneKharidBaKarteOzviat);
		gheymat.setForooshBaKarteMelliFaalAst(faalBoodaneKharidBaKarteMelli);
		gheymat.setForooshBeBolookFaalAst(faalBoodaneKharidBeTafkikeBolook);
		gheymateBaziDao.update(gheymat);
	}

	@Transactional
	public void cancelBazi(int id) {
		BaziEntity bazi = getBaziById(id);
		bazi.setTarikheLaghv(new Date(new java.util.Date().getTime()));
		baziDao.update(bazi);
	}

	@Transactional
	public void activeForoosh(BaziEntity bazi) {
		bazi.setForoosheBelitFaalAst(!bazi.getForoosheBelitFaalAst());
		baziDao.update(bazi);
	}

	@Transactional
	public List<BaziBean> getBaziFaal() {
		return getBaziFaalByTim(null);
	}

	@Transactional
	public List<BaziBean> getBaziFaalByTim(TimEntity tim) {
		DetachedCriteria criteria = DetachedCriteria.forClass(BaziEntity.class);
		criteria.add(Restrictions.eq("foroosheBelitFaalAst", Boolean.TRUE));
		if (tim != null)
			criteria.add(Restrictions.eq("mizban", tim));
		criteria.add(Restrictions.eq("sync", Boolean.FALSE));
		criteria.addOrder(Order.asc("tarikheBazi"));
		List<BaziEntity> list = baziDao.retrieveAllByCriteria(criteria);
		List<BaziBean> beans = new ArrayList<BaziBean>();
		for (BaziEntity entity : list) {
			beans.add(new BaziBean(entity));
		}
		return beans;
	}
}
