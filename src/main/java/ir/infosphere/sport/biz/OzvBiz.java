package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.OzvDao;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.util.FormatUtil;
import ir.infosphere.sport.util.PermissionUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.image.AImage;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;

@Component
public class OzvBiz {
	@Autowired
	private OzvDao ozvDao;

	public OzvEntity authenticate(String userName, String password) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OzvEntity.class);
		criteria.add(Restrictions.like("nameKarbari", userName));
		criteria.add(Restrictions.like("hasheRamzeOboor", getHash(password)));
		List<OzvEntity> list = ozvDao.retrieveAllByCriteria(criteria);
		if (!list.isEmpty())
			return list.get(0);
		return null;
	}

	
	@Transactional
	public void update(OzvEntity entity) {
		ozvDao.update(entity);
	}

	public String getHash(String plain) {
		MessageDigest algorithm = null;
		try {
			algorithm = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
		InputStream is = new ByteArrayInputStream(plain.getBytes());
		DigestInputStream dis = new DigestInputStream(is, algorithm);
		try {
			while (dis.read() != -1)
				;
		} catch (IOException ex) {
		}
		byte[] hash = algorithm.digest();
		return formatUtil.asHex(hash);
	}

	
}
