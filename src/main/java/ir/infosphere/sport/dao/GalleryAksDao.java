package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.GalleryAksEntity;

import org.springframework.stereotype.Component;

@Component
public class GalleryAksDao extends BaseDao<GalleryAksEntity, Integer> {

	@Override
	protected Class<GalleryAksEntity> getEntityType() {
		return GalleryAksEntity.class;
	}

}
