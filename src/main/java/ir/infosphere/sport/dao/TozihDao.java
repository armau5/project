package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.TozihEntity;

@Component
public class TozihDao extends BaseDao<TozihEntity, Integer> {

	@Override
	protected Class<TozihEntity> getEntityType() {
		return TozihEntity.class;
	}

}
