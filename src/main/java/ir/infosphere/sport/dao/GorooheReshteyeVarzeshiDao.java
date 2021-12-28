package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.GorooheReshteyeVarzeshiEntity;

@Component
public class GorooheReshteyeVarzeshiDao extends BaseDao<GorooheReshteyeVarzeshiEntity, Integer> {

	@Override
	protected Class<GorooheReshteyeVarzeshiEntity> getEntityType() {
		return GorooheReshteyeVarzeshiEntity.class;
	}

}
