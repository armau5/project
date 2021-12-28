package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.RadeFootballAmozanMadreseEntity;

import org.springframework.stereotype.Component;

@Component
public class RadeFootballAmozanMadreseDao extends BaseDao<RadeFootballAmozanMadreseEntity, Integer> {

	@Override
	protected Class<RadeFootballAmozanMadreseEntity> getEntityType() {
		return RadeFootballAmozanMadreseEntity.class;
	}

}