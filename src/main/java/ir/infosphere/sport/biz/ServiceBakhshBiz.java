package ir.infosphere.sport.biz;

import java.util.List;

import ir.infosphere.sport.bean.ServiceBakhshBean;
import ir.infosphere.sport.dao.ServiceBakhshDao;
import ir.infosphere.sport.dao.TozihDao;
import ir.infosphere.sport.entity.AksEntity;
import ir.infosphere.sport.entity.BakhshEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.ServiceBakhshEntity;
import ir.infosphere.sport.entity.TozihEntity;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ServiceBakhshBiz {

	@Autowired
	private ServiceBakhshDao serviceBakhshDao;
	@Autowired
	private TozihDao tozihDao;

	@Transactional
	public ServiceBakhshBean[] retrieveServices(DetachedCriteria criteria) {
		List<ServiceBakhshEntity> list = serviceBakhshDao.retrieveAllByCriteria(criteria);
		ServiceBakhshBean[] arr = new ServiceBakhshBean[list.size()];
		for (int i = 0; i < arr.length; i++)
			arr[i] = new ServiceBakhshBean(list.get(i));
		return arr;
	}
	
	@Transactional
	public List<ServiceBakhshEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return serviceBakhshDao.retrieveAllByCriteria(criteria);
		
	}

	
	@Transactional
	public List<ServiceBakhshEntity> getAllFaalServices() {
		DetachedCriteria criteria = DetachedCriteria.forClass(ServiceBakhshEntity.class);
		criteria.add(Restrictions.eq("gheyreFaal", Boolean.FALSE));
		List<ServiceBakhshEntity> list = serviceBakhshDao.retrieveAllByCriteria(criteria);
		if (list.size() == 0) return null;
		return list ;
	}

	@Transactional
	public void deleteService(Integer serviceId) {
		serviceBakhshDao.delete(serviceBakhshDao.retrieveByID(serviceId));
	}
	
	@Transactional
	public List<ServiceBakhshEntity> retrieveByCriteria(DetachedCriteria criteria, int min, int max) {
		return serviceBakhshDao.retrieveAllByCriteria(criteria, min, max);
	}

	@Transactional
	public void createService(String name, KodEntity jensiat, Integer gheymat, KodEntity noeService, Byte estefadeDarRooz,
			Short estefadeDarEtebar, Short modateEtebar, BakhshEntity bakhsh, TozihEntity tozih, AksEntity aks) {
		ServiceBakhshEntity service = new ServiceBakhshEntity();
		service.setNameService(name);
		service.setJensiat(jensiat);
		service.setGheymat(gheymat);
		service.setNoeService(noeService);
		service.setEstefadehDarEtebar(estefadeDarEtebar);
		service.setEstefadehDarRooz(estefadeDarRooz);
		service.setModateEtebar(modateEtebar);
		service.setBakhsh(bakhsh);
		service.setGheyreFaal(false);
		service.setAks(aks);
		if (tozih != null) {
			tozihDao.create(tozih);
			service.setShenaseyeTozih(tozih);
		}
		serviceBakhshDao.create(service);
	}

	@Transactional
	public void editService(Integer id, String name, KodEntity jensiat, Integer gheymat, Byte estefadeDarRooz,
			Short estefadeDarEtebar, Short modateEtebar, String tozih, KodEntity noeService, AksEntity aks) {
		ServiceBakhshEntity service = retrieveServiceById(id);
		service.setNameService(name);
		service.setJensiat(jensiat);
		service.setGheymat(gheymat);
		service.setEstefadehDarEtebar(estefadeDarEtebar);
		service.setEstefadehDarRooz(estefadeDarRooz);
		service.setModateEtebar(modateEtebar);
		service.setNoeService(noeService);
		if (aks != null)
			service.setAks(aks);
		if (tozih != null) {
			TozihEntity entity = service.getShenaseyeTozih();
			if (entity == null) {
				entity = new TozihEntity();
				entity.setTozih(tozih);
				tozihDao.create(entity);
			} else {
				entity.setTozih(tozih);
				tozihDao.update(entity);
			}
			service.setShenaseyeTozih(entity);
		}
		serviceBakhshDao.update(service);
	}

	@Transactional
	public void updateService(ServiceBakhshEntity entity) {
		serviceBakhshDao.update(entity);
	}

	@Transactional
	public ServiceBakhshEntity retrieveServiceById(Integer id) {
		return serviceBakhshDao.retrieveByID(id);
	}

	@Transactional
	public void changeServiceActivation(Integer id) {
		ServiceBakhshEntity service = retrieveServiceById(id);
		service.setGheyreFaal(!service.getGheyreFaal());
		serviceBakhshDao.update(service);
	}

}
