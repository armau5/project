package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.NahiyeDao;
import ir.infosphere.sport.entity.NahiyeEntity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class NahiyeBiz {

	@Autowired
	private NahiyeDao nahiyeDao;

	@Transactional
	public List<String> getAllOstan() {
		DetachedCriteria criteria = DetachedCriteria.forClass(NahiyeEntity.class);
		criteria.add(Restrictions.isNull("valed"));
		criteria.addOrder(Order.asc("nameNahiye"));
		List<NahiyeEntity> list = nahiyeDao.retrieveAllByCriteria(criteria);
		List<String> names = new ArrayList<String>();
		for (NahiyeEntity entity : list)
			names.add(entity.getNameNahiye());
		return names;
	}

	@Transactional
	public NahiyeEntity getOstan(String ostanName) {
		if (ostanName == null || ostanName.equals(""))
			return null;
		DetachedCriteria ostanCriteria = DetachedCriteria.forClass(NahiyeEntity.class);
		ostanCriteria.add(Restrictions.eq("nameNahiye", ostanName));
		ostanCriteria.add(Restrictions.isNull("valed"));
		List<NahiyeEntity> ostanList = nahiyeDao.retrieveAllByCriteria(ostanCriteria);
		if (ostanList.size() != 1)
			throw new IllegalArgumentException();
		return ostanList.get(0);
	}
	
	public List<NahiyeEntity> getAllByOstan(String ostanName){
		DetachedCriteria ostanCriteria = DetachedCriteria.forClass(NahiyeEntity.class);
		ostanCriteria.add(Restrictions.eq("nameNahiye", ostanName));
		ostanCriteria.add(Restrictions.isNull("valed"));
		List<NahiyeEntity> allList = nahiyeDao.retrieveAllByCriteria(ostanCriteria);
		
		
		NahiyeEntity ostanEntity = getOstan(ostanName);
		
		DetachedCriteria criteria = DetachedCriteria.forClass(NahiyeEntity.class);
		criteria.add(Restrictions.eq("valed", ostanEntity));
		criteria.addOrder(Order.asc("nameNahiye"));
		List<NahiyeEntity> list = nahiyeDao.retrieveAllByCriteria(criteria);
	//	getAllShahrestan(ostanName);
    	allList.addAll(list);
    	
    	for(NahiyeEntity nahiye : list){
    		NahiyeEntity shahrestanEntity = getShahrestan(ostanName, nahiye.getNameNahiye());
    		DetachedCriteria shahrCriteria = DetachedCriteria.forClass(NahiyeEntity.class);
    		shahrCriteria.add(Restrictions.eq("valed", shahrestanEntity));
    		allList.addAll(nahiyeDao.retrieveAllByCriteria(shahrCriteria));
    	}
    	
		return allList;
	}
	
	public List<NahiyeEntity> getAllByShahrestan(String ostanName, String shahrestanName) {
		
		NahiyeEntity shahrestanEntity = getShahrestan(ostanName, shahrestanName);
		DetachedCriteria shahrCriteria = DetachedCriteria.forClass(NahiyeEntity.class);
		shahrCriteria.add(Restrictions.eq("valed", shahrestanEntity));
		return nahiyeDao.retrieveAllByCriteria(shahrCriteria);
	}

	@Transactional
	public List<String> getAllShahrestan(String ostanName) {
		if (ostanName == null || ostanName.equals(""))
			return null;
		NahiyeEntity ostanEntity = getOstan(ostanName);
		if (ostanEntity == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(NahiyeEntity.class);
		criteria.add(Restrictions.eq("valed", ostanEntity));
		criteria.addOrder(Order.asc("nameNahiye"));
		List<NahiyeEntity> list = nahiyeDao.retrieveAllByCriteria(criteria);
		List<String> names = new ArrayList<String>();
		for (NahiyeEntity entity : list){
			names.add(entity.getNameNahiye());
	//		System.out.println(entity.getId());
		}
		return names;
	}

	@Transactional
	public NahiyeEntity getShahrestan(String ostanName, String shahrestanName) {
		if (ostanName == null || shahrestanName == null)
			return null;
		NahiyeEntity ostanEntity = getOstan(ostanName);
		if (ostanEntity == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(NahiyeEntity.class);
		criteria.add(Restrictions.eq("valed", ostanEntity));
		criteria.add(Restrictions.eq("nameNahiye", shahrestanName));
		criteria.addOrder(Order.asc("nameNahiye"));
		List<NahiyeEntity> list = nahiyeDao.retrieveAllByCriteria(criteria);
		if (list.size() != 1)
			return null;
		return list.get(0);
	}

	@Transactional
	public List<NahiyeEntity> getAllShahr(String ostanName, String shahrestanName) {
		if (ostanName == null || ostanName.equals("") || shahrestanName == null || shahrestanName.equals(""))
			return null;
		NahiyeEntity shahrestanEntity = getShahrestan(ostanName, shahrestanName);
		if (shahrestanEntity == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(NahiyeEntity.class);
		criteria.add(Restrictions.eq("valed", shahrestanEntity));
		return nahiyeDao.retrieveAllByCriteria(criteria);
	}
	
	

	@Transactional
	public NahiyeEntity getNahiyeById(Integer id) {
		return nahiyeDao.retrieveByID(id);
	}

	@Transactional
	public NahiyeEntity getShahr(String ostanName, String shahrestanName, String shahrName) {
		if (ostanName == null || shahrestanName == null || shahrName == null)
			return null;
		NahiyeEntity ostanEntity = getOstan(ostanName);
		if (ostanEntity == null)
			return null;
		NahiyeEntity shahrestanEntity = getShahrestan(ostanName, shahrestanName);
		if (shahrestanEntity == null)
			return null;

		DetachedCriteria criteria = DetachedCriteria.forClass(NahiyeEntity.class);
		criteria.add(Restrictions.eq("valed", shahrestanEntity));
		criteria.add(Restrictions.eq("nameNahiye", shahrName));
		criteria.addOrder(Order.asc("nameNahiye"));
		List<NahiyeEntity> list = nahiyeDao.retrieveAllByCriteria(criteria);
		if (list.size() != 1)
			throw new IllegalArgumentException();
		return list.get(0);
	}
	
	
	
	@Transactional
	public NahiyeEntity getNahiyeByValed(NahiyeEntity valed, String name){
		if(valed == null){
		//	System.out.println(name);
			DetachedCriteria ostanCriteria = DetachedCriteria.forClass(NahiyeEntity.class);
			ostanCriteria.add(Restrictions.eq("nameNahiye", name));
			ostanCriteria.add(Restrictions.isNull("valed"));
			List<NahiyeEntity> list = nahiyeDao.retrieveAllByCriteria(ostanCriteria);
			System.out.println(list.size());
			if(list.size()== 0){
				return null;
			}
			return list.get(0);
		
		}
		else{
			DetachedCriteria criteria = DetachedCriteria.forClass(NahiyeEntity.class);
			criteria.add(Restrictions.eq("valed", valed));
			criteria.add(Restrictions.eq("nameNahiye", name));
			criteria.addOrder(Order.asc("valed"));
			List<NahiyeEntity> list = nahiyeDao.retrieveAllByCriteria(criteria);
			if(list.size() == 0){
				return null;
			}
			else{
				return list.get(0);
			}
		}
	}
	
	@Transactional
	public void changeServiceActivation(Integer id) {
		NahiyeEntity nahiye = getNahiyeById(id);
		nahiye.setGheyreFaal(!nahiye.getGheyreFaal());
		nahiyeDao.update(nahiye);
	}

	@Transactional
	public void deleteNahiye(Integer id) {
		nahiyeDao.delete(getNahiyeById(id));
	}

	@Transactional
	public void createNahiye(String name, NahiyeEntity valed) {
		NahiyeEntity nahiye = new NahiyeEntity();
		nahiye.setNameNahiye(name);
		nahiye.setValed(valed);
		nahiye.setGheyreFaal(false);
		nahiyeDao.create(nahiye);
	}

	@Transactional
	public void editNahiye(Integer id, String name) {
		NahiyeEntity nahiye = getNahiyeById(id);
		nahiye.setNameNahiye(name);
		nahiyeDao.update(nahiye);
	}

	
}
