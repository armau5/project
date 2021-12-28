package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.TakhfifeServiceDao;
import ir.infosphere.sport.entity.SiasatTakhfifEntity;
import ir.infosphere.sport.entity.TakhfifeServiceEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TakhfifeServiceBiz {
	@Autowired
	private TakhfifeServiceDao takhfifeServiceDao;
	
	@Transactional
	public List<TakhfifeServiceEntity> retrieveByCriteria(DetachedCriteria criteria) {
		List<TakhfifeServiceEntity> list = takhfifeServiceDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public List<TakhfifeServiceEntity> getAllBySiasatTakhfif (SiasatTakhfifEntity siasatTakhfif) {
		DetachedCriteria criteria = DetachedCriteria.forClass(TakhfifeServiceEntity.class);
		criteria.add(Restrictions.eq("siasatTakhfif", siasatTakhfif));
		List<TakhfifeServiceEntity> list = takhfifeServiceDao.retrieveAllByCriteria(criteria);
		return (list.size() > 0) ? list : null;
	}
	
	@Transactional
	public TakhfifeServiceEntity retrieveById(Integer id) {
		return takhfifeServiceDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(TakhfifeServiceEntity takhfifService) {
		takhfifeServiceDao.create(takhfifService);
	}
	
	@Transactional
	public void update(TakhfifeServiceEntity takhfifService) {
		takhfifeServiceDao.update(takhfifService);
	}
	
	@Transactional
	public void delete(Integer id) {
		takhfifeServiceDao.delete(takhfifeServiceDao.retrieveByID(id));
	}
	
	@Transactional
	public void delete(TakhfifeServiceEntity takhfifeService) {
		takhfifeServiceDao.delete(takhfifeService);
	}
}
