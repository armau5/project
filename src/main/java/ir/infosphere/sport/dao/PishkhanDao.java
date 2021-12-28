package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.PishkhanEntity;

import org.springframework.stereotype.Component;

@Component
public class PishkhanDao extends BaseDao<PishkhanEntity, Integer> {

	@Override
	protected Class<PishkhanEntity> getEntityType() {
		return PishkhanEntity.class;
	}
}
