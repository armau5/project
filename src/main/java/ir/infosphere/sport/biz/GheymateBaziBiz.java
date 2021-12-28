package ir.infosphere.sport.biz;

import ir.infosphere.sport.bean.GheymatBean;
import ir.infosphere.sport.bean.MogheiyyatBean;
import ir.infosphere.sport.dao.GheymateBaziDao;
import ir.infosphere.sport.entity.BaziEntity;
import ir.infosphere.sport.entity.GheymateBaziEntity;
import ir.infosphere.sport.entity.MogheiyyatEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GheymateBaziBiz {
	@Autowired
	private GheymateBaziDao gheymateBaziDao;

	@Autowired
	private BaziBiz baziBiz;

	@Transactional
	public Map<Integer, GheymatBean> gheymatMap(Integer baziId) {
		BaziEntity bazi = baziBiz.getBaziById(baziId);
		Map<Integer, GheymatBean> gheymats = new HashMap<Integer, GheymatBean>();
		for (GheymateBaziEntity entity : bazi.getGheymatList()) {
			gheymats.put(
					entity.getMogheiyyat().getId(),
					new GheymatBean(entity.getGheymat(), entity
							.getGheymateBaKarteMelli()));
		}
		return gheymats;
	}

	@Transactional
	public List<MogheiyyatBean> getMogheiyats(Integer baziId) {
		BaziEntity bazi = baziBiz.getBaziById(baziId);

		DetachedCriteria criteria = DetachedCriteria
				.forClass(GheymateBaziEntity.class);
		criteria.add(Restrictions.eq("bazi", bazi));
		List<GheymateBaziEntity> list = gheymateBaziDao
				.retrieveAllByCriteria(criteria);
		List<MogheiyyatBean> beans = new ArrayList<MogheiyyatBean>();
		for (GheymateBaziEntity entity : list) {
			beans.add(new MogheiyyatBean(entity));
		}
		return beans;
	}

	@Transactional
	public List<GheymateBaziEntity> getGheymateBaziList(BaziEntity bazi) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(GheymateBaziEntity.class);
		criteria.add(Restrictions.eq("bazi", bazi));
		return gheymateBaziDao.retrieveAllByCriteria(criteria);
	}

	public GheymateBaziEntity retrieveByMogheiyat(MogheiyyatEntity mogheiyyat,
			BaziEntity bazi) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(GheymateBaziEntity.class);
		criteria.add(Restrictions.eq("bazi", bazi));
		criteria.add(Restrictions.eq("mogheiyyat", mogheiyyat));
		List<GheymateBaziEntity> list = gheymateBaziDao
				.retrieveAllByCriteria(criteria);
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	public GheymateBaziEntity retrieveByMogheiyat(MogheiyyatEntity mogheiyyat,
			Integer baziId) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(GheymateBaziEntity.class);
		criteria.createAlias("bazi", "bazi");
		criteria.add(Restrictions.eq("bazi.id", baziId));
		criteria.add(Restrictions.eq("mogheiyyat", mogheiyyat));
		List<GheymateBaziEntity> list = gheymateBaziDao
				.retrieveAllByCriteria(criteria);
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@Transactional
	public void update(GheymateBaziEntity entity) {
		gheymateBaziDao.update(entity);
	}

	@Transactional
	public void create(GheymateBaziEntity gheymateBaziEntity) {
		gheymateBaziDao.create(gheymateBaziEntity);
	}

	@Transactional
	public void resetSandalieBaghiMandeForBazi(BaziEntity bazi) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(GheymateBaziEntity.class);
		criteria.add(Restrictions.eq("bazi", bazi));
		List<GheymateBaziEntity> list = gheymateBaziDao
				.retrieveAllByCriteria(criteria);
		for (GheymateBaziEntity entity : list) {
			entity.setSandalieBaghimande(0);
			gheymateBaziDao.update(entity);
		}
	}

	@Transactional
	public void delete(GheymateBaziEntity gheymateBaziEntity) {
		gheymateBaziDao.delete(gheymateBaziEntity);
	}

	@Transactional
	public List<GheymateBaziEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return gheymateBaziDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public Integer executeQuery(String inputQuery) {
		Query query = gheymateBaziDao.getSessionFactory().getCurrentSession()
				.createSQLQuery(inputQuery);
		return query.executeUpdate();
	}
}
