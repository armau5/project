package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.ServiceHayeTakhfifeServiceEntity;

@Component
public class ServiceHayeTakhfifeServiceDao extends BaseDao<ServiceHayeTakhfifeServiceEntity, Integer> {

	@Override
	protected Class<ServiceHayeTakhfifeServiceEntity> getEntityType() {
		return ServiceHayeTakhfifeServiceEntity.class;
	}

}
