package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.DarkhasteBeliteSazmaniEntity;

@Component
public class DarkhasteBeliteSazmaniDao extends
		BaseDao<DarkhasteBeliteSazmaniEntity, Integer> {

	@Override
	protected Class<DarkhasteBeliteSazmaniEntity> getEntityType() {
		return DarkhasteBeliteSazmaniEntity.class;
	}

}
