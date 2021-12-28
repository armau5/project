package ir.infosphere.sport.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.LogePozEntity;
import ir.infosphere.sport.entity.OzviyyatEntity;

@Component
public class LogePozDao extends BaseDao<LogePozEntity, Integer> {

	@Override
	protected Class<LogePozEntity> getEntityType() {
		return LogePozEntity.class;
	}

	public Date getLastUsed(OzviyyatEntity ozviyyat, KodEntity kasreOzv) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(LogePozEntity.class);
		criteria.add(Restrictions.eq("shenaseyeOzviyyat", ozviyyat));
		criteria.add(Restrictions.eq("noeTarakonesh", kasreOzv));
		// criteria.add(Restrictions.eq("poz", pos));
		criteria.addOrder(Order.desc("zaman"));
		// TODO:1dune faghat mikham
		List<LogePozEntity> entities = retrieveAllByCriteria(criteria);
		if (entities == null || entities.size() == 0)
			return null;
		Date date = new Date(entities.get(0).getZaman().getTime());
		return date;
	}

	@SuppressWarnings("deprecation")
	public Long getTodayUsed(OzviyyatEntity ozviyyat, KodEntity kasreOzv,
			Date date) {
		// newDate.setYear(date.getYear());
		// newDate.setMonth(date.getMonth());
		// newDate.setDate(date.getDay());
		// newDate.setHours(23);
		// newDate.setMinutes(59);
		// newDate.setSeconds(59);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		DetachedCriteria criteria = DetachedCriteria
				.forClass(LogePozEntity.class);
		criteria.add(Restrictions.eq("shenaseyeOzviyyat", ozviyyat));
		criteria.add(Restrictions.eq("noeTarakonesh", kasreOzv));
		// criteria.add(Restrictions.eq("poz", pos));
		// criteria.add(Restrictions.gt("zaman", date));
		criteria.add(Restrictions.ge("zaman", date));
		criteria.setProjection(Projections.projectionList().add(
				Projections.countDistinct("zaman")));
		// List<LogePozEntity> entities= retrieveAllByCriteria(criteria);
		return getDistinctCountByCriteria(criteria);
	}

	public Long getAllUsed(OzviyyatEntity ozviyyat, KodEntity kasreOzv) {
		// TODO:bug agar bad ye modati 2bare biad sans bekhare!
		DetachedCriteria criteria = DetachedCriteria
				.forClass(LogePozEntity.class);
		criteria.add(Restrictions.eq("ozviyyat", ozviyyat));
		criteria.add(Restrictions.eq("noeTarakonesh", kasreOzv));
		// criteria.add(Restrictions.eq("poz", pos));
		// List<LogePozEntity> entities= retrieveAllByCriteria(criteria);
		criteria.setProjection(Projections.projectionList().add(
				Projections.countDistinct("zaman")));
		return getDistinctCountByCriteria(criteria);
	}

}
