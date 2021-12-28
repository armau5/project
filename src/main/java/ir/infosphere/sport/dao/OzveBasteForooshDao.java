package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.OzveBasteForooshEntity;

import org.springframework.stereotype.Component;

@Component
public class OzveBasteForooshDao extends BaseDao<OzveBasteForooshEntity, Integer> {

	@Override
	protected Class<OzveBasteForooshEntity> getEntityType() {
		return OzveBasteForooshEntity.class;
	}

}
