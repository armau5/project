package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.PardakhtSharjEntity;;

@Component
public class PardakhtSharjDao extends BaseDao<PardakhtSharjEntity, Long> {

	@Override
	protected Class<PardakhtSharjEntity> getEntityType() {
		return PardakhtSharjEntity.class;
	}

}
