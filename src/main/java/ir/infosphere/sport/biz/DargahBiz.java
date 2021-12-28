package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.DargahDao;
import ir.infosphere.sport.entity.DargahEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.PortalEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DargahBiz {
	@Autowired
	private DargahDao dargahDao;

	@Transactional
	public List<DargahEntity> getAllDargah() {
		List<DargahEntity> list = dargahDao.retrieveAll();
		return list;
	}

	@Transactional
	public List<DargahEntity> getAllByPortalAndDasteBandi(PortalEntity portal,
			KodEntity dasteBandi) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(DargahEntity.class);
		criteria.add(Restrictions.eq("portal", portal));
		criteria.add(Restrictions.eq("dasteBandi", dasteBandi));
		return dargahDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<DargahEntity> retrieveByCriteria(DetachedCriteria criteria) {
		List<DargahEntity> list = dargahDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public void setDadeyeEzafi4(DargahEntity dargah, String data) {
		dargah.setDadeyeEzafi4(data);
		dargahDao.update(dargah);
	}

	@Transactional
	public DargahEntity retrieveById(Integer id) {
		return dargahDao.retrieveByID(id);
	}

	@Transactional
	public void update(DargahEntity entity) {
		dargahDao.update(entity);
	}

	@Transactional
	public void delete(Integer id) {
		dargahDao.delete(retrieveById(id));
	}

	@Transactional
	public void create(DargahEntity entity) {
		dargahDao.create(entity);
	}
}
