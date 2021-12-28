package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.SanadetasviyehesabDao;
import ir.infosphere.sport.entity.FaktorEntity;
import ir.infosphere.sport.entity.HesabEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.SanadetasviyehesabEntity;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SanadetasviyehesabBiz {
	
	@Autowired
	private SanadetasviyehesabDao sanadetasviyehesabDao;
	
	@Transactional
	public List<SanadetasviyehesabEntity> retrieveAllByCriteria(
			DetachedCriteria criteria) {
		return sanadetasviyehesabDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public void createSanadeTasviye( HesabEntity hesab,KodEntity noeTasviye,String shomareSanad,FaktorEntity faktor,
			Date tarikheSanad,Date tarikheTasviye,Long mablagheMande,Long mablagheTasvieh){
		SanadetasviyehesabEntity sanad=new SanadetasviyehesabEntity();
		sanad.setHesab(hesab);
		sanad.setMablagheMande(mablagheMande);
		sanad.setMablagheTasvieh(mablagheTasvieh);
		sanad.setNoeTasviye(noeTasviye);
		sanad.setShomareSanad(shomareSanad);
		sanad.setTarikheSanad(tarikheSanad);
		sanad.setTarikheTasviye(tarikheTasviye);
		sanad.setFaktorTasviye(faktor);
		sanadetasviyehesabDao.create(sanad);
		
	}
	

}
