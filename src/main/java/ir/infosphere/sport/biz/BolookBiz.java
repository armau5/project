package ir.infosphere.sport.biz;

import java.util.List;

import ir.infosphere.sport.dao.BolookDao;
import ir.infosphere.sport.entity.BakhshEntity;
import ir.infosphere.sport.entity.BolookEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.MogheiyyatEntity;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BolookBiz {
	@Autowired
	private BolookDao bolookDao;

	@Transactional
	public List<BolookEntity> getBolookByBakhsh(BakhshEntity bakhsh) {
		DetachedCriteria criteria = DetachedCriteria.forClass(BolookEntity.class);
		criteria.add(Restrictions.eq("bakhsh", bakhsh));
		criteria.addOrder(Order.asc("shomareyeBolook"));
		return bolookDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<BolookEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return bolookDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public BolookEntity retrieveById(Short id) {
		return bolookDao.retrieveByID(id);
	}

	@Transactional
	public void delete(Short id) {
		bolookDao.delete(retrieveById(id));
	}

	@Transactional
	public void update(BolookEntity entity) {
		bolookDao.update(entity);
	}

	@Transactional
	public Short checkShomare(BakhshEntity bakhsh, MogheiyyatEntity mogheiyyat, Short shomare) {
		DetachedCriteria criteria = DetachedCriteria.forClass(BolookEntity.class);
		criteria.add(Restrictions.eq("bakhsh", bakhsh));
		criteria.add(Restrictions.eq("mogheiyyat", mogheiyyat));
		criteria.add(Restrictions.like("shomareyeBolook", shomare));
		List<BolookEntity> list = retrieveByCriteria(criteria);
		if(list.size() == 0)
			return 0;
		else
			return list.get(0).getId();
	}

	@Transactional
	public void create(BolookEntity entity) {
		bolookDao.create(entity);
	}

	@Transactional
	public List<BolookEntity> getBolook(BakhshEntity bakhsh,
			MogheiyyatEntity mogheiyyat, KodEntity mogheiyyateJoghrafiyayi) {
		DetachedCriteria criteria = DetachedCriteria.forClass(BolookEntity.class);
		criteria.add(Restrictions.eq("bakhsh", bakhsh));
		if(mogheiyyat != null){
			criteria.add(Restrictions.eq("mogheiyyat", mogheiyyat));
		}
		if(mogheiyyateJoghrafiyayi != null && !mogheiyyateJoghrafiyayi.getMeghdar().equals("�� ���")){
			criteria.add(Restrictions.eq("mogheiyyateJoghrafiyayi", mogheiyyateJoghrafiyayi));
		}
		criteria.addOrder(Order.asc("shomareyeBolook"));
		return bolookDao.retrieveAllByCriteria(criteria);		
	}
}
