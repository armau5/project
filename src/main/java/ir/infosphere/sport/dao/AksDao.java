package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.AksEntity;

import org.springframework.stereotype.Component;

@Component
public class AksDao extends BaseDao<AksEntity, Integer> {

	@Override
	protected Class<AksEntity> getEntityType() {
		return AksEntity.class;
	}

}
