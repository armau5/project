package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.GhalameFaktorEntity;

@Component
public class GhalameFaktorDao extends BaseDao<GhalameFaktorEntity, Integer> {

	@Override
	protected Class<GhalameFaktorEntity> getEntityType() {
		return GhalameFaktorEntity.class;
	}
}
