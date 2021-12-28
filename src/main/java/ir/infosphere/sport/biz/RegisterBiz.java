package ir.infosphere.sport.biz;

import ir.infosphere.sport.bean.UserBean;
import ir.infosphere.sport.entity.OzvEntity;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterBiz {
//	@PersistenceContext
//	EntityManager entityManager;
	
	@Autowired
	NahiyeBiz nahiyeBiz;
	
	public void registerUser(UserBean bean) {
		OzvEntity entity = new OzvEntity();
		entity.setName(bean.getName());
		entity.setFamil(bean.getFamily());
		entity.setKodeMeli(bean.getNationalCode());
		entity.setTarikheTavallod((Date) bean.getBirthday());
//		entity.setJensiat(jensiat)
		entity.setNahiye(nahiyeBiz.getOstan(bean.getOstan()));
//		entity.set
	}
}
