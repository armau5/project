package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.MatlabAksEntity;

@Component
public class MatlabAksDao extends BaseDao<MatlabAksEntity, Integer> {

	@Override
	protected Class<MatlabAksEntity> getEntityType() {
		return MatlabAksEntity.class;
	}

}