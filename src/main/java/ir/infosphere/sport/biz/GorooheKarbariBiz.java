package ir.infosphere.sport.biz;

import java.util.List;

import ir.infosphere.sport.dao.GorooheKarbariDao;
import ir.infosphere.sport.entity.GorooheKarbariEntity;
import ir.infosphere.sport.util.PermissionUtil;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GorooheKarbariBiz {
	@Autowired
	private GorooheKarbariDao gorooheKarbariDao;

	@Transactional
	public List<GorooheKarbariEntity> retrieveAll() {
		return gorooheKarbariDao.retrieveAll();
	}
	
	@Transactional
	public List<GorooheKarbariEntity> retrieveAllFaal() {
		return gorooheKarbariDao.retrieveAllByProperty("gheyreFaal", Boolean.FALSE);
	}
	
	@Transactional
	public List<GorooheKarbariEntity> retrieveAllFaalByPortal() {
		DetachedCriteria criteria = DetachedCriteria.forClass(GorooheKarbariEntity.class);
		criteria.add(Restrictions.eq("gheyreFaal", Boolean.FALSE));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		return gorooheKarbariDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<GorooheKarbariEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return gorooheKarbariDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public GorooheKarbariEntity retrieveById(Short id) {
		return gorooheKarbariDao.retrieveByID(id);
	}

	@Transactional
	public void delete(Short id) {
		gorooheKarbariDao.delete(retrieveById(id));
	}

	@Transactional
	public void update(GorooheKarbariEntity entity) {
		gorooheKarbariDao.update(entity);
	}
	
	@Transactional
	public Short checkName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(GorooheKarbariEntity.class);
		criteria.add(Restrictions.like("nam", name));
		List<GorooheKarbariEntity> list = retrieveByCriteria(criteria);
		if(list.size() == 0)
			return 0;
		else
			return list.get(0).getId();
	}
	
	@Transactional
	public void create(GorooheKarbariEntity entity) {
		gorooheKarbariDao.create(entity);
	}
}
