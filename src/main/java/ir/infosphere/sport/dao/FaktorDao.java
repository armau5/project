package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.FaktorEntity;

import org.springframework.stereotype.Component;

@Component
public class FaktorDao extends BaseDao<FaktorEntity, Integer> {
	
	@Override
	protected Class<FaktorEntity> getEntityType() {
		return FaktorEntity.class;
	}
}
