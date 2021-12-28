package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.GalleryDao;
import ir.infosphere.sport.entity.GalleryEntity;
import ir.infosphere.sport.entity.MadreseEntity;
import ir.infosphere.sport.entity.NahiyeEntity;
import ir.infosphere.sport.util.PermissionUtil;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GalleryBiz {
	@Autowired
	private GalleryDao galleryDao;
	
	@Transactional
	public List<GalleryEntity> getAllGalleryPortal(){
		DetachedCriteria criteria = DetachedCriteria.forClass(GalleryEntity.class);
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		criteria.add(Restrictions.isNull("ostan"));
		criteria.add(Restrictions.isNull("madrese"));
		List<GalleryEntity> list = retrieveGallery(criteria);
		return list;
	}
	
	@Transactional
	public List<GalleryEntity> getAllGalleryByOstan(NahiyeEntity ostan){
		DetachedCriteria criteria = DetachedCriteria.forClass(GalleryEntity.class);
		if(ostan==null){
			return null;
		}
		criteria.add(Restrictions.eq("ostan", ostan));
		criteria.add(Restrictions.isNull("madrese"));
		List<GalleryEntity> list = retrieveGallery(criteria);
		return list;
	}
	
	@Transactional
	public List<GalleryEntity> getLastGallery(){
		DetachedCriteria criteria = DetachedCriteria.forClass(GalleryEntity.class);
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		criteria.add(Restrictions.isNull("ostan"));
		criteria.add(Restrictions.isNull("madrese"));
		criteria.addOrder(Order.desc("id"));
		List<GalleryEntity> list =  galleryDao.retrieveAllByCriteria(criteria, 0, 3);
		return list;
	}
	
	@Transactional
	public List<GalleryEntity> retrieveGallery(DetachedCriteria criteria) {
		List<GalleryEntity> list = galleryDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public GalleryEntity retrieveGalleryById(Integer id) {
		return galleryDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(GalleryEntity gallery) {
		galleryDao.create(gallery);
	}
	
	@Transactional
	public void update(GalleryEntity gallery) {
		galleryDao.update(gallery);
	}
	
	
	@Transactional
	public void delete(Integer id) {
		galleryDao.delete(galleryDao.retrieveByID(id));
	}
	
	@Transactional
	public List<GalleryEntity> getAllGalleryByMadrese(MadreseEntity madrese) {
		if (madrese == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(GalleryEntity.class);
		criteria.add(Restrictions.eq("madrese", madrese));
		List<GalleryEntity> list = galleryDao.retrieveAllByCriteria(criteria);
		return list;
	}
}
