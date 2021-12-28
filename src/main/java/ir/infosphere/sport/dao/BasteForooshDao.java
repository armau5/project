package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.BasteForooshEntity;

import org.springframework.stereotype.Component;

@Component
public class BasteForooshDao extends BaseDao<BasteForooshEntity, Integer> {

	@Override
	protected Class<BasteForooshEntity> getEntityType() {
		return BasteForooshEntity.class;
	}

}
