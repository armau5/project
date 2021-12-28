package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.SiasatEntity;

@Component
public class SiasatDao extends BaseDao<SiasatEntity, Integer> {

	@Override
	protected Class<SiasatEntity> getEntityType() {
		return SiasatEntity.class;
	}

}