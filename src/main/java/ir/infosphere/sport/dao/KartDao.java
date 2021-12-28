package ir.infosphere.sport.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.KartEntity;

@Component
public class KartDao extends BaseDao<KartEntity, Long> {

	@Override
	protected Class<KartEntity> getEntityType() {
		return KartEntity.class;
	}

	public List<KartEntity> getGheyreFaalKart() {
		DetachedCriteria criteria = DetachedCriteria.forClass(KartEntity.class);
		criteria.add(Restrictions.eq("faal", Boolean.FALSE));
		criteria.add(Restrictions.eq("masdood", Boolean.FALSE));
		return retrieveAllByCriteria(criteria);
	}

}
