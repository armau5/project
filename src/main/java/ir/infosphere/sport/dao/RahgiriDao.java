package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.RahgiriEntity;

@Component
public class RahgiriDao extends BaseDao<RahgiriEntity, Long> {

	@Override
	protected Class<RahgiriEntity> getEntityType() {
		return RahgiriEntity.class;
	}

}
