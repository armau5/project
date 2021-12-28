package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.PishkhanDao;
import ir.infosphere.sport.entity.PishkhanEntity;
import ir.infosphere.sport.entity.PortalEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PishkhanBiz {
	@Autowired
	private PishkhanDao pishkhanDao;

	@Transactional
	public PishkhanEntity retrievePishkhanById(Integer id) {
		return pishkhanDao.retrieveByID(id);
	}

	@Transactional
	public List<PishkhanEntity> retrieveAllByPortal(PortalEntity currentPortal) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(PishkhanEntity.class);
		criteria.add(Restrictions.eq("portal", currentPortal));
		return pishkhanDao.retrieveAllByCriteria(criteria);
	}
}
