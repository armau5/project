package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.TakhfifeBelitDao;
import ir.infosphere.sport.entity.SiasatTakhfifEntity;
import ir.infosphere.sport.entity.TakhfifeBelitEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TakhfifeBelitBiz {
	@Autowired
	private TakhfifeBelitDao takhfifeBelitDao;
	
	@Transactional
	public List<TakhfifeBelitEntity> retrieveByCriteria(DetachedCriteria criteria) {
		List<TakhfifeBelitEntity> list = takhfifeBelitDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public List<TakhfifeBelitEntity> getAllBySiasatTakhfif (SiasatTakhfifEntity siasatTakhfif) {
		DetachedCriteria criteria = DetachedCriteria.forClass(TakhfifeBelitEntity.class);
		criteria.add(Restrictions.eq("siasatTakhfif", siasatTakhfif));
		List<TakhfifeBelitEntity> list = takhfifeBelitDao.retrieveAllByCriteria(criteria);
		return (list.size() > 0) ? list : null;
	}
	
	@Transactional
	public TakhfifeBelitEntity retrieveById(Integer id) {
		return takhfifeBelitDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(TakhfifeBelitEntity takhfifBelit) {
		takhfifeBelitDao.create(takhfifBelit);
	}
	
	@Transactional
	public void update(TakhfifeBelitEntity takhfifBelit) {
		takhfifeBelitDao.update(takhfifBelit);
	}
	
	@Transactional
	public void delete(Integer id) {
		takhfifeBelitDao.delete(takhfifeBelitDao.retrieveByID(id));
	}

	@Transactional
	public void delete(TakhfifeBelitEntity takhfifBelit) {
		takhfifeBelitDao.delete(takhfifBelit);
	}
}
