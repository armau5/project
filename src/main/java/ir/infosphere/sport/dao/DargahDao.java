package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.DargahEntity;

import org.springframework.stereotype.Component;

@Component
public class DargahDao extends BaseDao<DargahEntity, Integer> {

	@Override
	protected Class<DargahEntity> getEntityType() {
		return DargahEntity.class;
	}

}
