package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.NaghsheDao;
import ir.infosphere.sport.entity.NaghsheEntity;
import ir.infosphere.sport.entity.NahiyeEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class NaghsheBiz {
	@Autowired
	private NaghsheDao naghsheDao;
	
	@Transactional
	public List<NaghsheEntity> getAllNaghshe(){
		return naghsheDao.retrieveAll();
	}
	
	@Transactional
	public NaghsheEntity retrieveByNahiye(NahiyeEntity nahiye){
		DetachedCriteria criteria = DetachedCriteria.forClass(NaghsheEntity.class);
		criteria.add(Restrictions.eq("nahiye", nahiye));
		List<NaghsheEntity> list =  naghsheDao.retrieveAllByCriteria(criteria);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	
	@Transactional
	public List<NaghsheEntity> retrieveNaghsheByCriteria(DetachedCriteria criteria) {
		List<NaghsheEntity> list = naghsheDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public NaghsheEntity retrieveById(Integer id) {
		return naghsheDao.retrieveByID(id);
	}

}
