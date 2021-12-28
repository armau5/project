package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.ZaminTamrinMadreseEntity;

import org.springframework.stereotype.Component;

@Component
public class ZaminTamrinMadreseDao extends BaseDao<ZaminTamrinMadreseEntity, Integer> {

	@Override
	protected Class<ZaminTamrinMadreseEntity> getEntityType() {
		return ZaminTamrinMadreseEntity.class;
	}

}