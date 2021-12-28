package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.NoePozDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NoePozBiz {
	@SuppressWarnings("unused")
	private NoePozDao noePozDao;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(KodBiz.class);
	

	public void setNoePozDao(NoePozDao noePozDao) {
		this.noePozDao = noePozDao;
	}
	
	//TODO: Check below

//	@Transactional
//	public List<NoePozBean> retrieveListeNoePoz() {
//		logger.debug(LoggerUtil.log("retrieveListeNoePoz"));
//		
//		List<NoePozEntity> noePozList = noePozDao.retrieveAll();
//		List<NoePozBean> noePozBeanList = new ArrayList<NoePozBean>();
//		for (NoePozEntity noePoz : noePozList) {
//			noePozBeanList.add(new NoePozBean(noePoz));
//		}
//		return noePozBeanList;
//	}
//
//	@Transactional
//	public String retrieveMasireFileUpdateForPozModel(String model) {
//		logger.debug(LoggerUtil.log("retrieveMasireFileUpdateForPozModel",model));
//
//		NoePozEntity noePoz = (NoePozEntity) noePozDao.retrieveByProperty("model", model);
//		return noePoz.getMasireFileUpdate();
//	}
}
