package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.SoaleAmniatiEntity;

@Component
public class SoaleAmniatiDao extends BaseDao<SoaleAmniatiEntity, Short> {

	@Override
	protected Class<SoaleAmniatiEntity> getEntityType() {
		return SoaleAmniatiEntity.class;
	}

}
