package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.OzvEntity;

import org.springframework.stereotype.Component;

@Component
public class OzvDao extends BaseDao<OzvEntity, Integer> {

	@Override
	protected Class<OzvEntity> getEntityType() {
		return OzvEntity.class;
	}

}
