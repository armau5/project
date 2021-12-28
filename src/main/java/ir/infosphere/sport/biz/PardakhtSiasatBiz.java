package ir.infosphere.sport.biz;

import ir.infosphere.sport.bean.OzvBean;
import ir.infosphere.sport.bean.PardakhtBean;
import ir.infosphere.sport.bean.SiasatPardakhtOzveyatBean;
import ir.infosphere.sport.dao.PardakhtSiasatDao;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.MadreseEntity;
import ir.infosphere.sport.entity.OzvEntity;
import ir.infosphere.sport.entity.PardakhtEntity;
import ir.infosphere.sport.entity.PardakhtSiasatEntity;
import ir.infosphere.sport.entity.SiasatEntity;
import ir.infosphere.sport.entity.TimEntity;
import ir.infosphere.sport.util.dargah.PardakhtUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PardakhtSiasatBiz {
	@Autowired
	private PardakhtSiasatDao pardakhtSiasatDao;
	@Autowired
	private KodBiz kodBiz;
	@Autowired
	private SiasatBiz siasatBiz;
	@Autowired
	private RahgiriBiz rahgiriBiz;
	private HashMap<Integer,SiasatEntity> map;
	//use instead of retriveByID for high performance
	public void setMap(){
		List<SiasatEntity> siasats=siasatBiz.getAllSiasat();
		map=new HashMap<Integer,SiasatEntity>(siasats.size());
		for (SiasatEntity siasatEntity : siasats) {
			map.put(siasatEntity.getId(),siasatEntity);
		}
	}
	

	@Transactional
	public ArrayList<Boolean> darsadiPardaktiHaghaghiMadreseha(List<MadreseEntity> madreseha,Date start,Date end){
		ArrayList<Boolean> answer=new ArrayList<Boolean>(madreseha.size());
		Session session = pardakhtSiasatDao.getHibernateTemplate().getSessionFactory().openSession();
		HashMap<Integer,Integer> mymap=new HashMap<Integer,Integer>();
		String sql="select m.ShenaseyeMadrese,sum(s.Mizan) from siasat s " ;
		sql+="join madrese m on  m.ShenaseyeReshteVarzeshi=s.ShenaseyeReshteVarzeshi and s.GheyreFaal = 0 and s.ShenaseyeNoePardakht= ";
		//2 darsad garardad
		sql+=Constants.NoePardakht_Darsadi;
		sql+= " and s.ShenaseyeNoeSiasat=";
		sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeOzveMadrese).getId().toString();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join ozvemadrese o on m.ShenaseyeMadrese=o.ShenaseyeMadrese  and "+
		"s.SemateOzv=o.SemateOzv  and o.TarikheSabteNam <= s.TaTarikh "+
		"and if(o.TarikhePayaneEtebar is null ,true , o.TarikhePayaneEtebar >= s.AzTarikh ) ";
		
		sql+=" join pardakhtsiasat par on par.ShenaseyeSiasat=s.Shenaseyesiasat  and par.ShenaseyeOzv=o.ShenaseyeOzv " +
				"join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
		sql+=" group by o.ShenaseyeMadrese";
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
				answer.add(true);
			else answer.add(false);
		}
		return answer;
	}
	
	
	@Transactional
	public ArrayList<Boolean> darsadiPardaktiHoghoghiMadreseha(List<MadreseEntity> madreseha,Date start,Date end){
		ArrayList<Boolean> answer=new ArrayList<Boolean>(madreseha.size());
		Session session = pardakhtSiasatDao.getHibernateTemplate().getSessionFactory().openSession();
		HashMap<Integer,Integer> mymap=new HashMap<Integer,Integer>();
		String sql="select par.ShenaseyeMadreseYaTim,sum(s.mizan)" +
				" from pardakhtsiasat par join siasat s on par.ShenaseyeSiasat=s.Shenaseyesiasat " +
				"and s.ShenaseyeNoePardakht=";
		//2 darsad garardad
		sql+=Constants.NoePardakht_Darsadi;
		sql+=" and s.ShenaseyeNoeSiasat="+kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeMadrese).getId().toString();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
		sql+=" group by par.ShenaseyeMadreseYaTim";
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
				answer.add(true);
			else answer.add(false);
		}
		return answer;
	}
	
	
	
	@Transactional
	public ArrayList<Integer> sumPardaktiHoghoghiMadreseha(List<MadreseEntity> madreseha,Date start,Date end){
		ArrayList<Integer> answer=new ArrayList<Integer>(madreseha.size());
		Session session = pardakhtSiasatDao.getHibernateTemplate().getSessionFactory().openSession();
		HashMap<Integer,Integer> mymap=new HashMap<Integer,Integer>();
		String sql="select par.ShenaseyeMadreseYaTim,sum(s.mizan)" +
				" from pardakhtsiasat par join siasat s on par.ShenaseyeSiasat=s.Shenaseyesiasat " +
				"and s.ShenaseyeNoePardakht=";
		//1 mablagh tayin shode
		sql+=Constants.NoePardakht_Mablaghi;
		sql+=" and s.ShenaseyeNoeSiasat="+kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeMadrese).getId().toString();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
		sql+=" group by par.ShenaseyeMadreseYaTim";
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
	public ArrayList<Integer> sumPardaktiHaghaghiMadreseha(List<MadreseEntity> madreseha,Date start,Date end){
		ArrayList<Integer> answer=new ArrayList<Integer>(madreseha.size());
		Session session = pardakhtSiasatDao.getHibernateTemplate().getSessionFactory().openSession();
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
		sql+=" on mtable.ShenaseyeOzv=mtable2.ShenaseyeOzv ";
		sql+=" join pardakhtsiasat par on par.ShenaseyeSiasat=siasat.Shenaseyesiasat and par.ShenaseyeOzv=mtable.ShenaseyeOzv " ;
		sql+=" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
		sql+=" group by mtable2.ShenaseyeMadrese";
		
		
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
	public List<SiasatPardakhtOzveyatBean> madreseHoghoghiPardakhti(Date start,Date end) {
		String sql="select par.ShenaseyeMadreseYaTim,par.ShenaseyeSiasat,ozv.KodeMeli,ozv.Nam,ozv.Famil,p.Mablagh,p.KodeRahgiri,p.TarikhZaman,ozv.ShenaseyeOzv" +
				" from pardakhtsiasat par join siasat s on par.ShenaseyeSiasat=s.Shenaseyesiasat and s.ShenaseyeNoeSiasat=";
		sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeMadrese).getId().toString();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
		sql+=" join ozv on p.ShenaseyeOzv=ozv.ShenaseyeOzv";
		Session session = getPardakhtSiasatDao().getHibernateTemplate().getSessionFactory().openSession();
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
				Integer mablagh= (Integer) tmp[5];
				String rahgiri=(String) tmp[6];
				Date zaman=(Date) tmp[7];
				Integer id_ozv=(Integer) tmp[8];
				records.add(new SiasatPardakhtOzveyatBean(new OzvBean(kodeMeli,name,famil,id_ozv),
						new PardakhtBean(mablagh,rahgiri,zaman),s,madrese,null));
				
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
	public List<SiasatPardakhtOzveyatBean> madreseHaghighiPardakhti(Date start,Date end) {
		String sql="select par.ShenaseyeSiasat,ozv.KodeMeli,ozv.Nam,ozv.Famil,p.Mablagh,p.KodeRahgiri,p.TarikhZaman,ozv.ShenaseyeOzv " +
				"from pardakhtsiasat par join siasat s on par.ShenaseyeSiasat=s.Shenaseyesiasat and s.ShenaseyeNoeSiasat=";
		sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeOzveMadrese).getId().toString();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
		sql+=" join ozv on p.ShenaseyeOzv=ozv.ShenaseyeOzv";
		Session session = getPardakhtSiasatDao().getHibernateTemplate().getSessionFactory().openSession();
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
		for (Object object : l) {
			try {
				Object[] tmp = (Object[]) object;
				if(tmp[0] ==null)
					continue;
				SiasatEntity s=map.get((Integer) tmp[0]);
				String kodeMeli=(String) tmp[1];
				String name=(String) tmp[2];
				String famil=(String) tmp[3];
				Integer mablagh= (Integer) tmp[4];
				String rahgiri=(String) tmp[5];
				Date zaman=(Date) tmp[6];
				Integer id_ozv=(Integer) tmp[7];
				records.add(new SiasatPardakhtOzveyatBean
						(new OzvBean(kodeMeli,name,famil,id_ozv),new PardakhtBean(mablagh,rahgiri,zaman),s,null,null));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		return records;
	}
	
	public ArrayList<SiasatPardakhtOzveyatBean> getSiasatHaghighiMadresePardakhti(MadreseEntity madrese,Date start,Date end){
		Session session = getPardakhtSiasatDao().getHibernateTemplate().getSessionFactory().openSession();
		ArrayList<SiasatPardakhtOzveyatBean> answer=new ArrayList<SiasatPardakhtOzveyatBean>();
		String sql="select distinct(par.ShenaseyeSiasat),ozv.KodeMeli,ozv.Nam,ozv.Famil,p.Mablagh,p.KodeRahgiri,p.TarikhZaman,ozv.ShenaseyeOzv " ;
		sql+="from ozvemadrese o join siasat s on  o.ShenaseyeMadrese= " +madrese.getId();
		sql+=" and s.ShenaseyeNoeSiasat=";
		sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeOzveMadrese).getId().toString();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join pardakhtsiasat par on par.ShenaseyeSiasat=s.Shenaseyesiasat";
		sql+=" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
		
		sql+=" join ozv on par.ShenaseyeOzv=ozv.ShenaseyeOzv and ozv.ShenaseyeOzv=o.ShenaseyeOzv ";
		
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
						continue;
					SiasatEntity s=map.get((Integer) tmp[0]);
					String kodeMeli=(String) tmp[1];
					String name=(String) tmp[2];
					String famil=(String) tmp[3];
					Integer mablagh= (Integer) tmp[4];
					String rahgiri=(String) tmp[5];
					Date zaman=(Date) tmp[6];
					Integer id_ozv=(Integer) tmp[7];
					answer.add(new SiasatPardakhtOzveyatBean
							(new OzvBean(kodeMeli,name,famil,id_ozv),new PardakhtBean(mablagh,rahgiri,zaman),s,null,null));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		session.flush();
		session.disconnect();
		session.close();
		return answer;
		
	}
	
	public ArrayList<SiasatPardakhtOzveyatBean> getSiasatHoghoghiMadresePardakhti(MadreseEntity madrese,Date start,Date end){
		Session session = getPardakhtSiasatDao().getHibernateTemplate().getSessionFactory().openSession();
		ArrayList<SiasatPardakhtOzveyatBean> answer=new ArrayList<SiasatPardakhtOzveyatBean>();
			String sql="select par.ShenaseyeSiasat,ozv.KodeMeli,ozv.Nam,ozv.Famil,p.Mablagh,p.KodeRahgiri,p.TarikhZaman,ozv.ShenaseyeOzv" +
					" from pardakhtsiasat par join siasat s on par.ShenaseyeSiasat=s.Shenaseyesiasat and s.ShenaseyeNoeSiasat=";
			sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeMadrese).getId().toString();
			if(start!=null){
				sql+=" and s.AzTarikh >= :start ";
			}
			if(end!=null){
				sql+=" and s.TaTarikh <= :end "; 
			}
			sql+=" and par.ShenaseyeMadreseYaTim=" +madrese.getId();
			sql+=" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
			sql+=PardakhtUtil.transactionOK;

			
			sql+=" join ozv on p.ShenaseyeOzv=ozv.ShenaseyeOzv";
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
						continue;
					SiasatEntity s=map.get((Integer) tmp[0]);
					String kodeMeli=(String) tmp[1];
					String name=(String) tmp[2];
					String famil=(String) tmp[3];
					Integer mablagh= (Integer) tmp[4];
					String rahgiri=(String) tmp[5];
					Date zaman=(Date) tmp[6];
					Integer id_ozv=(Integer) tmp[7];
					answer.add(new SiasatPardakhtOzveyatBean
							(new OzvBean(kodeMeli,name,famil,id_ozv),new PardakhtBean(mablagh,rahgiri,zaman),s,madrese.getId(),null));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		session.flush();
		session.disconnect();
		session.close();
		return answer;
		
	}
	
	@Transactional
	public List<PardakhtSiasatEntity> getAllPardakhtHayeSiasatHa() {
		List<PardakhtSiasatEntity> list = getPardakhtSiasatDao().retrieveAll();
		return list;
	}
	
	@Transactional
	public List<PardakhtSiasatEntity> getAllBySiasat(SiasatEntity siasat) {
		if (siasat == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(PardakhtSiasatEntity.class);
		criteria.add(Restrictions.eq("siasat", siasat));
		return getPardakhtSiasatDao().retrieveAllByCriteria(criteria);
	}
	public List<SiasatPardakhtOzveyatBean> pardakhtNashodeSayer(Date start,Date end,Short idGorooheKarbari){
		List<SiasatPardakhtOzveyatBean> all=siasatBiz.getAllSiasatSayer(start, end, idGorooheKarbari);
		all.removeAll(getAllSayerPardakhti(start, end, idGorooheKarbari));
		return all;
	}
	
	public List<SiasatPardakhtOzveyatBean> pardakhtNashodeHoghoghiTims(Date start,Date end){
		List<SiasatPardakhtOzveyatBean> all=siasatBiz.getAllSiasatHoghoghiTims(start, end);
		all.removeAll(getAllTeamHoghoghiPardakhti(start, end));
		return all;
	}
	
	public List<SiasatPardakhtOzveyatBean> pardakhtNashodeHaghighiTims(Date start,Date end){
		ArrayList<SiasatPardakhtOzveyatBean> all=siasatBiz.getAllSiasatHaghighiTims(start, end);
		ArrayList<SiasatPardakhtOzveyatBean> removeTekrari=new ArrayList<SiasatPardakhtOzveyatBean>();
		for (SiasatPardakhtOzveyatBean bean : all) {
			if(!removeTekrari.contains(bean))
				removeTekrari.add(bean);
			else{
				System.out.println("tekrari");
				System.out.println(bean.getOzv().getId());
				System.out.println(bean.getId());
			}
		}
		removeTekrari.removeAll(getAllTeamHaghighiPardakhti(start, end));
		return removeTekrari;
	}
	
	public List<SiasatPardakhtOzveyatBean> pardakhtNashodeHoghoghiMadrese(Date start,Date end){
		List<SiasatPardakhtOzveyatBean> all=siasatBiz.madreseSiasatHoghoghi(start, end);
		all.removeAll(madreseHoghoghiPardakhti(start, end));
		return all;
	}
	
	public List<SiasatPardakhtOzveyatBean> pardakhtNashodeHaghighiMadrese(Date start,Date end){
		ArrayList<SiasatPardakhtOzveyatBean> all=siasatBiz.madreseSiasatHaghighi(start, end);
		ArrayList<SiasatPardakhtOzveyatBean> removeTekrari=new ArrayList<SiasatPardakhtOzveyatBean>();
		for (SiasatPardakhtOzveyatBean bean : all) {
			if(!removeTekrari.contains(bean))
				removeTekrari.add(bean);
			//else System.out.println("tekrari");
		}
		removeTekrari.removeAll(madreseHaghighiPardakhti(start, end));
		return removeTekrari;
	}
	
	public ArrayList<SiasatPardakhtOzveyatBean> getSiasatHaghighiTimPardakhti(TimEntity tim,Date start,Date end){
		Session session = getPardakhtSiasatDao().getHibernateTemplate().getSessionFactory().openSession();
		ArrayList<SiasatPardakhtOzveyatBean> answer=new ArrayList<SiasatPardakhtOzveyatBean>();
		
			String sql="select distinct(par.ShenaseyeSiasat),ozv.KodeMeli,ozv.Nam,ozv.Famil,p.Mablagh,p.KodeRahgiri,p.TarikhZaman,ozv.ShenaseyeOzv " +
					"from pardakhtsiasat par join siasat s on par.ShenaseyeSiasat=s.Shenaseyesiasat and s.ShenaseyeNoeSiasat=";
			sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeOzveTim).getId().toString();
			if(start!=null){
				sql+=" and s.AzTarikh >= :start ";
			}
			if(end!=null){
				sql+=" and s.TaTarikh <= :end "; 
			}
			sql+=" and s.SatheTim = "+tim.getSatheTim().getId()+
					" and s.ShenaseyeGorooheReshteyeVarzeshi = "+tim.getGorooheReshteyeVarzeshi().getId();
			sql+=" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
			sql+=PardakhtUtil.transactionOK;
			
			sql+=" join ozvetim on ozvetim.ShenaseyeTim=";
			sql+=tim.getId();
			sql+=" join ozv on par.ShenaseyeOzv=ozv.ShenaseyeOzv and ozv.ShenaseyeOzv=ozvetim.ShenaseyeOzv";
			
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
						continue;
					SiasatEntity s=map.get((Integer) tmp[0]);
					String kodeMeli=(String) tmp[1];
					String name=(String) tmp[2];
					String famil=(String) tmp[3];
					Integer mablagh= (Integer) tmp[4];
					String rahgiri=(String) tmp[5];
					Date zaman=(Date) tmp[6];
					Integer id_ozv=(Integer) tmp[7];
					answer.add(new SiasatPardakhtOzveyatBean
							(new OzvBean(kodeMeli,name,famil,id_ozv),new PardakhtBean(mablagh,rahgiri,zaman),s,null,null));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		session.flush();
		session.disconnect();
		session.close();
		return answer;
		
	}
	
	public ArrayList<SiasatPardakhtOzveyatBean> getSiasatHoghoghiTimPardakhti(TimEntity tim,Date start,Date end){
		Session session = getPardakhtSiasatDao().getHibernateTemplate().getSessionFactory().openSession();
		ArrayList<SiasatPardakhtOzveyatBean> answer=new ArrayList<SiasatPardakhtOzveyatBean>();
			String sql="select distinct(par.ShenaseyeSiasat),ozv.KodeMeli,ozv.Nam,ozv.Famil,p.Mablagh,p.KodeRahgiri,p.TarikhZaman,ozv.ShenaseyeOzv" +
					" from pardakhtsiasat par join siasat s on par.ShenaseyeSiasat=s.Shenaseyesiasat and s.ShenaseyeNoeSiasat=";
			sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeTim).getId().toString();
			if(start!=null){
				sql+=" and s.AzTarikh >= :start ";
			}
			if(end!=null){
				sql+=" and s.TaTarikh <= :end "; 
			}
			sql+=" and par.ShenaseyeMadreseYaTim=" +tim.getId()+
					" and s.ShenaseyeJensiat= ";
			sql+=tim.getJensiat().getId();
			sql+=" and s.SatheTim = "+tim.getSatheTim().getId()+
					" and s.ShenaseyeGorooheReshteyeVarzeshi = "+tim.getGorooheReshteyeVarzeshi().getId() +
					" and s.TaTarikh >= :payan";
			sql+=" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
			sql+=PardakhtUtil.transactionOK;
			
			sql+=" join ozv on p.ShenaseyeOzv=ozv.ShenaseyeOzv";
			Query query = session.createSQLQuery(sql);
			query.setDate("payan",tim.getTarikheSabtTim());
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
						continue;
					SiasatEntity s=map.get((Integer) tmp[0]);
					String kodeMeli=(String) tmp[1];
					String name=(String) tmp[2];
					String famil=(String) tmp[3];
					Integer mablagh= (Integer) tmp[4];
					String rahgiri=(String) tmp[5];
					Date zaman=(Date) tmp[6];
					Integer id_ozv=(Integer) tmp[7];
					answer.add(new SiasatPardakhtOzveyatBean
							(new OzvBean(kodeMeli,name,famil,id_ozv),new PardakhtBean(mablagh,rahgiri,zaman),s,null,tim.getId()));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		session.flush();
		session.disconnect();
		session.close();
		return answer;
		
	}
	
	@Transactional
	public ArrayList<Integer> sumPardaktiHoghoghiTimha(List<TimEntity> timha,Date start,Date end){
		ArrayList<Integer> answer=new ArrayList<Integer>(timha.size());
		Session session = pardakhtSiasatDao.getHibernateTemplate().getSessionFactory().openSession();
		HashMap<String,Integer> mymap=new HashMap<String,Integer>();
		String sql="select par.ShenaseyeMadreseYaTim,sum(s.mizan)" +
				" from pardakhtsiasat par join siasat s on par.ShenaseyeSiasat=s.Shenaseyesiasat " +
				"and s.ShenaseyeNoePardakht=";
		//1 mablagh tayin shode
		sql+=Constants.NoePardakht_Mablaghi;
		sql+=" and s.ShenaseyeNoeSiasat="+kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeTim).getId().toString();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
		sql+=" group by par.ShenaseyeMadreseYaTim";
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
				String tim_id=tmp[0].toString();
				Integer sum=Integer.parseInt(tmp[1].toString());
				mymap.put(tim_id, sum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		for (TimEntity tim : timha) {
			if(mymap.containsKey(tim.getId().toString())){
				answer.add(mymap.get(tim.getId().toString()));
			}
				
			else answer.add(0);
		}
		return answer;
	}
	
	@Transactional
	public ArrayList<Integer> sumPardaktiHaghaghiTimha(List<TimEntity> timha,Date start,Date end){
		ArrayList<Integer> answer=new ArrayList<Integer>(timha.size());
		Session session = pardakhtSiasatDao.getHibernateTemplate().getSessionFactory().openSession();
		HashMap<String,Integer> mymap=new HashMap<String,Integer>();

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
				" on mtable.ShenaseyeOzv=mtable2.ShenaseyeOzv" +
				" join pardakhtsiasat par on par.ShenaseyeSiasat=siasat.Shenaseyesiasat and par.ShenaseyeOzv=mtable.ShenaseyeOzv " +
				" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
		sql+=" group by  mtable2.ShenaseyeTim";
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
				String tim_id=tmp[0].toString();
				Integer sum=Integer.parseInt(tmp[1].toString());
				mymap.put(tim_id, sum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		for (TimEntity tim : timha) {
			if(mymap.containsKey(tim.getId().toString())){
				answer.add(mymap.get(tim.getId().toString()));
			}
				
			else answer.add(0);
		}
		return answer;
	}
	
	
	@Transactional
	public ArrayList<Boolean> darsadiPardaktiHoghoghiTimha(List<TimEntity> timha,Date start,Date end){
		ArrayList<Boolean> answer=new ArrayList<Boolean>(timha.size());
		Session session = pardakhtSiasatDao.getHibernateTemplate().getSessionFactory().openSession();
		HashMap<String,Integer> mymap=new HashMap<String,Integer>();
		String sql="select par.ShenaseyeMadreseYaTim,sum(s.mizan)" +
				" from pardakhtsiasat par join siasat s on par.ShenaseyeSiasat=s.Shenaseyesiasat " +
				"and s.ShenaseyeNoePardakht=";
		//2 darsad garardad
		sql+=Constants.NoePardakht_Darsadi;
		sql+=" and s.ShenaseyeNoeSiasat="+kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeTim).getId().toString();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
		sql+=" group by par.ShenaseyeMadreseYaTim";
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
				String tim_id=tmp[0].toString();
				Integer sum=Integer.parseInt(tmp[1].toString());
				mymap.put(tim_id, sum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		for (TimEntity tim : timha) {
			if(mymap.containsKey(tim.getId().toString()))
				answer.add(true);
			else answer.add(false);
		}
		return answer;
	}
	
	@Transactional
	public ArrayList<Boolean> darsadiPardaktiHaghaghiTimha(List<TimEntity> timha,Date start,Date end){
		ArrayList<Boolean> answer=new ArrayList<Boolean>(timha.size());
		Session session = pardakhtSiasatDao.getHibernateTemplate().getSessionFactory().openSession();
		HashMap<String,Integer> mymap=new HashMap<String,Integer>();
		String sql="select t.ShenaseyeTim,sum(s.mizan) from siasat s join tim t on t.SatheTim=s.SatheTim "
				+ "and t.ShenaseyeGorooheReshteyeVarzeshi=s.ShenaseyeGorooheReshteyeVarzeshi "
				+ "and s.ShenaseyeNoeSiasat=";
		sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeOzveTim).getId().toString();
		sql+=" and s.ShenaseyeNoePardakht=";
		//2 darsad garardad
		sql+=Constants.NoePardakht_Darsadi;
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join ozvetim o on s.SemateOzv=o.SemateOzv and o.ShorooeOzviat <= s.TaTarikh and if (o.PayaneOzviat is null ,true , o.PayaneOzviat >= s.AzTarikh ) "
				+ "and t.ShenaseyeTim=o.ShenaseyeTim ";
		sql+="join pardakhtsiasat par on par.ShenaseyeSiasat=s.Shenaseyesiasat and par.ShenaseyeOzv=o.ShenaseyeOzv " +
				"join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
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
				String tim_id=tmp[0].toString();
				Integer sum=Integer.parseInt(tmp[1].toString());
				mymap.put(tim_id, sum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.flush();
		session.disconnect();
		session.close();
		for (TimEntity tim : timha) {
			if(mymap.containsKey(tim.getId().toString())){
				answer.add(true);
			}
				
			else answer.add(false);
		}
		return answer;
	}
	
	@Transactional
	public List<SiasatPardakhtOzveyatBean> getAllSayerPardakhti(Date start,Date end,Short idGorooheKarbari) {
		String sql="select par.ShenaseyeSiasat,ozv.KodeMeli,ozv.Nam,ozv.Famil,p.Mablagh,p.KodeRahgiri,p.TarikhZaman,ozv.ShenaseyeOzv " +
				"from pardakhtsiasat par join siasat s on par.ShenaseyeSiasat=s.Shenaseyesiasat" +
				" and s.ShenaseyeGorooheKarbari=";
		sql+=idGorooheKarbari;
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
		sql+=" join ozv on par.ShenaseyeOzv=ozv.ShenaseyeOzv";
		Session session = getPardakhtSiasatDao().getHibernateTemplate().getSessionFactory().openSession();
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
		for (Object object : l) {
			try {
				Object[] tmp = (Object[]) object;
				if(tmp[0] ==null)
					continue;
				SiasatEntity s=map.get((Integer) tmp[0]);
				String kodeMeli=(String) tmp[1];
				String name=(String) tmp[2];
				String famil=(String) tmp[3];
				Integer mablagh= (Integer) tmp[4];
				String rahgiri=(String) tmp[5];
				Date zaman=(Date) tmp[6];
				Integer id_ozv=(Integer) tmp[7];
				records.add(new SiasatPardakhtOzveyatBean
						(new OzvBean(kodeMeli,name,famil,id_ozv),new PardakhtBean(mablagh,rahgiri,zaman),s,null,null));
				
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
	public List<SiasatPardakhtOzveyatBean> getAllTeamHoghoghiPardakhti(Date start,Date end) {
		String sql="select par.ShenaseyeMadreseYaTim,par.ShenaseyeSiasat,ozv.ShenaseyeOzv,ozv.KodeMeli,ozv.Nam,ozv.Famil,p.Mablagh,p.KodeRahgiri,p.TarikhZaman" +
				" from pardakhtsiasat par join siasat s on par.ShenaseyeSiasat=s.Shenaseyesiasat and s.ShenaseyeNoeSiasat=";
		sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeTim).getId().toString();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
		sql+=" join ozv on p.ShenaseyeOzv=ozv.ShenaseyeOzv";
		Session session = getPardakhtSiasatDao().getHibernateTemplate().getSessionFactory().openSession();
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
		for (Object object : l) {
			try {
				Object[] tmp = (Object[]) object;
				if(tmp[0] ==null)
					continue;
				Integer tim=(Integer) tmp[0];
				SiasatEntity s=map.get((Integer) tmp[1]);
				Integer id_ozv=(Integer) tmp[2];
				String kodeMeli=(String) tmp[3];
				String name=(String) tmp[4];
				String famil=(String) tmp[5];
				Integer mablagh= (Integer) tmp[6];
				String rahgiri=(String) tmp[7];
				Date zaman=(Date) tmp[8];
				records.add(new SiasatPardakhtOzveyatBean
						(new OzvBean(kodeMeli,name,famil,id_ozv),new PardakhtBean(mablagh,rahgiri,zaman),s,null,tim.shortValue()));
				
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
	public List<SiasatPardakhtOzveyatBean> getAllTeamHaghighiPardakhti(Date start,Date end) {
		String sql="select par.ShenaseyeSiasat,ozv.ShenaseyeOzv,ozv.KodeMeli,ozv.Nam,ozv.Famil,p.Mablagh,p.KodeRahgiri,p.TarikhZaman " +
				"from pardakhtsiasat par join siasat s on par.ShenaseyeSiasat=s.Shenaseyesiasat and s.ShenaseyeNoeSiasat=";
		sql+=kodBiz.getKodEntity(Constants.NoeSiasat,Constants.NoeOzveTim).getId().toString();
		if(start!=null){
			sql+=" and s.AzTarikh >= :start ";
		}
		if(end!=null){
			sql+=" and s.TaTarikh <= :end "; 
		}
		sql+=" join pardakht p on p.ShenaseyePardakht=par.ShenaseyePardakht and p.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
		sql+=" join ozv on p.ShenaseyeOzv=ozv.ShenaseyeOzv";
		Session session = getPardakhtSiasatDao().getHibernateTemplate().getSessionFactory().openSession();
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
		for (Object object : l) {
			try {
				Object[] tmp = (Object[]) object;
				if(tmp[0] ==null)
					continue;
				SiasatEntity s=map.get((Integer) tmp[0]);
				Integer id_ozv=(Integer) tmp[1];
				String kodeMeli=(String) tmp[2];
				String name=(String) tmp[3];
				String famil=(String) tmp[4];
				Integer mablagh= (Integer) tmp[5];
				String rahgiri=(String) tmp[6];
				Date zaman=(Date) tmp[7];
				records.add(new SiasatPardakhtOzveyatBean
						(new OzvBean(kodeMeli,name,famil,id_ozv),new PardakhtBean(mablagh,rahgiri,zaman),s,null,null));
				
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
	public List<PardakhtSiasatEntity> getAllByPardakht(PardakhtEntity pardakht) {
		if (pardakht == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(PardakhtSiasatEntity.class);
		criteria.add(Restrictions.eq("pardakht", pardakht));
		return getPardakhtSiasatDao().retrieveAllByCriteria(criteria);
	}

	@Transactional
	public Boolean pardakhtShodeYaKheyr(SiasatEntity siasat, OzvEntity ozv) {
		
		if (siasat == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(PardakhtSiasatEntity.class);
		criteria.add(Restrictions.eq("siasat", siasat));
		criteria.add(Restrictions.eq("ozv", ozv));
		List<PardakhtSiasatEntity> temp = getPardakhtSiasatDao()
				.retrieveAllByCriteria(criteria);

		for (PardakhtSiasatEntity item : temp) {
			if (item.getPardakht().getHalatePardakht() == PardakhtUtil.transactionOK)
				return true;
		}

		return false;
	}

	@Transactional
	public Boolean pardakhtShodeYaKheyrDarTimMadrese(SiasatEntity siasat,
			OzvEntity ozv, int madrese_tim_id) {
		if (siasat == null)
			return null;
		String sql="select p.ShenaseyePardakhtSiasat from pardakhtsiasat p join pardakht on p.ShenaseyePardakht=pardakht.ShenaseyePardakht " +
		"and pardakht.HalatePardakht=";
		sql+=PardakhtUtil.transactionOK;
		sql+=" and  p.ShenaseyeSiasat=";
		sql+=siasat.getId();
		sql+=" and p.ShenaseyeOzv=";
		sql+=ozv.getId();
		sql+=" and p.ShenaseyeMadreseYaTim=";
		sql+=madrese_tim_id;
		Session session = getPardakhtSiasatDao().getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(sql);
		@SuppressWarnings("rawtypes")
		List l=query.list();
		session.flush();
		session.disconnect();
		session.close();
		if(l.size()!=0)
			return true;
		return false;
	}

	@Transactional
	public Boolean pardakhtShodeYaKheyrDarTimMadrese(SiasatEntity siasat,
			int madrese_tim_id) {
		if (siasat == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(PardakhtSiasatEntity.class);
		criteria.add(Restrictions.eq("siasat", siasat));
		criteria.add(Restrictions.eq("madreseTim", madrese_tim_id));
		List<PardakhtSiasatEntity> temp = getPardakhtSiasatDao()
				.retrieveAllByCriteria(criteria);

		for (PardakhtSiasatEntity item : temp) {
			if (item.getPardakht().getHalatePardakht()==PardakhtUtil.transactionOK)
				return true;
		}

		return false;
	}

	@Transactional
	public PardakhtEntity getPardakht(SiasatEntity siasat, OzvEntity ozv) {
		if (siasat == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(PardakhtSiasatEntity.class);
		criteria.add(Restrictions.eq("siasat", siasat));
		criteria.add(Restrictions.eq("ozv", ozv));
		List<PardakhtSiasatEntity> temp = getPardakhtSiasatDao()
				.retrieveAllByCriteria(criteria);

		for (PardakhtSiasatEntity item : temp) {
			if (item.getPardakht().getHalatePardakht()==PardakhtUtil.transactionOK)
				return item.getPardakht();
		}

		return null;
	}

	@Transactional
	public List<PardakhtSiasatEntity> retrieveByCriteria(
			DetachedCriteria criteria) {
		List<PardakhtSiasatEntity> list = getPardakhtSiasatDao()
				.retrieveAllByCriteria(criteria);
		return list;
	}

	@Transactional
	public PardakhtSiasatEntity retrieveById(Integer id) {
		return getPardakhtSiasatDao().retrieveByID(id);
	}

	@Transactional
	public void update(PardakhtSiasatEntity entity) {
		getPardakhtSiasatDao().update(entity);
	}

	@Transactional
	public void delete(Integer id) {
		getPardakhtSiasatDao().delete(retrieveById(id));
	}

	@Transactional
	public void create(PardakhtSiasatEntity entity) {
		getPardakhtSiasatDao().create(entity);
	}

	@Transactional
	public Boolean isPardakhtValid(PardakhtEntity pardakht) {
		List<PardakhtSiasatEntity> list = getAllByPardakht(pardakht);
		if (list == null || list.size() == 0){
			return false;
		}
		else
			return true;
	}

	@Transactional
	public Boolean createPardakhtHa(PardakhtEntity pardakht) {
		List<PardakhtSiasatEntity> list = getAllByPardakht(pardakht);
		if (list == null || list.size() == 0){
			return false;
		}
		
		for (PardakhtSiasatEntity entity : list) {
			rahgiriBiz.createForHagheOzviat(entity, pardakht);
		}
		return true;
	}

	public PardakhtSiasatDao getPardakhtSiasatDao() {
		return pardakhtSiasatDao;
	}

	public void setPardakhtSiasatDao(PardakhtSiasatDao pardakhtSiasatDao) {
		this.pardakhtSiasatDao = pardakhtSiasatDao;
	}
}
