package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.DoreyeMosabeghatDao;
import ir.infosphere.sport.entity.DoreyeMosabeghatEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DoreyeMosabeghatBiz {

	@Autowired
	private DoreyeMosabeghatDao doreyeMosabeghatDao;

	@Transactional
	public List<DoreyeMosabeghatEntity> getAllDoreyeMosabeghat() {
		return doreyeMosabeghatDao.retrieveAll();
	}
	
	@Transactional
	public List<DoreyeMosabeghatEntity> retrieveDoreyeMosabeghat(DetachedCriteria criteria) {
		List<DoreyeMosabeghatEntity> list = doreyeMosabeghatDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public DoreyeMosabeghatEntity getDoreyeMosabeghatById(Integer id) {
		return doreyeMosabeghatDao.retrieveByID(id);
	}

}
