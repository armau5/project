package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.MojavezeGoroohDao;
import ir.infosphere.sport.enm.PermissionEnum;
import ir.infosphere.sport.entity.GorooheKarbariEntity;
import ir.infosphere.sport.entity.GoroohhayeKarbarEntity;
import ir.infosphere.sport.entity.MojavezEntity;
import ir.infosphere.sport.entity.MojavezeGoroohEntity;
import ir.infosphere.sport.entity.OzvEntity;

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
public class MojavezeGoroohBiz {
	@Autowired
	private MojavezeGoroohDao mojavezeGoroohDao;

	@Transactional
	public List<MojavezeGoroohEntity> retrieveAll() {
		return mojavezeGoroohDao.retrieveAll();
	}
	
	@Transactional
	public List<MojavezeGoroohEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return mojavezeGoroohDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public MojavezeGoroohEntity retrieveById(Short id) {
		return mojavezeGoroohDao.retrieveByID(id);
	}

	@Transactional
	public void delete(Short id) {
		mojavezeGoroohDao.deleteById(id);
	}

	@Transactional
	public void update(MojavezeGoroohEntity entity) {
		mojavezeGoroohDao.update(entity);
	}
	
	@Transactional
	public Short checkName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(MojavezeGoroohEntity.class);
		criteria.add(Restrictions.like("nam", name));
		List<MojavezeGoroohEntity> list = retrieveByCriteria(criteria);
		if(list.size() == 0)
			return 0;
		else
			return list.get(0).getId();
	}
	
	@Transactional
	public Boolean checkRepeat(MojavezEntity mojavez, GorooheKarbariEntity gorooh) {
		DetachedCriteria criteria = DetachedCriteria.forClass(MojavezeGoroohEntity.class);
		criteria.add(Restrictions.eq("mojavez", mojavez));
		criteria.add(Restrictions.eq("gorooh", gorooh));
		List<MojavezeGoroohEntity> list = retrieveByCriteria(criteria);
		if(list.size() == 0)
			return false;
		else
			return true;
	}
	
	@Transactional
	public void create(MojavezeGoroohEntity entity) {
		mojavezeGoroohDao.create(entity);
	}
	
	@Transactional
	public Boolean HasMojavezFinal(OzvEntity ozv, PermissionEnum permission) {

		ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
		ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		MojavezBiz mojavezBiz = (MojavezBiz) appContext.getBean("mojavezBiz");
		GoroohhayeKarbarBiz goroohhayeKarbarBiz = (GoroohhayeKarbarBiz) appContext.getBean("goroohhayeKarbarBiz");
		MojavezEntity mojavez = mojavezBiz.retrieveById(permission.getPermissionCode());

		List<GoroohhayeKarbarEntity> temp = goroohhayeKarbarBiz.getAllByOzvAndPortal(ozv, null);
		for (GoroohhayeKarbarEntity entity : temp) {
			{
			if (HasMojavezGorooh(entity.getGorooh(), mojavez))
				return true;
			}
		}
		
		return false;
	}
	
	@Transactional
	public List<MojavezeGoroohEntity> retriveByGorooh(GorooheKarbariEntity goroheKarbari) {

		if (goroheKarbari == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(MojavezeGoroohEntity.class);
		criteria.add(Restrictions.eq("gorooh", goroheKarbari));
		List<MojavezeGoroohEntity> list = mojavezeGoroohDao.retrieveAllByCriteria(criteria);
		if (list.isEmpty())
			return null;
		else
			return list;
	}
	
	@Transactional
	public Boolean HasMojavezGorooh(GorooheKarbariEntity goroheKarbari, MojavezEntity mojavez) {

		if (goroheKarbari == null || mojavez == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(MojavezeGoroohEntity.class);
		criteria.add(Restrictions.eq("mojavez", mojavez));
		criteria.add(Restrictions.eq("gorooh", goroheKarbari));
		List<MojavezeGoroohEntity> list = mojavezeGoroohDao.retrieveAllByCriteria(criteria);
		if (list.isEmpty())
			return false;
		else
			return true;
	}
}
