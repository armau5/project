package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.GoroohhayeKarbarEntity;

@Component
public class GoroohhayeKarbarDao extends BaseDao<GoroohhayeKarbarEntity, Integer> {

	@Override
	protected Class<GoroohhayeKarbarEntity> getEntityType() {
		return GoroohhayeKarbarEntity.class;
	}

}
