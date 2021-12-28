package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.ReshteyeVarzeshiEntity;

@Component
public class ReshteyeVarzeshiDao extends BaseDao<ReshteyeVarzeshiEntity, Short>{

	@Override
	protected Class<ReshteyeVarzeshiEntity> getEntityType() {
		return ReshteyeVarzeshiEntity.class;
	}

}
