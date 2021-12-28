package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.MogheiyyatEntity;

@Component
public class MogheiyyatDao extends BaseDao<MogheiyyatEntity, Integer> {

	@Override
	protected Class<MogheiyyatEntity> getEntityType() {
		return MogheiyyatEntity.class;
	}

}
