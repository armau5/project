package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.TakhfifeServiceEntity;

import org.springframework.stereotype.Component;

@Component
public class TakhfifeServiceDao extends BaseDao<TakhfifeServiceEntity, Integer> {

	@Override
	protected Class<TakhfifeServiceEntity> getEntityType() {
		return TakhfifeServiceEntity.class;
	}

}