package ir.infosphere.sport.biz;

import java.util.List;

import ir.infosphere.sport.dao.MojavezDao;
import ir.infosphere.sport.entity.MojavezEntity;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MojavezBiz {
	@Autowired
	private MojavezDao mojavezDao;

	@Transactional
	public List<MojavezEntity> retrieveAll() {
		return mojavezDao.retrieveAll();
	}
	
	@Transactional
	public List<MojavezEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return mojavezDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public MojavezEntity retrieveById(Integer id) {
		return mojavezDao.retrieveByID(id);
	}

	@Transactional
	public void delete(Integer id) {
		mojavezDao.deleteById(id);
	}

	@Transactional
	public void update(MojavezEntity entity) {
		mojavezDao.update(entity);
	}
	
	@Transactional
	public Integer checkName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(MojavezEntity.class);
		criteria.add(Restrictions.like("nam", name));
		List<MojavezEntity> list = retrieveByCriteria(criteria);
		if(list.size() == 0)
			return 0;
		else
			return list.get(0).getId();
	}
	
	@Transactional
	public void create(MojavezEntity entity) {
		mojavezDao.create(entity);
	}
}
