package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.VaziateBolookEntity;

@Component
public class VaziateBolookDao extends BaseDao<VaziateBolookEntity, Integer> {

	@Override
	protected Class<VaziateBolookEntity> getEntityType() {
		return VaziateBolookEntity.class;
	}

}
