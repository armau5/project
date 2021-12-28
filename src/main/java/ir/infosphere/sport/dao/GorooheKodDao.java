package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.GorooheKodEntity;

@Component
public class GorooheKodDao extends BaseDao<GorooheKodEntity, Short> {

	@Override
	protected Class<GorooheKodEntity> getEntityType() {
		return GorooheKodEntity.class;
	}

}
