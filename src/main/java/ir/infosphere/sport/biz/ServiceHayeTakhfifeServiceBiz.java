package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.ServiceHayeTakhfifeServiceDao;
import ir.infosphere.sport.entity.ServiceBakhshEntity;
import ir.infosphere.sport.entity.ServiceHayeTakhfifeServiceEntity;
import ir.infosphere.sport.entity.TakhfifeServiceEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ServiceHayeTakhfifeServiceBiz {
	@Autowired
	private ServiceHayeTakhfifeServiceDao serviceHayeTakhfifeServiceDao;
	
	@Transactional
	public List<ServiceHayeTakhfifeServiceEntity> retrieveByCriteria(DetachedCriteria criteria) {
		List<ServiceHayeTakhfifeServiceEntity> list = serviceHayeTakhfifeServiceDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public boolean isRepeated (TakhfifeServiceEntity takhfifeService, ServiceBakhshEntity service) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ServiceHayeTakhfifeServiceEntity.class);
		criteria.add(Restrictions.eq("takhfifeService", takhfifeService));
		criteria.add(Restrictions.eq("service", service));
		List<ServiceHayeTakhfifeServiceEntity> list = serviceHayeTakhfifeServiceDao.retrieveAllByCriteria(criteria, 0, 1);
		return (list.size() > 0) ? true : false;
	}
	
	@Transactional
	public List<ServiceHayeTakhfifeServiceEntity> getAllByTakhfifeService (TakhfifeServiceEntity takhfifeService) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ServiceHayeTakhfifeServiceEntity.class);
		criteria.add(Restrictions.eq("takhfifeService", takhfifeService));
		List<ServiceHayeTakhfifeServiceEntity> list = serviceHayeTakhfifeServiceDao.retrieveAllByCriteria(criteria);
		return (list.size() > 0) ? list : null;
	}
	
	@Transactional
	public ServiceHayeTakhfifeServiceEntity retrieveById(Integer id) {
		return serviceHayeTakhfifeServiceDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(ServiceHayeTakhfifeServiceEntity entity) {
		serviceHayeTakhfifeServiceDao.create(entity);
	}
	
	@Transactional
	public void update(ServiceHayeTakhfifeServiceEntity entity) {
		serviceHayeTakhfifeServiceDao.update(entity);
	}
	
	@Transactional
	public void delete(Integer id) {
		serviceHayeTakhfifeServiceDao.delete(serviceHayeTakhfifeServiceDao.retrieveByID(id));
	}
	
	@Transactional
	public void delete(ServiceHayeTakhfifeServiceEntity takhfifeService) {
		serviceHayeTakhfifeServiceDao.delete(takhfifeService);
	}
}
