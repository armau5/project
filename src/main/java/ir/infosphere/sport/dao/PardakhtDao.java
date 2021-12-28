package ir.infosphere.sport.dao;


import ir.infosphere.sport.bean.ForoosheKartReportBean;
import ir.infosphere.sport.bean.ForoosheKarteGoroohiReportBean;
import ir.infosphere.sport.entity.PardakhtEntity;
import ir.infosphere.sport.util.dargah.PardakhtUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class PardakhtDao extends BaseDao<PardakhtEntity, Integer> {

	@Override
	protected Class<PardakhtEntity> getEntityType() {
		return PardakhtEntity.class;
	}


	
	public List<ForoosheKartReportBean> getForoosheKartReport(
			Long shenaseyePortal, Short shenaseyeKodeForoosheKart,
			Date azTarikh, Date taTarikh) {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd");
		String query = "SELECT mablagh, count(*), sum(mablagh) FROM pardakht join dargah on pardakht.shenaseyedargah = dargah.shenaseyedargah where pardakht.HalatePardakht = "
				+ PardakhtUtil.transactionOK
				+ " and pardakht.shenaseyedastebandi = "
				+ shenaseyeKodeForoosheKart
				+ " and dargah.shenaseyeportal = "
				+ shenaseyePortal
				+ " and pardakht.tarikhzaman >= '"
				+ sdfDestination.format(azTarikh)
				+ "' and pardakht.tarikhzaman <= '"
				+ sdfDestination.format(taTarikh)
				+ "' group by mablagh with rollup";
		Query q = session.createSQLQuery(query);
		@SuppressWarnings("rawtypes")
		List temp = q.list();
		session.flush();
		session.disconnect();
		session.close();

		List<ForoosheKartReportBean> ret = new ArrayList<>();
		for (Object object : temp) {

			try {
				Object[] tmp = (Object[]) object;
				ForoosheKartReportBean record = new ForoosheKartReportBean();

				record.setFi((Integer) tmp[0]);
				record.setTedad(((BigInteger) tmp[1]).intValue());
				record.setGheymat(((BigDecimal) tmp[2]).longValue());

				ret.add(record);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return ret;
	}

	public List<ForoosheKarteGoroohiReportBean> getForoosheKarteGoroohiReport(
			Long shenaseyePortal, Short shenaseyeKodeForoosheKarteGoroohi,
			Date azTarikh, Date taTarikh) {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd");
		String query = "SELECT count(*), sum(mablagh) FROM pardakht join dargah on pardakht.shenaseyedargah = dargah.shenaseyedargah where pardakht.HalatePardakht = "
				+ PardakhtUtil.transactionOK
				+ " and pardakht.shenaseyedastebandi = "
				+ shenaseyeKodeForoosheKarteGoroohi
				+ " and dargah.shenaseyeportal = "
				+ shenaseyePortal
				+ " and pardakht.tarikhzaman >= '"
				+ sdfDestination.format(azTarikh)
				+ "' and pardakht.tarikhzaman <= '"
				+ sdfDestination.format(taTarikh)
				+ "' group by mablagh with rollup";
		Query q = session.createSQLQuery(query);
		@SuppressWarnings("rawtypes")
		List temp = q.list();
		session.flush();
		session.disconnect();
		session.close();

		List<ForoosheKarteGoroohiReportBean> ret = new ArrayList<>();
		for (Object object : temp) {

			try {
				Object[] tmp = (Object[]) object;
				ForoosheKarteGoroohiReportBean record = new ForoosheKarteGoroohiReportBean();

				record.setTedad(((BigInteger) tmp[0]).intValue());
				record.setGheymat(((BigDecimal) tmp[1]).longValue());

				ret.add(record);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return ret;
	}

}
