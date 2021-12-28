package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.SanadetasviyehesabEntity;

@Component
public class SanadetasviyehesabDao extends BaseDao<SanadetasviyehesabEntity, Integer>{

	@Override
	protected Class<SanadetasviyehesabEntity> getEntityType() {
		return SanadetasviyehesabEntity.class;
	}

}
