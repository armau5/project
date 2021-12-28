package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.PortalEntity;;

@Component
public class PortalDao extends BaseDao<PortalEntity, Long> {

	@Override
	protected Class<PortalEntity> getEntityType() {
		return PortalEntity.class;
	}

}