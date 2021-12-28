package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.MorabiFormeArzeshyabiEntity;

import org.springframework.stereotype.Component;

@Component
public class MorabiFormeArzeshyabiDao extends BaseDao<MorabiFormeArzeshyabiEntity, Integer> {
	
	@Override
	protected Class<MorabiFormeArzeshyabiEntity> getEntityType() {
		return MorabiFormeArzeshyabiEntity.class;
	}
}
