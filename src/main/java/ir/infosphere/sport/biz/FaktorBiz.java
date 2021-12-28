package ir.infosphere.sport.biz;

import ir.infosphere.sport.bean.FactorBean;
import ir.infosphere.sport.bean.GhalamFactorBean;
import ir.infosphere.sport.dao.FaktorDao;
import ir.infosphere.sport.dao.GhalameFaktorDao;
import ir.infosphere.sport.entity.AksEntity;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.FaktorEntity;
import ir.infosphere.sport.entity.GhalameFaktorEntity;
import ir.infosphere.sport.entity.HesabEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.TarafeGharardadEntity;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FaktorBiz {
	
	@Autowired
	private FaktorDao faktorDao;
	@Autowired
	private GhalameFaktorDao ghalameFaktorDao;
	@Autowired
	private HesabBiz hesabBiz;
	@Autowired
	private KodBiz kodBiz;
	
	@Transactional
	public List<FaktorEntity> getAllFaktors() {
		return faktorDao.retrieveAll();
	}
	
	@Transactional
	public List<FaktorEntity> getAllFaktors(DetachedCriteria criteria) {
		return faktorDao.retrieveAllByCriteria(criteria);
	}
	
	private Long generatePeygiri(HesabEntity hesab){
		Long time=System.currentTimeMillis();
		String s=time.toString()+hesab.getId().toString();
		return Long.valueOf(s);
	}
	
	@Transactional
	public List<FaktorEntity> getFaktorsKhadamat(HesabEntity hesab,Date azTarikh,Date taTarikh,String noeF) {
		DetachedCriteria criteria = DetachedCriteria.forClass(FaktorEntity.class);
		criteria.add(Restrictions.eq("hesab", hesab));
		if(azTarikh!=null)
			criteria.add(Restrictions.ge("tarikh", azTarikh));
		if(taTarikh!=null)
			criteria.add(Restrictions.lt("tarikh", taTarikh));
		KodEntity noeFaktor=kodBiz.getKodEntity(Constants.NoeFactor,noeF);
		KodEntity dalileFaktor=kodBiz.getKodEntity(Constants.DalileFaktor,Constants.Khadamat);
		criteria.add(Restrictions.eq("noeFaktor",noeFaktor));
		criteria.add(Restrictions.eq("dalileFaktor", dalileFaktor));
		return faktorDao.retrieveAllByCriteria(criteria);
	}
	
	public Integer generatRadifGhalam(FaktorEntity faktor){
		DetachedCriteria criteria = DetachedCriteria.forClass(GhalameFaktorEntity.class);
		criteria.add(Restrictions.eq("faktor", faktor));
		return (int) (ghalameFaktorDao.getCountByCriteria(criteria)+1);
	}
	
	public void updateGhalamFaktor(GhalameFaktorEntity ghalam,
			String NamKalaYaKhadamat,Long gheymatVahed ,Integer tedadYaMeghdar){
		ghalam.setNamKalaYaKhadamat(NamKalaYaKhadamat);
		ghalam.setGheymatVahed(gheymatVahed);
		ghalam.setTedadYaMeghdar(tedadYaMeghdar);
		ghalameFaktorDao.update(ghalam);
	}
	

	@Transactional
	public boolean isTekrariShomareVaTarikh(Date tarikh, Integer shomare, HesabEntity hesab){
		DetachedCriteria criteria = DetachedCriteria.forClass(FaktorEntity.class);
		TarafeGharardadEntity contract=hesab.getTarafeGharardad();
		criteria.add(Restrictions.in("hesab", hesabBiz.getAllHesabByTarafeGharardad(contract,Constants.TamamiMavared)));
		criteria.add(Restrictions.eq("tarikh", tarikh));
		criteria.add(Restrictions.eq("shomareyeFaktor",shomare));
		if (faktorDao.getCountByCriteria(criteria)>0)
			return true;
		return false;
		
	}

	
	@Transactional
	public List<FaktorEntity> getAllFaktorsByHesab(HesabEntity hesab) {
		if (hesab == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(FaktorEntity.class);
		criteria.add(Restrictions.eq("hesab", hesab));
		return faktorDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public FaktorEntity retrieveFaktorById(Integer id) {
		return faktorDao.retrieveByID(id);
	}

	@Transactional
	public GhalameFaktorEntity retrieveGhalamFactorById(Integer id) {
		return ghalameFaktorDao.retrieveByID(id);
	}
	
	
	@Transactional
	public List<GhalameFaktorEntity> getAllAghlam(FaktorEntity faktor) {
		if (faktor == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(GhalameFaktorEntity.class);
		criteria.add(Restrictions.eq("faktor", faktor));
		criteria.addOrder(Order.asc("radifeGhalam"));
		return ghalameFaktorDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public Long getJameKoleFaktor(FaktorEntity faktor) {
		Long sum = new Long(0);
		if (faktor == null)
			return sum;
		DetachedCriteria criteria = DetachedCriteria.forClass(GhalameFaktorEntity.class);
		criteria.add(Restrictions.eq("faktor", faktor));
		List<GhalameFaktorEntity> list = ghalameFaktorDao.retrieveAllByCriteria(criteria);
		if(list.size() == 0){
			return sum;
		}
		for (int i = 0; i < list.size(); i++) {
			Integer tedad = list.get(i).getTedadYaMeghdar();
			Long gheimateVahed = list.get(i).getGheymatVahed();
			Long gheimateKol = tedad * gheimateVahed;
			sum += gheimateKol;
		}
		
		return sum;
	}

	@Transactional
	public FaktorEntity createFaktorTasviye(HesabEntity hesab,String onvan,Long mablagh,KodEntity noeFaktor,Date tarikhTasviye,AksEntity aks){
		FaktorEntity faktor=new FaktorEntity();
		faktor.setNoeFaktor(noeFaktor);
		faktor.setPeygiri(generatePeygiri(hesab));
		if(aks!=null)
			faktor.setAks(aks);
		faktor.setTarikh(tarikhTasviye);
		faktor.setHesab(hesab);
		KodEntity kod=kodBiz.getKodEntity(Constants.DalileFaktor,Constants.TasviyeKardanHesab);
		faktor.setDalileFaktor(kod);
		faktorDao.create(faktor);
		addGhalamFaktor(faktor,1, onvan, mablagh,1);
		return faktor;
	}
	
	@Transactional
	public FaktorEntity createFaktorKhadamat(FactorBean f) {
		FaktorEntity faktor=new FaktorEntity();
		faktor.setNoeFaktor(f.getNoeFaktor());
		faktor.setShomareyeFaktor(f.getShomareyeFaktor());
		faktor.setTarikh(f.getTarikh());
		faktor.setAks(f.getAks());
		faktor.setTozih(f.getTozih());
		faktor.setHesab(f.getHesab());
		faktor.setPeygiri(generatePeygiri(f.getHesab()));
		KodEntity kod=kodBiz.getKodEntity(Constants.DalileFaktor,Constants.Khadamat);
		faktor.setDalileFaktor(kod);
		faktorDao.create(faktor);
		List<GhalamFactorBean> ghalams=f.getGhalams();
		for (GhalamFactorBean ghalamBean : ghalams) {
			GhalameFaktorEntity g=new GhalameFaktorEntity();
			g.setFaktor(faktor);
			g.setGheymatVahed(ghalamBean.getGheimateVahed());
			g.setNamKalaYaKhadamat(ghalamBean.getNameKala());
			g.setTedadYaMeghdar(ghalamBean.getTedad());
			g.setRadifeGhalam(generatRadifGhalam(faktor));
			ghalameFaktorDao.create(g);
			}
		return faktor;
	}
	
	@Transactional
	public void delete(FaktorEntity faktor) {
		faktorDao.delete(faktor);
	}
	
	@Transactional
	public void deleteGhalamFactor(Integer ghalamId) {
		ghalameFaktorDao.deleteById(ghalamId);
	}
	
	@Transactional
	public void addGhalamFaktor(FaktorEntity faktor,Integer radifeGhalam,
			String NamKalaYaKhadamat,Long gheymatVahed ,Integer tedadYaMeghdar){
		GhalameFaktorEntity ghalam=new GhalameFaktorEntity();
		ghalam.setFaktor(faktor);
		ghalam.setGheymatVahed(gheymatVahed);
		ghalam.setNamKalaYaKhadamat(NamKalaYaKhadamat);
		ghalam.setTedadYaMeghdar(tedadYaMeghdar);
		ghalam.setRadifeGhalam(radifeGhalam);
		ghalameFaktorDao.create(ghalam);
		
	}
	
	@Transactional
	public boolean isLastOneGhalam(FaktorEntity faktor){
		DetachedCriteria criteria = DetachedCriteria.forClass(GhalameFaktorEntity.class);
		criteria.add(Restrictions.eq("faktor",faktor));
		if (ghalameFaktorDao.getCountByCriteria(criteria)>0)
			return true;
		return false;
	}
	
	@Transactional
	public boolean isTekrariNameKala(FaktorEntity faktor,String NamKalaYaKhadamat){
		DetachedCriteria criteria = DetachedCriteria.forClass(GhalameFaktorEntity.class);
		criteria.add(Restrictions.eq("faktor",faktor));
		criteria.add(Restrictions.ilike("namKalaYaKhadamat",NamKalaYaKhadamat));
		if (ghalameFaktorDao.getCountByCriteria(criteria)>0)
			return true;
		return false;
	}
}
