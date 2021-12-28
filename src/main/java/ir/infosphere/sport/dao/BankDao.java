package ir.infosphere.sport.dao;

import ir.infosphere.sport.entity.BankEntity;

import org.springframework.stereotype.Component;

@Component
public class BankDao extends BaseDao<BankEntity, Short> {

	@Override
	protected Class<BankEntity> getEntityType() {
		return BankEntity.class;
	}

}
