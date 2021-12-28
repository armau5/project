package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.HesabEntity;

@Component
public class HesabDao extends BaseDao<HesabEntity, Short> {

	@Override
	protected Class<HesabEntity> getEntityType() {
		return HesabEntity.class;
	}
}
