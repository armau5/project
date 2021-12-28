package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.SoaleAmniatiDao;
import ir.infosphere.sport.entity.SoaleAmniatiEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SoaleAmniatiBiz {
	@Autowired
	private SoaleAmniatiDao soaleAmniatiDao;

	@Transactional
	public List<SoaleAmniatiEntity> getAllSecurityQuestions() {
		return soaleAmniatiDao.retrieveAll();
	}

	@Transactional
	public SoaleAmniatiEntity getSecurityQuestionById(Short id) {
		return soaleAmniatiDao.retrieveByID(id);
	}

}
