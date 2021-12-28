package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.SiasatTakhfifDao;
import ir.infosphere.sport.entity.BasteForooshEntity;
import ir.infosphere.sport.entity.GorooheKarbariEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.SiasatTakhfifEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SiasatTakhfifBiz {
	@Autowired
	private SiasatTakhfifDao siasatTakhfifDao;
	
	@Transactional
	public List<SiasatTakhfifEntity> getAllSiasatTakhfif(){
		return siasatTakhfifDao.retrieveAll();
	}
	
	@Transactional
	public List<SiasatTakhfifEntity> getAllSiasatTakhfifByNoeSiasat(KodEntity noeSiasat, Boolean GheyreFaal){
		DetachedCriteria criteria = DetachedCriteria.forClass(SiasatTakhfifEntity.class);
		if (noeSiasat != null)
			criteria.add(Restrictions.eq("noeBasteTakhfif", noeSiasat));
		if (GheyreFaal != null)
			criteria.add(Restrictions.eq("gheyreFaal", GheyreFaal));
		return siasatTakhfifDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<SiasatTakhfifEntity> getAllSiasatTakhfifByBasteForosh(BasteForooshEntity basteForoosh){
		if (basteForoosh == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SiasatTakhfifEntity.class);
		criteria.add(Restrictions.eq("basteForoosh", basteForoosh));
		criteria.add(Restrictions.eq("gheyreFaal", Boolean.FALSE));
		return siasatTakhfifDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<SiasatTakhfifEntity> getAllSiasatTakhfifByGorooheKarbari(GorooheKarbariEntity gorooheKarbari){
		if (gorooheKarbari == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SiasatTakhfifEntity.class);
		criteria.add(Restrictions.eq("gorooheKarbari", gorooheKarbari));
		criteria.add(Restrictions.eq("gheyreFaal", Boolean.FALSE));
		return siasatTakhfifDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<SiasatTakhfifEntity> retrieveSiasatTakhfifByCriteria(DetachedCriteria criteria) {
		List<SiasatTakhfifEntity> list = siasatTakhfifDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public SiasatTakhfifEntity retrieveById(Integer id) {
		return siasatTakhfifDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(SiasatTakhfifEntity siasatTakhfif) {
		siasatTakhfifDao.create(siasatTakhfif);
	}
	
	@Transactional
	public void update(SiasatTakhfifEntity siasatTakhfif) {
		siasatTakhfifDao.update(siasatTakhfif);
	}
	
	@Transactional
	public void delete(Integer id) {
		siasatTakhfifDao.delete(siasatTakhfifDao.retrieveByID(id));
	}
	
	@Transactional
	public void delete(SiasatTakhfifEntity siasatTakhfif) {
		siasatTakhfifDao.delete(siasatTakhfif);
	}
}
