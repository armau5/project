package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.GorooheReshteyeVarzeshiDao;
import ir.infosphere.sport.entity.GorooheReshteyeVarzeshiEntity;
import ir.infosphere.sport.entity.ReshteyeVarzeshiEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GorooheReshteyeVarzeshiBiz {

	@Autowired
	private GorooheReshteyeVarzeshiDao gorooheReshteyeVarzeshiDao;

	@Transactional
	public List<GorooheReshteyeVarzeshiEntity> getAllGoroohByReshte(ReshteyeVarzeshiEntity reshteh) {
		if (reshteh == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(GorooheReshteyeVarzeshiEntity.class);
		criteria.add(Restrictions.eq("reshteyeVarzeshi", reshteh));
		return gorooheReshteyeVarzeshiDao.retrieveAllByCriteria(criteria);
	}
	
	
	
	@Transactional
	public GorooheReshteyeVarzeshiEntity getGorooheVarzeshiById(Integer id) {
		return gorooheReshteyeVarzeshiDao.retrieveByID(id);
	}


    @Transactional
	public List<GorooheReshteyeVarzeshiEntity> retrieveByCriteria(DetachedCriteria criteria) {    	
		return gorooheReshteyeVarzeshiDao.retrieveAllByCriteria(criteria);
	}


    @Transactional
	public void create(GorooheReshteyeVarzeshiEntity goroh) {
		gorooheReshteyeVarzeshiDao.create(goroh);
	}



	public void update(GorooheReshteyeVarzeshiEntity gorooheReshteyeVarzeshi,
			String name) {
		GorooheReshteyeVarzeshiEntity entity = getGorooheVarzeshiById(gorooheReshteyeVarzeshi.getId());
		entity.setNameGorooheReshteyeVarzeshi(name);
		gorooheReshteyeVarzeshiDao.update(entity);
	}



	public void deleteGorooh(Integer id) {
		gorooheReshteyeVarzeshiDao.deleteById(id);
	}



	public void update(GorooheReshteyeVarzeshiEntity entity) {
      gorooheReshteyeVarzeshiDao.update(entity);		
	}

}
