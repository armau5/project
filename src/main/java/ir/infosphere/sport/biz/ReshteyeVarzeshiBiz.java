package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.ReshteyeVarzeshiDao;
import ir.infosphere.sport.entity.ReshteyeVarzeshiEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ReshteyeVarzeshiBiz {

	@Autowired
	private ReshteyeVarzeshiDao reshteyeVarzeshiDao;

	@Transactional
	public List<ReshteyeVarzeshiEntity> getAllReshteh() {
		DetachedCriteria criteria = DetachedCriteria.forClass(ReshteyeVarzeshiEntity.class);
		criteria.addOrder(Order.asc("nameReshteyeVarzeshi"));
		return reshteyeVarzeshiDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public ReshteyeVarzeshiEntity getReshteh(String reshteh) {
		if (reshteh == null || reshteh.equals(""))
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(ReshteyeVarzeshiEntity.class);
		criteria.add(Restrictions.eq("nameReshteyeVarzeshi", reshteh));
		List<ReshteyeVarzeshiEntity> list = reshteyeVarzeshiDao.retrieveAllByCriteria(criteria);
		if(list.size() == 0){
			return null;
		}
		return list.get(0);
	}
	
	@Transactional
	public ReshteyeVarzeshiEntity getReshtehById(Short id) {
		return reshteyeVarzeshiDao.retrieveByID(id);
	}

	@Transactional
	public  List<ReshteyeVarzeshiEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return reshteyeVarzeshiDao.retrieveAllByCriteria(criteria);
		
	}
	
	@Transactional
	public void createReshte(String name){
		ReshteyeVarzeshiEntity entity = new ReshteyeVarzeshiEntity();
		entity.setNameReshteyeVarzeshi(name);
		reshteyeVarzeshiDao.create(entity);
	}
	
	@Transactional
	public void update(Short id, String name){
		ReshteyeVarzeshiEntity entity = getReshtehById(id);
		entity.setNameReshteyeVarzeshi(name);
		reshteyeVarzeshiDao.update(entity);
	}
	
	@Transactional
	public void update(ReshteyeVarzeshiEntity entity){
		reshteyeVarzeshiDao.update(entity);
	}
	
	@Transactional
	public void deleteReshte(Short id){
		reshteyeVarzeshiDao.delete(reshteyeVarzeshiDao.retrieveByID(id));
	}

}
