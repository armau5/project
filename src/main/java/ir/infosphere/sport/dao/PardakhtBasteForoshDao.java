package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.PardakhtBasteForoshEntity;;

@Component
public class PardakhtBasteForoshDao extends BaseDao<PardakhtBasteForoshEntity, Integer> {

	@Override
	protected Class<PardakhtBasteForoshEntity> getEntityType() {
		return PardakhtBasteForoshEntity.class;
	}

}
