package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.KarbaranePortalEntity;

import org.springframework.stereotype.Component;

@Component
public class KarbaranePortalDao extends BaseDao<KarbaranePortalEntity, Short> {

	@Override
	protected Class<KarbaranePortalEntity> getEntityType() {
		return KarbaranePortalEntity.class;
	}

}