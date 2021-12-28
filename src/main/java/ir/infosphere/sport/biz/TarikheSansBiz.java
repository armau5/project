package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.TarikheSansDao;
import ir.infosphere.sport.entity.BakhshEntity;
import ir.infosphere.sport.entity.SansEntity;
import ir.infosphere.sport.entity.TarikheSansEntity;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TarikheSansBiz {
	@Autowired
	private TarikheSansDao tarikheSansDao;
	
	@Transactional
	public void create(TarikheSansEntity entity) {
		tarikheSansDao.create(entity);
	}
	
	@Transactional
	public List<TarikheSansEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return tarikheSansDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<TarikheSansEntity> retrieveAllByBakhsh(BakhshEntity bakhsh) {
		//DetachedCriteria criteria = DetachedCriteria.forClass(TarikheSansEntity.class);
		//criteria.createAlias("shenaseyeSans", "sans");
		//criteria.add(Restrictions.eq("sans.shenaseyeBakhsh", bakhsh));
		return tarikheSansDao.retrieveAll();
	}
	
	@Transactional
	public List<TarikheSansEntity> retrieveAllBySans(SansEntity sans) {
		DetachedCriteria criteria = DetachedCriteria.forClass(TarikheSansEntity.class);
		criteria.createAlias("shenaseyeSans", "sans");
		return tarikheSansDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public boolean isAvailable(SansEntity sans, Date tarikh) throws ParseException {
		DetachedCriteria criteria = DetachedCriteria.forClass(TarikheSansEntity.class);
		criteria.add(Restrictions.eq("shenaseyeSans", sans));
		criteria.add(Restrictions.eq("tarikh", tarikh));
		Long tedad = tarikheSansDao.getCountByCriteria(criteria);
		return (tedad.equals(0L)) ? false : true;
	}
}
