package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.EmkanatMadreseEntity;

import org.springframework.stereotype.Component;

@Component
public class EmkanatMadreseDao extends BaseDao<EmkanatMadreseEntity, Integer> {

	@Override
	protected Class<EmkanatMadreseEntity> getEntityType() {
		return EmkanatMadreseEntity.class;
	}

}