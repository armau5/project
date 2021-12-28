package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.DarkhasteBeliteSazmaniDao;
import ir.infosphere.sport.entity.DarkhasteBeliteSazmaniEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.VaziateSandaliEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DarkhasteBeliteSazmaniBiz {

	@Autowired
	private DarkhasteBeliteSazmaniDao darkhasteBeliteSazmaniDao;

	@Autowired
	private KodBiz kodBiz;
	@Autowired
	private VaziateSandaliBiz vaziateSandaliBiz;

	@Transactional
	public void create(DarkhasteBeliteSazmaniEntity entity) {
		darkhasteBeliteSazmaniDao.create(entity);
	}

	@Transactional
	public void update(DarkhasteBeliteSazmaniEntity entity) {
		darkhasteBeliteSazmaniDao.update(entity);
	}

	@Transactional
	public List<DarkhasteBeliteSazmaniEntity> retrieveAll(int firstResult,
			int maxResults) {
		return darkhasteBeliteSazmaniDao.retrieveAll(firstResult, maxResults);
	}

	@Transactional
	public DarkhasteBeliteSazmaniEntity retrieveByID(Integer id) {
		return darkhasteBeliteSazmaniDao.retrieveByID(id);
	}

	@Transactional
	public List<DarkhasteBeliteSazmaniEntity> getListeDarkhasteBeliteSazmani(
			OzvEntity ozv) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(DarkhasteBeliteSazmaniEntity.class);
		criteria.add(Restrictions.eq("ozveDarkhastDahandeh", ozv));
		return darkhasteBeliteSazmaniDao.retrieveAllByCriteria(criteria, 0, 50);
	}

	@Transactional
	public List<DarkhasteBeliteSazmaniEntity> getListeDarkhasteBeliteSazmani(
			OzvEntity ozv, KodEntity vaziateDarkhast) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(DarkhasteBeliteSazmaniEntity.class);
		criteria.add(Restrictions.eq("ozveDarkhastDahandeh", ozv));
		criteria.add(Restrictions.eq("vaziateDarkhast", vaziateDarkhast));
		return darkhasteBeliteSazmaniDao.retrieveAllByCriteria(criteria, 0, 50);
	}

	@Transactional
	public void updateDarkhasteBeliteSazmaniAndVaziateSandalis(
			DarkhasteBeliteSazmaniEntity darkhasteBeliteSazmaniEntity,
			List<VaziateSandaliEntity> vaziateSandaliList) {
		for (VaziateSandaliEntity vaziateSandali : vaziateSandaliList) {
			vaziateSandali
					.setDarkhasteBeliteSazmani(darkhasteBeliteSazmaniEntity);
			vaziateSandaliBiz.update(vaziateSandali);
		}

	}

}
