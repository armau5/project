package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.TimhayeDoreyeMosabeghatDao;
import ir.infosphere.sport.entity.DoreyeMosabeghatEntity;
import ir.infosphere.sport.entity.TimEntity;
import ir.infosphere.sport.entity.TimhayeDoreyeMosabeghatEntity;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TimhayeDoreyeMosabeghatBiz {
	@Autowired
	private TimhayeDoreyeMosabeghatDao timhayeDoreyeMosabeghatDao;

	@Transactional
	public TimhayeDoreyeMosabeghatEntity getTimhayeDoreyeMosabeghat(
			TimEntity tim, DoreyeMosabeghatEntity doreyeMosabeghat) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(TimhayeDoreyeMosabeghatEntity.class);
		criteria.add(Restrictions.eq("tim", tim));
		criteria.add(Restrictions.eq("doreyeMosabeghat", doreyeMosabeghat));
		List<TimhayeDoreyeMosabeghatEntity> result = timhayeDoreyeMosabeghatDao
				.retrieveAllByCriteria(criteria);
		if (result.size() == 0)
			return null;
		return result.get(0);
	}

	@Transactional
	public void create(TimEntity tim, DoreyeMosabeghatEntity doreyeMosabeghat) {
		TimhayeDoreyeMosabeghatEntity entity = new TimhayeDoreyeMosabeghatEntity();
		entity.setDoreyeMosabeghat(doreyeMosabeghat);
		entity.setTim(tim);
		timhayeDoreyeMosabeghatDao.create(entity);
	}

}
