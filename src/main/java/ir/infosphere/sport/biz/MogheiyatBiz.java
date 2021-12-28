package ir.infosphere.sport.biz;

import java.util.List;

import ir.infosphere.sport.dao.MogheiyyatDao;
import ir.infosphere.sport.entity.BakhshEntity;
import ir.infosphere.sport.entity.MogheiyyatEntity;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MogheiyatBiz {
	@Autowired
	private MogheiyyatDao mogheiyyatDao;

	@Transactional
	public List<MogheiyyatEntity> getMogheiyyatFaalByBakhsh(BakhshEntity bakhsh) {
		DetachedCriteria criteria = DetachedCriteria.forClass(MogheiyyatEntity.class);
		criteria.add(Restrictions.eq("bakhsh", bakhsh));
		criteria.add(Restrictions.eq("gheyreFaal", false));
		criteria.addOrder(Order.asc("nameMogheiyyat"));
		return mogheiyyatDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<MogheiyyatEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return mogheiyyatDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public MogheiyyatEntity retrieveById(Integer id) {
		return mogheiyyatDao.retrieveByID(id);
	}

	@Transactional
	public void delete(Integer id) {
		mogheiyyatDao.delete(retrieveById(id));
	}

	@Transactional
	public void update(MogheiyyatEntity entity) {
		mogheiyyatDao.update(entity);
	}
	
	@Transactional
	public Integer checkName(BakhshEntity bakhsh, String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(MogheiyyatEntity.class);
		criteria.add(Restrictions.eq("bakhsh", bakhsh));
		criteria.add(Restrictions.like("nameMogheiyyat", name));
		List<MogheiyyatEntity> list = retrieveByCriteria(criteria);
		if(list.size() == 0)
			return 0;
		else
			return list.get(0).getId();
	}
	
	@Transactional
	public void create(MogheiyyatEntity entity) {
		mogheiyyatDao.create(entity);
	}
}
