package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.BaseEntity;

import java.io.Serializable;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.zkoss.zk.ui.Sessions;

@SuppressWarnings({ "unchecked" })
public abstract class BaseDao<T extends BaseEntity<PKType>, PKType extends Serializable>
		extends HibernateDaoSupport {

	private void updateChangedMetaData(T entity, Boolean isUpdate) {
		entity.setNoeTaghir(isUpdate);
		entity.setZamaneAkharinTaghir(new Timestamp(new Date().getTime()));
		try {
			Integer userID = (Integer) Sessions.getCurrent().getAttribute(
					"userID");
			if (userID != null)
				entity.setShenaseyeKarbareTaghirDahande(userID);
		} catch (Exception e) {
			entity.setShenaseyeKarbareTaghirDahande(0);
		}
		try {
			entity.setPortaleTaghirDahandeh(getLongValue(InetAddress
					.getLocalHost().getAddress()));
		} catch (Exception e) {
			entity.setPortaleTaghirDahandeh(0L);
		}
	}

	private Long getLongValue(byte[] bytes) {
		Long val = 0L;
		for (int i = 0; i < bytes.length; i++) {
			val <<= 8;
			val |= bytes[i] & 0xff;
		}
		return val;
	}

	public void create(T entity) {
		updateChangedMetaData(entity, false);
		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	public int batchCreate(String hql) {
		Session session = getHibernateTemplate()
				.getSessionFactory().openSession();
		Query q = session
				.createSQLQuery(hql);
		int ret = q.executeUpdate();
		session.flush();
		session.disconnect();
		session.close();
		return ret;
	}

	public void update(T entity) {
		updateChangedMetaData(entity, true);
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void deleteAll() {
		getHibernateTemplate().deleteAll(retrieveAll());
	}

	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	public void deleteById(PKType id) {
		delete(retrieveByID(id));
	}

	public Long getCountByCriteria(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		return (Long) (getHibernateTemplate().findByCriteria(criteria, 0, 1)
				.get(0));
	}

	public Long getDistinctCountByCriteria(DetachedCriteria criteria) {
		// criteria.setProjection(Projections.rowCount());
		// criteria.setProjection(Projections.distinct(Projections
		// .projectionList().add(Projections.property("zaman"))
		// .add(Projections.property("shenaseyeOzviyyat"))
		// .add(Projections.property("noeTarakonesh"))));
		Long num = (Long) (getHibernateTemplate().findByCriteria(criteria)
				.get(0));
		return num;
	}

	public T retrieveByID(PKType id) {
		return getHibernateTemplate().get(getEntityType(), id);
	}

	public List<T> retrieveAll() {
		List<T> a = getHibernateTemplate().loadAll(getEntityType());
		return a;
	}

	public List<T> retrieveAll(int firstResult, int maxResults) {
		DetachedCriteria criteria = createDetachedCriteria();
		return getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}

	public List<T> retrieveAllByCriteria(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public List<T> retrieveAllByCriteria(DetachedCriteria criteria,
			int firstResult, int maxResults) {
		return getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}

	public List<T> retrieveAllByProperty(String propertyName, Object value) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq(propertyName, value));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public T retrieveByProperty(String propertyName, Object value) {
		List<T> all = retrieveAllByProperty(propertyName, value);
		if (all.size() == 0)
			return null;
		if (all.size() > 1)
			throw new IllegalArgumentException();
		return all.get(0);
	}

	public T retrieveByExample(T object) {
		List<T> all = getHibernateTemplate().findByExample(object, 0, 1);
		if (all.size() == 0)
			return null;
		if (all.size() > 1)
			throw new IllegalArgumentException();
		return all.get(0);

	}

	public List<T> retrieveAllByExample(T object) {
		List<T> resultList = getHibernateTemplate().findByExample(object);
		return resultList;
	}

	public List<T> retrieveAllByExample(T object, int firstResult,
			int maxResults) {
		List<T> resultList = getHibernateTemplate().findByExample(object,
				firstResult, maxResults);
		return resultList;
	}

	protected DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(getEntityType());
	}

	protected abstract Class<T> getEntityType();
}
