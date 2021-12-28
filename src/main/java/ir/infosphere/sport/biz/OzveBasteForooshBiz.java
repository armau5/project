package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.OzveBasteForooshDao;
import ir.infosphere.sport.entity.BasteForooshEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.OzveBasteForooshEntity;
import ir.infosphere.sport.entity.PardakhtBasteForoshEntity;
import ir.infosphere.sport.entity.PardakhtEntity;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OzveBasteForooshBiz {
	@Autowired
	private OzveBasteForooshDao ozveBasteForooshDao;
	@Autowired
	private PardakhtBasteForoshBiz pardakhtBasteForoshBiz;
	@Autowired
	private RahgiriBiz rahgiriBiz;
	
	@Transactional
	public List<OzveBasteForooshEntity> getAllAzayeHameyeBasteHa() {
		List<OzveBasteForooshEntity> list = ozveBasteForooshDao.retrieveAll();
		return list;
	}

	@Transactional
	public List<OzveBasteForooshEntity> getAllOzvByBasteForosh(
			BasteForooshEntity basteForosh) {
		if (basteForosh == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(OzveBasteForooshEntity.class);
		criteria.add(Restrictions.eq("basteForoosh", basteForosh));
		criteria.addOrder(Order.asc("tarikhKharid"));
		return ozveBasteForooshDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<OzveBasteForooshEntity> getAllBasteHayeOzv(OzvEntity ozv) {
		if (ozv == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(OzveBasteForooshEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		return ozveBasteForooshDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<OzveBasteForooshEntity> retrieveByCriteria(
			DetachedCriteria criteria) {
		List<OzveBasteForooshEntity> list = ozveBasteForooshDao
				.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public OzveBasteForooshEntity retrieveById(Integer id) {
		return ozveBasteForooshDao.retrieveByID(id);
	}

	@Transactional
	public void update(OzveBasteForooshEntity entity) {
		ozveBasteForooshDao.update(entity);
	}

	@Transactional
	public void delete(Integer id) {
		ozveBasteForooshDao.delete(retrieveById(id));
	}

	@Transactional
	public void create(OzveBasteForooshEntity entity) {
		ozveBasteForooshDao.create(entity);
	}
	
	@Transactional
	public boolean createForPardakht(PardakhtEntity pardakht) {
		try {
			List<PardakhtBasteForoshEntity> list = pardakhtBasteForoshBiz.getForPardakht(pardakht);
			for (PardakhtBasteForoshEntity pardakhtBasteForoshEntity : list) {
				OzveBasteForooshEntity ozveBasteForoosh = new OzveBasteForooshEntity();
				ozveBasteForoosh.setBasteForoosh(pardakhtBasteForoshEntity.getBasteForosh());
				ozveBasteForoosh.setOzv(pardakhtBasteForoshEntity.getOzv());
				ozveBasteForoosh.setTarikhKharid(new Date());
				create(ozveBasteForoosh);
				
				try {
					rahgiriBiz.createForBasteForosh(ozveBasteForoosh, pardakht);
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			return true;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
