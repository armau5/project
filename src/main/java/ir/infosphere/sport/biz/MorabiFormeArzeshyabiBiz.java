package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.MorabiFormeArzeshyabiDao;
import ir.infosphere.sport.entity.FormeArzeshyabiMadaresEntity;
import ir.infosphere.sport.entity.MorabiFormeArzeshyabiEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MorabiFormeArzeshyabiBiz {
	@Autowired
	private MorabiFormeArzeshyabiDao morabiFormeArzeshyabiDao;
	
	@Transactional
	public List<MorabiFormeArzeshyabiEntity> getAllMorabiFormeArzeshyabies(){
		List<MorabiFormeArzeshyabiEntity> list = morabiFormeArzeshyabiDao.retrieveAll();
		return list;
	}
	
	@Transactional
	public List<MorabiFormeArzeshyabiEntity> retrieveMorabiFormeArzeshyabiByCriteria(DetachedCriteria criteria) {
		List<MorabiFormeArzeshyabiEntity> list = morabiFormeArzeshyabiDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public MorabiFormeArzeshyabiEntity retrieveMorabiFormeArzeshyabiById(Integer id) {
		return morabiFormeArzeshyabiDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(MorabiFormeArzeshyabiEntity morabi) {
		morabiFormeArzeshyabiDao.create(morabi);
	}
	
	@Transactional
	public void update(MorabiFormeArzeshyabiEntity morabi) {
		morabiFormeArzeshyabiDao.update(morabi);
	}
	
	@Transactional
	public void deleteMorabiFormeArzeshyabi(Integer id) {
		morabiFormeArzeshyabiDao.delete(morabiFormeArzeshyabiDao.retrieveByID(id));
	}
	
	@Transactional
	public List<MorabiFormeArzeshyabiEntity> getAllMorabiByFormeArzeshyabi(FormeArzeshyabiMadaresEntity formeArzeshyabi) {
		if (formeArzeshyabi == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(MorabiFormeArzeshyabiEntity.class);
		criteria.add(Restrictions.eq("formeArzeshyabi", formeArzeshyabi));
		List<MorabiFormeArzeshyabiEntity> list = morabiFormeArzeshyabiDao.retrieveAllByCriteria(criteria);
		return list;
	}
}
