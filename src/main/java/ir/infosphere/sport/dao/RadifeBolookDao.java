package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.RadifeBolookEntity;

@Component
public class RadifeBolookDao extends BaseDao<RadifeBolookEntity, Integer> {

	@Override
	protected Class<RadifeBolookEntity> getEntityType() {
		return RadifeBolookEntity.class;
	}

}
