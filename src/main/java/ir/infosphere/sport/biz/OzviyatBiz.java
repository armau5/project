package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.OzviyyatDao;
import ir.infosphere.sport.dao.SanseOzviyyatDao;
import ir.infosphere.sport.entity.OzviyyatEntity;
import ir.infosphere.sport.entity.PardakhtEntity;
import ir.infosphere.sport.entity.SansEntity;
import ir.infosphere.sport.entity.SanseOzviyyatEntity;
import ir.infosphere.sport.entity.ServiceBakhshEntity;
import ir.infosphere.sport.util.dargah.PardakhtUtil;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OzviyatBiz {
	@Autowired
	private OzviyyatDao ozviyyatDao;
	@Autowired
	private SanseOzviyyatDao sanseOzviyyatDao;
	
	@Transactional
	public OzviyyatEntity retrieveById(Integer id) {
		return ozviyyatDao.retrieveByID(id);
	}
	
	@Transactional
	public List<OzviyyatEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return ozviyyatDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public SansEntity[] retrieveSanseOzviyyat(OzviyyatEntity ozviyyat) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SanseOzviyyatEntity.class);
		criteria.add(Restrictions.eq("shenaseyeOzviyyat", ozviyyat));
		List<SanseOzviyyatEntity> list = sanseOzviyyatDao.retrieveAllByCriteria(criteria);
		SansEntity[] sansHa = new SansEntity[list.size()];
		for (int i = 0; i < list.size(); i++) {
			sansHa[i] = list.get(i).getShenaseyeSans();
		}
		return sansHa;
	}
	
	@Transactional
	public List<OzviyyatEntity> getAllOzviyatPardakhtiByService(List<ServiceBakhshEntity> services,Date start,Date end) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OzviyyatEntity.class);
		criteria.add(Restrictions.in("service",services));
		criteria.createAlias("pardakht", "pardakht");
		criteria.add(Restrictions.eq("pardakht.halatePardakht",
				PardakhtUtil.transactionOK));
		if(start!=null){
			criteria.add(Restrictions.ge("pardakht.tarikhZaman",start));
		}
		if(end!=null){
			criteria.add(Restrictions.le("pardakht.tarikhZaman",end));
		}
		return ozviyyatDao.retrieveAllByCriteria(criteria);
	}
	
	@SuppressWarnings("null")
	@Transactional
	public boolean finilizeOzviatHa(PardakhtEntity pardakht)
	{
		List<OzviyyatEntity> list = getByPardakht(pardakht);
		if (list != null || list.size() != 0){
			for (OzviyyatEntity ozviyat : list) {
				if (ozviyat.getService().getModateEtebar() != 25) {
					ozviyat.setTarikhShoro(new Date());
					update(ozviyat);
				}
			}
			return true;
		}
		return false;
	}
	
	@Transactional
	public void editOzviyyat(OzviyyatEntity ozviyyat, Date shuruDate, List<SansEntity> sansHa) {
		ozviyyat.setTarikhShoro(shuruDate);
		ozviyyatDao.update(ozviyyat);
		
		DetachedCriteria criteria = DetachedCriteria.forClass(SanseOzviyyatEntity.class);
		criteria.add(Restrictions.eq("shenaseyeOzviyyat", ozviyyat));
		List<SanseOzviyyatEntity> list = sanseOzviyyatDao.retrieveAllByCriteria(criteria);
		for (int i = 0; i < list.size(); i++) {
			sanseOzviyyatDao.delete(list.get(i));
		}
		for (int i = 0; i < sansHa.size(); i++) {
			SanseOzviyyatEntity entity = new SanseOzviyyatEntity();
			entity.setOzviyyat(ozviyyat);
			entity.setShenaseyeSans(sansHa.get(i));
			sanseOzviyyatDao.create(entity);
		}
	}
	
	private List<OzviyyatEntity> getByPardakht(PardakhtEntity pardakht) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OzviyyatEntity.class);
		criteria.add(Restrictions.eq("pardakht", pardakht));
		List<OzviyyatEntity> list = ozviyyatDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	public Boolean isPardakhtValid(PardakhtEntity pardakht) {
		List<OzviyyatEntity> list = getByPardakht(pardakht);
		if (list == null || list.size() == 0)
			return false;
		return true;
	}
	
	@Transactional
	public void update (OzviyyatEntity entity) {
		ozviyyatDao.update(entity);
	}
	
	@Transactional
	public void delete (OzviyyatEntity entity) {
		ozviyyatDao.delete(entity);
	}
	
	@Transactional
	public void create (OzviyyatEntity entity) {
		ozviyyatDao.create(entity);
	}
}
