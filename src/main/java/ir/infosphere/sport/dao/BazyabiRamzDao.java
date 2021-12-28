package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.BazyabiRamzEntity;

import org.springframework.stereotype.Component;

@Component
public class BazyabiRamzDao extends BaseDao<BazyabiRamzEntity, Integer> {

	@Override
	protected Class<BazyabiRamzEntity> getEntityType() {
		return BazyabiRamzEntity.class;
	}
}
