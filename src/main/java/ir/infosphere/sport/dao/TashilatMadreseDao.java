package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.TashilatMadreseEntity;

import org.springframework.stereotype.Component;

@Component
public class TashilatMadreseDao extends BaseDao<TashilatMadreseEntity, Integer> {

	@Override
	protected Class<TashilatMadreseEntity> getEntityType() {
		return TashilatMadreseEntity.class;
	}

}