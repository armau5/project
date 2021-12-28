package ir.infosphere.sport.dao;


import ir.infosphere.sport.entity.VoroodieBolookEntity;

import org.springframework.stereotype.Component;

@Component
public class VoroodieBolookDao extends BaseDao<VoroodieBolookEntity, Integer> {

	@Override
	protected Class<VoroodieBolookEntity> getEntityType() {
		return VoroodieBolookEntity.class;
	}

}
