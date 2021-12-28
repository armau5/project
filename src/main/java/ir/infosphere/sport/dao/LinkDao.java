package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.LinkEntity;

@Component
public class LinkDao extends BaseDao<LinkEntity, Integer> {

	@Override
	protected Class<LinkEntity> getEntityType() {
		return LinkEntity.class;
	}

}