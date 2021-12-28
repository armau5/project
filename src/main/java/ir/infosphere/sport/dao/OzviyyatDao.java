package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.OzviyyatEntity;

import org.springframework.stereotype.Component;

@Component
public class OzviyyatDao extends BaseDao<OzviyyatEntity, Integer> {

	@Override
	protected Class<OzviyyatEntity> getEntityType() {
		return OzviyyatEntity.class;
	}

}
