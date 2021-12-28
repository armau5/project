package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.SanseOzviyyatEntity;

import org.springframework.stereotype.Component;

@Component
public class SanseOzviyyatDao extends BaseDao<SanseOzviyyatEntity, Integer> {

	@Override
	protected Class<SanseOzviyyatEntity> getEntityType() {
		return SanseOzviyyatEntity.class;
	}

}
