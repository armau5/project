package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.BazyabiRamzDao;
import ir.infosphere.sport.entity.BazyabiRamzEntity;
import ir.infosphere.sport.util.PermissionUtil;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BazyabiRamzBiz {
	@Autowired
	private BazyabiRamzDao bazyabiRamzDao;

	@Transactional
	public List<BazyabiRamzEntity> retrieveAll() {
		return bazyabiRamzDao.retrieveAll();
	}

	@Transactional
	public List<BazyabiRamzEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return bazyabiRamzDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public BazyabiRamzEntity retrieveByUID(String UID) {
		DetachedCriteria criteria = DetachedCriteria.forClass(BazyabiRamzEntity.class);
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		criteria.add(Restrictions.eq("kodeErsali", UID));
		criteria.add(Restrictions.eq("gheyreFaal", false));
		List<BazyabiRamzEntity> list = bazyabiRamzDao.retrieveAllByCriteria(criteria, 0, 1);
		if (list.size() == 1) 
			return list.get(0);
		else
			return null;
	}

	@Transactional
	public BazyabiRamzEntity retrieveById(Integer id) {
		return bazyabiRamzDao.retrieveByID(id);
	}

	@Transactional
	public void delete(Integer id) {
		bazyabiRamzDao.delete(retrieveById(id));
	}

	@Transactional
	public void update(BazyabiRamzEntity entity) {
		bazyabiRamzDao.update(entity);
	}

	@Transactional
	public void create(BazyabiRamzEntity entity) {
		bazyabiRamzDao.create(entity);
	}
}
