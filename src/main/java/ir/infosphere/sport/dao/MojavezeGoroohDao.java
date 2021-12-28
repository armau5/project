package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.MojavezeGoroohEntity;;

@Component
public class MojavezeGoroohDao extends BaseDao<MojavezeGoroohEntity, Short> {

	@Override
	protected Class<MojavezeGoroohEntity> getEntityType() {
		return MojavezeGoroohEntity.class;
	}

}
