package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.PardakhtBelitEntity;

import org.springframework.stereotype.Component;

@Component
public class PardakhtBelitDao extends BaseDao<PardakhtBelitEntity, Long> {

	@Override
	protected Class<PardakhtBelitEntity> getEntityType() {
		return PardakhtBelitEntity.class;
	}

}