package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.VarzeshgahEntity;

@Component
public class VarzeshgahDao extends BaseDao<VarzeshgahEntity, Short> {

	@Override
	protected Class<VarzeshgahEntity> getEntityType() {
		return VarzeshgahEntity.class;
	}

}
