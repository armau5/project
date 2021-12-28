package ir.infosphere.sport.biz;

import java.util.List;

import ir.infosphere.sport.dao.SlidDao;
import ir.infosphere.sport.entity.SlidEntity;
import ir.infosphere.sport.util.PermissionUtil;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SlidBiz {

	@Autowired
	private SlidDao slidDao;

	@Transactional
	public List<SlidEntity> getAllSlid() {
		List<SlidEntity> list = slidDao.retrieveAll();
		return list;
	}

	@Transactional
	public List<SlidEntity> getAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(SlidEntity.class);
		criteria.add(Restrictions.eq("portal",
				PermissionUtil.getCurrentPortal()));
		return slidDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public void create(SlidEntity slid) {
		slidDao.create(slid);

	}
	
	@Transactional
	public SlidEntity retrieveById(Integer id) {
		return slidDao.retrieveByID(id);
	}
	
	@Transactional
	public void update(SlidEntity entity) {
		slidDao.update(entity);
	}
	
	@Transactional
	public void delete(Integer id) {
		slidDao.delete(retrieveById(id));
	}
	

}
