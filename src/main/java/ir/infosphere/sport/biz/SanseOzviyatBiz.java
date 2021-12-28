package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.SanseOzviyyatDao;
import ir.infosphere.sport.entity.OzviyyatEntity;
import ir.infosphere.sport.entity.SansEntity;
import ir.infosphere.sport.entity.SanseOzviyyatEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SanseOzviyatBiz {
	@Autowired
	private SanseOzviyyatDao sanseOzviyyatDao;

	@Transactional
	public List<SanseOzviyyatEntity> retrieveAll() {
		return sanseOzviyyatDao.retrieveAll();
	}
	
	@Transactional
	public List<SanseOzviyyatEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return sanseOzviyyatDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public SanseOzviyyatEntity retrieveById(Integer id) {
		return sanseOzviyyatDao.retrieveByID(id);
	}
	
	@Transactional
	public List<SanseOzviyyatEntity> retrieveByOzviat(OzviyyatEntity ozviyyat) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SanseOzviyyatEntity.class);
		criteria.add(Restrictions.eq("ozviyyat", ozviyyat));
		return sanseOzviyyatDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<SanseOzviyyatEntity> retrieveBySans(List<OzviyyatEntity> ozviyyatha,SansEntity sans) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SanseOzviyyatEntity.class);
		criteria.add(Restrictions.eq("shenaseyeSans",sans));
		if(ozviyyatha !=null && ozviyyatha.size()>0)
			criteria.add(Restrictions.in("ozviyyat",ozviyyatha));
		return sanseOzviyyatDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public void delete(Integer id) {
		sanseOzviyyatDao.deleteById(id);
	}

	@Transactional
	public void update(SanseOzviyyatEntity entity) {
		sanseOzviyyatDao.update(entity);
	}
	
	@Transactional
	public void create(SanseOzviyyatEntity entity) {
		sanseOzviyyatDao.create(entity);
	}
}
