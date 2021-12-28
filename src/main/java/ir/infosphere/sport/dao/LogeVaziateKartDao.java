package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.LogeVaziateKartEntity;

@Component
public class LogeVaziateKartDao extends BaseDao<LogeVaziateKartEntity, Long> {

	@Override
	protected Class<LogeVaziateKartEntity> getEntityType() {
		return LogeVaziateKartEntity.class;
	}

}
