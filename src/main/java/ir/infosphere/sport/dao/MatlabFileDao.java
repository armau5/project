package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.MatlabFileEntity;

import org.springframework.stereotype.Component;

@Component
public class MatlabFileDao extends BaseDao<MatlabFileEntity, Integer> {

	@Override
	protected Class<MatlabFileEntity> getEntityType() {
		return MatlabFileEntity.class;
	}

}