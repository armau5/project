package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.VoroodiDao;
import ir.infosphere.sport.entity.BakhshEntity;
import ir.infosphere.sport.entity.VoroodiEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VoroodiBiz {
	
	@Autowired
	VoroodiDao voroodiDao;
	
	@Transactional
	public List<VoroodiEntity> getAllVoroodi() {
		List<VoroodiEntity> list = voroodiDao.retrieveAll();
		return list;
	}
	
	@Transactional
	public List<VoroodiEntity> getAllVoroodiByBakhsh(BakhshEntity bakhsh){
		if (bakhsh == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(VoroodiEntity.class);
		criteria.add(Restrictions.eq("bakhsh", bakhsh));
		criteria.addOrder(Order.asc("shomareyeVoroodi"));
		List<VoroodiEntity> list = voroodiDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public VoroodiEntity retrieveById(Integer id) {
		return voroodiDao.retrieveByID(id);
	}

}
