package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.TozihDao;
import ir.infosphere.sport.entity.TozihEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TozihBiz {
	@Autowired
	private TozihDao tozihDao;

	@Transactional
	public void create(TozihEntity entity) {
		tozihDao.create(entity);
	}
	
	@Transactional
	public void update(TozihEntity entity) {
		tozihDao.update(entity);
	}
}
