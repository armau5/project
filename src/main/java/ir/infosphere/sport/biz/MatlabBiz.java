package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.MatlabDao;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.MadreseEntity;
import ir.infosphere.sport.entity.MatlabEntity;
import ir.infosphere.sport.entity.NahiyeEntity;
import ir.infosphere.sport.entity.NoeMatlabEntity;
import ir.infosphere.sport.util.PermissionUtil;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MatlabBiz {
	@Autowired
	private MatlabDao matlabDao;

	@Transactional
	public List<MatlabEntity> getAllMatlab() {
		List<MatlabEntity> list = matlabDao.retrieveAll();
		return list;
	}
	
	private DetachedCriteria getOrderCtiretia(DetachedCriteria criteria) {
		criteria.addOrder(Order.desc("olaviateNamayesh"));
		criteria.addOrder(Order.desc("zamaneAkharinTaghir"));
		return criteria;
	}

	@Transactional
	public List<MatlabEntity> getAllMatlabByMadrese(MadreseEntity madrese ) {
		if (madrese == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MatlabEntity.class);
		criteria.add(Restrictions.eq("madrese",madrese));
		criteria.add(Restrictions.eq("gheyreFaal",false));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		criteria = getOrderCtiretia(criteria);
		return matlabDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<MatlabEntity> getAllMatlabFaalByNoeMatlab(
			NoeMatlabEntity noeMatlab) {
		if (noeMatlab == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MatlabEntity.class);
		criteria.add(Restrictions.eq("noeMatlab", noeMatlab));
		criteria.add(Restrictions.eq("gheyreFaal",false));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		criteria = getOrderCtiretia(criteria);
		return matlabDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<MatlabEntity> getAllMatlabByNoeMatlab(
			NoeMatlabEntity noeMatlab) {
		if (noeMatlab == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MatlabEntity.class);
		criteria.add(Restrictions.eq("noeMatlab", noeMatlab));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		criteria = getOrderCtiretia(criteria);
		return matlabDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<MatlabEntity> getAllMatlabFaalByKodeMatlab(
			KodEntity kodeMatlab) {
		if (kodeMatlab == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(MatlabEntity.class);
		criteria.add(Restrictions.eq("kodeMatlab", kodeMatlab));
		criteria.add(Restrictions.eq("gheyreFaal",false));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		criteria = getOrderCtiretia(criteria);
		return matlabDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<MatlabEntity> getAllMatlabByKodeMatlab(
			KodEntity kodeMatlab) {
		if (kodeMatlab == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(MatlabEntity.class);
		criteria.add(Restrictions.eq("kodeMatlab", kodeMatlab));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		criteria = getOrderCtiretia(criteria);
		return matlabDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<MatlabEntity> getAllMatlabByKodeMatlabAndOstan(KodEntity kodeMatlab, NahiyeEntity ostan) {
		if (kodeMatlab == null || ostan == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(MatlabEntity.class);
		criteria.add(Restrictions.eq("kodeMatlab", kodeMatlab));
		criteria.add(Restrictions.eq("ostan", ostan));
		criteria.add(Restrictions.eq("gheyreFaal",false));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		criteria = getOrderCtiretia(criteria);
		return matlabDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<MatlabEntity> getAllMatlabByNahiye(NahiyeEntity ostan) {
		if (ostan == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(MatlabEntity.class);
		criteria.add(Restrictions.eq("ostan",ostan));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		criteria = getOrderCtiretia(criteria);
		return matlabDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<MatlabEntity> getLastMatlabByKodeMatlab(KodEntity kodeMatlab, Integer Count) {
		if (kodeMatlab == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(MatlabEntity.class);
		criteria.add(Restrictions.eq("kodeMatlab", kodeMatlab));
		criteria.add(Restrictions.isNull("ostan"));
		criteria.add(Restrictions.isNull("madrese"));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		criteria.add(Restrictions.eq("gheyreFaal",false));
		criteria = getOrderCtiretia(criteria);
		return matlabDao.retrieveAllByCriteria(criteria, 0, Count);
	}
	
	@Transactional
	public List<MatlabEntity> getAllMatlabByKodeMatlabAndNoeMatlab(KodEntity kodeMatlab, NoeMatlabEntity noeMatlab) {
		if (kodeMatlab == null || noeMatlab == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(MatlabEntity.class);
		criteria.add(Restrictions.eq("kodeMatlab", kodeMatlab));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		criteria.add(Restrictions.eq("gheyreFaal",false));
		criteria.add(Restrictions.eq("noeMatlab", noeMatlab));
		criteria = getOrderCtiretia(criteria);
		return matlabDao.retrieveAllByCriteria(criteria);
	}


	@Transactional
	public List<MatlabEntity> retrieveByCriteria(
			DetachedCriteria criteria) {
		List<MatlabEntity> list = matlabDao
				.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public MatlabEntity retrieveByKod(KodEntity kodeMatlab) {
		if (kodeMatlab == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(MatlabEntity.class);
		criteria.add(Restrictions.eq("kodeMatlab", kodeMatlab));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		List<MatlabEntity> matlab = matlabDao.retrieveAllByCriteria(criteria, 0, 1);
		if (matlab.size() == 0)
			return null;
		else
			return matlab.get(0);
	}
	
	@Transactional
	public MatlabEntity retrieveById(Integer id) {
		return matlabDao.retrieveByID(id);
	}

	@Transactional
	public void update(MatlabEntity entity) {
		matlabDao.update(entity);
	}

	@Transactional
	public void delete(Integer id) {
		matlabDao.delete(retrieveById(id));
	}

	@Transactional
	public void create(MatlabEntity entity) {
		matlabDao.create(entity);
	}
}
