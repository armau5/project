package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.EstefadeTakhfifDao;
import ir.infosphere.sport.entity.EstefadeTakhfifEntity;
import ir.infosphere.sport.entity.PardakhtEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EstefadeTakhfifBiz {
	@Autowired
	private EstefadeTakhfifDao estefadeTakhfifDao;
	
	@Transactional
	public EstefadeTakhfifEntity retrieveByPardakht(PardakhtEntity pardakht){
		DetachedCriteria criteria = DetachedCriteria.forClass(EstefadeTakhfifEntity.class);
		criteria.add(Restrictions.eq("pardakht", pardakht));
		List<EstefadeTakhfifEntity> list = estefadeTakhfifDao.retrieveAllByCriteria(criteria, 0, 1);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	@Transactional
	public List<EstefadeTakhfifEntity> retrieveAll(){
		return estefadeTakhfifDao.retrieveAll();
	}

	@Transactional
	public List<EstefadeTakhfifEntity> retrieveByCriteria(DetachedCriteria criteria) {
		List<EstefadeTakhfifEntity> list = estefadeTakhfifDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public EstefadeTakhfifEntity retrieveById(Integer id) {
		return estefadeTakhfifDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(EstefadeTakhfifEntity estefadeTakhfif) {
		estefadeTakhfifDao.create(estefadeTakhfif);
	}
	
	@Transactional
	public void update(EstefadeTakhfifEntity estefadeTakhfif) {
		estefadeTakhfifDao.update(estefadeTakhfif);
	}
	
	
	@Transactional
	public void delete(Integer id) {
		estefadeTakhfifDao.delete(estefadeTakhfifDao.retrieveByID(id));
	}
}
