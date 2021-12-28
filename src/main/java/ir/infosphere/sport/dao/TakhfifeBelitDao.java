package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.TakhfifeBelitEntity;

import org.springframework.stereotype.Component;

@Component
public class TakhfifeBelitDao extends BaseDao<TakhfifeBelitEntity, Integer> {

	@Override
	protected Class<TakhfifeBelitEntity> getEntityType() {
		return TakhfifeBelitEntity.class;
	}

}