package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.FormeArzeshyabiMadaresEntity;

import org.springframework.stereotype.Component;

@Component
public class FormeArzeshyabiMadreseDao extends BaseDao<FormeArzeshyabiMadaresEntity, Integer> {

	@Override
	protected Class<FormeArzeshyabiMadaresEntity> getEntityType() {
		return FormeArzeshyabiMadaresEntity.class;
	}

}