package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.BaziEntity;

@Component
public class BaziDao extends BaseDao<BaziEntity, Integer> {

	@Override
	protected Class<BaziEntity> getEntityType() {
		return BaziEntity.class;
	}

}
