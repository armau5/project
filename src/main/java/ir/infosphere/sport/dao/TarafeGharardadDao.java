package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.TarafeGharardadEntity;

import org.springframework.stereotype.Component;

@Component
public class TarafeGharardadDao extends BaseDao<TarafeGharardadEntity, Short> {

	@Override
	protected Class<TarafeGharardadEntity> getEntityType() {
		return TarafeGharardadEntity.class;
	}

}
