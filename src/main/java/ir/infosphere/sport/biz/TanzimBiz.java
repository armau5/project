package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.TanzimDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TanzimBiz {
	@SuppressWarnings("unused")
	private TanzimDao tanzimDao;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(TanzimBiz.class);


	public void setTanzimDao(TanzimDao tanzimDao) {
		this.tanzimDao = tanzimDao;
	}

	
	//TODO:Check below
//	/**
//	 * Retrieve list of TanzimatBeans of all Tanzim entities.
//	 * 
//	 * @return
//	 */
//	@Transactional
//	public List<TanzimatBean> retrieveAllTanzimatBeans() {
//		logger.debug(LoggerUtil.log("retrieveAllTanzimatBeans"));
//
//		List<TanzimatBean> list = new ArrayList<TanzimatBean>();
//		for (TanzimEntity tanzimEntity : tanzimDao.retrieveAll()) {
//			list.add(new TanzimatBean(tanzimEntity));
//		}
//		return list;
//	}
//
//	/**
//	 * Set value of a Tanzim entity.
//	 * 
//	 * @param id
//	 *            the id of Tanzim entity
//	 * @param value
//	 *            the velue to set
//	 */
//	@Transactional
//	public void setValue(Integer id, String value) {
//		logger.debug(LoggerUtil.log("setValue",id,value));
//
//		TanzimEntity tanzimEntity = tanzimDao.retrieveByID(id);
//		tanzimEntity.setMeghdar(value);
//		tanzimDao.update(tanzimEntity);
//	}
//
//	/**
//	 * Get value of a Tanzim entity.
//	 * 
//	 * @param name
//	 * @return the value
//	 */
//	@Transactional
//	public String getValue(String name) {
//		logger.debug(LoggerUtil.log("getValue",name));
//
//		return tanzimDao.retrieveByProperty("name", name).getMeghdar();
//	}
//
//	/**
//	 * Retrieve list of TanzimBeans of all Tanzim entities.
//	 * 
//	 * @return
//	 */
//	@Transactional
//	public List<TanzimBean> retrieveAllTanzimBeans() {
//		logger.debug(LoggerUtil.log("retrieveAllTanzimBeans"));
//
//		List<TanzimBean> list = new ArrayList<TanzimBean>();
//		for (TanzimEntity entity : tanzimDao.retrieveAll()) {
//			list.add(new TanzimBean(entity));
//		}
//		return list;
//	}
}
