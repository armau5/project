package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.PozeHesabDao;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.HesabEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.PozEntity;
import ir.infosphere.sport.entity.PozeHesabEntity;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PozeHesabBiz {
	@Autowired
	private PozeHesabDao pozeHesabDao;
	@Autowired
	private PozBiz pozBiz;
	@Autowired
	private KodBiz kodBiz;
	
	public static final String OdatShode="عودت شده";
	
	@Transactional
	public List<PozeHesabEntity> getAllPozeHesab(){
		List<PozeHesabEntity> list = pozeHesabDao.retrieveAll();
		return list;
	}
	
	@Transactional
	public PozeHesabEntity getById(Integer id){
		return pozeHesabDao.retrieveByID(id);
	}
	
	@Transactional
	public boolean isPozeTahvileByHesab(HesabEntity hesab){
		DetachedCriteria criteria = DetachedCriteria.forClass(PozeHesabEntity.class);
		criteria.add(Restrictions.eq("hesab", hesab));
		criteria.add(Restrictions.isNull("tarikheOdat"));
		criteria.createAlias("poz", "poz", Criteria.LEFT_JOIN);
		criteria.createAlias("poz.vaziat", "vaziat", Criteria.LEFT_JOIN);
		criteria.add(Restrictions.like("vaziat.meghdar",Constants.DasteTarafeGharardad));
		if(pozeHesabDao.getCountByCriteria(criteria)>0)
			return true;
		
		return false;
	}
	
	

	@Transactional
	public List<PozeHesabEntity> getAllPozeHesabByHesab(HesabEntity hesab, String status) {
		if (status == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(PozeHesabEntity.class);
		criteria.add(Restrictions.eq("hesab", hesab));
		if(status.equals(Constants.TamamiMavared)){
			return pozeHesabDao.retrieveAllByCriteria(criteria);
		}
		else if(!status.equals(OdatShode)){
			criteria.add(Restrictions.isNull("tarikheOdat"));
			criteria.createAlias("poz", "poz", Criteria.LEFT_JOIN);
			criteria.createAlias("poz.vaziat", "vaziat", Criteria.LEFT_JOIN);
			criteria.add(Restrictions.like("vaziat.meghdar", status));
			return pozeHesabDao.retrieveAllByCriteria(criteria);	
		}
		else{
			criteria.add(Restrictions.isNotNull("tarikheOdat"));
			return pozeHesabDao.retrieveAllByCriteria(criteria);
			
		}
		
	}
	public HesabEntity getHesabTarafeGharardad(PozEntity poz) {
		if (poz == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(PozeHesabEntity.class);
		criteria.add(Restrictions.eq("poz", poz));
		List<PozeHesabEntity> list = pozeHesabDao.retrieveAllByCriteria(criteria);
		if(list.size() != 0){
			return list.get(0).getHesab();
		}
		return null;
	}

	@Transactional
	public PozeHesabEntity getPozeHesabByPoz(PozEntity poz) {
		if (poz == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(PozeHesabEntity.class);
		criteria.add(Restrictions.eq("poz", poz));
		List<PozeHesabEntity> list = pozeHesabDao.retrieveAllByCriteria(criteria);
		if(list.size() != 0){
			return list.get(0);
		}
		return null;
	}

	
	@Transactional
	public void setOdatPozeHesab(Integer id, Date odatDate) {
		PozeHesabEntity pozeHesab = pozeHesabDao.retrieveByID(id);
		pozeHesab.setTarikheOdat(odatDate);
		pozeHesabDao.update(pozeHesab);
		KodEntity vaziat = kodBiz.getKodEntity(Constants.VaziatePoz,Constants.MojoodDarAnbar);
		pozBiz.updateStatusPoz(pozeHesab.getPoz(), vaziat);
	}

	@Transactional
	public void deletePozeHesab(Integer id) {
		PozeHesabEntity pozeHesab = pozeHesabDao.retrieveByID(id);
		KodEntity vaziat = kodBiz.getKodEntity(Constants.VaziatePoz,Constants.MojoodDarAnbar);
		pozBiz.updateStatusPoz(pozeHesab.getPoz(), vaziat);
		pozeHesabDao.delete(pozeHesab);
	}

	@Transactional
	public void createPozeHesab(HesabEntity hesab, PozEntity poz,
			KodEntity karbariPoz, Date tarikheTahvil, String nameTahvilGirande,
			String famileTahvilGirande, String shomareHamrah,
			String shomareSabet) {
		PozeHesabEntity pozeHesab = new PozeHesabEntity();
		pozeHesab.setHesab(hesab);
		pozeHesab.setPoz(poz);
		pozeHesab.setKarbari(karbariPoz);
		pozeHesab.setTarikheTahvil(tarikheTahvil);
		pozeHesab.setNameTahvilGirandeh(nameTahvilGirande);
		pozeHesab.setFamileTahvilGirandeh(famileTahvilGirande);
		pozeHesab.setShomareyeHamrah(shomareHamrah);
		pozeHesab.setShomareyeSabet(shomareSabet);
		pozeHesabDao.create(pozeHesab);
	}

	public void updatePozeHesab(Integer id, HesabEntity hesab, PozEntity poz,
			 KodEntity karbariPoz, Date tarikheTahvil,
			String nameTahvilGirande, String famileTahvilGirande,
			String shomareHamrah, String shomareSabet) {
		PozeHesabEntity pozeHesab = pozeHesabDao.retrieveByID(id);
		pozeHesab.setHesab(hesab);
		pozeHesab.setPoz(poz);
		pozeHesab.setKarbari(karbariPoz);
		pozeHesab.setTarikheTahvil(tarikheTahvil);
		pozeHesab.setNameTahvilGirandeh(nameTahvilGirande);
		pozeHesab.setFamileTahvilGirandeh(famileTahvilGirande);
		pozeHesab.setShomareyeHamrah(shomareHamrah);
		pozeHesab.setShomareyeSabet(shomareSabet);
		pozeHesabDao.update(pozeHesab);		
	}
}
