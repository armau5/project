package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.TimhayeDoreyeMosabeghatEntity;

import org.springframework.stereotype.Component;

@Component
public class TimhayeDoreyeMosabeghatDao extends
		BaseDao<TimhayeDoreyeMosabeghatEntity, Integer> {

	@Override
	protected Class<TimhayeDoreyeMosabeghatEntity> getEntityType() {
		return TimhayeDoreyeMosabeghatEntity.class;
	}

}
