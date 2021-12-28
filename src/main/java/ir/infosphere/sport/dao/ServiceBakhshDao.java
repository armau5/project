package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.ServiceBakhshEntity;

@Component
public class ServiceBakhshDao extends BaseDao<ServiceBakhshEntity, Integer> {

	@Override
	protected Class<ServiceBakhshEntity> getEntityType() {
		return ServiceBakhshEntity.class;
	}

}
