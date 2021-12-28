package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.NoePardakhtEntity;;

@Component
public class NoePardakhtDao extends BaseDao<NoePardakhtEntity, Integer> {

	@Override
	protected Class<NoePardakhtEntity> getEntityType() {
		return NoePardakhtEntity.class;
	}

}