package ir.infosphere.sport.dao;

import org.springframework.stereotype.Component;

import ir.infosphere.sport.entity.FileZamimeEntity;

@Component
public class FileZamimeDao extends BaseDao<FileZamimeEntity, Integer> {

	@Override
	protected Class<FileZamimeEntity> getEntityType() {
		return FileZamimeEntity.class;
	}

}