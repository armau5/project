package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.TarikheSansEntity;

@Component
public class TarikheSansDao extends BaseDao<TarikheSansEntity, Integer> {

	@Override
	protected Class<TarikheSansEntity> getEntityType() {
		return TarikheSansEntity.class;
	}

}
