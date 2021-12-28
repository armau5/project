package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.EstefadeTakhfifEntity;

import org.springframework.stereotype.Component;

@Component
public class EstefadeTakhfifDao extends BaseDao<EstefadeTakhfifEntity, Integer> {
	
	@Override
	protected Class<EstefadeTakhfifEntity> getEntityType() {
		return EstefadeTakhfifEntity.class;
	}
}