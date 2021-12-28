package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.NoeMatlabEntity;

@Component
public class NoeMatlabDao extends BaseDao<NoeMatlabEntity, Integer> {

	@Override
	protected Class<NoeMatlabEntity> getEntityType() {
		return NoeMatlabEntity.class;
	}

}
