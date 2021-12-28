package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.NoeMatlabDao;
import ir.infosphere.sport.entity.AksEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.MatlabAksEntity;
import ir.infosphere.sport.entity.MatlabEntity;
import ir.infosphere.sport.entity.NoeMatlabEntity;
import ir.infosphere.sport.util.PermissionUtil;

import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;

@Component
public class NoeMatlabBiz {
	@Autowired
	private NoeMatlabDao noeMatlabDao;

	@Transactional
	public List<NoeMatlabEntity> getAllNoeMataleb() {
		List<NoeMatlabEntity> list = noeMatlabDao.retrieveAll();
		return list;
	}

	@Transactional
	public List<NoeMatlabEntity> getAllNoeMatlabByKodeMatlab(
			KodEntity kodeMatlab) {
		if (kodeMatlab == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(NoeMatlabEntity.class);
		criteria.add(Restrictions.eq("kodeMatlab", kodeMatlab));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		return noeMatlabDao.retrieveAllByCriteria(criteria);
	}
	
	public AksEntity getDefaultAks(NoeMatlabEntity noeMatlab)
	{
		ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
		WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		MatlabBiz matlabBiz = (MatlabBiz) appContext.getBean("matlabBiz");
		MatlabAksBiz matlabAksBiz = (MatlabAksBiz) appContext.getBean("matlabAksBiz");
		List<MatlabEntity> list = matlabBiz.getAllMatlabFaalByNoeMatlab(noeMatlab);
		if (list.size() > 0)
		{
			MatlabEntity temp = list.get(0);
			List<MatlabAksEntity> listAks = matlabAksBiz.getAllAksByMatlab(temp);
			if (listAks.size() > 0)
				return listAks.get(0).getAks();
			else
				return null;
		}
		else
			return null;
	}

	@Transactional
	public List<NoeMatlabEntity> retrieveByCriteria(
			DetachedCriteria criteria) {
		List<NoeMatlabEntity> list = noeMatlabDao
				.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public NoeMatlabEntity retrieveById(Integer id) {
		return noeMatlabDao.retrieveByID(id);
	}

	@Transactional
	public void update(NoeMatlabEntity entity) {
		noeMatlabDao.update(entity);
	}

	@Transactional
	public void delete(Integer id) {
		noeMatlabDao.delete(retrieveById(id));
	}

	@Transactional
	public void create(NoeMatlabEntity entity) {
		noeMatlabDao.create(entity);
	}
}
