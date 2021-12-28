package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.SharjDao;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.PardakhtEntity;
import ir.infosphere.sport.entity.PardakhtSharjEntity;
import ir.infosphere.sport.entity.PortalEntity;
import ir.infosphere.sport.entity.SharjEntity;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SharjBiz {
	@Autowired
	private SharjDao sharjDao;
	@Autowired
	private RahgiriBiz rahgiriBiz;
	
	@Autowired
	private PardakhtSharjBiz pardakhtSharjBiz;
	
	public Integer getMojodi(PortalEntity portal, OzvEntity ozv) {
		Session session = sharjDao.getHibernateTemplate().getSessionFactory().openSession();
		String sql = "SELECT SUM(mablagh) FROM Sharj WHERE ShenaseyeOzv = " + ozv.getId() + " AND ShenaseyePortal = " + portal.getId();
		Query query = session.createSQLQuery(sql);
		Object result = query.uniqueResult();
		if (result == null)
			return 0;
		int mojodi = ((BigDecimal)result).intValue();
		return mojodi;
	}
	
	@Transactional
	public SharjEntity IncreaseMojodi(PortalEntity portal, OzvEntity ozv, Integer mablagh) {
		SharjEntity sharj = new SharjEntity();
		sharj.setMablagh(mablagh);
		sharj.setOzv(ozv);
		sharj.setPortal(portal);
		create(sharj);
		return sharj;
	}
	
	@Transactional
	public Boolean IncreaseMojodiForPardakht(PardakhtEntity pardakht) {
		try {
			List<PardakhtSharjEntity> list = pardakhtSharjBiz.getForPardakht(pardakht);
			if (list == null) return false;
			for (PardakhtSharjEntity item : list) {
				SharjEntity sharj = IncreaseMojodi(item.getPortal(), item.getOzv(), item.getMablagh());
				item.setSharj(sharj);
				pardakhtSharjBiz.update(item);
				
				try {
				rahgiriBiz.createForSharj(item);
				} catch (Exception ex) {}
			}
			return true;
		}
		catch (Exception ex) { 
			return false;
		}
	}
	
	@Transactional
	public boolean DecreaseMojodi(PortalEntity portal, OzvEntity ozv, Integer mablagh) {
		Integer mojodi = getMojodi(portal, ozv);
		if ((mojodi - mablagh) >= 0)
		{
			SharjEntity temp = IncreaseMojodi(portal, ozv, -mablagh);
			if (getMojodi(portal, ozv) < 0) {
				delete(temp);
				return false;
			}
			return true;
		}
		else
			return false;
	}
	
	@Transactional
	public List<SharjEntity> retrieveByCriteria(DetachedCriteria criteria) {
		List<SharjEntity> list = sharjDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public SharjEntity retrieveSharjById(Long id) {
		return sharjDao.retrieveByID(id);
	}
	
	private void create(SharjEntity sharj) {
		sharjDao.create(sharj);
	}
	
	@Transactional
	private void update(SharjEntity sharj) {
		sharjDao.update(sharj);
	}
	
	@Transactional
	private void delete(SharjEntity sharj) {
		sharjDao.delete(sharj);
	}
}
