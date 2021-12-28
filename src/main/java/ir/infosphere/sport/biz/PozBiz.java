package ir.infosphere.sport.biz;

import java.util.Date;
import java.util.List;

import ir.infosphere.sport.dao.NoePozDao;
import ir.infosphere.sport.dao.PozDao;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.MogheiyyatEntity;
import ir.infosphere.sport.entity.NoePozEntity;
import ir.infosphere.sport.entity.PozEntity;
import ir.infosphere.sport.entity.VoroodiEntity;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PozBiz {

	@Autowired
	private PozDao pozDao;
	@Autowired
	private NoePozDao noePozDao;
	

	@Transactional
	public PozEntity getPozById(Integer id) {
		return pozDao.retrieveByID(id);
	}

	@Transactional
	public List<PozEntity> getPozByShomareSerial(Integer shomareSerial) {
		if (shomareSerial == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(PozEntity.class);
		criteria.add(Restrictions.eq("serial", shomareSerial));
		return pozDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public void esghatePoz(Integer id, Date esghatDate, KodEntity status) {
		PozEntity poz = getPozById(id);
		poz.setTarikheEsghat(esghatDate);
		poz.setVaziat(status);
		pozDao.update(poz);
	}

	@Transactional
	public void tahvilPoz(Integer id, KodEntity status) {
		PozEntity poz = getPozById(id);
		poz.setVaziat(status);
		pozDao.update(poz);		
	}

	@Transactional
	public List<PozEntity> getAllPozByStatus(String status) {
		if (status == null)
			return null;
		if(status.equals(Constants.TamamiMavared)){
			return pozDao.retrieveAll();
		}
		else{
			DetachedCriteria criteria = DetachedCriteria.forClass(PozEntity.class);
			criteria.createAlias("vaziat", "vaziat", Criteria.LEFT_JOIN);
			criteria.add(Restrictions.like("vaziat.meghdar", status));
			return pozDao.retrieveAllByCriteria(criteria);
		}
	}

	@Transactional
	public void deletePoz(Integer id) {
		pozDao.delete(pozDao.retrieveByID(id));
	}

	@Transactional
	public List<?> getAllPozModel() {
		DetachedCriteria criteria = DetachedCriteria.forClass(NoePozEntity.class);
		ProjectionList properties = Projections.projectionList();
		properties.add(Projections.groupProperty("model"));
		criteria.setProjection(properties);
		return noePozDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public NoePozEntity getPozByModel(String model, String sherkateSazande){
		DetachedCriteria criteria = DetachedCriteria.forClass(NoePozEntity.class);
	//	criteria.add(Restrictions.like("model", model));
		criteria.add(Restrictions.like("sherkateSazandeh", sherkateSazande));
		List<NoePozEntity> list = noePozDao.retrieveAllByCriteria(criteria);
		return list.get(0);
	}

	@Transactional
	public void createPoz(String serial, Date kharidDate, NoePozEntity noePoz, KodEntity vaziat,VoroodiEntity voroodi, MogheiyyatEntity mogheiyat) {
		PozEntity poz = new PozEntity();
		poz.setSerial(Integer.valueOf(serial));
		poz.setNoePoz(noePoz);
		poz.setTarikheKharid(kharidDate);
		poz.setVersion(new Integer(0));
		poz.setVaziat(vaziat);
		poz.setVoroodi(voroodi);
		poz.setMogheiyyat(mogheiyat);

		pozDao.create(poz);
	}

	@Transactional
	public List<?> getPozSazandeByModel(String model) {
		DetachedCriteria criteria = DetachedCriteria.forClass(NoePozEntity.class);
		ProjectionList properties = Projections.projectionList();
		properties.add(Projections.groupProperty("sherkateSazandeh"));
		criteria.setProjection(properties);
		criteria.add(Restrictions.like("model", model));
		return noePozDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public Integer getPozVersion(String model, String sherkateSazande) {
		DetachedCriteria criteria = DetachedCriteria.forClass(NoePozEntity.class);
//		ProjectionList properties = Projections.projectionList();
//		properties.add(Projections.groupProperty("version"));
//		criteria.setProjection(properties);
		criteria.add(Restrictions.like("model", model));
		criteria.add(Restrictions.like("sherkateSazandeh", sherkateSazande));
		List<NoePozEntity> list = noePozDao.retrieveAllByCriteria(criteria);
		return list.get(0).getVersion();
	}

	@Transactional
	public PozEntity getPozBySerial(Integer serial, NoePozEntity noePoz) {
		DetachedCriteria criteria = DetachedCriteria.forClass(PozEntity.class);
		criteria.add(Restrictions.like("serial", serial));
		criteria.add(Restrictions.eq("noePoz", noePoz));
		List<PozEntity> list = pozDao.retrieveAllByCriteria(criteria);
		if(list.size() == 0){
			return null;
		}
		else{
			return list.get(0);
		}
	}

	@Transactional
	public void updatePoz(Integer id, String serial, Date kharidDate,
			NoePozEntity noePoz, Integer version, VoroodiEntity voroodi, MogheiyyatEntity mogheiyat) {
		PozEntity poz = getPozById(id);
		poz.setSerial(Integer.valueOf(serial));
		poz.setTarikheKharid(kharidDate);
		poz.setNoePoz(noePoz);
		poz.setVersion(version);
		poz.setVoroodi(voroodi);
		poz.setMogheiyyat(mogheiyat);
		pozDao.update(poz);
	}

	@Transactional
	public void updateStatusPoz(PozEntity poz,KodEntity vaziat){
		poz.setVaziat(vaziat);
		pozDao.update(poz);
	}
	@Transactional
	public List<PozEntity> getAllPoz(DetachedCriteria criteria) {
		return pozDao.retrieveAllByCriteria(criteria);
	}
	
}
