package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.GorooheKodDao;
import ir.infosphere.sport.dao.KodMeghdarDao;
import ir.infosphere.sport.entity.GorooheKodEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.KodMeghdarEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class KodMeghdarBiz {
	@Autowired
	private KodMeghdarDao kodMeghdarDao;
	@Autowired
	private GorooheKodDao gorooheKodDao;

	public void setGorooheKodDao(GorooheKodDao gorooheKodDao) {
		this.gorooheKodDao = gorooheKodDao;
	}

	public void setKodMeghdarDao(KodMeghdarDao kodMeghdarDao) {
		this.kodMeghdarDao = kodMeghdarDao;
	}

	@Transactional
	public Integer getKodMeghdarID(String goroohName, String kodName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(KodMeghdarEntity.class);
		criteria.createAlias("gorooheKod", "gorooheKod");
		criteria.add(Restrictions.eq("gorooheKod.name", goroohName));
		criteria.add(Restrictions.eq("nam", kodName));
		List<KodMeghdarEntity> kods = kodMeghdarDao.retrieveAllByCriteria(criteria);
		if (kods.size() == 0)
			return null;
		return kods.get(0).getId();
	}
	
	@Transactional
	public Integer getKodMeghdarValue(String goroohName, String kodName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(KodMeghdarEntity.class);
		criteria.createAlias("gorooheKod", "gorooheKod");
		criteria.add(Restrictions.eq("gorooheKod.name", goroohName));
		criteria.add(Restrictions.eq("nam", kodName));
		List<KodMeghdarEntity> kods = kodMeghdarDao.retrieveAllByCriteria(criteria);
		if (kods.size() == 0)
			return null;
		return kods.get(0).getMeghdar();
	}

	@Transactional
	public KodMeghdarEntity getKodMeghdarEntity(String goroohName, String kodName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(KodMeghdarEntity.class);
		criteria.createAlias("gorooheKod", "gorooheKod");
		criteria.add(Restrictions.eq("gorooheKod.name", goroohName));
		criteria.add(Restrictions.eq("nam", kodName));
		List<KodMeghdarEntity> kods = kodMeghdarDao.retrieveAllByCriteria(criteria);
		if (kods.size() == 0)
			return null;
		return kods.get(0);
	}

	@Transactional
	public List<KodMeghdarEntity> getGorooheKodValues(String goroohName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(KodEntity.class);
		criteria.createAlias("gorooheKod", "gorooheKod");
		criteria.add(Restrictions.eq("gorooheKod.name", goroohName));
		return kodMeghdarDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public KodMeghdarEntity retrieveKodMeghdarById(int id) {
		return kodMeghdarDao.retrieveByID(id);
	}
	
	@Transactional
	public List<GorooheKodEntity> getAllGorooheKod(){
		List<GorooheKodEntity> list = gorooheKodDao.retrieveAll();
		return list;
	}
	
	@Transactional
	public List<KodMeghdarEntity> getAllKodMeghdarByGorooheKod(String noeKod, String active) {
		DetachedCriteria criteria = DetachedCriteria.forClass(KodMeghdarEntity.class);
		criteria.createAlias("gorooheKod", "gorooheKod");
		if(!noeKod.equals("تمامی موارد")){
		criteria.add(Restrictions.eq("gorooheKod.name", noeKod));
		}
		if (active.equals("فعال"))
			criteria.add(Restrictions.eq("gheyreFaal", false));
		else if (active.equals("غیرفعال"))
			criteria.add(Restrictions.eq("gheyreFaal", true));
		criteria.addOrder(Order.asc("gorooheKod.name"));
		criteria.addOrder(Order.desc("meghdar"));
		return kodMeghdarDao.retrieveAllByCriteria(criteria);		
	}

	@Transactional
	public void deleteCode(Integer id) {
		kodMeghdarDao.delete(kodMeghdarDao.retrieveByID(id));
	}

	@Transactional
	public GorooheKodEntity getGorooheKodById(short id) {
		return gorooheKodDao.retrieveByID(id);
	}
}
