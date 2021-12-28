package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.TarakonesheMaliEntity;

@Component
public class TarakonesheMaliDao extends BaseDao<TarakonesheMaliEntity, Long> {

	@Override
	protected Class<TarakonesheMaliEntity> getEntityType() {
		return TarakonesheMaliEntity.class;
	}

}
