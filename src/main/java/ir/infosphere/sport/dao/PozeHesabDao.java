package ir.infosphere.sport.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.PozeHesabEntity;

@Component
public class PozeHesabDao extends BaseDao<PozeHesabEntity, Integer> {

	@Override
	protected Class<PozeHesabEntity> getEntityType() {
		return PozeHesabEntity.class;
	}

	public List<PozeHesabEntity> retrieveAllPozeCms(Integer shenaseyeCMS) {
		DetachedCriteria criteria = DetachedCriteria.forClass(PozeHesabEntity.class);
		criteria.createAlias("poz", "poz");
		criteria.add(Restrictions.eq("poz.shenaseyeCMS", shenaseyeCMS));
		List<PozeHesabEntity> entities = this.retrieveAllByCriteria(criteria);
		return entities;
	}

}
