package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.VaziateZaherMorabiMadreseEntity;

import org.springframework.stereotype.Component;

@Component
public class VaziateZaherMorabiMadreseDao extends BaseDao<VaziateZaherMorabiMadreseEntity, Integer> {

	@Override
	protected Class<VaziateZaherMorabiMadreseEntity> getEntityType() {
		return VaziateZaherMorabiMadreseEntity.class;
	}

}