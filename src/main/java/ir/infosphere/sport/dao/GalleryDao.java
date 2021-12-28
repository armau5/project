package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.GalleryEntity;

import org.springframework.stereotype.Component;

@Component
public class GalleryDao extends BaseDao<GalleryEntity, Integer> {
	
	@Override
	protected Class<GalleryEntity> getEntityType() {
		return GalleryEntity.class;
	}
}