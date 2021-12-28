package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.SanseServiceEntity;

@Component
public class SanseServiceDao extends BaseDao<SanseServiceEntity, Integer> {

	@Override
	protected Class<SanseServiceEntity> getEntityType() {
		return SanseServiceEntity.class;
	}

}
