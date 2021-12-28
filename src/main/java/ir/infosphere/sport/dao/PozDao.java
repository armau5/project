package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.PozEntity;

@Component
public class PozDao extends BaseDao<PozEntity, Integer> {

	@Override
	protected Class<PozEntity> getEntityType() {
		return PozEntity.class;
	}
}
