package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.BakhshEntity;

@Component
public class BakhshDao extends BaseDao<BakhshEntity, Integer> {

	@Override
	protected Class<BakhshEntity> getEntityType() {
		return BakhshEntity.class;
	}

}
