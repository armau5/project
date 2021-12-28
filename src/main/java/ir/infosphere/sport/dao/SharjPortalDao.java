package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.SharjPortalEntity;

@Component
public class SharjPortalDao extends BaseDao<SharjPortalEntity, Integer> {

	@Override
	protected Class<SharjPortalEntity> getEntityType() {
		return SharjPortalEntity.class;
	}

}