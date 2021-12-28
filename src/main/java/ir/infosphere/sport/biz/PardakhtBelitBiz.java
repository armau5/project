package ir.infosphere.sport.biz;

import ir.infosphere.sport.bean.PardakhtBean;
import ir.infosphere.sport.dao.PardakhtBelitDao;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.PardakhtBelitEntity;
import ir.infosphere.sport.entity.PardakhtEntity;
import ir.infosphere.sport.entity.VaziateSandaliEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PardakhtBelitBiz {
	@Autowired
	private PardakhtBelitDao pardakhtBelitDao;
	@Autowired
	private KodBiz kodBiz;
	@Autowired
	private PardakhtBiz pardakhtBiz;
	@Autowired
	private VaziateSandaliBiz vaziateSandaliBiz;
	@Autowired
	private RahgiriBiz rahgiriBiz;

	@Transactional
	public List<PardakhtBelitEntity> retrievePardakhtBelitByCriteria(DetachedCriteria criteria) {
		List<PardakhtBelitEntity> list = pardakhtBelitDao.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public PardakhtBean createPardakhtBelit(OzvEntity ozv, Integer totalPrice, List<VaziateSandaliEntity> vaziatList) {
		KodEntity dastebandiPardakht = kodBiz.getKodEntity(Constants.DasteBandiPardakht,
				Constants.DasteBandiPardakht_Bilit);

		PardakhtEntity pardakhtEntity = pardakhtBiz.createNewPardakht(dastebandiPardakht, ozv, totalPrice);

		for (VaziateSandaliEntity item : vaziatList) {
			PardakhtBelitEntity entity = new PardakhtBelitEntity();
			entity.setPardakht(pardakhtEntity);
			entity.setVaziateSandali(item);
			create(entity);
		}
		return new PardakhtBean(pardakhtEntity);
	}

	@Transactional
	public PardakhtBelitEntity retrievePardakhtBelitById(Long id) {
		return pardakhtBelitDao.retrieveByID(id);
	}

	@Transactional
	public void create(PardakhtBelitEntity pardakhtBelit) {
		pardakhtBelitDao.create(pardakhtBelit);
	}

	@Transactional
	public void update(PardakhtBelitEntity pardakhtBelit) {
		pardakhtBelitDao.update(pardakhtBelit);
	}

	@Transactional
	public void delete(Long id) {
		pardakhtBelitDao.delete(pardakhtBelitDao.retrieveByID(id));
	}

	public Boolean isPardakhtValid(PardakhtEntity pardakht) {
		List<PardakhtBelitEntity> list = getAllByPardakht(pardakht);
		if (list == null || list.size() == 0) {
			return false;
		}

		for (PardakhtBelitEntity pardakhtBelit : list) {
			try {
				vaziateSandaliBiz.deleteEventForRemove(pardakhtBelit.getVaziateSandali());
			} catch (Exception e) {
			}
		}

		for (PardakhtBelitEntity pardakhtBelit : list) {
			if (!vaziateSandaliBiz.isValidForPardakht(pardakhtBelit)) {
				return false;
			}
		}

		return true;

	}

	@Transactional
	private List<PardakhtBelitEntity> getAllByPardakht(PardakhtEntity pardakht) {
		if (pardakht == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(PardakhtBelitEntity.class);
		criteria.add(Restrictions.eq("pardakht", pardakht));
		return pardakhtBelitDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<PardakhtBelitEntity> getAllByPardakht(PardakhtBean pardakht) {
		if (pardakht == null)
			return null;
		PardakhtEntity entity = pardakhtBiz.retrieveById(pardakht.getId());
		return getAllByPardakht(entity);
	}

	@Transactional
	public Boolean createBilitHa(PardakhtEntity pardakht) {
		List<PardakhtBelitEntity> list = getAllByPardakht(pardakht);
		if (list == null || list.size() == 0)
			return false;

		for (PardakhtBelitEntity entity : list) {
			vaziateSandaliBiz.nahayiSazieBelit(entity.getVaziateSandali());
			try {
				rahgiriBiz.createForBelit(entity.getVaziateSandali(), pardakht, list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			rahgiriBiz.createForKarbarKharidar(pardakht, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Transactional
	public void cancelBilitHa(PardakhtEntity pardakht) {
		List<PardakhtBelitEntity> list = getAllByPardakht(pardakht);
		if (list == null || list.size() == 0)
			return;
		for (PardakhtBelitEntity entity : list) {
			vaziateSandaliBiz.cancelReservedBelit(entity.getVaziateSandali());
		}
	}

}
