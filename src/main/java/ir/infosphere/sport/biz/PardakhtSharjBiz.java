package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.PardakhtSharjDao;
import ir.infosphere.sport.entity.PardakhtEntity;
import ir.infosphere.sport.entity.PardakhtSharjEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PardakhtSharjBiz {
	@Autowired
	private PardakhtSharjDao pardakhtSharjDao;

	@Transactional
	public PardakhtSharjEntity retrieveById(Long id) {
		return pardakhtSharjDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(PardakhtSharjEntity pardakhtSharj) {
		pardakhtSharjDao.create(pardakhtSharj);
	}
	
	@Transactional
	public void update(PardakhtSharjEntity pardakhtSharj) {
		pardakhtSharjDao.update(pardakhtSharj);
	}
	
	@Transactional
	public List<PardakhtSharjEntity> getForPardakht(PardakhtEntity pardakht) {
		DetachedCriteria criteria = DetachedCriteria.forClass(PardakhtSharjEntity.class);
		criteria.add(Restrictions.eq("pardakht", pardakht));
		List<PardakhtSharjEntity> list = pardakhtSharjDao.retrieveAllByCriteria(criteria);
		return (list != null && list.size() > 0) ? list : null;  
	}
	
	
	public Boolean isPardakhtValid(PardakhtEntity pardakht) {
		if (getForPardakht(pardakht) != null)
			return true;
		return false;
	}
	
	@Transactional
	public void delete(Long id) {
		pardakhtSharjDao.delete(pardakhtSharjDao.retrieveByID(id));
	}
}
