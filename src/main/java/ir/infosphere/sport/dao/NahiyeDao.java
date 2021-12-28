package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.NahiyeEntity;

@Component
public class NahiyeDao extends BaseDao<NahiyeEntity, Integer>{

	@Override
	protected Class<NahiyeEntity> getEntityType() {
		return NahiyeEntity.class;
	}

}
