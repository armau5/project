package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.GorooheKarbariEntity;

@Component
public class GorooheKarbariDao extends BaseDao<GorooheKarbariEntity, Short> {

	@Override
	protected Class<GorooheKarbariEntity> getEntityType() {
		return GorooheKarbariEntity.class;
	}

}
