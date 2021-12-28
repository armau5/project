package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.DarkhasteKartEntity;

@Component
public class DarkhastKartDao extends BaseDao<DarkhasteKartEntity, Long> {

	@Override
	protected Class<DarkhasteKartEntity> getEntityType() {
		return DarkhasteKartEntity.class;
	}

}
