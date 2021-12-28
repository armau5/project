package ir.infosphere.sport.biz;

import ir.infosphere.sport.bean.ForoosheKartReportBean;
import ir.infosphere.sport.bean.ForoosheKarteGoroohiReportBean;
import ir.infosphere.sport.dao.PardakhtDao;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.PardakhtEntity;
import ir.infosphere.sport.util.dargah.PardakhtUtil;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PardakhtBiz {
	@Autowired
	private PardakhtDao pardakhtDao;

	@Transactional
	public List<PardakhtEntity> getAllPardakhtHa() {
		List<PardakhtEntity> list = pardakhtDao.retrieveAll();
		return list;
	}

	@Transactional
	public List<PardakhtEntity> getAllByOzv(OzvEntity ozv) {
		if (ozv == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(PardakhtEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		return pardakhtDao.retrieveAllByCriteria(criteria);
	}

	@Transactional
	public List<PardakhtEntity> getLastPardakhtsByOzv(OzvEntity ozv,
			Integer number) {
		if (ozv == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(PardakhtEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		criteria.addOrder(Order.desc("id"));
		return pardakhtDao.retrieveAllByCriteria(criteria, 0, number);
	}

	@Transactional
	public List<PardakhtEntity> getMovafaghByOzvVaZaman(OzvEntity ozv,
			Date start, Date end) {
		if (ozv == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(PardakhtEntity.class);
		criteria.add(Restrictions.eq("ozv", ozv));
		criteria.add(Restrictions.eq("halatePardakht",
				PardakhtUtil.transactionOK));
		if (start != null)
			criteria.add(Restrictions.ge("tarikhZaman", start));
		if (end != null)
			criteria.add(Restrictions.le("tarikhZaman", end));
		return pardakhtDao.retrieveAllByCriteria(criteria);
	}

	// public PardakhtEntity getFromPaymentBean(PaymentBean payment)
	// {
	// PardakhtEntity pardakht = new PardakhtEntity();
	// pardakht.setId(payment.getId());
	// pardakht.setAdreseBargasht(payment.getAdreseBargasht());
	// pardakht.setDargah(payment.getDargah());
	// pardakht.setDastebandi(payment.getDastebandi());
	// pardakht.setHalatePardakht(payment.getHalatePardakht());
	// pardakht.setKodeRahgiri(payment.getKodeRahgiri());
	// pardakht.setMablagh(payment.getMablagh());
	// pardakht.setOzv(payment.getOzv());
	// pardakht.setPortal(payment.getPortal());
	// pardakht.setTarikhZaman(payment.getTarikhZaman());
	// return pardakht;
	// }

	@Transactional
	public List<PardakhtEntity> retrieveByCriteria(DetachedCriteria criteria) {
		List<PardakhtEntity> list = pardakhtDao.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public PardakhtEntity retrieveById(Integer id) {
		return pardakhtDao.retrieveByID(id);
	}

	@Transactional
	public void update(PardakhtEntity entity) {
		pardakhtDao.update(entity);
	}

	@Transactional
	public void delete(Integer id) {
		pardakhtDao.delete(retrieveById(id));
	}

	@Transactional
	public void create(PardakhtEntity entity) {
		pardakhtDao.create(entity);
	}

	@Transactional
	public PardakhtEntity createNewPardakht(KodEntity dastebandiPardakht,
			OzvEntity ozv, Integer Mablagh) {
		PardakhtEntity pardakht = new PardakhtEntity();
		pardakht.setDargah(null);
		pardakht.setDastebandi(dastebandiPardakht);
		pardakht.setMablagh(Mablagh);
		pardakht.setMablaghErsaliBeBank(Mablagh);
		pardakht.setTakhfif(0);
		pardakht.setOzv(ozv);
		pardakht.setTarikhZaman(new Date());
		pardakht.setKodeRahgiri(null);
		pardakht.setHalatePardakht(PardakhtUtil.transactionStarted);
		pardakhtDao.create(pardakht);
		return pardakht;
	}

	@Transactional
	public void setHalatePardakht(Integer id, Short halatePardakht) {
		PardakhtEntity pardakht = pardakhtDao.retrieveByID(id);
		if (pardakht == null)
			return;
		pardakht.setHalatePardakht(halatePardakht);
		pardakhtDao.update(pardakht);
	}

	@Transactional
	public void setHalatePardakhtAndRahgiri(Integer id, Short halatePardakht,
			String kodeRahgiri) {
		PardakhtEntity pardakht = pardakhtDao.retrieveByID(id);
		if (pardakht == null)
			return;
		pardakht.setHalatePardakht(halatePardakht);
		pardakht.setKodeRahgiri(kodeRahgiri);
		pardakhtDao.update(pardakht);
	}

	@Transactional
	public List<ForoosheKartReportBean> getForoosheKartReport(
			Long shenaseyePortal, Short shenaseyeKodeForoosheKart,
			Date azTarikh, Date taTarikh) {
		return pardakhtDao.getForoosheKartReport(shenaseyePortal,
				shenaseyeKodeForoosheKart, azTarikh, taTarikh);
	}

	@Transactional
	public List<ForoosheKarteGoroohiReportBean> getForoosheKarteGoroohiReport(
			Long shenaseyePortal, Short shenaseyeKodeForoosheKarteGoroohi,
			Date azTarikh, Date taTarikh) {
		return pardakhtDao.getForoosheKarteGoroohiReport(shenaseyePortal,
				shenaseyeKodeForoosheKarteGoroohi, azTarikh, taTarikh);
	}

}
