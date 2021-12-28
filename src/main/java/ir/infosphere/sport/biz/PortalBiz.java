package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.PortalDao;
import ir.infosphere.sport.entity.PortalEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PortalBiz {
	@Autowired
	private PortalDao portalDao;
	
	@Transactional
	public PortalEntity retrievePortalById(Long id) {
		return portalDao.retrieveByID(id);
	}

	@Transactional
	public PortalEntity getPortalByDomain(String domain) {
		return portalDao.retrieveByProperty("damane", domain);
	}
}
