package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.HesabDao;
import ir.infosphere.sport.entity.HesabEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.TarafeGharardadEntity;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HesabBiz {
	@Autowired
	private HesabDao hesabDao;

	
	
	public String generateShomareHesab(TarafeGharardadEntity tarafeGharardad){
		String time=String.valueOf(System.currentTimeMillis());
		String s=tarafeGharardad.getId().toString();
		int t=s.length();
		for (int k = 0; k < 6-t;k++) {
			s="0"+s;
		}
		return s+time;
	}
	
	@Transactional
	public List<HesabEntity> getAllHesabByTarafeGharardad(TarafeGharardadEntity tarafeGharardad, String active) {
		if (tarafeGharardad == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(HesabEntity.class);
		criteria.add(Restrictions.eq("tarafeGharardad", tarafeGharardad));
		if (active.equals("فعال"))
			criteria.add(Restrictions.eq("gheyreFaal", false));
		else if (active.equals("غیرفعال"))
			criteria.add(Restrictions.eq("gheyreFaal", true));
		return hesabDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public HesabEntity getHesabById(Short id) {
		return hesabDao.retrieveByID(id);
	}

	@Transactional
	public void changeServiceActivation(short id) {
		HesabEntity hesab = getHesabById(id);
		hesab.setGheyreFaal(!hesab.getGheyreFaal());
		hesabDao.update(hesab);		
	}

	@Transactional
	public void deleteContract(Short id) {
		hesabDao.delete(hesabDao.retrieveByID(id));		
	}

	@Transactional
	public void createAccount(String sahmeRiali, String sahmeSharj,String sahmeBelit, String sahmeOzviyat,
			OzvEntity ozv, TarafeGharardadEntity tarafeGharardad,String hesabID) {
		HesabEntity hesab = new HesabEntity();
		hesab.setTarafeGharardad(tarafeGharardad);
		hesab.setOzv(ozv);
		hesab.setSahmeOzviyat(new BigDecimal(sahmeOzviyat));
		hesab.setSahmeRiali(new BigDecimal(sahmeRiali));
		hesab.setSahmeSharj(new BigDecimal(sahmeSharj));
		hesab.setSahmeBelit(new BigDecimal(sahmeBelit));
		hesab.setGheyreFaal(false);
		// set shomarehesab
		hesab.setShomareyeHesab(hesabID);
		hesabDao.create(hesab);
	}

	@Transactional
	public void editAccount(short id, String sahmeRiali, String sahmeSharj,	String sahmeBelit, 
			 String sahmeOzviyat,OzvEntity ozv,
			TarafeGharardadEntity tarafeGharardad) {
		HesabEntity hesab = getHesabById(id);
		hesab.setTarafeGharardad(tarafeGharardad);
		hesab.setOzv(ozv);
		hesab.setSahmeRiali(new BigDecimal(sahmeRiali));
		hesab.setSahmeSharj(new BigDecimal(sahmeSharj));
		hesab.setSahmeBelit(new BigDecimal(sahmeBelit));
		hesab.setSahmeOzviyat(new BigDecimal(sahmeOzviyat));
		hesab.setGheyreFaal(false);
		hesab.setShomareyeHesab(hesab.getShomareyeHesab());
		hesabDao.update(hesab);
	}
	
}
