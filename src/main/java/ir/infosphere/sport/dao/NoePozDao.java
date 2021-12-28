package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.NoePozEntity;

@Component
public class NoePozDao extends BaseDao<NoePozEntity, Short> {

	@Override
	protected Class<NoePozEntity> getEntityType() {
		return NoePozEntity.class;
	}
}
