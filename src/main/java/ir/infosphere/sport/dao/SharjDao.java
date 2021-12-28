package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.SharjEntity;

@Component
public class SharjDao extends BaseDao<SharjEntity, Long> {

	@Override
	protected Class<SharjEntity> getEntityType() {
		return SharjEntity.class;
	}

}