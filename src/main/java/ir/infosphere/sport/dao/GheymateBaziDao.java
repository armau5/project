package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.GheymateBaziEntity;

@Component
public class GheymateBaziDao extends BaseDao<GheymateBaziEntity, Integer> {

	@Override
	protected Class<GheymateBaziEntity> getEntityType() {
		return GheymateBaziEntity.class;
	}

}
