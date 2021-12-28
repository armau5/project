package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.TakhfifeKartEntity;

import org.springframework.stereotype.Component;

@Component
public class TakhfifeKartDao extends BaseDao<TakhfifeKartEntity, Integer> {

	@Override
	protected Class<TakhfifeKartEntity> getEntityType() {
		return TakhfifeKartEntity.class;
	}

}