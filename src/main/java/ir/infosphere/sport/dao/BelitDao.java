package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.BelitEntity;

@Component
public class BelitDao extends BaseDao<BelitEntity, Long> {

	@Override
	protected Class<BelitEntity> getEntityType() {
		return BelitEntity.class;
	}

}
