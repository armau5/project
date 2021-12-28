package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.PardakhteDarkhasteKartDao;
import ir.infosphere.sport.entity.DarkhasteKartEntity;
import ir.infosphere.sport.entity.KartEntity;
import ir.infosphere.sport.entity.PardakhtEntity;
import ir.infosphere.sport.entity.PardakhteDarkhasteKartEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PardakhteDarkhasteKartBiz {
	@Autowired
	private PardakhteDarkhasteKartDao pardakhteDarkhasteKartDao;
	
	@Autowired
	private DarkhastKartBiz darkhastKartBiz;
	@Autowired
	private KartBiz kartBiz;
	@Autowired
	private RahgiriBiz rahgiriBiz;

	@Transactional
	public List<PardakhteDarkhasteKartEntity> getAllPardakhtHayeDarkhasteKart() {
		List<PardakhteDarkhasteKartEntity> list = pardakhteDarkhasteKartDao.retrieveAll();
		return list;
	}
	
	@Transactional
	public List<PardakhteDarkhasteKartEntity> getAllByPardakht(
			PardakhtEntity pardakht) {
		if (pardakht == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(PardakhteDarkhasteKartEntity.class);
		criteria.add(Restrictions.eq("pardakht", pardakht));
		return pardakhteDarkhasteKartDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public Boolean isPardakhtValid(PardakhtEntity pardakht){
		List<PardakhteDarkhasteKartEntity> list = getAllByPardakht(pardakht);
		if (list == null || list.size() == 0){
			return false;
		}
		else
			return true;
	}
	
	@Transactional
	public List<PardakhteDarkhasteKartEntity> retrieveByCriteria(
			DetachedCriteria criteria) {
		List<PardakhteDarkhasteKartEntity> list = pardakhteDarkhasteKartDao
				.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public PardakhteDarkhasteKartEntity retrieveById(Integer id) {
		return pardakhteDarkhasteKartDao.retrieveByID(id);
	}

	@Transactional
	public void update(PardakhteDarkhasteKartEntity entity) {
		pardakhteDarkhasteKartDao.update(entity);
	}

	@Transactional
	public void delete(Integer id) {
		pardakhteDarkhasteKartDao.delete(retrieveById(id));
	}

	@Transactional
	public void create(PardakhteDarkhasteKartEntity entity) {
		pardakhteDarkhasteKartDao.create(entity);
	}

	public Boolean createDarkhastHa(PardakhtEntity pardakht) {
		List<PardakhteDarkhasteKartEntity> list = getAllByPardakht(pardakht);
		if (list == null || list.size() == 0)
			return false;
		for (PardakhteDarkhasteKartEntity entity : list) {
			KartEntity kart = kartBiz.creatNewKartOrAlmosana(entity);
			DarkhasteKartEntity darkhast = darkhastKartBiz.create(entity, kart);
			rahgiriBiz.createForDarkhasteKart(darkhast, pardakht);
		}
		return true;
	}
	
}
