package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.KodEntity;

import org.springframework.stereotype.Component;

@Component
public class KodDao extends BaseDao<KodEntity, Short> {

	@Override
	protected Class<KodEntity> getEntityType() {
		return KodEntity.class;
	}

}
