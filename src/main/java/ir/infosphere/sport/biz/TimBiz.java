package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.TimDao;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.ReshteyeVarzeshiEntity;
import ir.infosphere.sport.entity.SiasatEntity;
import ir.infosphere.sport.entity.TimEntity;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TimBiz {

	@Autowired
	private TimDao timDao;

	@Transactional
	public List<TimEntity> getAllTimByReshteh(ReshteyeVarzeshiEntity reshteh) {
		if (reshteh == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(TimEntity.class);
		criteria.createAlias("gorooheReshteyeVarzeshi", "gorooh", Criteria.LEFT_JOIN);
		criteria.add(Restrictions.eq("gorooh.reshteyeVarzeshi", reshteh));
		criteria.addOrder(Order.asc("nameTim"));
		return timDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public Integer getCountTimByOzveMasool(OzvEntity ozveMasool) {
		if (ozveMasool == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(TimEntity.class);
		criteria.add(Restrictions.eq("ozveMasool", ozveMasool));
		return timDao.getCountByCriteria(criteria).intValue();
	}
	
	@Transactional
	public List<TimEntity> getAllTimByOzveMasoolForSiasat(OzvEntity ozveMasool, SiasatEntity siasat) {
		if (ozveMasool == null || siasat == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(TimEntity.class);
		criteria.add(Restrictions.eq("ozveMasool", ozveMasool));
		criteria.add(Restrictions.eq("gorooheReshteyeVarzeshi", siasat.getGorooheReshteVarzeshi()));
		criteria.add(Restrictions.eq("satheTim", siasat.getSatheTim()));
		criteria.add(Restrictions.eq("jensiat", siasat.getJensiat()));
		criteria.add(Restrictions.ge("tarikheSabtTim", siasat.getAzTarikh()));
		return timDao.retrieveAllByCriteria(criteria);
	}
	
	
	
	@Transactional
	public List<TimEntity> getAllTimBySiasat(SiasatEntity siasat) {
		if (siasat == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(TimEntity.class);
		criteria.add(Restrictions.eq("gorooheReshteyeVarzeshi", siasat.getGorooheReshteVarzeshi()));
		criteria.add(Restrictions.eq("jensiat", siasat.getJensiat()));
		criteria.add(Restrictions.eq("timeDakheli", false));
		criteria.add(Restrictions.eq("gheyreFaal", false));
		return timDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<TimEntity> getAllTim() {
		DetachedCriteria criteria = DetachedCriteria.forClass(TimEntity.class);
		criteria.addOrder(Order.asc("nameTim"));
		return timDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public TimEntity getTimById(Short id) {
		return timDao.retrieveByID(id);
	}

	@Transactional
	public List<TimEntity> retrieveByCriteria(DetachedCriteria criteria) {
		return timDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<TimEntity> retrieveAll() {
		List<TimEntity> list = timDao.retrieveAll();
		return list;
	}

	@Transactional
	public void deleteTim(Short id) {
		timDao.deleteById(id);
	}

	@Transactional
	public void changeTimActivation(TimEntity tim) {
		tim.setGheyreFaal(!tim.getGheyreFaal());
		timDao.update(tim);
	}

	@Transactional
	public void createTim(TimEntity tim) {
		timDao.create(tim);
	}
	
	@Transactional
	public void updateTim(TimEntity tim) {
		timDao.update(tim);
	}
	
	@Transactional
	public Short checkName(ReshteyeVarzeshiEntity reshte, String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(TimEntity.class);
		criteria.createAlias("gorooheReshteyeVarzeshi", "gorooh", Criteria.LEFT_JOIN);
		criteria.add(Restrictions.eq("gorooh.reshteyeVarzeshi", reshte));
		criteria.add(Restrictions.like("nameTim", name));
		List<TimEntity> list = retrieveByCriteria(criteria);
		if(list.size() == 0)
			return 0;
		else
			return list.get(0).getId();
	}
	
	@Transactional
	public Short checkNameForEdit(ReshteyeVarzeshiEntity reshte, String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(TimEntity.class);
		criteria.createAlias("gorooheReshteyeVarzeshi", "gorooh", Criteria.LEFT_JOIN);
		criteria.add(Restrictions.eq("gorooh.reshteyeVarzeshi", reshte));
		criteria.add(Restrictions.like("nameTim", name));
		List<TimEntity> list = retrieveByCriteria(criteria);
		if(list.size() <= 1)
			return 0;
		else
			return list.get(0).getId();
	}

}
