package ir.infosphere.sport.biz;

import ir.infosphere.sport.bean.MadreseCountReportBean;
import ir.infosphere.sport.dao.MadreseDao;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.MadreseEntity;
import ir.infosphere.sport.entity.NahiyeEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.SiasatEntity;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MadreseBiz {
	@Autowired
	private MadreseDao madreseDao;

	@Autowired
	private KodBiz kodBiz;

	@Transactional
	public List<MadreseEntity> getAllMadrese() {
		return madreseDao.retrieveAll();
	}

	@Transactional
	public List<MadreseEntity> getAllMadreseHayeFaalShahrestan(
			NahiyeEntity shahrestan) {
		if (shahrestan == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MadreseEntity.class);
		criteria.add(Restrictions.eq("gheyreFaal", false));
		criteria.createAlias("nahiye.valed", "shahrestan", Criteria.LEFT_JOIN);
		criteria.add(Restrictions.like("shahrestan.nameNahiye",
				shahrestan.getNameNahiye()));
		return madreseDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<MadreseEntity> getAllMadreseHayeFaal() {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MadreseEntity.class);
		criteria.add(Restrictions.eq("gheyreFaal", false));
		return madreseDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<MadreseEntity> getAllMadreseBySiasat(SiasatEntity siasat) {
		if (siasat == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MadreseEntity.class);
		criteria.add(Restrictions.eq("reshteVarzeshi",
				siasat.getReshteVarzeshi()));
		criteria.add(Restrictions.eq("jensiat", siasat.getJensiat()));
		criteria.add(Restrictions.eq("gheyreFaal", false));
		return madreseDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<MadreseEntity> getAllMadreseByOzveMasool(OzvEntity ozveMasool) {
		if (ozveMasool == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MadreseEntity.class);
		criteria.add(Restrictions.eq("ozveMasool", ozveMasool));
		return madreseDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public Integer getCountMadreseByOzveMasool(OzvEntity ozveMasool) {
		if (ozveMasool == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MadreseEntity.class);
		criteria.add(Restrictions.eq("ozveMasool", ozveMasool));
		return madreseDao.getCountByCriteria(criteria).intValue();
	}

	@Transactional
	public List<MadreseEntity> getAllMadreseByOzveMasoolForSiasat(
			OzvEntity ozveMasool, SiasatEntity siasat) {
		if (ozveMasool == null || siasat == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(MadreseEntity.class);
		criteria.add(Restrictions.eq("ozveMasool", ozveMasool));
		criteria.add(Restrictions.eq("jensiat", siasat.getJensiat()));
		criteria.add(Restrictions.eq("reshteVarzeshi", siasat.getReshteVarzeshi()));
		criteria.add(Restrictions.ge("tarikheSabt", siasat.getAzTarikh()));
		return madreseDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<MadreseEntity> getAllMadreseByNahiye(NahiyeEntity nahiye) {
		if (nahiye == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MadreseEntity.class);
		criteria.add(Restrictions.eq("nahiye", nahiye));
		criteria.addOrder(Order.asc("name"));
		return madreseDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public MadreseEntity getMadreseByName(NahiyeEntity nahiye, String name) {
		if (name == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MadreseEntity.class);
		criteria.add(Restrictions.eq("nahiye", nahiye));
		criteria.add(Restrictions.like("name", name));
		List<MadreseEntity> list = madreseDao.retrieveAllByCriteria(criteria);
		return list.get(0);
	}

	@Transactional
	public List<MadreseEntity> getMadreseByDateSabt(Date start, Date end) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MadreseEntity.class);
		criteria.add(Restrictions.ge("tarikheSabt", start));
		criteria.add(Restrictions.le("tarikheSabt", end));
		List<MadreseEntity> list = madreseDao.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public List<MadreseEntity> retrieveByCriteria(DetachedCriteria criteria) {
		List<MadreseEntity> list = madreseDao.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public List<MadreseCountReportBean> getMadreseCountReport(
			Integer shenaseyeOstan) {
		return madreseDao.getMadreseCountReport(shenaseyeOstan, kodBiz
				.getKodEntity(Constants.Jensiat, Constants.Mard).getId());
	}

	/*
	 * private DetachedCriteria handleCriteria (DetachedCriteria criteria) {
	 * OzvEntity ozv = PermissionUtil.getCurrentUser();
	 * 
	 * if (PermissionUtil.Check(PermissionEnum.MADRESE_KoleKeshvar)) return
	 * criteria; else if
	 * (PermissionUtil.Check(PermissionEnum.MADRESE_KoleOstan)) {
	 * criteria.createAlias("nahiye.valed.valed", "ostan", Criteria.LEFT_JOIN);
	 * criteria.add(Restrictions.like("ostan.nameNahiye",
	 * ozv.getNahiye().getValed().getValed().getNameNahiye())); } else
	 * criteria.add(Restrictions.like("ozveMasool", ozv));
	 * 
	 * return criteria; }
	 * 
	 * @Transactional public List<MadreseEntity>
	 * retrieveByCriteriaAndPardakht(DetachedCriteria criteria, Boolean
	 * pardakht){ criteria = handleCriteria(criteria); List<MadreseEntity> list
	 * = madreseDao.retrieveAllByCriteria(criteria);
	 * 
	 * ServletContext servletContext =
	 * Executions.getCurrent().getDesktop().getWebApp().getServletContext();
	 * WebApplicationContext appContext =
	 * WebApplicationContextUtils.getWebApplicationContext(servletContext);
	 * SiasatBiz siasatBiz = (SiasatBiz) appContext.getBean("siasatBiz"); return
	 * siasatBiz.getAllSiasatHayePardakhtMadrese(list, pardakht); }
	 */

	@Transactional
	public MadreseEntity retrieveById(Integer id) {
		return madreseDao.retrieveByID(id);
	}

	@Transactional
	public void update(MadreseEntity entity) {
		madreseDao.update(entity);
	}

	@Transactional
	public void delete(Integer id) {
		madreseDao.delete(retrieveById(id));
	}

	@Transactional
	public void create(MadreseEntity entity) {
		madreseDao.create(entity);
	}
}
