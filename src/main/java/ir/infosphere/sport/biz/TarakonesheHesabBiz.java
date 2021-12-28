package ir.infosphere.sport.biz;

import ir.infosphere.sport.bean.GardeshMaliHesabBean;
import ir.infosphere.sport.dao.TarakonesheHesabDao;
import ir.infosphere.sport.entity.Constants;
import ir.infosphere.sport.entity.FaktorEntity;
import ir.infosphere.sport.entity.HesabEntity;
import ir.infosphere.sport.entity.KodEntity;
import ir.infosphere.sport.entity.SanadetasviyehesabEntity;
import ir.infosphere.sport.entity.TarakonesheHesabEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;

@Component
public class TarakonesheHesabBiz {
	
	@Autowired
	private TarakonesheHesabDao tarakonesheHesabDao;
	@Autowired
	private KodBiz kodBiz;
	@Autowired
	private FaktorBiz faktorBiz;
	
	
	@Transactional
	public TarakonesheHesabEntity getTarakonesheHesabById(Long id) {
		return tarakonesheHesabDao.retrieveByID(id);
	}
	
	@Transactional
	public SanadetasviyehesabEntity getLastSanadeTasviye(HesabEntity hesab){
		if (hesab == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SanadetasviyehesabEntity.class);
		criteria.add(Restrictions.eq("hesab", hesab));
		criteria.addOrder(Order.desc("tarikheTasviye"));
		criteria.addOrder(Order.desc("id"));
		ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
		WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		SanadetasviyehesabBiz sanadetasviyehesabBiz = appContext.getBean(SanadetasviyehesabBiz.class);
		List<SanadetasviyehesabEntity> list = sanadetasviyehesabBiz.retrieveAllByCriteria(criteria); 
		if(list.size() == 0){
			return null;
		}
		return list.get(0);
	}
	
	@Transactional
	public List<TarakonesheHesabEntity> getTarakonesh(HesabEntity hesab, Date azTarikh, Date taTarikh,KodEntity noe) {
		if (taTarikh == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(TarakonesheHesabEntity.class);
		criteria.add(Restrictions.eq("hesab", hesab));
		if(azTarikh!=null)
			criteria.add(Restrictions.ge("zamanTarakonesh", azTarikh));
		criteria.add(Restrictions.le("zamanTarakonesh", taTarikh));
		criteria.add(Restrictions.eq("noeTarakonesh", noe));
		criteria.addOrder(Order.asc("zamanTarakonesh"));
		return tarakonesheHesabDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public HashMap<String, GardeshMaliHesabBean> gardeshHesab(HesabEntity hesab, Date azTarikh, Date taTarikh){
		HashMap<String,GardeshMaliHesabBean> answer=new HashMap<String,GardeshMaliHesabBean>(5);
		Session session = tarakonesheHesabDao.getHibernateTemplate().getSessionFactory().openSession();
		String sqlBase="select sum(t.Mablagh) from tarakoneshehesab t where t.ShenaseyeHesab=";
		sqlBase+=hesab.getId();
		if(azTarikh!=null){
			sqlBase+=" and t.ZamanTarakonesh >= :start";
		}
		sqlBase+=" and t.ZamanTarakonesh < :end";
		sqlBase+=" and  t.NoeTarakonesh=";
		BigDecimal output = null;
		String sql1=sqlBase+kodBiz.getKodEntity(Constants.NoeTarakonesh,Constants.ForoshReyali).getId();
		Query query1 = session.createSQLQuery(sql1);
		if(azTarikh!=null){
			query1.setTimestamp("start",azTarikh);
		}
		query1.setTimestamp("end",taTarikh);
		@SuppressWarnings("rawtypes")
		List l1=query1.list();
		Long sum1 =Long.parseLong("0");
		for (Object object : l1) {
			try {
				output=(BigDecimal) object;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		double sahmRiali=hesab.getSahmeRiali().doubleValue()/100;
		Long bedehkar1=Long.parseLong("0");
		Long bestankar1=Long.parseLong("0");
		if(output!=null){
			sum1=output.longValue();
			bestankar1=(long) (sahmRiali*sum1);
			bedehkar1=sum1-bestankar1;
		}
		answer.put(Constants.ForoshReyali,new GardeshMaliHesabBean(bedehkar1, bestankar1));
		
		String sql2=sqlBase+kodBiz.getKodEntity(Constants.NoeTarakonesh,Constants.ForoshSharj).getId();
		Query query2 = session.createSQLQuery(sql2);
		if(azTarikh!=null){
			query2.setTimestamp("start",azTarikh);
		}
		query2.setTimestamp("end",taTarikh);
		@SuppressWarnings("rawtypes")
		List l2=query2.list();
		Long sum2= Long.parseLong("0");;
		for (Object object : l2) {
			try {
				output=(BigDecimal) object;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		double sahmSharj=hesab.getSahmeSharj().doubleValue()/100;
		Long bedehkar2=Long.parseLong("0");
		Long bestankar2=Long.parseLong("0");
		if(output!=null){
			sum2=output.longValue();
			bestankar2=(long) (sahmSharj*sum2);
			bedehkar2=sum2-bestankar2;
		}
		answer.put(Constants.ForoshSharj,new GardeshMaliHesabBean(bedehkar2, bestankar2));
		
		String sql3=sqlBase+kodBiz.getKodEntity(Constants.NoeTarakonesh,Constants.ForoshBelit).getId();
		Query query3 = session.createSQLQuery(sql3);
		if(azTarikh!=null){
			query3.setTimestamp("start",azTarikh);
		}
		query3.setTimestamp("end",taTarikh);
		@SuppressWarnings("rawtypes")
		List l3=query3.list();
		Long sum3= Long.parseLong("0");
		for (Object object : l3) {
			try {
				output=(BigDecimal) object;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		double sahmBelit=hesab.getSahmeBelit().doubleValue()/100;
		Long bedehkar3=Long.parseLong("0");
		Long bestankar3=Long.parseLong("0");
		if(output!=null){
			sum3=output.longValue();
			bestankar3=(long) (sahmBelit*sum3);
			bedehkar3=sum3-bestankar3;
		}
		answer.put(Constants.ForoshBelit,new GardeshMaliHesabBean(bedehkar3, bestankar3));
		
		String sql4=sqlBase+kodBiz.getKodEntity(Constants.NoeTarakonesh,Constants.ForoshOzviyat).getId();
		Query query4 = session.createSQLQuery(sql4);
		if(azTarikh!=null){
			query4.setTimestamp("start",azTarikh);
		}
		query4.setTimestamp("end",taTarikh);
		@SuppressWarnings("rawtypes")
		List l4=query4.list();
		Long sum4= Long.parseLong("0");
		for (Object object : l4) {
			try {
				output=(BigDecimal) object;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		double sahmeOzviyat=hesab.getSahmeOzviyat().doubleValue()/100;
		Long bedehkar4=Long.parseLong("0");
		Long bestankar4=Long.parseLong("0");
		if(output!=null){
			sum4=output.longValue();
			bestankar4=(long) (sahmeOzviyat*sum4);
			bedehkar4=sum4-bestankar4;
		}
		answer.put(Constants.ForoshOzviyat,new GardeshMaliHesabBean(bedehkar4, bestankar4));
		List<FaktorEntity> bedehkarFaktorKhadamat=faktorBiz.getFaktorsKhadamat(
				hesab,azTarikh, taTarikh,Constants.NoeFactor_SadereAzSamane);
		List<FaktorEntity> bestankarFaktorKhadamat=faktorBiz.getFaktorsKhadamat(
				hesab,azTarikh, taTarikh,Constants.NoeFactor_SadereAzContract);
		Long bedehkar5=Long.parseLong("0");
		Long bestankar5=Long.parseLong("0");
		for (FaktorEntity faktorBedehkar : bedehkarFaktorKhadamat) {
			bedehkar5+=faktorBiz.getJameKoleFaktor(faktorBedehkar);
		}
		for (FaktorEntity faktorBestankar : bestankarFaktorKhadamat) {
			bestankar5+=faktorBiz.getJameKoleFaktor(faktorBestankar);
		}
		answer.put(Constants.Khadamat,new GardeshMaliHesabBean(bedehkar5, bestankar5));
		return answer;
		
	}
	@Transactional
	public Long getMojodiHesab(HesabEntity hesab){
		Long bedehkariha=Long.parseLong("0");
		Long bestankariha=Long.parseLong("0");
		Date azTarikh;
		SanadetasviyehesabEntity Tasviye=getLastSanadeTasviye(hesab);
		if(Tasviye!=null){
			azTarikh=Tasviye.getTarikheTasviye();
			if(Tasviye.getMablagheMande()>0)
				bestankariha+=Tasviye.getMablagheMande();
			else
				bedehkariha+=Math.abs(Tasviye.getMablagheMande());
		}else
			azTarikh=null;
		
		Session session = tarakonesheHesabDao.getHibernateTemplate().getSessionFactory().openSession();
		String sqlBase="select sum(t.Mablagh) from tarakoneshehesab t where t.ShenaseyeHesab=";
		sqlBase+=hesab.getId();
		if(azTarikh!=null){
			sqlBase+=" and t.ZamanTarakonesh >= :start";
		}
		sqlBase+=" and  t.NoeTarakonesh=";
		BigDecimal output = null;
		String sql1=sqlBase+kodBiz.getKodEntity(Constants.NoeTarakonesh,Constants.ForoshReyali).getId();
		Query query1 = session.createSQLQuery(sql1);
		if(azTarikh!=null){
			query1.setTimestamp("start",azTarikh);
		}
		@SuppressWarnings("rawtypes")
		List l1=query1.list();
		Long sum1 =Long.parseLong("0");
		for (Object object : l1) {
			try {
				output=(BigDecimal) object;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		double sahmRiali=hesab.getSahmeRiali().doubleValue()/100;
		Long bedehkar1=Long.parseLong("0");
		Long bestankar1=Long.parseLong("0");
		if(output!=null){
			sum1=output.longValue();
			bestankar1=(long) (sahmRiali*sum1);
			bedehkar1=sum1-bestankar1;
		}
		bedehkariha+=bedehkar1;
		bestankariha+=bestankar1;
		
		String sql2=sqlBase+kodBiz.getKodEntity(Constants.NoeTarakonesh,Constants.ForoshSharj).getId();
		Query query2 = session.createSQLQuery(sql2);
		if(azTarikh!=null){
			query2.setTimestamp("start",azTarikh);
		}
		@SuppressWarnings("rawtypes")
		List l2=query2.list();
		Long sum2= Long.parseLong("0");
		for (Object object : l2) {
			try {
				output=(BigDecimal) object;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		double sahmSharj=hesab.getSahmeSharj().doubleValue()/100;
		Long bedehkar2=Long.parseLong("0");
		Long bestankar2=Long.parseLong("0");
		if(output!=null){
			sum2=output.longValue();
			bestankar2=(long) (sahmSharj*sum2);
			bedehkar2=sum2-bestankar2;
		}
		bedehkariha+=bedehkar2;
		bestankariha+=bestankar2;
		
		String sql3=sqlBase+kodBiz.getKodEntity(Constants.NoeTarakonesh,Constants.ForoshBelit).getId();
		Query query3 = session.createSQLQuery(sql3);
		if(azTarikh!=null){
			query3.setTimestamp("start",azTarikh);
		}
		@SuppressWarnings("rawtypes")
		List l3=query3.list();
		Long sum3= Long.parseLong("0");
		for (Object object : l3) {
			try {
				output=(BigDecimal) object;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		double sahmBelit=hesab.getSahmeBelit().doubleValue()/100;
		Long bedehkar3=Long.parseLong("0");
		Long bestankar3=Long.parseLong("0");
		if(output!=null){
			sum3=output.longValue();
			bestankar3=(long) (sahmBelit*sum3);
			bedehkar3=sum3-bestankar3;
		}
		bedehkariha+=bedehkar3;
		bestankariha+=bestankar3;
		
		String sql4=sqlBase+kodBiz.getKodEntity(Constants.NoeTarakonesh,Constants.ForoshOzviyat).getId();
		Query query4 = session.createSQLQuery(sql4);
		if(azTarikh!=null){
			query4.setTimestamp("start",azTarikh);
		}
		@SuppressWarnings("rawtypes")
		List l4=query4.list();
		Long sum4= Long.parseLong("0");
		for (Object object : l4) {
			try {
				output=(BigDecimal) object;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		double sahmeOzviyat=hesab.getSahmeOzviyat().doubleValue()/100;
		Long bedehkar4=Long.parseLong("0");
		Long bestankar4=Long.parseLong("0");
		if(output!=null){
			sum4=output.longValue();
			bestankar4=(long) (sahmeOzviyat*sum4);
			bedehkar4=sum4-bestankar4;
		}
		bedehkariha+=bedehkar4;
		bestankariha+=bestankar4;
		
		List<FaktorEntity> bedehkarFaktorKhadamat=faktorBiz.getFaktorsKhadamat(
				hesab,azTarikh,null,Constants.NoeFactor_SadereAzSamane);
		List<FaktorEntity> bestankarFaktorKhadamat=faktorBiz.getFaktorsKhadamat(
				hesab,azTarikh,null,Constants.NoeFactor_SadereAzContract);
		Long bedehkar5=Long.parseLong("0");
		Long bestankar5=Long.parseLong("0");
		for (FaktorEntity faktorBedehkar : bedehkarFaktorKhadamat) {
			bedehkar5+=faktorBiz.getJameKoleFaktor(faktorBedehkar);
		}
		for (FaktorEntity faktorBestankar : bestankarFaktorKhadamat) {
			bestankar5+=faktorBiz.getJameKoleFaktor(faktorBestankar);
		}
		bedehkariha+=bedehkar5;
		bestankariha+=bestankar5;
		
		return bestankariha-bedehkariha;
		
	}
	
}
