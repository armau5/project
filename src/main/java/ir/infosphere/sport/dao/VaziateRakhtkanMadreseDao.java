package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.VaziateRakhtkanMadreseEntity;

import org.springframework.stereotype.Component;

@Component
public class VaziateRakhtkanMadreseDao extends BaseDao<VaziateRakhtkanMadreseEntity, Integer> {

	@Override
	protected Class<VaziateRakhtkanMadreseEntity> getEntityType() {
		return VaziateRakhtkanMadreseEntity.class;
	}

}