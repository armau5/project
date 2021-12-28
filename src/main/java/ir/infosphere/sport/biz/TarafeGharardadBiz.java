package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.TarafeGharardadDao;
import ir.infosphere.sport.entity.BakhshEntity;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.HesabEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.NahiyeEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.TarafeGharardadEntity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TarafeGharardadBiz {
	@Autowired
	private TarafeGharardadDao tarafeGharardadDao;
	@Autowired
	private HesabBiz hesabBiz;
	@Autowired
	private PozeHesabBiz pozeHesabBiz;	

	@Transactional
	public List<TarafeGharardadEntity> getAllTarafeGharardadS(String active){
		DetachedCriteria criteria = DetachedCriteria.forClass(TarafeGharardadEntity.class);
		if (active.equals(Constants.Faal))
			criteria.add(Restrictions.eq("gheyreFaal", false));
		else if (active.equals(Constants.GheyreFaal))
			criteria.add(Restrictions.eq("gheyreFaal", true));
		List<TarafeGharardadEntity> list = tarafeGharardadDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public boolean canDeactive(TarafeGharardadEntity tarafeGharardad){
		List<HesabEntity> accounts=hesabBiz.getAllHesabByTarafeGharardad(tarafeGharardad,"تمامی موارد");
		for (HesabEntity hesabEntity : accounts) {
			if(pozeHesabBiz.isPozeTahvileByHesab(hesabEntity))
				return false;
		}
		return true;
	}
	
	@Transactional
	public boolean isTekrari(String name,NahiyeEntity nahiye){
		DetachedCriteria criteria = DetachedCriteria.forClass(TarafeGharardadEntity.class);
		criteria.add(Restrictions.eq("nahiye",nahiye));
		criteria.add(Restrictions.eq("name",name));
		if(tarafeGharardadDao.getCountByCriteria(criteria)>0)
			return true;
		return false;
	}
	
	@Transactional
	public List<TarafeGharardadEntity> retrieveTarafeGharardads(DetachedCriteria criteria) {
		List<TarafeGharardadEntity> list = tarafeGharardadDao.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public TarafeGharardadEntity retrieveTarafeGharardadById(Short id) {
		return tarafeGharardadDao.retrieveByID(id);
	}

	@Transactional
	public void deleteContract(Short contractId) {
		tarafeGharardadDao.delete(tarafeGharardadDao.retrieveByID(contractId));
	}

	@Transactional
	public void createContract(String name, NahiyeEntity shenaseyeNahiye, String adres, KodEntity noeFaaliat, String shomareyeHamrah, String shomareyeSabet,
			BakhshEntity bakhsh, OzvEntity ozv, String nameSahebeHesab, String shomareyeHesab, KodEntity nameBank, String shomareyeKart, String nameShobe, String kodeShobe){
		TarafeGharardadEntity tarafeGharardad = new TarafeGharardadEntity();
		tarafeGharardad.setName(name);
		tarafeGharardad.setNahiye(shenaseyeNahiye);
		tarafeGharardad.setAdres(adres);
		tarafeGharardad.setNoeFaaliat(noeFaaliat);
		tarafeGharardad.setShomareyeHamrah(shomareyeHamrah);
		tarafeGharardad.setShomareyeSabet(shomareyeSabet);
		tarafeGharardad.setBakhsh(bakhsh);
		tarafeGharardad.setOzv(ozv);
		tarafeGharardad.setNameSahebeHesab(nameSahebeHesab);
		tarafeGharardad.setShomareyeHesab(shomareyeHesab);
		tarafeGharardad.setNameBank(nameBank);
		tarafeGharardad.setShomareyeKart(shomareyeKart);
		tarafeGharardad.setNameShobe(nameShobe);
		tarafeGharardad.setKodeShobe(kodeShobe);
		tarafeGharardad.setGheyreFaal(false);
		tarafeGharardadDao.create(tarafeGharardad);
	}
	
	@Transactional
	public void editContract(short id, String name, NahiyeEntity shenaseyeNahiye, String adres, KodEntity noeFaaliat, String shomareyeHamrah, String shomareyeSabet,
			BakhshEntity bakhsh, OzvEntity ozv, String nameSahebeHesab, String shomareyeHesab, KodEntity nameBank, String shomareyeKart, String nameShobe, String kodeShobe){
		TarafeGharardadEntity tarafeGharardad = tarafeGharardadDao.retrieveByID(id);
		
		tarafeGharardad.setName(name);
		tarafeGharardad.setNahiye(shenaseyeNahiye);
		tarafeGharardad.setAdres(adres);
		tarafeGharardad.setNoeFaaliat(noeFaaliat);
		tarafeGharardad.setShomareyeHamrah(shomareyeHamrah);
		tarafeGharardad.setShomareyeSabet(shomareyeSabet);
		tarafeGharardad.setBakhsh(bakhsh);
		tarafeGharardad.setOzv(ozv);
		tarafeGharardad.setNameSahebeHesab(nameSahebeHesab);
		tarafeGharardad.setShomareyeHesab(shomareyeHesab);
		tarafeGharardad.setNameBank(nameBank);
		tarafeGharardad.setShomareyeKart(shomareyeKart);
		tarafeGharardad.setNameShobe(nameShobe);
		tarafeGharardad.setKodeShobe(kodeShobe);
		
		tarafeGharardadDao.update(tarafeGharardad);
	}

	@Transactional
	public void changeContractActivation(Short id) {
		TarafeGharardadEntity tarafeGharardad = retrieveTarafeGharardadById(id);
		tarafeGharardad.setGheyreFaal(!tarafeGharardad.getGheyreFaal());
		tarafeGharardadDao.update(tarafeGharardad);
	}

	@Transactional
	public List<TarafeGharardadEntity> getAllTarafeGharardadByNahiye(NahiyeEntity nahiye) {
		if (nahiye == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(TarafeGharardadEntity.class);
		criteria.add(Restrictions.eq("nahiye", nahiye));
		criteria.addOrder(Order.asc("name"));
		return tarafeGharardadDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public List<TarafeGharardadEntity> getAllTarafeGharardadByBakhsh(BakhshEntity bakhsh, NahiyeEntity nahiye){
		List<TarafeGharardadEntity> list= getAllTarafeGharardadByNahiye(nahiye);
		List<TarafeGharardadEntity> listOK = new ArrayList<>();
		for(TarafeGharardadEntity entity : list){
			if(entity.getBakhsh().getId() == bakhsh.getId()){
				listOK.add(entity);
			}
		}
		return listOK;
	}
	
}
