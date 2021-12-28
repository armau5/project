package ir.infosphere.sport.biz;

import ir.infosphere.sport.bean.OzvBean;
import ir.infosphere.sport.bean.SiasatPardakhtOzveyatBean;
import ir.infosphere.sport.dao.SiasatDao;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.GoroohhayeKarbarEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.MadreseEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.OzveMadreseEntity;
import ir.infosphere.sport.entity.OzveTimEntity;
import ir.infosphere.sport.entity.SiasatEntity;
import ir.infosphere.sport.entity.TimEntity;
import ir.infosphere.sport.util.PermissionUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;

@Component
public class SiasatBiz {
	@Autowired
	private SiasatDao siasatDao;
	@Autowired
	private OzveTimBiz ozveTimBiz;
	@Autowired
	private KodBiz kodBiz;
	@Autowired
	private PardakhtSiasatBiz pardakhtSiasatBiz;
	@Autowired
	private NoePardakhtBiz noePardakhtBiz;
	
	
	@Transactional
	public List<SiasatEntity> getAllSiasat(){
		DetachedCriteria criteria = DetachedCriteria.forClass(SiasatEntity.class);
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		return siasatDao.retrieveAllByCriteria(criteria);
	}
	
	public void generateAllRahgiriOzv (SiasatEntity siasat)
	{
		if (siasat == null) return;
		ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
		WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		RahgiriBiz rahgiriBiz = appContext.getBean(RahgiriBiz.class);
		if (siasat.getNoesisat().getMeghdar().equals(Constants.NoeMadrese))
		{
			MadreseBiz madreseBiz = appContext.getBean(MadreseBiz.class);
			List<MadreseEntity> madareseMoredeNazar = madreseBiz.getAllMadreseBySiasat(siasat);
			for (MadreseEntity madrese : madareseMoredeNazar) {
				rahgiriBiz.createForSiasat(siasat, Constants.NoeMadrese, madrese.getOzveMasool(), madrese.getNameMadrese());
			}
		}
		else if (siasat.getNoesisat().getMeghdar().equals(Constants.NoeOzveMadrese))
		{
			OzveMadreseBiz ozveMadreseBiz = appContext.getBean(OzveMadreseBiz.class);
			List<OzveMadreseEntity> azayeMoreNazar = ozveMadreseBiz.getAllOzveMadreseBySiasat(siasat);
			for (OzveMadreseEntity ozvMadrese : azayeMoreNazar) {
				rahgiriBiz.createForSiasat(siasat, Constants.NoeOzveMadrese, ozvMadrese.getOzveMadrese(), ozvMadrese.getSemat().getMeghdar());
			}
		}
		else if (siasat.getNoesisat().getMeghdar().equals(Constants.NoeTim)) {
			TimBiz timBiz = appContext.getBean(TimBiz.class);
			List<TimEntity> timHayeMoredeNazar = timBiz.getAllTimBySiasat(siasat);
			for (TimEntity tim : timHayeMoredeNazar) {
				rahgiriBiz.createForSiasat(siasat, Constants.NoeTim, tim.getOzveMasool(), tim.getNameTim());
			}
		}
		else if (siasat.getNoesisat().getMeghdar().equals(Constants.NoeOzveTim)) {
			OzveTimBiz ozveTimBiz = appContext.getBean(OzveTimBiz.class);
			List<OzveTimEntity> azayeMoreNazar = ozveTimBiz.getAllOzveTimBySiasat(siasat);
			for (OzveTimEntity ozvTim : azayeMoreNazar) {
				rahgiriBiz.createForSiasat(siasat, Constants.NoeOzveTim, ozvTim.getOzv(), ozvTim.getSemat().getMeghdar());
			}
		}
		else if (siasat.getNoesisat().getMeghdar().equals(Constants.NoeSayer)) {
			GoroohhayeKarbarBiz goroohhayeKarbarBiz = appContext.getBean(GoroohhayeKarbarBiz.class);
			List<GoroohhayeKarbarEntity> azayeMoredeNazar = goroohhayeKarbarBiz.getAllAzayeGoroohyeKarbariBySiasat(siasat);
			for (GoroohhayeKarbarEntity goroohhayeKarbar : azayeMoredeNazar) {
				rahgiriBiz.createForSiasat(siasat, Constants.NoeSayer, goroohhayeKarbar.getKarbar(), goroohhayeKarbar.getGorooh().getNam());
			}
		}
	}
	
	public List<SiasatPardakhtOzveyatBean> getAllSiasatHayePardakht(OzvEntity ozv, boolean pardakhtShode)
	{
		ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
		WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		PardakhtSiasatBiz pardakhtSiasatBiz = appContext.getBean(PardakhtSiasatBiz.class);
		
		List<SiasatPardakhtOzveyatBean> listeSiasatHayePardakht = new ArrayList<SiasatPardakhtOzveyatBean>();
		List<SiasatEntity> listSiasatHayeFaalDarZamaneHal = getAllSiasatHayeFaalDarZamaneHal();
		for (SiasatEntity siasatItem : listSiasatHayeFaalDarZamaneHal) {
			if (siasatItem.getNoesisat().getMeghdar().equals(Constants.NoeMadrese)) {
				MadreseBiz madreseBiz = appContext.getBean(MadreseBiz.class);
				List <MadreseEntity> listeMadares = madreseBiz.getAllMadreseByOzveMasoolForSiasat(ozv, siasatItem);
				for (MadreseEntity madrese : listeMadares) {
					if (pardakhtSiasatBiz.pardakhtShodeYaKheyrDarTimMadrese(siasatItem, ozv, madrese.getId()) == pardakhtShode)
						listeSiasatHayePardakht.add(new SiasatPardakhtOzveyatBean(siasatItem, madrese, null, new OzvBean(ozv)));
				}
			}
			
			else if (siasatItem.getNoesisat().getMeghdar().equals(Constants.NoeOzveMadrese)) {
				OzveMadreseBiz ozveMadreseBiz = appContext.getBean(OzveMadreseBiz.class);
				OzveMadreseEntity ozveMadrese = ozveMadreseBiz.getAllByOzveMadreseAndSemat(ozv, siasatItem);
				if (ozveMadrese != null)
					if (pardakhtSiasatBiz.pardakhtShodeYaKheyr(siasatItem, ozveMadrese.getOzveMadrese()) == pardakhtShode)
						listeSiasatHayePardakht.add(new SiasatPardakhtOzveyatBean(siasatItem, null, null, new OzvBean(ozv)));
			}
			
			else if (siasatItem.getNoesisat().getMeghdar().equals(Constants.NoeTim)) {
				TimBiz timBiz = appContext.getBean(TimBiz.class);
				List <TimEntity> listeTim = timBiz.getAllTimByOzveMasoolForSiasat(ozv, siasatItem);
				for (TimEntity tim : listeTim) {
					if (pardakhtSiasatBiz.pardakhtShodeYaKheyrDarTimMadrese(siasatItem, ozv, tim.getId()) == pardakhtShode)
						listeSiasatHayePardakht.add(new SiasatPardakhtOzveyatBean(siasatItem, null, tim, new OzvBean(ozv)));
				}
			}
			
			else if (siasatItem.getNoesisat().getMeghdar().equals(Constants.NoeOzveTim)) {
				OzveTimBiz ozveTimBiz = appContext.getBean(OzveTimBiz.class); 
				OzveTimEntity ozveTim = ozveTimBiz.getAllByOzveTimAndSemat(ozv, siasatItem);
				if (ozveTim != null)
					if (pardakhtSiasatBiz.pardakhtShodeYaKheyr(siasatItem, ozveTim.getOzv()) == pardakhtShode)
						listeSiasatHayePardakht.add(new SiasatPardakhtOzveyatBean(siasatItem, null, null, new OzvBean(ozv)));
			}

			else if (siasatItem.getNoesisat().getMeghdar().equals(Constants.NoeSayer)) {
				GoroohhayeKarbarBiz goroohhayeKarbarBiz = appContext.getBean(GoroohhayeKarbarBiz.class);
				if (goroohhayeKarbarBiz.isSiasatForUser(ozv, siasatItem))
					if (pardakhtSiasatBiz.pardakhtShodeYaKheyr(siasatItem, ozv) == pardakhtShode)
						listeSiasatHayePardakht.add(new SiasatPardakhtOzveyatBean(siasatItem, null, null, new OzvBean(ozv)));
			}
		}
		return listeSiasatHayePardakht;
	}
	
	@Transactional
	public List<SiasatEntity> getAllSiasatHayeFaalDarZamaneHal() {
		DetachedCriteria criteria = DetachedCriteria.forClass(SiasatEntity.class);
		criteria.add(Restrictions.eq("gheyreFaal", false));
		criteria.add(Restrictions.le("azTarikh", new Date()));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		return siasatDao.retrieveAllByCriteria(criteria);
	}
	@Transactional
	public ArrayList<SiasatPardakhtOzveyatBean> getAllSiasatSayer(Date start, Date end,Short idGorooheKarbari) {
		String sql="select distinct(s.Shenaseyesiasat),ozv.KodeMeli,ozv.Nam,ozv.Famil,ozv.ShenaseyeOzv from siasat s ";
		sql+="join goroohhayekarbar g on s.GheyreFaal=0 and s.ShenaseyeGorooheKarbari=g.Gorooh and g.Gorooh=";
		sql+=idGorooheKarbari;
		sql+=" and g.ShorooeTakhsis <= s.TaTarikh "+
		"and if(g.PayaneTakhsis is null ,true , g.PayaneTakhsis >= s.AzTarikh )";
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join ozv on g.Karbar=ozv.ShenaseyeOzv";
		Session session = siasatDao.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql);
		if(start!=null){
			query.setDate("start",start);
		}
		if(end!=null){
			 query.setDate("end",end);
		}
		@SuppressWarnings("rawtypes")
		List l=query.list();
		ArrayList<SiasatPardakhtOzveyatBean> records= new ArrayList<SiasatPardakhtOzveyatBean>(); 
		List<SiasatEntity> siasats=getAllSiasat();
		HashMap<Integer,SiasatEntity> map=new HashMap<Integer,SiasatEntity>(siasats.size());
		for (SiasatEntity siasatEntity : siasats) {
			map.put(siasatEntity.getId(),siasatEntity);
		}
		for (Object object : l) {
			try {
				Object[] tmp = (Object[]) object;
				if(tmp[0] ==null)
					continue;
				SiasatEntity s=map.get((Integer) tmp[0]);
				String kodeMeli=(String) tmp[1];
				String name=(String) tmp[2];
				String famil=(String) tmp[3];
				Integer id_ozv=(Integer) tmp[4];
				records.add(new SiasatPardakhtOzveyatBean
						(new OzvBean(kodeMeli,name,famil,id_ozv),null,s,null,null));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		return records;
	}
	
	
	@Transactional
	public ArrayList<Integer> getSumOfHoghoghiMadreseha(List<MadreseEntity> madreseha,Date start,Date end){
		ArrayList<Integer> answer=new ArrayList<Integer>(madreseha.size());
		Session session = siasatDao.getHibernateTemplate().getSessionFactory().openSession();
		HashMap<Integer,Integer> mymap=new HashMap<Integer,Integer>();
		KodEntity kod=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeMadrese);
		
		String sql="select m.ShenaseyeMadrese,sum(s.Mizan) from madrese m join siasat s on "+
		"m.ShenaseyeReshteVarzeshi=s.ShenaseyeReshteVarzeshi and s.ShenaseyeNoePardakht=";
		//1 mablagh tayin shode
		sql+=Constants.NoePardakht_Mablaghi;
		sql+=" and s.ShenaseyeJensiat=m.ShenaseyeJensiat and "+
		"s.TaTarikh >= m.TarikheSabtMadrese and s.GheyreFaal=0 and m.GheyreFaal=0 and s.ShenaseyeNoeSiasat=";
		sql+=kod.getId();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" group by m.ShenaseyeMadrese";
		Query query = session.createSQLQuery(sql);
		if(start!=null){
			query.setDate("start",start);
		}
		if(end!=null){
			 query.setDate("end",end);
		}
		@SuppressWarnings("rawtypes")
		List l=query.list();
		for (Object object : l) {
			try {
				Object[] tmp = (Object[]) object;
				if(tmp[0] ==null)
					break;
				Integer madrese_id=(Integer) tmp[0];
				Integer sum=Integer.parseInt(tmp[1].toString());
				mymap.put(madrese_id, sum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		for (MadreseEntity madrese : madreseha) {
			if(mymap.containsKey(madrese.getId()))
				answer.add(mymap.get(madrese.getId()));
			else answer.add(0);
		}
		return answer;
	}
	
	
	@Transactional
	public ArrayList<Integer> getSumOfHaghighiMadreseha(List<MadreseEntity> madreseha,Date start,Date end){
		ArrayList<Integer> answer=new ArrayList<Integer>(madreseha.size());
		Session session = siasatDao.getHibernateTemplate().getSessionFactory().openSession();
		HashMap<Integer,Integer> mymap=new HashMap<Integer,Integer>();
		
		String sql="select mtable2.ShenaseyeMadrese,sum(siasat.Mizan) from " +
				"(select distinct s.ShenaseyeSiasat,o.ShenaseyeOzv from siasat s join madrese m on " +
				" m.ShenaseyeReshteVarzeshi=s.ShenaseyeReshteVarzeshi and s.GheyreFaal = 0" +
				" and s.ShenaseyeNoeSiasat=";
		sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeOzveMadrese).getId();
		sql+=" and s.ShenaseyeNoePardakht=";
		//1 mablagh tayin shode
		sql+=Constants.NoePardakht_Mablaghi;
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join ozvemadrese o on m.ShenaseyeMadrese=o.ShenaseyeMadrese  and ";
		sql+=" s.SemateOzv=o.SemateOzv  and o.TarikheSabteNam <= s.TaTarikh ";
		sql+=" and if(o.TarikhePayaneEtebar is null ,true , o.TarikhePayaneEtebar >= s.AzTarikh ) ";
		sql+=" join ozv oz on o.ShenaseyeOzv=oz.ShenaseyeOzv and s.ShenaseyeJensiat=oz.Jensiat ) as mtable";
		sql+=" join siasat on mtable.ShenaseyeSiasat=siasat.Shenaseyesiasat join ";
		sql+=" (select ozvemadrese.ShenaseyeOzv,ozvemadrese.ShenaseyeMadrese from ";
		sql+=" ozvemadrese group by ozvemadrese.ShenaseyeOzv ) as mtable2 ";
		sql+=" on mtable.ShenaseyeOzv=mtable2.ShenaseyeOzv group by mtable2.ShenaseyeMadrese";
				
		Query query = session.createSQLQuery(sql);
		if(start!=null){
			query.setDate("start",start);
		}
		if(end!=null){
			 query.setDate("end",end);
		}
		@SuppressWarnings("rawtypes")
		List l=query.list();
		for (Object object : l) {
			try {
				Object[] tmp = (Object[]) object;
				if(tmp[0] ==null)
					break;
				Integer madrese_id=(Integer) tmp[0];
				Integer sum=Integer.parseInt(tmp[1].toString());
				mymap.put(madrese_id, sum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		for (MadreseEntity madrese : madreseha) {
			if(mymap.containsKey(madrese.getId()))
				answer.add(mymap.get(madrese.getId()));
			else answer.add(0);
		}
		return answer;
	}
	
	@Transactional
	public ArrayList<Boolean> darsadiHoghoghiMadreseha(List<MadreseEntity> madreseha,Date start,Date end){
		ArrayList<Boolean> answer=new ArrayList<Boolean>(madreseha.size());
		Session session = siasatDao.getHibernateTemplate().getSessionFactory().openSession();
		ArrayList<Integer> darsadi=new ArrayList<Integer>();
		
		String sql="select m.ShenaseyeMadrese from madrese m join siasat s on "+
		"m.ShenaseyeReshteVarzeshi=s.ShenaseyeReshteVarzeshi ";
		sql+="and s.ShenaseyeNoePardakht=";
		//2 darsad garardad
		sql+=Constants.NoePardakht_Darsadi;
		sql+=" and s.ShenaseyeJensiat=m.ShenaseyeJensiat and "+
		"s.TaTarikh >= m.TarikheSabtMadrese and s.GheyreFaal=0 and  m.GheyreFaal=0 ";
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" group by m.ShenaseyeMadrese";
		Query query = session.createSQLQuery(sql);
		if(start!=null){
			query.setDate("start",start);
		}
		if(end!=null){
			 query.setDate("end",end);
		}
		@SuppressWarnings("rawtypes")
		List l=query.list();
		for (Object object : l) {
			try {
				Integer madrese_id=(Integer) object;
				darsadi.add(madrese_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		for (MadreseEntity madrese : madreseha) {
			if(darsadi.contains(madrese.getId()))
				answer.add(true);
			else answer.add(false);
		}
		return answer;
	}
	
	
	@Transactional
	public ArrayList<Boolean> darsadiHaghighiMadreseha(List<MadreseEntity> madreseha,Date start,Date end){
		ArrayList<Boolean> answer=new ArrayList<Boolean>(madreseha.size());
		Session session = siasatDao.getHibernateTemplate().getSessionFactory().openSession();
		ArrayList<Integer> darsadi=new ArrayList<Integer>();
		String sql="select m.ShenaseyeMadrese from siasat s " ;
		sql+="join madrese m on  m.ShenaseyeReshteVarzeshi=s.ShenaseyeReshteVarzeshi and s.GheyreFaal = 0 and s.ShenaseyeNoePardakht=";
		//2 darsad garardad
		sql+=Constants.NoePardakht_Darsadi;
		sql+=" join ozvemadrese o on m.ShenaseyeMadrese=o.ShenaseyeMadrese  and "+
		"s.SemateOzv=o.SemateOzv  and o.TarikheSabteNam <= s.TaTarikh "+
		"and if(o.TarikhePayaneEtebar is null ,true , o.TarikhePayaneEtebar >= s.AzTarikh ) ";
				if(start!=null){
					sql+=" and s.AzTarikh >= :start ";
				}
				if(end!=null){
					sql+=" and s.TaTarikh <= :end "; 
				}
		
		sql+=" join ozv oz on o.ShenaseyeOzv=oz.ShenaseyeOzv and s.ShenaseyeJensiat=oz.Jensiat group by m.ShenaseyeMadrese";
		
		Query query = session.createSQLQuery(sql);
		if(start!=null){
			query.setDate("start",start);
		}
		if(end!=null){
			 query.setDate("end",end);
		}
		@SuppressWarnings("rawtypes")
		List l=query.list();
		for (Object object : l) {
			try {
				Integer madrese_id=(Integer)object;
				darsadi.add(madrese_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		for (MadreseEntity madrese : madreseha) {
			if(darsadi.contains(madrese.getId()))
				answer.add(true);
			else answer.add(false);
		}
		return answer;
	}
	
	
	@Transactional
	public ArrayList<SiasatPardakhtOzveyatBean> madreseSiasatHaghighi(Date start,Date end){
		String sql="select s.ShenaseyeSiasat,ozv.KodeMeli,ozv.Nam,ozv.Famil,ozv.ShenaseyeOzv from siasat s join madrese t on "
				+ "t.ShenaseyeReshteVarzeshi=s.ShenaseyeReshteVarzeshi and "
				+ "s.GheyreFaal = 0 and s.ShenaseyeNoeSiasat=";
		sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeOzveMadrese).getId().toString();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		
		sql+=" join ozvemadrese o on s.SemateOzv=o.SemateOzv and o.TarikheSabteNam <= s.TaTarikh and if (o.TarikhePayaneEtebar is null ,true , o.TarikhePayaneEtebar >= s.AzTarikh ) "
				+ "and t.ShenaseyeMadrese=o.ShenaseyeMadrese join ozv on ozv.ShenaseyeOzv=o.ShenaseyeOzv and ozv.Jensiat=s.ShenaseyeJensiat";
		Session session = siasatDao.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql);
		if(start!=null){
			query.setDate("start",start);
		}
		if(end!=null){
			 query.setDate("end",end);
		}
		@SuppressWarnings("rawtypes")
		List l=query.list();
		ArrayList<SiasatPardakhtOzveyatBean> records= new ArrayList<SiasatPardakhtOzveyatBean>();
		List<SiasatEntity> siasats=getAllSiasat();
		HashMap<Integer,SiasatEntity> map=new HashMap<Integer,SiasatEntity>(siasats.size());
		for (SiasatEntity siasatEntity : siasats) {
			map.put(siasatEntity.getId(),siasatEntity);
		}
		for (Object object : l) {
			try {
				Object[] tmp = (Object[]) object;
				if(tmp[0] ==null)
					continue;
				SiasatEntity s=map.get((Integer) tmp[0]);
				String kodeMeli=(String) tmp[1];
				String name=(String) tmp[2];
				String famil=(String) tmp[3];
				Integer id_ozv=(Integer) tmp[4];
				records.add(new SiasatPardakhtOzveyatBean(new OzvBean(kodeMeli,name,famil,id_ozv),null,s,null,null));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		return records;
	}
	
	
	public List<SiasatPardakhtOzveyatBean> madreseSiasatHoghoghi(Date start,Date end){
		String sql="select t.ShenaseyeMadrese,s.Shenaseyesiasat,ozv.KodeMeli,ozv.Nam,ozv.Famil,ozv.ShenaseyeOzv from madrese t join siasat s on ";
		sql+="t.ShenaseyeReshteVarzeshi=s.ShenaseyeReshteVarzeshi and ";
		sql+=" s.ShenaseyeJensiat=t.ShenaseyeJensiat and" +
				" s.TaTarikh >= t.TarikheSabtMadrese and s.GheyreFaal=0 and t.GheyreFaal=0 and s.ShenaseyeNoeSiasat=";
		sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeMadrese).getId().toString();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join ozv on t.ShenaseyeOzveMasool=ozv.ShenaseyeOzv";
		Session session = siasatDao.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql);
		if(start!=null){
			query.setDate("start",start);
		}
		if(end!=null){
			 query.setDate("end",end);
		}
		@SuppressWarnings("rawtypes")
		List l=query.list();
		List<SiasatPardakhtOzveyatBean> records= new ArrayList<SiasatPardakhtOzveyatBean>(); 
		List<SiasatEntity> siasats=getAllSiasat();
		HashMap<Integer,SiasatEntity> map=new HashMap<Integer,SiasatEntity>(siasats.size());
		for (SiasatEntity siasatEntity : siasats) {
			map.put(siasatEntity.getId(),siasatEntity);
		}
		for (Object object : l) {
			try {
				Object[] tmp = (Object[]) object;
				if(tmp[0] ==null)
					continue;
				Integer madrese=(Integer) tmp[0];
				SiasatEntity s=map.get((Integer) tmp[1]);
				String kodeMeli=(String) tmp[2];
				String name=(String) tmp[3];
				String famil=(String) tmp[4];
				Integer id_ozv=(Integer) tmp[5];
				records.add(new SiasatPardakhtOzveyatBean(new OzvBean(kodeMeli,name,famil,id_ozv),null,s,madrese,null));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		return records;
	}
	

	
	public List<SiasatPardakhtOzveyatBean> getAllSiasatHoghoghiTims(Date start,Date end){
		String sql="select t.ShenaseyeTim,s.Shenaseyesiasat,ozv.KodeMeli,ozv.Nam,ozv.Famil,ozv.ShenaseyeOzv from tim t join siasat s on ";
		sql+="t.ShenaseyeGorooheReshteyeVarzeshi=s.ShenaseyeGorooheReshteyeVarzeshi and ";
		sql+="s.SatheTim=t.SatheTim and s.ShenaseyeJensiat=t.ShenaseyeJensiat and" +
				" s.TaTarikh >= t.TarikheSabtTim and s.GheyreFaal=0 and t.GheyreFaal=0 and s.ShenaseyeNoeSiasat=";
		sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeTim).getId().toString();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join ozv on t.ShenaseyeOzveMasool=ozv.ShenaseyeOzv";
		Session session = siasatDao.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql);
		if(start!=null){
			query.setDate("start",start);
		}
		if(end!=null){
			 query.setDate("end",end);
		}
		@SuppressWarnings("rawtypes")
		List l=query.list();
		List<SiasatPardakhtOzveyatBean> records= new ArrayList<SiasatPardakhtOzveyatBean>(); 
		List<SiasatEntity> siasats=getAllSiasat();
		HashMap<Integer,SiasatEntity> map=new HashMap<Integer,SiasatEntity>(siasats.size());
		for (SiasatEntity siasatEntity : siasats) {
			map.put(siasatEntity.getId(),siasatEntity);
		}
		for (Object object : l) {
			try {
				Object[] tmp = (Object[]) object;
				if(tmp[0] ==null)
					continue;
				Short tim=(Short) tmp[0];
				SiasatEntity s=map.get((Integer) tmp[1]);
				String kodeMeli=(String) tmp[2];
				String name=(String) tmp[3];
				String famil=(String) tmp[4];
				Integer id_ozv=(Integer) tmp[5];
				records.add(new SiasatPardakhtOzveyatBean(new OzvBean(kodeMeli,name,famil,id_ozv),null,s,null,tim));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		return records;
	}
	

	public ArrayList<SiasatPardakhtOzveyatBean> getAllSiasatHaghighiTims(Date start,Date end){
		String sql="select s.ShenaseyeSiasat,ozv.KodeMeli,ozv.Nam,ozv.Famil,ozv.ShenaseyeOzv from siasat s join tim t on t.SatheTim=s.SatheTim "
				+ "and t.ShenaseyeGorooheReshteyeVarzeshi=s.ShenaseyeGorooheReshteyeVarzeshi and "
				+ "s.GheyreFaal = 0 and s.ShenaseyeNoeSiasat=";
		sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeOzveTim).getId().toString();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		
		sql+=" join ozvetim o on s.SemateOzv=o.SemateOzv and o.ShorooeOzviat <= s.TaTarikh and if (o.PayaneOzviat is null ,true , o.PayaneOzviat >= s.AzTarikh ) "
				+ "and t.ShenaseyeTim=o.ShenaseyeTim join ozv on ozv.ShenaseyeOzv=o.ShenaseyeOzv and ozv.Jensiat=s.ShenaseyeJensiat";
		Session session = siasatDao.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql);
		if(start!=null){
			query.setDate("start",start);
		}
		if(end!=null){
			 query.setDate("end",end);
		}
		@SuppressWarnings("rawtypes")
		List l=query.list();
		ArrayList<SiasatPardakhtOzveyatBean> records= new ArrayList<SiasatPardakhtOzveyatBean>();
		List<SiasatEntity> siasats=getAllSiasat();
		HashMap<Integer,SiasatEntity> map=new HashMap<Integer,SiasatEntity>(siasats.size());
		for (SiasatEntity siasatEntity : siasats) {
			map.put(siasatEntity.getId(),siasatEntity);
		}
		for (Object object : l) {
			try {
				Object[] tmp = (Object[]) object;
				if(tmp[0] ==null)
					continue;
				SiasatEntity s=map.get((Integer) tmp[0]);
				String kodeMeli=(String) tmp[1];
				String name=(String) tmp[2];
				String famil=(String) tmp[3];
				Integer id_ozv=(Integer) tmp[4];
				records.add(new SiasatPardakhtOzveyatBean(new OzvBean(kodeMeli,name,famil,id_ozv),null,s,null,null));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		return records;
	}

	
	@Transactional
	public ArrayList<Boolean> darsadiHaghighiTims(List<TimEntity> teams,Date start,Date end){
		ArrayList<Boolean> answer=new ArrayList<Boolean>(teams.size());
		Session session = siasatDao.getHibernateTemplate().getSessionFactory().openSession();
		ArrayList<Short> darsadi=new ArrayList<Short>();
		String sql="select t.ShenaseyeTim from siasat s join ozvetim o on "+
		"s.SemateOzv=o.SemateOzv  and s.GheyreFaal = 0 and o.ShorooeOzviat <= s.TaTarikh "+
		"and if(o.PayaneOzviat is null ,true , o.PayaneOzviat>= s.AzTarikh ) and s.ShenaseyeNoePardakht=";
		//2 darsad garardad
		sql+=Constants.NoePardakht_Darsadi;
				if(start!=null){
					sql+=" and s.AzTarikh >= :start ";
				}
				if(end!=null){
					sql+=" and s.TaTarikh <= :end "; 
				}
		sql+=" join tim t on t.ShenaseyeTim=o.ShenaseyeTim  and t.SatheTim=s.SatheTim and "+
		" t.ShenaseyeGorooheReshteyeVarzeshi=s.ShenaseyeGorooheReshteyeVarzeshi "+
		"join ozv oz on o.ShenaseyeOzv=oz.ShenaseyeOzv and s.ShenaseyeJensiat=oz.Jensiat group by t.ShenaseyeTim";
		
		Query query = session.createSQLQuery(sql);
		if(start!=null){
			query.setDate("start",start);
		}
		if(end!=null){
			 query.setDate("end",end);
		}
		@SuppressWarnings("rawtypes")
		List l=query.list();
		for (Object object : l) {
			try {
				Short tim_id=(Short) object;
				darsadi.add(tim_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		for (TimEntity timEntity : teams) {
			if(darsadi.contains(timEntity.getId()))
				answer.add(true);
			else answer.add(false);
		}
		return answer;
	}
	
	
	@Transactional
	public ArrayList<Boolean> darsadiHoghoghiTims(List<TimEntity> teams,Date start,Date end){
		ArrayList<Boolean> answer=new ArrayList<Boolean>(teams.size());
		Session session = siasatDao.getHibernateTemplate().getSessionFactory().openSession();
		
		KodEntity kod=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeTim);
		ArrayList<Short> darsadi=new ArrayList<Short>();
		String sql="select t.ShenaseyeTim from tim t join siasat s on "+
		"t.ShenaseyeGorooheReshteyeVarzeshi=s.ShenaseyeGorooheReshteyeVarzeshi and "+
		"s.SatheTim=t.SatheTim and s.ShenaseyeJensiat=t.ShenaseyeJensiat and "+
		"s.TaTarikh >= t.TarikheSabtTim and s.GheyreFaal=0 and t.GheyreFaal=0 and s.ShenaseyeNoePardakht=" ;
		//2 darsad garardad
		sql+=Constants.NoePardakht_Darsadi;
		sql+=" and s.ShenaseyeNoeSiasat="+kod.getId();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" group by t.ShenaseyeTim";
		Query query = session.createSQLQuery(sql);
		if(start!=null){
			query.setDate("start",start);
		}
		if(end!=null){
			 query.setDate("end",end);
		}
		@SuppressWarnings("rawtypes")
		List l=query.list();
		for (Object object : l) {
			try {
				Short tim_id=(Short) object;
				darsadi.add(tim_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		for (TimEntity timEntity : teams) {
			if(darsadi.contains(timEntity.getId()))
				answer.add(true);
			else answer.add(false);
		}
		return answer;
	}
	
	
	@Transactional
	public ArrayList<Integer> getSumOfHaghighiTims(List<TimEntity> teams,Date start,Date end){
		ArrayList<Integer> answer=new ArrayList<Integer>(teams.size());
		Session session = siasatDao.getHibernateTemplate().getSessionFactory().openSession();
		HashMap<Short,Integer> mymap=new HashMap<Short,Integer>();
		String sql="select mtable2.ShenaseyeTim,sum(siasat.Mizan) from " +
				"(select distinct s.ShenaseyeSiasat,o.ShenaseyeOzv from siasat s join tim t on t.SatheTim=s.SatheTim " +
				"and t.ShenaseyeGorooheReshteyeVarzeshi=s.ShenaseyeGorooheReshteyeVarzeshi and " +
				"s.GheyreFaal = 0 and s.ShenaseyeNoeSiasat=";
		sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeOzveTim).getId();
		sql+=" and s.ShenaseyeNoePardakht=";
		//1 mablagh tayin shode
		sql+=Constants.NoePardakht_Mablaghi;
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join ozvetim o on s.SemateOzv=o.SemateOzv and" +
				" o.ShorooeOzviat <= s.TaTarikh and if (o.PayaneOzviat is null ,true , o.PayaneOzviat >= s.AzTarikh )" +
				" and t.ShenaseyeTim=o.ShenaseyeTim join " +
				" ozv on ozv.ShenaseyeOzv=o.ShenaseyeOzv and ozv.Jensiat=s.ShenaseyeJensiat ) as mtable" +
				" join siasat on mtable.ShenaseyeSiasat=siasat.Shenaseyesiasat join " +
				" (select ozvetim.ShenaseyeOzv,ozvetim.ShenaseyeTim from ozvetim group by ozvetim.ShenaseyeOzv ) as mtable2" +
				" on mtable.ShenaseyeOzv=mtable2.ShenaseyeOzv group by mtable2.ShenaseyeTim";
		Query query = session.createSQLQuery(sql);
		if(start!=null){
			query.setDate("start",start);
		}
		if(end!=null){
			 query.setDate("end",end);
		}
		@SuppressWarnings("rawtypes")
		List l=query.list();
		for (Object object : l) {
			try {
				Object[] tmp = (Object[]) object;
				if(tmp[0] ==null)
					break;
				Short tim_id=(Short) tmp[0];
				Integer sum=Integer.parseInt(tmp[1].toString());
				mymap.put(tim_id, sum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		for (TimEntity timEntity : teams) {
			if(mymap.containsKey(timEntity.getId()))
				answer.add(mymap.get(timEntity.getId()));
			else answer.add(0);
		}
		return answer;
	}
	
	
	@Transactional
	public ArrayList<Integer> getSumOfHoghoghiTims(List<TimEntity> teams,Date start,Date end){
		ArrayList<Integer> answer=new ArrayList<Integer>(teams.size());
		Session session = siasatDao.getHibernateTemplate().getSessionFactory().openSession();
		HashMap<Short,Integer> mymap=new HashMap<Short,Integer>();
		KodEntity kod=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeTim);
		
		String sql="select t.ShenaseyeTim,sum(s.Mizan) from tim t join siasat s on "+
		"t.ShenaseyeGorooheReshteyeVarzeshi=s.ShenaseyeGorooheReshteyeVarzeshi and "+
		"s.SatheTim=t.SatheTim and s.ShenaseyeJensiat=t.ShenaseyeJensiat and "+
		"s.TaTarikh >= t.TarikheSabtTim and s.GheyreFaal=0 and t.GheyreFaal=0 and s.ShenaseyeNoePardakht=" ;
		//1 mablagh tayin shode
		sql+=Constants.NoePardakht_Mablaghi;
		sql+=" and s.ShenaseyeNoeSiasat="+kod.getId();
		if(start!=null){
			sql+=" and s.AzTarikh > :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh < :end "; 
		}
		sql+=" group by t.ShenaseyeTim";
		Query query = session.createSQLQuery(sql);
		if(start!=null){
			query.setDate("start",start);
		}
		if(end!=null){
			 query.setDate("end",end);
		}
		@SuppressWarnings("rawtypes")
		List l=query.list();
		for (Object object : l) {
			try {
				Object[] tmp = (Object[]) object;
				if(tmp[0] ==null)
					break;
				Short tim_id=(Short) tmp[0];
				Integer sum=Integer.parseInt(tmp[1].toString());
				mymap.put(tim_id, sum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		for (TimEntity timEntity : teams) {
			if(mymap.containsKey(timEntity.getId()))
				answer.add(mymap.get(timEntity.getId()));
			else answer.add(0);
		}
		return answer;
	}
	
		
	@Transactional
	public List<SiasatEntity> retrieveByCriteria(DetachedCriteria criteria){
		List<SiasatEntity> list = siasatDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public Boolean isSiasatTekrari(SiasatEntity siasat, boolean virayesh) {
		if (siasat == null)
			return false;
		
		DetachedCriteria criteria = DetachedCriteria
				.forClass(SiasatEntity.class);
		criteria.add(Restrictions.eq("jensiat", siasat.getJensiat()));
		criteria.add(Restrictions.eq("noesisat", siasat.getNoesisat()));
		criteria.add(Restrictions.eq("portal", PermissionUtil.getCurrentPortal()));
		
		
		if (siasat.getReshteVarzeshi() == null)
			criteria.add(Restrictions.isNull("reshteVarzeshi"));
		else
			criteria.add(Restrictions.eq("reshteVarzeshi", siasat.getReshteVarzeshi()));
		
		if (siasat.getGorooheReshteVarzeshi() == null)
			criteria.add(Restrictions.isNull("gorooheReshteVarzeshi"));
		else
			criteria.add(Restrictions.eq("gorooheReshteVarzeshi", siasat.getGorooheReshteVarzeshi()));
		
		if (siasat.getSatheTim() == null)
			criteria.add(Restrictions.isNull("satheTim"));
		else
			criteria.add(Restrictions.eq("satheTim", siasat.getSatheTim()));
		
		if (siasat.getGorooheKarbari() == null)
			criteria.add(Restrictions.isNull("gorooheKarbari"));
		else
			criteria.add(Restrictions.eq("gorooheKarbari", siasat.getGorooheKarbari()));
		
		if (siasat.getSemateOzv() == null)
			criteria.add(Restrictions.isNull("semateOzv"));
		else
			criteria.add(Restrictions.eq("semateOzv", siasat.getSemateOzv()));
		
		Criterion rest3 = Restrictions.and(
				Restrictions.ge("azTarikh", siasat.getTaTarikh()),
				Restrictions.le("taTarikh", siasat.getAzTarikh()));
		
		Criterion rest4 = Restrictions.and(
				Restrictions.ge("taTarikh", siasat.getAzTarikh()),
				Restrictions.le("azTarikh", siasat.getTaTarikh()));
		
		criteria.add(Restrictions.or(rest3, rest4));
		
		if (virayesh)
		{
			if (siasatDao.getCountByCriteria(criteria) > 1)
				return true;
		}
		else
		{
			if (siasatDao.getCountByCriteria(criteria) > 0)
				return true;
		}
		return false;
	}
	
	@Transactional
	public SiasatEntity retrieveById(Integer id) {
		return siasatDao.retrieveByID(id);
	}
	
	@Transactional
	public void update(SiasatEntity entity) {
		siasatDao.update(entity);
	}
	
	@Transactional
	public void delete(Integer id) {
		siasatDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void create(SiasatEntity entity) {
		siasatDao.create(entity);
	}
}
