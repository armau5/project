package ir.infosphere.sport.biz;

import java.util.Date;
import java.util.List;

import ir.infosphere.sport.dao.TaghvimeTatilateBakhshDao;
import ir.infosphere.sport.entity.BakhshEntity;
import ir.infosphere.sport.entity.TaghvimeTatilateBakhshEntity;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TaghvimeTatilateBakhshBiz {
	@Autowired
	private TaghvimeTatilateBakhshDao taghvimeTatilateBakhshDao;
	
	@Transactional
	public TaghvimeTatilateBakhshEntity retrieveById (Integer id) {
		return taghvimeTatilateBakhshDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(TaghvimeTatilateBakhshEntity entity) {
		taghvimeTatilateBakhshDao.create(entity);
	}
	
	@Transactional
	public void delete(TaghvimeTatilateBakhshEntity entity) {
		taghvimeTatilateBakhshDao.delete(entity);
	}
	
	@Transactional
	public List<TaghvimeTatilateBakhshEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return taghvimeTatilateBakhshDao.retrieveAllByCriteria(criteria);		
	}
	
	@Transactional
	public boolean isTatil(Date date, BakhshEntity bakhsh) {
		DetachedCriteria criteria = DetachedCriteria.forClass(TaghvimeTatilateBakhshEntity.class);
		criteria.add(Restrictions.eq("tarikh", date));
		criteria.add(Restrictions.eq("shenaseyeBakhsh", bakhsh));
		if (retrieveByCriteria(criteria).size() != 0)
			return true;
		else
			return false;
	}
}
