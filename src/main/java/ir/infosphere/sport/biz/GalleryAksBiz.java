package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.GalleryAksDao;
import ir.infosphere.sport.entity.GalleryAksEntity;
import ir.infosphere.sport.entity.GalleryEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GalleryAksBiz {
	@Autowired
	private GalleryAksDao galleryAksDao;

	@Transactional
	public List<GalleryAksEntity> getAllAkshayeGalleryHa() {
		List<GalleryAksEntity> list = galleryAksDao.retrieveAll();
		return list;
	}

	@Transactional
	public List<GalleryAksEntity> getAllAksHayeGallery(
			GalleryEntity gallery) {
		if (gallery == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(GalleryAksEntity.class);
		criteria.add(Restrictions.eq("gallery", gallery));
		criteria.addOrder(Order.desc("id"));
		return galleryAksDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public GalleryAksEntity getDefaultAksGallery(GalleryEntity gallery) {
		if (gallery == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(GalleryAksEntity.class);
		criteria.add(Restrictions.eq("gallery", gallery));
		criteria.addOrder(Order.desc("id"));
		List<GalleryAksEntity> temp = galleryAksDao.retrieveAllByCriteria(criteria);
		if (temp.size() > 0)
			return temp.get(0);
		else
			return null;
	}


	@Transactional
	public List<GalleryAksEntity> retrieveByCriteria(
			DetachedCriteria criteria) {
		List<GalleryAksEntity> list = galleryAksDao
				.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public GalleryAksEntity retrieveById(Integer id) {
		return galleryAksDao.retrieveByID(id);
	}

	@Transactional
	public void update(GalleryAksEntity entity) {
		galleryAksDao.update(entity);
	}

	@Transactional
	public void delete(Integer id) {
		galleryAksDao.delete(retrieveById(id));
	}

	@Transactional
	public void create(GalleryAksEntity entity) {
		galleryAksDao.create(entity);
	}
}
