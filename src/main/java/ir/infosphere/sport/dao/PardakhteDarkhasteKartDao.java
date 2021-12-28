package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.PardakhteDarkhasteKartEntity;

import org.springframework.stereotype.Component;

@Component
public class PardakhteDarkhasteKartDao extends BaseDao<PardakhteDarkhasteKartEntity, Integer> {

	@Override
	protected Class<PardakhteDarkhasteKartEntity> getEntityType() {
		return PardakhteDarkhasteKartEntity.class;
	}

}
