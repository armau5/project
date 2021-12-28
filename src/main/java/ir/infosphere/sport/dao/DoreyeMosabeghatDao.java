package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.DoreyeMosabeghatEntity;

@Component
public class DoreyeMosabeghatDao extends BaseDao<DoreyeMosabeghatEntity, Integer> {

	@Override
	protected Class<DoreyeMosabeghatEntity> getEntityType() {
		return DoreyeMosabeghatEntity.class;
	}
}
