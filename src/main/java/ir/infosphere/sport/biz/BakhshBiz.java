package ir.infosphere.sport.biz;

import java.util.List;

import ir.infosphere.sport.dao.BakhshDao;
import ir.infosphere.sport.entity.BakhshEntity;
import ir.infosphere.sport.entity.VarzeshgahEntity;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BakhshBiz {
	@Autowired
	private BakhshDao bakhshDao;
	
	@Transactional
	public List<BakhshEntity> getAllBakhshes(){
		List<BakhshEntity> list = bakhshDao.retrieveAll();
		return list;
	}
	
	@Transactional
	public List<BakhshEntity> retrieveBakhshes(DetachedCriteria criteria) {
		List<BakhshEntity> list = bakhshDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public BakhshEntity retrieveBakhshById(Integer id) {
		return bakhshDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(BakhshEntity bakhsh) {
		bakhshDao.create(bakhsh);
	}
	
	@Transactional
	public void update(BakhshEntity bakhsh) {
		bakhshDao.update(bakhsh);
	}
	
	@Transactional
	public Integer checkName(VarzeshgahEntity varzeshgah, String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(BakhshEntity.class);
		criteria.add(Restrictions.eq("varzeshgah", varzeshgah));
		criteria.add(Restrictions.like("nameBakhsh", name));
		List<BakhshEntity> list = retrieveBakhshes(criteria);
		if(list.size() == 0)
			return 0;
		else
			return list.get(0).getId();
	}
	
	@Transactional
	public void deleteBakhsh(BakhshEntity bakhsh) {
		bakhshDao.delete(bakhsh);
	}
	
	@Transactional
	public void deleteBakhsh(Integer id) {
		bakhshDao.delete(bakhshDao.retrieveByID(id));
	}
	
	@Transactional
	public List<BakhshEntity> getAllBakhshByVarzaeshgah(VarzeshgahEntity varzeshgah) {
		if (varzeshgah == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(BakhshEntity.class);
		criteria.add(Restrictions.eq("varzeshgah", varzeshgah));
		criteria.addOrder(Order.asc("nameBakhsh"));
		List<BakhshEntity> list = bakhshDao.retrieveAllByCriteria(criteria);
		return list;
	}
}
