package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.PardakhtBasteForoshDao;
import ir.infosphere.sport.entity.PardakhtBasteForoshEntity;
import ir.infosphere.sport.entity.PardakhtEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PardakhtBasteForoshBiz {
	@Autowired
	private PardakhtBasteForoshDao pardakhtBasteForoshDao;
	
	@Transactional
	public List<PardakhtBasteForoshEntity> retrievePardakhtBasteForosh(DetachedCriteria criteria) {
		List<PardakhtBasteForoshEntity> list = pardakhtBasteForoshDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public PardakhtBasteForoshEntity retrievePardakhtBasteForoshById(Integer id) {
		return pardakhtBasteForoshDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(PardakhtBasteForoshEntity pardakhtBasteForosh) {
		pardakhtBasteForoshDao.create(pardakhtBasteForosh);
	}
	
	@Transactional
	public void update(PardakhtBasteForoshEntity pardakhtBasteForosh) {
		pardakhtBasteForoshDao.update(pardakhtBasteForosh);
	}
	
	@Transactional
	public void delete(Integer id) {
		pardakhtBasteForoshDao.delete(pardakhtBasteForoshDao.retrieveByID(id));
	}
	
	@Transactional
	public List<PardakhtBasteForoshEntity> getForPardakht(PardakhtEntity pardakht) {
		DetachedCriteria criteria = DetachedCriteria.forClass(PardakhtBasteForoshEntity.class);
		criteria.add(Restrictions.eq("pardakht", pardakht));
		List<PardakhtBasteForoshEntity> list = pardakhtBasteForoshDao.retrieveAllByCriteria(criteria);
		return (list != null && list.size() > 0) ? list : null;  
	}
	
	
	public Boolean isPardakhtValid(PardakhtEntity pardakht) {
		if (getForPardakht(pardakht) != null)
			return true;
		return false;
	}
}
