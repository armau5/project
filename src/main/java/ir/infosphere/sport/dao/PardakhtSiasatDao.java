package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.PardakhtSiasatEntity;;

@Component
public class PardakhtSiasatDao extends BaseDao<PardakhtSiasatEntity, Integer> {

	@Override
	protected Class<PardakhtSiasatEntity> getEntityType() {
		return PardakhtSiasatEntity.class;
	}

}
