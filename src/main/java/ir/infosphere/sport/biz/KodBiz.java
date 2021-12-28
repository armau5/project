package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.GorooheKodDao;
import ir.infosphere.sport.dao.KodDao;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.GorooheKodEntity;
import ir.infosphere.sport.entity.KodEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class KodBiz {
	@Autowired
	private KodDao kodDao;
	@Autowired
	private GorooheKodDao gorooheKodDao;

	public void setGorooheKodDao(GorooheKodDao gorooheKodDao) {
		this.gorooheKodDao = gorooheKodDao;
	}

	public void setKodDao(KodDao kodDao) {
		this.kodDao = kodDao;
	}

	@Transactional
	public Short getKodValue(String goroohName, String kodName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(KodEntity.class);
		criteria.createAlias("gorooheKod", "gorooheKod");
		criteria.add(Restrictions.eq("gorooheKod.name", goroohName));
		criteria.add(Restrictions.eq("meghdar", kodName));
		List<KodEntity> kods = kodDao.retrieveAllByCriteria(criteria);
		if (kods.size() == 0)
			return null;
		return kods.get(0).getId();
	}

	@Transactional
	public KodEntity getKodEntity(String goroohName, String kodName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(KodEntity.class);
		criteria.createAlias("gorooheKod", "gorooheKod");
		criteria.add(Restrictions.eq("gorooheKod.name", goroohName));
		criteria.add(Restrictions.eq("meghdar", kodName));
		List<KodEntity> kods = kodDao.retrieveAllByCriteria(criteria);
		if (kods.size() == 0)
			return null;
		return kods.get(0);
	}

	@Transactional
	public List<KodEntity> getGorooheKodValues(String goroohName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(KodEntity.class);
		criteria.createAlias("gorooheKod", "gorooheKod");
		criteria.add(Restrictions.eq("gorooheKod.name", goroohName));
		return kodDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public KodEntity retrieveKodById(short id) {
		return kodDao.retrieveByID(id);
	}

	@Transactional
	public List<GorooheKodEntity> getAllGorooheKod() {
		List<GorooheKodEntity> list = gorooheKodDao.retrieveAll();
		return list;
	}

	@Transactional
	public List<KodEntity> getAllCodesByGorooheKod(String gorooheKod, Boolean active) {
		DetachedCriteria criteria = DetachedCriteria.forClass(KodEntity.class);
		criteria.createAlias("gorooheKod", "gorooheKod");
		if (!gorooheKod
				.equals(Constants.TamamiMavared)) {
			criteria.add(Restrictions.eq("gorooheKod.name", gorooheKod));
		}
		if (active != null) {
			if (active)
				criteria.add(Restrictions.eq("gheyreFaal", Boolean.FALSE));
			else
				criteria.add(Restrictions.eq("gheyreFaal", Boolean.TRUE));
		}
		criteria.addOrder(Order.asc("gorooheKod.name"));
		criteria.addOrder(Order.asc("meghdar"));
		return kodDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public void changeServiceActivation(short id) {
		KodEntity kod = retrieveKodById(id);
		kod.setGheyreFaal(!kod.getGheyreFaal());
		kodDao.update(kod);
	}

	@Transactional
	public void deleteCode(Short id) {
		kodDao.delete(kodDao.retrieveByID(id));
	}

	@Transactional
	public GorooheKodEntity getGorooheKodById(short id) {
		return gorooheKodDao.retrieveByID(id);
	}

	@Transactional
	public void createCode(String name, GorooheKodEntity gorooheKod) {
		KodEntity kod = new KodEntity();
		kod.setMeghdar(name);
		kod.setGorooheKod(gorooheKod);
		kod.setGheyreFaal(false);

		kodDao.create(kod);
	}

	@Transactional
	public void updateCode(Short id, String name, GorooheKodEntity gorooheKod) {
		KodEntity kod = retrieveKodById(id);
		kod.setGorooheKod(gorooheKod);
		kod.setMeghdar(name);
		kodDao.update(kod);
	}
}
