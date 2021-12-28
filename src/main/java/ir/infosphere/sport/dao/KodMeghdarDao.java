package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.KodMeghdarEntity;

import org.springframework.stereotype.Component;

@Component
public class KodMeghdarDao extends BaseDao<KodMeghdarEntity, Integer> {

	@Override
	protected Class<KodMeghdarEntity> getEntityType() {
		return KodMeghdarEntity.class;
	}

}
