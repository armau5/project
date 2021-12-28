package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.TarakonesheHesabEntity;

import org.springframework.stereotype.Component;

@Component
public class TarakonesheHesabDao extends BaseDao<TarakonesheHesabEntity, Long>{
	
	@Override
	protected Class<TarakonesheHesabEntity> getEntityType() {
		return TarakonesheHesabEntity.class;
	}
}
