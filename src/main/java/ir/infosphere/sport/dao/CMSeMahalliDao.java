package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.CMSeMahalliEntity;

@Component
public class CMSeMahalliDao extends BaseDao<CMSeMahalliEntity	, Integer> {

	@Override
	protected Class<CMSeMahalliEntity> getEntityType() {
		return CMSeMahalliEntity.class;
	}
	
	

}
