package ir.infosphere.sport.biz;

import ir.infosphere.sport.bean.FootballAmoozeMadreseCountReportBean;
import ir.infosphere.sport.bean.MorabieMadreseCountReportBean;
import ir.infosphere.sport.dao.OzveMadreseDao;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.MadreseEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.OzveMadreseEntity;
import ir.infosphere.sport.entity.SiasatEntity;

import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;

@Component
public class OzveMadreseBiz {
	@Autowired
	private OzveMadreseDao ozveMadreseDao;
	@Autowired
	private KodBiz kodBiz;

	@Transactional
	public List<OzveMadreseEntity> getAllAzayeMadrese() {
		List<OzveMadreseEntity> list = ozveMadreseDao.retrieveAll();
		return list;
	}

	@Transactional
	public List<OzveMadreseEntity> getAllOzvByMadrese(MadreseEntity madrese) {
		if (madrese == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(OzveMadreseEntity.class);
		criteria.add(Restrictions.eq("madrese", madrese));
		return ozveMadreseDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<OzveMadreseEntity> getAllOzvHayeHazerByMadrese(MadreseEntity madrese) {
		if (madrese == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(OzveMadreseEntity.class);
		criteria.add(Restrictions.eq("madrese", madrese));
		criteria.add(Restrictions.isNull("tarikhePayaneEtebar"));
		return ozveMadreseDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public Integer getCountByOzvAndSematForGorooheKarbari(OzvEntity ozv,
			KodEntity semat) {
		if (ozv == null || semat == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(OzveMadreseEntity.class);
		criteria.add(Restrictions.eq("ozveMadrese", ozv));
		criteria.add(Restrictions.eq("semat", semat));
		criteria.add(Restrictions.isNull("tarikhePayaneEtebar"));
		return ozveMadreseDao.getCountByCriteria(criteria).intValue();
	}

	@Transactional
	public List<OzveMadreseEntity> getAllOzvHazerByMadreseAndSemat(
			MadreseEntity madrese, KodEntity semat) {
		if (madrese == null || semat == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(OzveMadreseEntity.class);
		criteria.add(Restrictions.eq("madrese", madrese));
		criteria.add(Restrictions.isNull("tarikhePayaneEtebar"));
		criteria.add(Restrictions.eq("semat", semat));
		return ozveMadreseDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<OzveMadreseEntity> getAllOzveMadreseBySiasat(SiasatEntity siasat) {
		if (siasat == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(OzveMadreseEntity.class);

		criteria.add(Restrictions.eq("semat", siasat.getSemateOzv()));

		criteria.createAlias("ozveMadrese", "ozv", Criteria.LEFT_JOIN);
		criteria.add(Restrictions.eq("ozv.jensiat", siasat.getJensiat()));

		criteria.createAlias("madrese", "madreseMoredeNazar",
				Criteria.LEFT_JOIN);
		criteria.add(Restrictions.eq("madreseMoredeNazar.reshteVarzeshi",
				siasat.getReshteVarzeshi()));

		Criterion rest1 = Restrictions.isNotNull("tarikhePayaneEtebar");
		Criterion rest2 = Restrictions.and(
				Restrictions.le("tarikheSabteNam", siasat.getTaTarikh()),
				Restrictions.ge("tarikhePayaneEtebar", siasat.getAzTarikh()));
		Criterion rest3 = Restrictions.and(rest1, rest2);

		Criterion rest4 = Restrictions.isNull("tarikhePayaneEtebar");
		Criterion rest5 = Restrictions.and(
				Restrictions.le("tarikheSabteNam", siasat.getTaTarikh()),
				Restrictions.ge("tarikheSabteNam", siasat.getAzTarikh()));
		Criterion rest6 = Restrictions.and(rest4, rest5);

		criteria.add(Restrictions.or(rest3, rest6));

		return ozveMadreseDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public Integer getCountAllOzveMadreseBySemat(MadreseEntity madrese,
			KodEntity semat) {
		if (madrese == null || semat == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(OzveMadreseEntity.class);
		criteria.add(Restrictions.eq("madrese", madrese));
		criteria.add(Restrictions.eq("semat", semat));
		return ozveMadreseDao.getCountByCriteria(criteria).intValue();
	}

	@Transactional
	public List<OzveMadreseEntity> getAllMadreseByDaneshAmoz(
			OzvEntity daneshAmoz) {
		if (daneshAmoz == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(OzveMadreseEntity.class);
		criteria.add(Restrictions.eq("ozveMadrese", daneshAmoz));
		criteria.addOrder(Order.desc("tarikheSabteNam"));
		return ozveMadreseDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public MadreseEntity getMadreseyeHazerByOzv(OzvEntity ozv) {
		if (ozv == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(OzveMadreseEntity.class);
		criteria.add(Restrictions.eq("ozveMadrese", ozv));
		criteria.add(Restrictions.isNull("tarikhePayaneEtebar"));
		criteria.addOrder(Order.desc("tarikheSabteNam"));
		List<OzveMadreseEntity> list = ozveMadreseDao.retrieveAllByCriteria(criteria);
		if (list == null)
			return null;
		if  (list.size() == 0)
			return null;
		return list.get(0).getMadrese();
	}

	@Transactional
	public List<OzveMadreseEntity> getAllByOzveMadrese(OzvEntity daneshAmoz) {
		if (daneshAmoz == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(OzveMadreseEntity.class);
		criteria.add(Restrictions.eq("ozveMadrese", daneshAmoz));
		criteria.addOrder(Order.desc("tarikheSabteNam"));
		return ozveMadreseDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public OzveMadreseEntity getAllByOzveMadreseAndSemat(OzvEntity daneshAmoz,
			SiasatEntity siasatItem) {
		if (daneshAmoz == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(OzveMadreseEntity.class);
		criteria.createAlias("ozveMadrese", "ozv", Criteria.LEFT_JOIN);
		criteria.createAlias("madrese", "madrese", Criteria.LEFT_JOIN);
		criteria.add(Restrictions.eq("ozveMadrese", daneshAmoz));
		criteria.add(Restrictions.eq("semat", siasatItem.getSemateOzv()));
		criteria.add(Restrictions.eq("ozv.jensiat", siasatItem.getJensiat()));
		criteria.add(Restrictions.eq("madrese.reshteVarzeshi",
				siasatItem.getReshteVarzeshi()));

		Criterion rest1 = Restrictions.isNotNull("tarikhePayaneEtebar");
		Criterion rest2 = Restrictions.and(Restrictions.le("tarikheSabteNam",
				siasatItem.getTaTarikh()), Restrictions.ge(
				"tarikhePayaneEtebar", siasatItem.getAzTarikh()));
		Criterion rest3 = Restrictions.and(rest1, rest2);

		Criterion rest4 = Restrictions.isNull("tarikhePayaneEtebar");
		Criterion rest5 = Restrictions.and(
				Restrictions.le("tarikheSabteNam", siasatItem.getTaTarikh()),
				Restrictions.ge("tarikheSabteNam", siasatItem.getAzTarikh()));
		Criterion rest6 = Restrictions.and(rest4, rest5);

		criteria.add(Restrictions.or(rest3, rest6));

		List<OzveMadreseEntity> result = ozveMadreseDao.retrieveAllByCriteria(
				criteria, 0, 1);

		return (result.size() > 0) ? result.get(0) : null;
	}

	@Transactional
	public List<OzveMadreseEntity> getAllMorabianMadrese(MadreseEntity madrese) {
		ServletContext servletContext = Executions.getCurrent().getDesktop()
				.getWebApp().getServletContext();
		ApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		KodBiz kodBiz = (KodBiz) appContext.getBean("kodBiz");
		KodEntity semat = kodBiz.getKodEntity(Constants.SemateMadrese,
				Constants.SemateMadrese_Morabi);
		if (madrese == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(OzveMadreseEntity.class);
		criteria.add(Restrictions.eq("madrese", madrese));
		criteria.add(Restrictions.eq("semat", semat));
		criteria.add(Restrictions.isNull("tarikhePayaneEtebar"));
		return ozveMadreseDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public Boolean isOzveMadreseDarTarikhe(OzveMadreseEntity ozveMadrese,
			boolean update) {
		if (ozveMadrese == null)
			return false;

		ServletContext servletContext = Executions.getCurrent().getDesktop()
				.getWebApp().getServletContext();
		WebApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		KodBiz kodBiz = (KodBiz) appContext.getBean("kodBiz");

		Criterion tarikhPayanNadarad = Restrictions
				.isNull("tarikhePayaneEtebar");
		Criterion bazeZamaniTekrari = Restrictions.and(
				Restrictions.ge("tarikhePayaneEtebar",
						ozveMadrese.getTarikheSabteNam()),
				Restrictions.le("tarikheSabteNam",
						ozveMadrese.getTarikheSabteNam()));
		Criterion sharteOmomi = Restrictions.or(tarikhPayanNadarad,
				bazeZamaniTekrari);
		Criterion madreseTekrari = Restrictions.eq("madrese",
				ozveMadrese.getMadrese());

		DetachedCriteria criteria = DetachedCriteria
				.forClass(OzveMadreseEntity.class);
		criteria.add(Restrictions.eq("ozveMadrese",
				ozveMadrese.getOzveMadrese()));
		criteria.add(Restrictions.eq("semat", ozveMadrese.getSemat()));

		if (ozveMadrese.getSemat().equals(
				kodBiz.getKodEntity(Constants.SemateMadrese,
						Constants.SemateMadrese_DaneshAmoz)))
			criteria.add(sharteOmomi);
		else if (ozveMadrese.getSemat().equals(
				kodBiz.getKodEntity(Constants.SemateMadrese,
						Constants.SemateMadrese_Morabi)))
			criteria.add(Restrictions.and(madreseTekrari, sharteOmomi));

		if (update) {
			if (ozveMadreseDao.getCountByCriteria(criteria) > 1)
				return true;
		} else {
			if (ozveMadreseDao.getCountByCriteria(criteria) > 0)
				return true;
		}
		return false;
	}

	@Transactional
	public List<MorabieMadreseCountReportBean> getMorabiCountReport(
			Integer shenaseyeOstan) {
		return ozveMadreseDao.getMorabiCountReport(
				shenaseyeOstan,
				kodBiz.getKodEntity(Constants.Jensiat, Constants.Mard).getId(),
				kodBiz.getKodEntity(Constants.SemateMadrese,
						Constants.SemateMadrese_Morabi).getId());
	}

	@Transactional
	public FootballAmoozeMadreseCountReportBean getFootballAmoozCountReport(
			Integer shenaseyeOstan, Integer senAz, Integer senTa) {
		return ozveMadreseDao.getFootballAmoozCountReport(
				shenaseyeOstan,
				kodBiz.getKodEntity(Constants.Jensiat, Constants.Mard).getId(),
				kodBiz.getKodEntity(Constants.SemateMadrese,
						Constants.SemateMadrese_DaneshAmoz).getId(), senAz, senTa);
	}

	@Transactional
	public List<OzveMadreseEntity> retrieveByCriteria(DetachedCriteria criteria) {
		List<OzveMadreseEntity> list = ozveMadreseDao
				.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public OzveMadreseEntity retrieveById(Integer id) {
		return ozveMadreseDao.retrieveByID(id);
	}

	@Transactional
	public void update(OzveMadreseEntity entity) {
		ozveMadreseDao.update(entity);
	}

	@Transactional
	public void delete(Integer id) {
		ozveMadreseDao.delete(retrieveById(id));
	}

	@Transactional
	public void create(OzveMadreseEntity entity) {
		ozveMadreseDao.create(entity);
	}
}
