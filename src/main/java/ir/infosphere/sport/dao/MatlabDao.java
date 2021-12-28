package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.MatlabEntity;;

@Component
public class MatlabDao extends BaseDao<MatlabEntity, Integer> {

	@Override
	protected Class<MatlabEntity> getEntityType() {
		return MatlabEntity.class;
	}

}