package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.VoroodiEntity;

import org.springframework.stereotype.Component;

@Component
public class VoroodiDao extends BaseDao<VoroodiEntity, Integer> {

	@Override
	protected Class<VoroodiEntity> getEntityType() {
		return VoroodiEntity.class;
	}
	

}
