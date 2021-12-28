package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.MojavezEntity;

@Component
public class MojavezDao extends BaseDao<MojavezEntity, Integer> {

	@Override
	protected Class<MojavezEntity> getEntityType() {
		return MojavezEntity.class;
	}

}
