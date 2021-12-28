package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.LogePozDao;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.OzviyyatEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LogePozBiz {
	@Autowired
	private LogePozDao logePozDao;
	
	@Transactional
	public Long getAllUsed(OzviyyatEntity ozviyyat, KodEntity kasreOzv) {
		return logePozDao.getAllUsed(ozviyyat, kasreOzv);
	}
}
