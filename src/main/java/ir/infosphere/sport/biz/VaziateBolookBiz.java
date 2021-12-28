package ir.infosphere.sport.biz;

import ir.infosphere.sport.bean.BolookBean;
import ir.infosphere.sport.bean.MogheiyyatBean;
import ir.infosphere.sport.dao.VaziateBolookDao;
import ir.infosphere.sport.entity.BaziEntity;
import ir.infosphere.sport.entity.BolookEntity;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.MogheiyyatEntity;
import ir.infosphere.sport.entity.VaziateBolookEntity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VaziateBolookBiz {
	@Autowired
	private VaziateBolookDao vaziateBolookDao;

	@Autowired
	private BaziBiz baziBiz;

	@Autowired
	private MogheiyatBiz mogheiyatBiz;

	@Autowired
	private KodBiz kodBiz;

	@Transactional
	public void create(VaziateBolookEntity entity) {
		vaziateBolookDao.create(entity);
	}

	@Transactional
	public void update(VaziateBolookEntity entity) {
		vaziateBolookDao.update(entity);
	}

	@Transactional
	public List<VaziateBolookEntity> retrieveAll() {
		return vaziateBolookDao.retrieveAll();
	}

	@Transactional
	public List<VaziateBolookEntity> retrieveByCriteria(
			DetachedCriteria criteria) {
		return vaziateBolookDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public void delete(VaziateBolookEntity entity) {
		vaziateBolookDao.delete(entity);
	}

	@Transactional
	public VaziateBolookEntity getVaziateBolook(BaziEntity bazi,
			BolookEntity bolook) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(VaziateBolookEntity.class);
		criteria.add(Restrictions.eq("bazi", bazi));
		criteria.add(Restrictions.eq("bolook", bolook));
		List<VaziateBolookEntity> result = vaziateBolookDao
				.retrieveAllByCriteria(criteria);
		if (result.size() == 0)
			return null;
		return result.get(0);
	}

	@Transactional
	public List<VaziateBolookEntity> getVaziateBolookList(BaziEntity bazi) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(VaziateBolookEntity.class);
		criteria.add(Restrictions.eq("bazi", bazi));
		return vaziateBolookDao.retrieveAllByCriteria(criteria);
	}

	// @Transactional
	// public List<BolookBean> getBolooks(Integer baziId, boolean isMizban,
	// Map<Integer, GheymatBean> gheymateBazi) {
	// BaziEntity bazi = baziBiz.getBaziById(baziId);
	//
	// KodEntity kod;
	// if (isMizban)
	// kod = kodBiz.getKodEntity(Constants.VaziateBolook,
	// Constants.BolookeMizban);
	// else
	// kod = kodBiz.getKodEntity(Constants.VaziateBolook,
	// Constants.BolookeMihman);
	// DetachedCriteria criteria = DetachedCriteria
	// .forClass(VaziateBolookEntity.class);
	// criteria.add(Restrictions.eq("bazi", bazi));
	// criteria.add(Restrictions.eq("vaziateBolook", kod));
	// List<VaziateBolookEntity> list = vaziateBolookDao
	// .retrieveAllByCriteria(criteria);
	// List<BolookBean> beans = new ArrayList<BolookBean>();
	// for (VaziateBolookEntity entity : list) {
	// beans.add(new BolookBean(entity, gheymateBazi));
	// }
	// return beans;
	// }

	@Transactional
	public List<BolookBean> getBolooks(Integer baziId, Boolean isMizban,
			MogheiyyatBean mogheiyatBean) {
		BaziEntity bazi = baziBiz.getBaziById(baziId);
		MogheiyyatEntity mogheiyat = mogheiyatBiz.retrieveById(mogheiyatBean
				.getId());

		KodEntity kod;
		if (isMizban)
			kod = kodBiz.getKodEntity(Constants.VaziateBolook,
					Constants.BolookeMizban);
		else
			kod = kodBiz.getKodEntity(Constants.VaziateBolook,
					Constants.BolookeMihman);

		DetachedCriteria criteria = DetachedCriteria
				.forClass(VaziateBolookEntity.class);
		criteria.add(Restrictions.eq("bazi", bazi));
		criteria.add(Restrictions.eq("vaziateBolook", kod));
		criteria.createAlias("bolook", "bolook");
		criteria.add(Restrictions.eq("bolook.mogheiyyat", mogheiyat));
		List<VaziateBolookEntity> list = vaziateBolookDao
				.retrieveAllByCriteria(criteria);
		List<BolookBean> beans = new ArrayList<BolookBean>();
		for (VaziateBolookEntity entity : list) {
			beans.add(new BolookBean(entity, mogheiyatBean));
		}
		return beans;
	}

	@Transactional
	public List<BolookBean> getBolookHayeGhabeleForoosh(Integer baziId,
			MogheiyyatBean mogheiyatBean) {
		BaziEntity bazi = baziBiz.getBaziById(baziId);
		MogheiyyatEntity mogheiyat = mogheiyatBiz.retrieveById(mogheiyatBean
				.getId());

		KodEntity kod = kodBiz.getKodEntity(Constants.VaziateBolook,
				Constants.BolookeGheyreGhabeleForoosh);

		DetachedCriteria criteria = DetachedCriteria
				.forClass(VaziateBolookEntity.class);
		criteria.add(Restrictions.eq("bazi", bazi));
		criteria.add(Restrictions.ne("vaziateBolook", kod));
		criteria.createAlias("bolook", "bolook");
		criteria.add(Restrictions.eq("bolook.mogheiyyat", mogheiyat));
		List<VaziateBolookEntity> list = vaziateBolookDao
				.retrieveAllByCriteria(criteria);
		List<BolookBean> beans = new ArrayList<BolookBean>();
		for (VaziateBolookEntity entity : list) {
			beans.add(new BolookBean(entity, mogheiyatBean));
		}
		return beans;
	}

	@Transactional
	public VaziateBolookEntity getById(int id) {
		return vaziateBolookDao.retrieveByID(id);
	}

	@Transactional
	public Integer executeQuery(String inputQuery) {
		Query query = vaziateBolookDao.getSessionFactory().getCurrentSession()
				.createSQLQuery(inputQuery);
		return query.executeUpdate();
	}
}
