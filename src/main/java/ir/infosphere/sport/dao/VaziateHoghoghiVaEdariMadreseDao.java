package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.VaziateHoghoghiVaEdariMadreseEntity;

import org.springframework.stereotype.Component;

@Component
public class VaziateHoghoghiVaEdariMadreseDao extends BaseDao<VaziateHoghoghiVaEdariMadreseEntity, Integer> {

	@Override
	protected Class<VaziateHoghoghiVaEdariMadreseEntity> getEntityType() {
		return VaziateHoghoghiVaEdariMadreseEntity.class;
	}

}