package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.SiasatTakhfifEntity;

import org.springframework.stereotype.Component;

@Component
public class SiasatTakhfifDao extends BaseDao<SiasatTakhfifEntity, Integer> {

	@Override
	protected Class<SiasatTakhfifEntity> getEntityType() {
		return SiasatTakhfifEntity.class;
	}

}
