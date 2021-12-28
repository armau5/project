package ir.infosphere.sport.biz;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ir.infosphere.sport.bean.SemateTimCountReportBean;
import ir.infosphere.sport.dao.OzveTimDao;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.OzveTimEntity;
import ir.infosphere.sport.entity.SiasatEntity;
import ir.infosphere.sport.entity.TimEntity;

@Component
public class OzveTimBiz {
	@Autowired
	private OzveTimDao ozveTimDao;

	@Transactional
	public List<OzveTimEntity> retrieveAll() {
		return ozveTimDao.retrieveAll();
	}

	@Transactional
	public List<OzveTimEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return ozveTimDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public Long retrieveCountByCriteria(DetachedCriteria criteria) {
		return ozveTimDao.getCountByCriteria(criteria);
	}

	@Transactional
	public OzveTimEntity retrieveById(Integer id) {
		return ozveTimDao.retrieveByID(id);
	}

	@Transactional
	public void delete(Integer id) {
		ozveTimDao.delete(retrieveById(id));
	}

	@Transactional
	public void delete(OzveTimEntity ozveTim) {
		ozveTimDao.delete(ozveTim);
	}

	@Transactional
	public void update(OzveTimEntity entity) {
		ozveTimDao.update(entity);
	}

	@Transactional
	public void create(OzveTimEntity entity) {
		ozveTimDao.create(entity);
	}

	@Transactional
	public List<OzveTimEntity> getAllTimByOzv(OzvEntity ozv) {
		if (ozv == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(OzveTimEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		criteria.addOrder(Order.desc("shorooeOzviat"));
		return ozveTimDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<OzveTimEntity> getAllOzvByTim(TimEntity tim) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OzveTimEntity.class);
		criteria.add(Restrictions.eq("tim", tim));
		return ozveTimDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<OzveTimEntity> getAllOzveTimBySiasat(SiasatEntity siasat) {
		if (siasat == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(OzveTimEntity.class);
		criteria.createAlias("ozv", "ozv");
		criteria.createAlias("tim", "tim");
		criteria.add(Restrictions.eq("ozv.jensiat", siasat.getJensiat()));
		criteria.add(Restrictions.eq("tim.gorooheReshteyeVarzeshi", siasat.getGorooheReshteVarzeshi()));
		criteria.add(Restrictions.eq("tim.satheTim", siasat.getSatheTim()));
		criteria.add(Restrictions.eq("semat", siasat.getSemateOzv()));

		Criterion rest1 = Restrictions.isNotNull("payaneOzviat");
		Criterion rest2 = Restrictions.and(Restrictions.le("shorooeOzviat", siasat.getTaTarikh()),
				Restrictions.ge("payaneOzviat", siasat.getAzTarikh()));
		Criterion rest3 = Restrictions.and(rest1, rest2);

		Criterion rest4 = Restrictions.isNull("payaneOzviat");
		Criterion rest5 = Restrictions.and(Restrictions.le("shorooeOzviat", siasat.getTaTarikh()),
				Restrictions.ge("shorooeOzviat", siasat.getAzTarikh()));
		Criterion rest6 = Restrictions.and(rest4, rest5);

		criteria.add(Restrictions.or(rest3, rest6));

		// criteria.setProjection(Projections.distinct(Projections.property("ozv")));

		return ozveTimDao.retrieveAllByCriteria(criteria);
	}

	/*
	 * @Transactional public List<OzveTimEntity>
	 * getAllByOzveTimAndSemat(OzvEntity ozveTim, KodEntity semat, SiasatEntity
	 * siasatItem) { if (ozveTim == null) return null; DetachedCriteria criteria
	 * = DetachedCriteria.forClass(OzveTimEntity.class);
	 * criteria.createAlias("ozv", "ozvTim"); criteria.createAlias("tim",
	 * "tim"); criteria.add(Restrictions.eq("ozv", ozveTim));
	 * criteria.add(Restrictions.eq("tim.gorooheReshteyeVarzeshi",
	 * siasatItem.getGorooheReshteVarzeshi()));
	 * criteria.add(Restrictions.eq("tim.satheTim", siasatItem.getSatheTim()));
	 * criteria.add(Restrictions.eq("semat", semat));
	 * criteria.add(Restrictions.eq("ozvTim.jensiat", siasatItem.getJensiat()));
	 * return ozveTimDao.retrieveAllByCriteria(criteria); }
	 */

	@Transactional
	public Integer getCountByOzvAndSematForGorooheKarbari(OzvEntity ozv, KodEntity semat) {
		if (ozv == null || semat == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(OzveTimEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		criteria.add(Restrictions.eq("semat", semat));
		criteria.add(Restrictions.isNull("payaneOzviat"));
		return ozveTimDao.getCountByCriteria(criteria).intValue();
	}

	@Transactional
	public OzveTimEntity getAllByOzveTimAndSemat(OzvEntity ozveTim, SiasatEntity siasatItem) {
		if (ozveTim == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(OzveTimEntity.class);
		criteria.createAlias("ozv", "ozvTim");
		criteria.createAlias("tim", "tim");
		criteria.add(Restrictions.eq("ozv", ozveTim));
		criteria.add(Restrictions.eq("tim.gorooheReshteyeVarzeshi", siasatItem.getGorooheReshteVarzeshi()));
		criteria.add(Restrictions.eq("tim.satheTim", siasatItem.getSatheTim()));
		criteria.add(Restrictions.eq("semat", siasatItem.getSemateOzv()));
		criteria.add(Restrictions.eq("ozvTim.jensiat", siasatItem.getJensiat()));

		Criterion rest1 = Restrictions.isNotNull("payaneOzviat");
		Criterion rest2 = Restrictions.and(Restrictions.le("shorooeOzviat", siasatItem.getTaTarikh()),
				Restrictions.ge("payaneOzviat", siasatItem.getAzTarikh()));
		Criterion rest3 = Restrictions.and(rest1, rest2);

		Criterion rest4 = Restrictions.isNull("payaneOzviat");
		Criterion rest5 = Restrictions.and(Restrictions.le("shorooeOzviat", siasatItem.getTaTarikh()),
				Restrictions.ge("shorooeOzviat", siasatItem.getAzTarikh()));
		Criterion rest6 = Restrictions.and(rest4, rest5);

		criteria.add(Restrictions.or(rest3, rest6));

		List<OzveTimEntity> result = ozveTimDao.retrieveAllByCriteria(criteria, 0, 1);
		return (result.size() != 0) ? result.get(0) : null;
	}

	@Transactional
	public Boolean isOzveTimDarTarikhe(OzveTimEntity ozveTim, boolean update) {
		if (ozveTim == null)
			return false;
		DetachedCriteria criteria = DetachedCriteria.forClass(OzveTimEntity.class);
		criteria.add(Restrictions.eq("ozv", ozveTim.getOzv()));

		criteria.add(Restrictions.eq("semat", ozveTim.getSemat()));

		Criterion rest0 = Restrictions.isNull("payaneOzviat");

		Criterion rest1 = Restrictions.and(Restrictions.ge("payaneOzviat", ozveTim.getShorooeOzviat()),
				Restrictions.le("shorooeOzviat", ozveTim.getShorooeOzviat()));

		Criterion rest2 = Restrictions.isNotNull("payaneOzviat");
		Criterion rest3 = Restrictions.and(rest1, rest2);

		criteria.add(Restrictions.or(rest0, rest3));

		if (update) {
			if (ozveTimDao.getCountByCriteria(criteria) > 1)
				return true;
		} else {
			if (ozveTimDao.getCountByCriteria(criteria) > 0)
				return true;
		}
		return false;
	}

	@Transactional
	public TimEntity getTimHazerByOzv(OzvEntity ozv) {
		if (ozv == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(OzveTimEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		criteria.add(Restrictions.isNull("payaneOzviat"));
		criteria.addOrder(Order.desc("shorooeOzviat"));
		List<OzveTimEntity> list = ozveTimDao.retrieveAllByCriteria(criteria);
		if (list == null)
			return null;
		if (list.size() == 0)
			return null;
		return list.get(0).getTim();
	}

	public List<SemateTimCountReportBean> getSematCountReport(Integer shenaseyeOstan) {
		return ozveTimDao.getSematCountReport(shenaseyeOstan);
	}
}
