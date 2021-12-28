package ir.infosphere.sport.biz;

import java.util.List;

import ir.infosphere.sport.dao.VarzeshgahDao;
import ir.infosphere.sport.entity.NahiyeEntity;
import ir.infosphere.sport.entity.VarzeshgahEntity;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VarzeshgahBiz {
	@Autowired
	private VarzeshgahDao varzeshgahDao;
	
	@Transactional
	public List<VarzeshgahEntity> getAllVarzeshgah(){
		List<VarzeshgahEntity> list = varzeshgahDao.retrieveAll();
		return list;
	}
	
	@Transactional
	public List<VarzeshgahEntity> getAllVarzeshgahByNahiye(NahiyeEntity nahiye) {
		if (nahiye == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(VarzeshgahEntity.class);
		criteria.add(Restrictions.eq("nahiye", nahiye));
		criteria.addOrder(Order.asc("name"));
		return varzeshgahDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public VarzeshgahEntity getVarzeshgahByName(NahiyeEntity nahiye, String name) {
		if (name == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(VarzeshgahEntity.class);
		criteria.add(Restrictions.eq("nahiye", nahiye));
		criteria.add(Restrictions.like("name", name));
		List<VarzeshgahEntity> list = varzeshgahDao.retrieveAllByCriteria(criteria);
		return list.get(0);
	}
	
	@Transactional
	public List<VarzeshgahEntity> retrieveByCriteria(DetachedCriteria criteria){
		List<VarzeshgahEntity> list = varzeshgahDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public VarzeshgahEntity retrieveById(Short id) {
		return varzeshgahDao.retrieveByID(id);
	}
	
	@Transactional
	public void update(VarzeshgahEntity entity) {
		varzeshgahDao.update(entity);
	}
	
	@Transactional
	public void delete(Short id) {
		varzeshgahDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void create(VarzeshgahEntity entity) {
		varzeshgahDao.create(entity);
	}
}
