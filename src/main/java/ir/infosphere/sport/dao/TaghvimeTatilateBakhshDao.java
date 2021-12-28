package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.TaghvimeTatilateBakhshEntity;

@Component
public class TaghvimeTatilateBakhshDao extends BaseDao<TaghvimeTatilateBakhshEntity, Integer> {

	@Override
	protected Class<TaghvimeTatilateBakhshEntity> getEntityType() {
		return TaghvimeTatilateBakhshEntity.class;
	}

}
