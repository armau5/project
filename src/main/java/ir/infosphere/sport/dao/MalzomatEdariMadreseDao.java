package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.MalzomatEdariMadreseEntity;

import org.springframework.stereotype.Component;

@Component
public class MalzomatEdariMadreseDao extends BaseDao<MalzomatEdariMadreseEntity, Integer> {

	@Override
	protected Class<MalzomatEdariMadreseEntity> getEntityType() {
		return MalzomatEdariMadreseEntity.class;
	}

}