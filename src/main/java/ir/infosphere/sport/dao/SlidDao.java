package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.SlidEntity;

@Component
public class SlidDao extends BaseDao<SlidEntity, Integer>{

	@Override
	protected Class<SlidEntity> getEntityType() {
		return SlidEntity.class;
	}

}
