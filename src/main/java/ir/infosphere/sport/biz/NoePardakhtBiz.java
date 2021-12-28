package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.NoePardakhtDao;
import ir.infosphere.sport.entity.NoePardakhtEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class NoePardakhtBiz {
	@Autowired
	private NoePardakhtDao noePardakhtDao;
	
	@Transactional
	public List<NoePardakhtEntity> getAllNoePardakht(){
		List<NoePardakhtEntity> list = noePardakhtDao.retrieveAll();
		return list;
	}
	
	@Transactional
	public NoePardakhtEntity getNoePardakhtById(Integer id) {
		return noePardakhtDao.retrieveByID(id);
	}
}
