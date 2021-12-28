package ir.infosphere.sport.biz;

import java.util.List;

import ir.infosphere.sport.dao.RadifeBolookDao;
import ir.infosphere.sport.entity.BolookEntity;
import ir.infosphere.sport.entity.RadifeBolookEntity;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RadifeBolookBiz {
	@Autowired
	private RadifeBolookDao radifeBolookDao;

	@Transactional
	public List<RadifeBolookEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return radifeBolookDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public RadifeBolookEntity retrieveById(Integer id) {
		return radifeBolookDao.retrieveByID(id);
	}

	@Transactional
	public void delete(Integer id) {
		radifeBolookDao.delete(retrieveById(id));
	}

	@Transactional
	public void update(RadifeBolookEntity entity) {
		radifeBolookDao.update(entity);
	}
	
	@Transactional
	public Integer checkShomare(BolookEntity bolook, String shomare) {
		DetachedCriteria criteria = DetachedCriteria.forClass(RadifeBolookEntity.class);
		criteria.add(Restrictions.eq("bolook", bolook));
		criteria.add(Restrictions.like("shomareyeRadif", shomare));
		List<RadifeBolookEntity> list = retrieveByCriteria(criteria);
		if(list.size() == 0)
			return 0;
		else
			return list.get(0).getId();
	}
	
	@Transactional
	public void create(RadifeBolookEntity entity) {
		radifeBolookDao.create(entity);
	}

	@Transactional
	public List<RadifeBolookEntity> getRadifByBolook(BolookEntity bolook) {
		DetachedCriteria criteria = DetachedCriteria.forClass(RadifeBolookEntity.class);
		criteria.add(Restrictions.eq("bolook", bolook));
	    return retrieveByCriteria(criteria);
	}
}
