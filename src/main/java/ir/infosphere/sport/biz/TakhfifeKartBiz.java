package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.TakhfifeKartDao;
import ir.infosphere.sport.entity.SiasatTakhfifEntity;
import ir.infosphere.sport.entity.TakhfifeKartEntity;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TakhfifeKartBiz {
	@Autowired
	private TakhfifeKartDao takhfifeKartDao;
	
	@Transactional
	public List<TakhfifeKartEntity> retrieveByCriteria(DetachedCriteria criteria) {
		List<TakhfifeKartEntity> list = takhfifeKartDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	
	@Transactional
	public List<TakhfifeKartEntity> getAllBySiasatTakhfif (SiasatTakhfifEntity siasatTakhfif) {
		DetachedCriteria criteria = DetachedCriteria.forClass(TakhfifeKartEntity.class);
		criteria.add(Restrictions.eq("siasatTakhfif", siasatTakhfif));
		criteria.add(Restrictions.le("tarikhShoro", new Date()));
		criteria.add(Restrictions.ge("tarikhPayan", new Date()));
		List<TakhfifeKartEntity> list = takhfifeKartDao.retrieveAllByCriteria(criteria);
		return (list.size() > 0) ? list : null;
	}
	
	@Transactional
	public TakhfifeKartEntity retrieveById(Integer id) {
		return takhfifeKartDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(TakhfifeKartEntity takhfifKart) {
		takhfifeKartDao.create(takhfifKart);
	}
	
	@Transactional
	public void update(TakhfifeKartEntity takhfifKart) {
		takhfifeKartDao.update(takhfifKart);
	}
	
	@Transactional
	public void delete(Integer id) {
		takhfifeKartDao.delete(takhfifeKartDao.retrieveByID(id));
	}
	
	@Transactional
	public void delete(TakhfifeKartEntity takhfifeKart) {
		takhfifeKartDao.delete(takhfifeKart);
	}
}
