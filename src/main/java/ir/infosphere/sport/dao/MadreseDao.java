package ir.infosphere.sport.dao;

import ir.infosphere.sport.bean.MadreseCountReportBean;
import ir.infosphere.sport.entity.MadreseEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class MadreseDao extends BaseDao<MadreseEntity, Integer> {

	@Override
	protected Class<MadreseEntity> getEntityType() {
		return MadreseEntity.class;
	}

	public List<MadreseCountReportBean> getMadreseCountReport(
			Integer shenaseyeOstan, Short kodeMard) {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		String queryString0 = "SELECT reshteyevarzeshi.NameReshteyeVarzeshi, SUM(IF(shenaseyejensiat= "
				+ kodeMard
				+ ", 1, 0)) as aghayan, SUM(IF(shenaseyejensiat!= "
				+ kodeMard
				+ ", 1, 0)) as khanumha, count(*)  FROM `madrese` join reshteyevarzeshi on madrese.shenaseyereshtevarzeshi=reshteyevarzeshi.shenaseyereshteyevarzeshi ";
		String queryString1 = " join nahiyeh as n1 on n1.shenaseyenahiye = madrese.ShenaseyeNahie join nahiyeh as n2 on n1.shenaseyevaled = n2.shenaseyenahiye join nahiyeh as n3 on n2.shenaseyevaled = n3.shenaseyenahiye ";
		String queryString2 = " WHERE madrese.`GheyreFaal` = 0 ";
		String queryString3 = " and n3.shenaseyenahiye = " + shenaseyeOstan;
		String queryString4 = " group by madrese.shenaseyereshtevarzeshi with rollup ";
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

		List<MadreseCountReportBean> ret = new ArrayList<>();
		for (Object object : temp) {

			try {
				Object[] tmp = (Object[]) object;
				MadreseCountReportBean record = new MadreseCountReportBean();

				if (tmp[0] != null) {
					String nam = tmp[0].toString();
					record.setNameReshteh(nam);
				} else {
					record.setNameReshteh("مجموع");
				}

				record.setTedadAghayan(((BigDecimal) tmp[1]).intValue());
				record.setTedadKhanumha(((BigDecimal) tmp[2]).intValue());
				record.setTedadKol(((BigInteger) tmp[3]).intValue());

				ret.add(record);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return ret;
	}

}