package ir.infosphere.sport.dao;

import ir.infosphere.sport.bean.FootballAmoozeMadreseCountReportBean;
import ir.infosphere.sport.bean.MorabieMadreseCountReportBean;
import ir.infosphere.sport.entity.OzveMadreseEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class OzveMadreseDao extends BaseDao<OzveMadreseEntity, Integer> {

	@Override
	protected Class<OzveMadreseEntity> getEntityType() {
		return OzveMadreseEntity.class;
	}

	public List<MorabieMadreseCountReportBean> getMorabiCountReport(
			Integer shenaseyeOstan, Short kodeMard, Short kodeSemateMorabi) {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		String queryString0 = "SELECT kodmeghdar.nam, count(*)  FROM `ozvemadrese` join kodmeghdar on kodmeghdar.shenaseyekodmeghdar = ozvemadrese.shenaseyedarajemorabigari join madrese on ozvemadrese.shenaseyemadrese = madrese.shenaseyemadrese ";
		String queryString1 = " join nahiyeh as n1 on n1.shenaseyenahiye = madrese.ShenaseyeNahie join nahiyeh as n2 on n1.shenaseyevaled = n2.shenaseyenahiye join nahiyeh as n3 on n2.shenaseyevaled = n3.shenaseyenahiye ";
		String queryString2 = " WHERE `TarikhePayaneEtebar` IS NULL AND `SemateOzv` = "
				+ kodeSemateMorabi;
		String queryString3 = " and n3.shenaseyenahiye = " + shenaseyeOstan;
		String queryString4 = " group by ozvemadrese.shenaseyedarajemorabigari with rollup ";
		String queryString;
		if (shenaseyeOstan == null) {
			queryString = queryString0 + queryString2 + queryString4;
		} else {
			queryString = queryString0 + queryString1 + queryString2
					+ queryString3 + queryString4;
		}
		Query q = session.createSQLQuery(queryString);
		@SuppressWarnings("rawtypes")
		List temp = q.list();
		session.flush();
		session.disconnect();
		session.close();

		List<MorabieMadreseCountReportBean> ret = new ArrayList<>();
		for (Object object : temp) {

			try {
				Object[] tmp = (Object[]) object;
				MorabieMadreseCountReportBean record = new MorabieMadreseCountReportBean();

				if (tmp[0] != null) {
					String nam = tmp[0].toString();
					record.setDaraje(nam);
				} else {
					record.setDaraje("مجموع");
				}

				record.setTedad(((BigInteger) tmp[1]).intValue());

				ret.add(record);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return ret;
	}

	public FootballAmoozeMadreseCountReportBean getFootballAmoozCountReport(
			Integer shenaseyeOstan, Short kodeMard,
			Short kodeSemateFootballAmooz, Integer senAz, Integer senTa) {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		String queryString0 = "SELECT SUM(IF(jensiat="
				+ kodeMard
				+ ",1,0)) as aghayan,SUM(IF(jensiat!="
				+ kodeMard
				+ ",1,0)) as khanumha, count(*) FROM `ozvemadrese` join `ozv` on ozv.shenaseyeozv = ozvemadrese.shenaseyeozv ";
		String queryString1 = " join nahiyeh as n1 on n1.shenaseyenahiye = ozv.ShenaseyeNahiye join nahiyeh as n2 on n1.shenaseyevaled = n2.shenaseyenahiye join nahiyeh as n3 on n2.shenaseyevaled = n3.shenaseyenahiye ";
		String queryString2 = " WHERE `TarikhePayaneEtebar` IS NULL AND `SemateOzv` = "
				+ kodeSemateFootballAmooz
				+ " and floor(datediff(curdate(),tarikhetavallod) / 365.25) >="
				+ senAz
				+ "  and floor(datediff(curdate(),tarikhetavallod) / 365.25) < "
				+ senTa;
		String queryString3 = " and n3.shenaseyenahiye = " + shenaseyeOstan;
		String queryString;
		if (shenaseyeOstan == null) {
			queryString = queryString0 + queryString2;
		} else {
			queryString = queryString0 + queryString1 + queryString2
					+ queryString3;
		}
		Query q = session.createSQLQuery(queryString);
		@SuppressWarnings("rawtypes")
		List temp = q.list();
		session.flush();
		session.disconnect();
		session.close();

		List<FootballAmoozeMadreseCountReportBean> ret = new ArrayList<>();
		for (Object object : temp) {

			try {
				Object[] tmp = (Object[]) object;
				FootballAmoozeMadreseCountReportBean record = new FootballAmoozeMadreseCountReportBean();

				if (tmp[0] == null)
					record.setTedadAghayan(0);
				else
					record.setTedadAghayan(((BigDecimal) tmp[0]).intValue());
				if (tmp[1] == null)
					record.setTedadKhanumha(0);
				else
					record.setTedadKhanumha(((BigDecimal) tmp[1]).intValue());
				if (tmp[2] == null)
					record.setTedadKol(0);
				else
					record.setTedadKol(((BigInteger) tmp[2]).intValue());
				ret.add(record);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return ret.get(0);
	}

}
