package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.LinkDao;
import ir.infosphere.sport.entity.LinkEntity;
import ir.infosphere.sport.util.PermissionUtil;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LinkBiz {
	@Autowired
	private LinkDao linkDao;

	@Transactional
	public List<LinkEntity> getAllLink() {
		List<LinkEntity> list = linkDao.retrieveAll();
		return list;
	}

	@Transactional
	public List<LinkEntity> retrieveByCriteria(
			DetachedCriteria criteria) {
		List<LinkEntity> list = linkDao
				.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public List<LinkEntity> getAll() {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(LinkEntity.class);
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		return linkDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public LinkEntity retrieveById(Integer id) {
		return linkDao.retrieveByID(id);
	}

	@Transactional
	public void update(LinkEntity entity) {
		linkDao.update(entity);
	}

	@Transactional
	public void delete(Integer id) {
		linkDao.delete(retrieveById(id));
	}

	@Transactional
	public void create(LinkEntity entity) {
		linkDao.create(entity);
	}
}
