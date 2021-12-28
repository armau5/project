package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.VaziateRadifeBolookEntity;

@Component
public class VaziateRadifeBolookDao extends BaseDao<VaziateRadifeBolookEntity, Integer> {

	@Override
	protected Class<VaziateRadifeBolookEntity> getEntityType() {
		return VaziateRadifeBolookEntity.class;
	}

}
