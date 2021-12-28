package ir.infosphere.sport.biz;


import ir.infosphere.sport.dao.LogeVaziateKartDao;
import ir.infosphere.sport.entity.DarkhasteKartEntity;
import ir.infosphere.sport.entity.LogeVaziateKartEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LogeVaziateKartBiz {

	@Autowired
	LogeVaziateKartDao logeVaziateKartDao;
	@Autowired
	KodBiz kodBiz;

	@Transactional
	public void createLogeVaziateKartForFaalSazi(DarkhasteKartEntity changedDarkhast) {
		LogeVaziateKartEntity logeVaziat = new LogeVaziateKartEntity(
				changedDarkhast);
		logeVaziateKartDao.create(logeVaziat);
	}

}
