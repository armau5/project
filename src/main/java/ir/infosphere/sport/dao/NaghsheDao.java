package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.NaghsheEntity;

import org.springframework.stereotype.Component;

@Component
public class NaghsheDao extends BaseDao<NaghsheEntity, Integer> {
	
	@Override
	protected Class<NaghsheEntity> getEntityType() {
		return NaghsheEntity.class;
	}
}