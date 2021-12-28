package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.SansDao;
import ir.infosphere.sport.dao.SanseServiceDao;
import ir.infosphere.sport.entity.BakhshEntity;
import ir.infosphere.sport.entity.SansEntity;
import ir.infosphere.sport.entity.SanseServiceEntity;
import ir.infosphere.sport.entity.ServiceBakhshEntity;
import ir.infosphere.sport.entity.TarikheSansEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SansBiz {
	@Autowired
	private SansDao sansDao;
	@Autowired
	private SanseServiceDao sanseServiceDao;
	@Autowired
	private TarikheSansBiz tarikheSansBiz;

	@Transactional
	public SansEntity retrieveById(Integer id) {
		return sansDao.retrieveByID(id);
	}

	@Transactional
	public List<SansEntity> retrieveByCriteria(DetachedCriteria criteria) {
		List<SansEntity> list = sansDao.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public boolean selected(SansEntity sans, ServiceBakhshEntity service) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SanseServiceEntity.class);
		criteria.add(Restrictions.eq("shenaseyeSans", sans));
		criteria.add(Restrictions.eq("shenaseyeServiceBakhsh", service));
		List<SanseServiceEntity> list = sanseServiceDao.retrieveAllByCriteria(criteria);
		if (list.isEmpty())
			return false;
		else
			return true;
	}
	
	@Transactional
	public void addSans(SansEntity sans, ServiceBakhshEntity service) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SanseServiceEntity.class);
		criteria.add(Restrictions.eq("shenaseyeSans", sans));
		criteria.add(Restrictions.eq("shenaseyeServiceBakhsh", service));
		List<SanseServiceEntity> list = sanseServiceDao.retrieveAllByCriteria(criteria);
		if(list.isEmpty()) {
			SanseServiceEntity sanseService = new SanseServiceEntity();
			sanseService.setShenaseyeSans(sans);
			sanseService.setShenaseyeServiceBakhsh(service);
			sanseService.setGheyreFaal(false);
			sanseServiceDao.create(sanseService);
		}
	}

	@Transactional
	public void removeSans(SansEntity sans, ServiceBakhshEntity service) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SanseServiceEntity.class);
		criteria.add(Restrictions.eq("shenaseyeSans", sans));
		criteria.add(Restrictions.eq("shenaseyeServiceBakhsh", service));
		List<SanseServiceEntity> list = sanseServiceDao.retrieveAllByCriteria(criteria);
		if(!list.isEmpty())
			sanseServiceDao.delete(list.get(0));
	}
	
	@Transactional
	public SansEntity[] getSansByService(ServiceBakhshEntity service) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SanseServiceEntity.class);
		criteria.add(Restrictions.eq("shenaseyeServiceBakhsh", service));
		List<SanseServiceEntity> list = sanseServiceDao.retrieveAllByCriteria(criteria);
		SansEntity[] sansHa = new SansEntity[list.size()];
		for (int i = 0; i < list.size(); i++) {
			sansHa[i] = list.get(i).getShenaseyeSans();
		}
		return sansHa;
	}
	
	@Transactional
	public List<SansEntity> getAllByService(ServiceBakhshEntity service) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SanseServiceEntity.class);
		criteria.add(Restrictions.eq("shenaseyeServiceBakhsh", service));
		List<SanseServiceEntity> list = sanseServiceDao.retrieveAllByCriteria(criteria);
		List<SansEntity> listSans = new ArrayList<SansEntity>();
		if (list.size() == 0)
			return listSans;
		for (SanseServiceEntity sans : list) {
			listSans.add(sans.getShenaseyeSans());
		}
		return listSans;
	}
	
	@Transactional
	public List<Date> getAllByTarikheSansService(ServiceBakhshEntity service) {
		List<SansEntity> listSans = getAllByService(service);
		List<Date> listDate = new ArrayList<Date>();
		for (SansEntity sansEntity : listSans) {
			List<TarikheSansEntity> listTarikheInSans = tarikheSansBiz.retrieveAllBySans(sansEntity);
			for (TarikheSansEntity tarikh : listTarikheInSans) {
				listDate.add(tarikh.getTarikh());
			}
		}
		return listDate;
	}
	
	@Transactional
	public void delete(Integer id) {
		sansDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void update(SansEntity entity) {
		sansDao.update(entity);
	}
	
	@Transactional
	public Integer checkName(BakhshEntity bakhsh, String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SansEntity.class);
		criteria.add(Restrictions.eq("shenaseyeBakhsh", bakhsh));
		criteria.add(Restrictions.like("nameSans", name));
		List<SansEntity> list = retrieveByCriteria(criteria);
		if(list.size() == 0)
			return 0;
		else
			return list.get(0).getId();
	}
	
	@Transactional
	public void create(SansEntity entity) {
		sansDao.create(entity);
	}
}
