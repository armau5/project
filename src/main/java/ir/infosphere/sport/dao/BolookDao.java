package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.BolookEntity;

@Component
public class BolookDao extends BaseDao<BolookEntity, Short> {

	@Override
	protected Class<BolookEntity> getEntityType() {
		return BolookEntity.class;
	}

}
