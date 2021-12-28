package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.GoroohhayeKarbarDao;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.EssentialGroups;
import ir.infosphere.sport.entity.GorooheKarbariEntity;
import ir.infosphere.sport.entity.GoroohhayeKarbarEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.PortalEntity;
import ir.infosphere.sport.entity.SiasatEntity;
import ir.infosphere.sport.util.PermissionUtil;

import java.util.Date;
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
public class GoroohhayeKarbarBiz {
	@Autowired
	private GoroohhayeKarbarDao goroohhayeKarbarDao;

	@Transactional
	public List<GoroohhayeKarbarEntity> retrieveAll() {
		return goroohhayeKarbarDao.retrieveAll();
	}
	
	@Transactional
	public List<GoroohhayeKarbarEntity> getAllByOzvAndPortal(OzvEntity ozv, PortalEntity portal) {
		if (ozv == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(GoroohhayeKarbarEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		criteria.add(Restrictions.isNull("payaneTakhsis"));
		if (portal != null) {
			criteria.createAlias("gorooh", "gorooh");
			criteria.add(Restrictions.eq("gorooh.portal", portal));
		}
		return goroohhayeKarbarDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<GoroohhayeKarbarEntity> getAllByGoroohKarbari(GorooheKarbariEntity gorooh) {
		if (gorooh == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(GoroohhayeKarbarEntity.class);
		criteria.createAlias("gorooh", "gorooh");
		criteria.add(Restrictions.eq("gorooh.portal", PermissionUtil.getCurrentPortal()));
		criteria.add(Restrictions.eq("gorooh", gorooh));
		criteria.add(Restrictions.isNull("payaneTakhsis"));
		return goroohhayeKarbarDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<GoroohhayeKarbarEntity> getAllAzayeGoroohyeKarbariBySiasat(SiasatEntity siasatItem) {
		if (siasatItem == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(GoroohhayeKarbarEntity.class);
		criteria.createAlias("ozv", "ozv");
		criteria.add(Restrictions.eq("gorooh", siasatItem.getGorooheKarbari()));
		criteria.add(Restrictions.ge("payaneTakhsis", siasatItem.getAzTarikh()));
		criteria.add(Restrictions.le("shorooeTakhsis", siasatItem.getTaTarikh()));
		return goroohhayeKarbarDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public boolean isSiasatForUser(OzvEntity ozv, SiasatEntity siasatItem) {
		if (siasatItem == null || ozv ==  null)
			return false;
		DetachedCriteria criteria = DetachedCriteria.forClass(GoroohhayeKarbarEntity.class);
		criteria.createAlias("ozv", "ozv");
		criteria.add(Restrictions.eq("ozv", ozv));
		criteria.add(Restrictions.eq("gorooh", siasatItem.getGorooheKarbari()));
		criteria.add(Restrictions.ge("payaneTakhsis", siasatItem.getAzTarikh()));
		criteria.add(Restrictions.le("shorooeTakhsis", siasatItem.getTaTarikh()));
		
		if (goroohhayeKarbarDao.getCountByCriteria(criteria) > 0)
			return true;
		else
			return false;
	}
	
	@Transactional
	public boolean isOzveGorooh(OzvEntity ozv, GorooheKarbariEntity gorooh) {
		if (ozv == null || gorooh == null)
			return false;
		DetachedCriteria criteria = DetachedCriteria.forClass(GoroohhayeKarbarEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		criteria.add(Restrictions.eq("gorooh", gorooh));
		criteria.add(Restrictions.isNull("payaneTakhsis"));
		if (goroohhayeKarbarDao.getCountByCriteria(criteria) > 0)
			return true;
		else
			return false;
	}
	
	@Transactional
	public List<GoroohhayeKarbarEntity> retrieveGoroohayeKarbar(OzvEntity ozv, GorooheKarbariEntity gorooh) {
		if (ozv == null || gorooh == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(GoroohhayeKarbarEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		criteria.add(Restrictions.eq("gorooh", gorooh));
		List<GoroohhayeKarbarEntity> temp = goroohhayeKarbarDao.retrieveAllByCriteria(criteria);
		if (temp.size() > 0)
			return temp;
		else
			return null;
	}
	
	@Transactional
	public List<GoroohhayeKarbarEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return goroohhayeKarbarDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<GoroohhayeKarbarEntity> ozvGorooh(GorooheKarbariEntity gorooh) {
		if (gorooh == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(GoroohhayeKarbarEntity.class);
		criteria.add(Restrictions.eq("gorooh", gorooh));
		return goroohhayeKarbarDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public GoroohhayeKarbarEntity retrieveById(Integer id) {
		return goroohhayeKarbarDao.retrieveByID(id);
	}

	@Transactional
	public void delete(Integer id) {
		goroohhayeKarbarDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void setPayanTakhsis(OzvEntity ozv, GorooheKarbariEntity gorooh) {
		List<GoroohhayeKarbarEntity> list = retrieveGoroohayeKarbar(ozv, gorooh);
		if (list != null) {
			for (GoroohhayeKarbarEntity entity : list) {
				if(entity.getPayaneTakhsis()==null){
					entity.setPayaneTakhsis(new Date());
					update(entity);
				}
			}
		}
	}

	@Transactional
	public void update(GoroohhayeKarbarEntity entity) {
		goroohhayeKarbarDao.update(entity);
	}
	
	@Transactional
	public void addEssentialGroup(String EssentialGroupId, OzvEntity ozv) {
		ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
		WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		GorooheKarbariBiz gorooheKarbariBiz = (GorooheKarbariBiz) appContext.getBean("gorooheKarbariBiz");
		GorooheKarbariEntity essentialGroup = gorooheKarbariBiz.retrieveById(Short.valueOf(EssentialGroupId)); 
		if (isOzveGorooh(ozv, essentialGroup))
			return;
		GoroohhayeKarbarEntity goroohKarbar = new GoroohhayeKarbarEntity();
		goroohKarbar.setGorooh(essentialGroup);
		goroohKarbar.setKarbar(ozv);
		goroohKarbar.setShorooeTakhsis(new Date());
		goroohKarbar.setPayaneTakhsis(null);
		create(goroohKarbar);
	}
	
	@Transactional
	public void removeEssentialGroup(String EssentialGroupId, OzvEntity ozv) {
		ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
		WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		GorooheKarbariBiz gorooheKarbariBiz = (GorooheKarbariBiz) appContext.getBean("gorooheKarbariBiz");
		KodBiz kodBiz = (KodBiz) appContext.getBean("kodBiz");
		GorooheKarbariEntity essentialGroup = gorooheKarbariBiz.retrieveById(Short.valueOf(EssentialGroupId));
		
		if (EssentialGroupId.equals(EssentialGroups.MasooleMadrese)) {
			MadreseBiz madreseBiz = (MadreseBiz) appContext.getBean("madreseBiz");
			if (madreseBiz.getCountMadreseByOzveMasool(ozv) == 1)
				setPayanTakhsis(ozv, essentialGroup);
		}
		else if (EssentialGroupId.equals(EssentialGroups.FootballAmozMadrese)) {
			OzveMadreseBiz ozveMadreseBiz = (OzveMadreseBiz) appContext.getBean("ozveMadreseBiz");
			KodEntity footBallAmoz = kodBiz.getKodEntity(Constants.SemateMadrese, Constants.SemateMadrese_DaneshAmoz);
			if (ozveMadreseBiz.getCountByOzvAndSematForGorooheKarbari(ozv, footBallAmoz) == 1)
				setPayanTakhsis(ozv, essentialGroup);
		}
		else if (EssentialGroupId.equals(EssentialGroups.MorabiMadrese)) {
			OzveMadreseBiz ozveMadreseBiz = (OzveMadreseBiz) appContext.getBean("ozveMadreseBiz");
			KodEntity morabi = kodBiz.getKodEntity(Constants.SemateMadrese, Constants.SemateMadrese_Morabi);
			if (ozveMadreseBiz.getCountByOzvAndSematForGorooheKarbari(ozv, morabi) == 1)
				setPayanTakhsis(ozv, essentialGroup);
		}
		else if (EssentialGroupId.equals(EssentialGroups.MasooleTim)) {
			TimBiz timBiz = (TimBiz) appContext.getBean("timBiz");
			if (timBiz.getCountTimByOzveMasool(ozv) == 1)
				setPayanTakhsis(ozv, essentialGroup);
		}
		else if (EssentialGroupId.equals(EssentialGroups.BazikonTim)) {
			OzveTimBiz ozveTimBiz = (OzveTimBiz) appContext.getBean("ozveTimBiz");
			KodEntity bazikon = kodBiz.getKodEntity(Constants.SemateTim, Constants.SemateTim_Bazikon);
			if (ozveTimBiz.getCountByOzvAndSematForGorooheKarbari(ozv, bazikon) == 1)
				setPayanTakhsis(ozv, essentialGroup);
		}
		else if (EssentialGroupId.equals(EssentialGroups.KadreFanniTim)) {
			OzveTimBiz ozveTimBiz = (OzveTimBiz) appContext.getBean("ozveTimBiz");
			KodEntity kadreFanni = kodBiz.getKodEntity(Constants.SemateTim, Constants.SemateTim_KadreFanni);
			if (ozveTimBiz.getCountByOzvAndSematForGorooheKarbari(ozv, kadreFanni) == 1)
				setPayanTakhsis(ozv, essentialGroup);
		}
		else if (EssentialGroupId.equals(EssentialGroups.MorabiTim)) {
			OzveTimBiz ozveTimBiz = (OzveTimBiz) appContext.getBean("ozveTimBiz");
			KodEntity morabi = kodBiz.getKodEntity(Constants.SemateTim, Constants.SemateTim_Morabi);
			if (ozveTimBiz.getCountByOzvAndSematForGorooheKarbari(ozv, morabi) == 1)
				setPayanTakhsis(ozv, essentialGroup);
		}
		else if (EssentialGroupId.equals(EssentialGroups.PezeshkTim)) {
			OzveTimBiz ozveTimBiz = (OzveTimBiz) appContext.getBean("ozveTimBiz");
			KodEntity pezeshk = kodBiz.getKodEntity(Constants.SemateTim, Constants.SemateTim_Pezeshk);
			if (ozveTimBiz.getCountByOzvAndSematForGorooheKarbari(ozv, pezeshk) == 1)
				setPayanTakhsis(ozv, essentialGroup);
		}
		else if (EssentialGroupId.equals(EssentialGroups.SarMorabiTim)) {
			OzveTimBiz ozveTimBiz = (OzveTimBiz) appContext.getBean("ozveTimBiz");
			KodEntity sarMorabi = kodBiz.getKodEntity(Constants.SemateTim, Constants.SemateTim_SarMorabi);
			if (ozveTimBiz.getCountByOzvAndSematForGorooheKarbari(ozv, sarMorabi) == 1)
				setPayanTakhsis(ozv, essentialGroup);
		}
		else if (EssentialGroupId.equals(EssentialGroups.SarparastTim)) {
			OzveTimBiz ozveTimBiz = (OzveTimBiz) appContext.getBean("ozveTimBiz");
			KodEntity sarparast = kodBiz.getKodEntity(Constants.SemateTim, Constants.SemateTim_Sarparast);
			if (ozveTimBiz.getCountByOzvAndSematForGorooheKarbari(ozv, sarparast) == 1) {
				setPayanTakhsis(ozv, essentialGroup);
			}
		}
	}
	
	@Transactional
	public Integer checkName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(GoroohhayeKarbarEntity.class);
		criteria.add(Restrictions.like("nam", name));
		List<GoroohhayeKarbarEntity> list = retrieveByCriteria(criteria);
		if(list.size() == 0)
			return 0;
		else
			return list.get(0).getId();
	}
	
	@Transactional
	public void create(GoroohhayeKarbarEntity entity) {
		goroohhayeKarbarDao.create(entity);
	}
	
	@Transactional
	public void createIfNotExists(GoroohhayeKarbarEntity entity) {
		if (isOzveGorooh(entity.getKarbar(), entity.getGorooh()))
			return;
		create(entity);
	}
	
}
