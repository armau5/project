package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.KarbaranePortalDao;
import ir.infosphere.sport.entity.KarbaranePortalEntity;
import ir.infosphere.sport.entity.PortalEntity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class KarbaranePortalBiz {
	@Autowired
	private KarbaranePortalDao karbaranePortalDao;

	@Transactional
	public KarbaranePortalEntity retrieveById(Short id) {
		return karbaranePortalDao.retrieveByID(id);
	}

	@Transactional
	public List<PortalEntity> retrieveByPortal(PortalEntity portal) {
		List<KarbaranePortalEntity> list = karbaranePortalDao
				.retrieveAllByProperty("portal", portal);
		List<PortalEntity> portals = new ArrayList<PortalEntity>();
		for (KarbaranePortalEntity karbaranePortal : list) {
			portals.add(karbaranePortal.getPortaleKarbaran());
		}
		return portals;
	}

}
