package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.BasteForooshDao;
import ir.infosphere.sport.entity.BasteForooshEntity;
import ir.infosphere.sport.entity.PortalEntity;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BasteForooshBiz {
	@Autowired
	private BasteForooshDao basteForooshDao;
	
	@Transactional
	public List<BasteForooshEntity> getAllBasteForooshByPortal(PortalEntity portal, Date tarikhShoro, Date tarikhPayan){
		DetachedCriteria criteria = DetachedCriteria.forClass(BasteForooshEntity.class);
		if (portal != null)
			criteria.add(Restrictions.eq("portal", portal));
		if (tarikhShoro != null)
			criteria.add(Restrictions.ge("tarikhShoro", tarikhShoro));
		if (tarikhPayan != null)
			criteria.add(Restrictions.le("tarikhPayan", tarikhPayan));
		List<BasteForooshEntity> list = retrieveBasteForooshByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public List<BasteForooshEntity> getAllBasteHayeForoshFaalByPortalDarZamaneHal(PortalEntity portal){
		DetachedCriteria criteria = DetachedCriteria.forClass(BasteForooshEntity.class);
		criteria.add(Restrictions.eq("portal", portal));
		criteria.add(Restrictions.eq("gheyreFaal", Boolean.FALSE));
		Criterion rest1 = Restrictions.le("tarikhShoro", new Date());
		Criterion rest2 = Restrictions.ge("tarikhPayan", new Date());
		criteria.add(Restrictions.and(rest1, rest2));
		List<BasteForooshEntity> list = retrieveBasteForooshByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public List<BasteForooshEntity> retrieveBasteForooshByCriteria(DetachedCriteria criteria) {
		List<BasteForooshEntity> list = basteForooshDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public BasteForooshEntity retrieveById(Integer id) {
		return basteForooshDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(BasteForooshEntity basteforoosh) {
		basteForooshDao.create(basteforoosh);
	}
	
	@Transactional
	public void update(BasteForooshEntity basteforoosh) {
		basteForooshDao.update(basteforoosh);
	}
	
	@Transactional
	public void delete(Integer id) {
		basteForooshDao.delete(basteForooshDao.retrieveByID(id));
	}
}
