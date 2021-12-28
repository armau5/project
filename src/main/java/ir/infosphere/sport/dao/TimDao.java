package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.TimEntity;

@Component
public class TimDao extends BaseDao<TimEntity, Short> {

	@Override
	protected Class<TimEntity> getEntityType() {
		return TimEntity.class;
	}

}
