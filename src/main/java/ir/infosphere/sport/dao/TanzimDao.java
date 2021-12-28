package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.TanzimEntity;

@Component
public class TanzimDao extends BaseDao<TanzimEntity, Integer>{

	@Override
	protected Class<TanzimEntity> getEntityType() {
		return TanzimEntity.class;
	}

}
