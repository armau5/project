package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.MatlabAksDao;
import ir.infosphere.sport.entity.AksEntity;
import ir.infosphere.sport.entity.MatlabAksEntity;
import ir.infosphere.sport.entity.MatlabEntity;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;

@Component
public class MatlabAksBiz {
	@Autowired
	private MatlabAksDao matlabAksDao;
	
	@Transactional
	public List<MatlabAksEntity> getAllAksHayeMataleb(){
		List<MatlabAksEntity> list = matlabAksDao.retrieveAll();
		return list;
	}
	
	@Transactional
	public List<MatlabAksEntity> getAllAksByMatlab(MatlabEntity matlab) {
		if (matlab == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(MatlabAksEntity.class);
		criteria.add(Restrictions.eq("matlab", matlab));
		return matlabAksDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public MatlabAksEntity getDefaultAllAksByMatlab(MatlabEntity matlab) {
		if (matlab == null) return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(MatlabAksEntity.class);
		criteria.add(Restrictions.eq("matlab", matlab));
		List<MatlabAksEntity> list = matlabAksDao.retrieveAllByCriteria(criteria); 
		if (list.size() > 0) return list.get(0);
		return null; 
	}
	
	@Transactional
	public MatlabAksEntity getByMatlabAndAks(MatlabEntity matlab, AksEntity aks) {
		if (matlab == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MatlabAksEntity.class);
		criteria.add(Restrictions.eq("matlab", matlab));
		criteria.add(Restrictions.eq("aks", aks));
		List<MatlabAksEntity> list = matlabAksDao.retrieveAllByCriteria(criteria);
		if (list.size() == 0)
			return null;
		else
			return list.get(0);
	}
	
	@Transactional
	public Long getCountByMatlabAndAks(MatlabEntity matlab, AksEntity aks) {
		if (matlab == null || aks == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MatlabAksEntity.class);
		criteria.add(Restrictions.eq("matlab", matlab));
		criteria.add(Restrictions.eq("aks", aks));
		return matlabAksDao.getCountByCriteria(criteria);
	}
	
	
	@Transactional
	public void deleteByMatlabAndAks(MatlabEntity matlab, AksEntity aks) {
		if (matlab == null)
			return;
		if(aks == null)
			return;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MatlabAksEntity.class);
		criteria.add(Restrictions.eq("matlab", matlab));
		criteria.add(Restrictions.eq("aks", aks));
		List<MatlabAksEntity> akshayeKhabar = matlabAksDao.retrieveAllByCriteria(criteria);
		for (MatlabAksEntity matlabAksEntity : akshayeKhabar) {
			File file = new File(matlabAksEntity.getAks().getNameFileAks());
			if (file.exists())
				file.delete();
			Integer IDAks = matlabAksEntity.getAks().getId();
			delete(matlabAksEntity.getId());
			ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
			ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			AksBiz aksBiz = (AksBiz) appContext.getBean("aksBiz");
			aksBiz.delete(aksBiz.retrieveById(IDAks));
		}
	}
	@Transactional
	public List<MatlabAksEntity> retrieveByCriteria(DetachedCriteria criteria){
		List<MatlabAksEntity> list = matlabAksDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public MatlabAksEntity retrieveById(Integer id) {
		return matlabAksDao.retrieveByID(id);
	}
	
	@Transactional
	public void update(MatlabAksEntity entity) {
		matlabAksDao.update(entity);
	}
	
	@Transactional
	public void deleteAllByMatlab(MatlabEntity matlab) {
		List<MatlabAksEntity> akshayeKhabar = getAllAksByMatlab(matlab);
		for (MatlabAksEntity matlabAksEntity : akshayeKhabar) {
			File file = new File(matlabAksEntity.getAks().getNameFileAks());
			if (file.exists())
				file.delete();
			Integer IDAks = matlabAksEntity.getAks().getId();
			delete(matlabAksEntity.getId());
			ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
			ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			AksBiz aksBiz = (AksBiz) appContext.getBean("aksBiz");
			aksBiz.delete(aksBiz.retrieveById(IDAks));
		}
	}
	
	@Transactional
	public void delete(Integer id) {
		matlabAksDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void create(MatlabAksEntity entity) {
		matlabAksDao.create(entity);
	}
}
