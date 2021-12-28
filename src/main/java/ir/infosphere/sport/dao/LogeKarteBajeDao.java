package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.LogeKarteBajeEntity;

import org.springframework.stereotype.Component;

@Component
public class LogeKarteBajeDao extends BaseDao<LogeKarteBajeEntity, Long> {

	@Override
	protected Class<LogeKarteBajeEntity> getEntityType() {
		return LogeKarteBajeEntity.class;
	}

}
