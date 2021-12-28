package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.DarkhastKartDao;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.DarkhasteKartEntity;
import ir.infosphere.sport.entity.KartEntity;
import ir.infosphere.sport.entity.PardakhteDarkhasteKartEntity;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DarkhastKartBiz {
	@Autowired
	private DarkhastKartDao darkhastKartDao;

	@Autowired
	private KodBiz kodBiz;

	@Transactional
	public List<DarkhasteKartEntity> getAllDarkhastKart() {
		List<DarkhasteKartEntity> list = darkhastKartDao.retrieveAll();
		return list;
	}

	@Transactional
	public List<DarkhasteKartEntity> retrieveByCriteria(
			DetachedCriteria criteria) {
		List<DarkhasteKartEntity> list = darkhastKartDao
				.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public DarkhasteKartEntity getByKart(KartEntity kart) {
		DetachedCriteria criteria = DetachedCriteria.forClass(DarkhasteKartEntity.class);
		criteria.add(Restrictions.eq("kart", kart));
		List<DarkhasteKartEntity> list = darkhastKartDao.retrieveAllByCriteria(criteria, 0, 1);
		if (list.size() > 0) 
			return list.get(0);
		return null;
	}

	@Transactional
	public DarkhasteKartEntity retrieveById(Long id) {
		return darkhastKartDao.retrieveByID(id);
	}

	@Transactional
	public void update(DarkhasteKartEntity entity) {
		darkhastKartDao.update(entity);
	}

	@Transactional
	public void delete(Long id) {
		darkhastKartDao.delete(retrieveById(id));
	}

	@Transactional
	public void create(DarkhasteKartEntity entity) {
		darkhastKartDao.create(entity);
	}

	public DarkhasteKartEntity create(PardakhteDarkhasteKartEntity entity, KartEntity kart) {
		DarkhasteKartEntity darkhast = new DarkhasteKartEntity();
		darkhast.setBayganiShode(Boolean.FALSE);
		darkhast.setKart(kart);
		darkhast.setMarhaleyeSodoor(kodBiz.getKodEntity(
				Constants.MarhaleyeSodoor, Constants.EtebarSanji));
		darkhast.setNahveyeTahvil(entity.getNahveyeTahvil());
		darkhast.setOzv(entity.getOzv());
		darkhast.setPishkhan(entity.getPishkhan());
		darkhast.setDadeyeEzafi(entity.getDadeyeEzafi());
		darkhast.setSazman(entity.getPortal());
		darkhast.setVaziateMarhale(kodBiz.getKodEntity(
				Constants.VaziateMarhale, Constants.BarresiNashode));
		darkhast.setZamaneDarkhast(new Date());
		darkhastKartDao.create(darkhast);
		return darkhast;
	}
}