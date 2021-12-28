package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.SansEntity;

@Component
public class SansDao extends BaseDao<SansEntity, Integer> {

	@Override
	protected Class<SansEntity> getEntityType() {
		return SansEntity.class;
	}

}
