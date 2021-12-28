package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.SharjPortalDao;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.PortalEntity;
import ir.infosphere.sport.entity.SharjPortalEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SharjPortalBiz {
	@Autowired
	private SharjPortalDao sharjPortalDao;
	
	
	@Transactional
	public boolean isActiveInPortal(PortalEntity portal, KodEntity dasteBandiPardakht) {
		if (portal == null && dasteBandiPardakht == null)
			return false;
		DetachedCriteria criteria = DetachedCriteria.forClass(SharjPortalEntity.class);
		criteria.add(Restrictions.eq("portal", portal));
		criteria.add(Restrictions.eq("dasteBandiPardakht", dasteBandiPardakht));
		Long result = sharjPortalDao.getCountByCriteria(criteria);
		return (result == 0) ? false : true;
	}
	
	@Transactional
	public boolean hasAnyActiveInPortal(PortalEntity portal) {
		if (portal == null)
			return false;
		DetachedCriteria criteria = DetachedCriteria.forClass(SharjPortalEntity.class);
		criteria.add(Restrictions.eq("portal", portal));
		Long result = sharjPortalDao.getCountByCriteria(criteria);
		return (result == 0) ? false : true;
	}
	
	@Transactional
	public List<SharjPortalEntity> retreiveAllSharjPortal(){
		return sharjPortalDao.retrieveAll();
	}

	@Transactional
	public List<SharjPortalEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return sharjPortalDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public SharjPortalEntity retrieveSharjPortalById(Integer id) {
		return sharjPortalDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(SharjPortalEntity sharjPortal) {
		sharjPortalDao.create(sharjPortal);
	}
	
	@Transactional
	public void update(SharjPortalEntity sharjPortal) {
		sharjPortalDao.update(sharjPortal);
	}
	
	@Transactional
	public void delete(Integer id) {
		sharjPortalDao.delete(sharjPortalDao.retrieveByID(id));
	}
}
