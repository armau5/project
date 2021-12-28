package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.EstedadMadreseEntity;

import org.springframework.stereotype.Component;

@Component
public class EstedadMadreseDao extends BaseDao<EstedadMadreseEntity, Integer> {

	@Override
	protected Class<EstedadMadreseEntity> getEntityType() {
		return EstedadMadreseEntity.class;
	}

}