package ir.infosphere.sport.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import ir.infosphere.sport.bean.SemateTimCountReportBean;
import ir.infosphere.sport.entity.OzveTimEntity;

@Component
public class OzveTimDao extends BaseDao<OzveTimEntity, Integer> {

	@Override
	protected Class<OzveTimEntity> getEntityType() {
		return OzveTimEntity.class;
	}

	public List<SemateTimCountReportBean> getSematCountReport(Integer shenaseyeOstan) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String queryString0 = "SELECT semat.Meghdar, COUNT(*) FROM ozvetim "
				+ "INNER JOIN tim AS team ON ozvetim.ShenaseyeTim = team.ShenaseyeTim "
				+ "INNER JOIN nahiyeh AS shahr ON team.ShenaseyeNahiye = shahr.ShenaseyeNahiye "
				+ "INNER JOIN nahiyeh AS shahrestan ON shahr.ShenaseyeValed = shahrestan.ShenaseyeNahiye "
				+ "INNER JOIN nahiyeh AS ostan ON shahrestan.ShenaseyeValed = ostan.ShenaseyeNahiye "
				+ "INNER JOIN kod AS semat ON ozvetim.SemateOzv = semat.ShenaseyeKod ";
		String queryString1 = "WHERE ostan.shenaseyenahiye = " + shenaseyeOstan;
		String queryString2 = " GROUP BY ozvetim.SemateOzv with rollup";
		String queryString;
		if (shenaseyeOstan == null) {
			queryString = queryString0 + queryString2;
		} else {
			queryString = queryString0 + queryString1 + queryString2;
		}
		Query q = session.createSQLQuery(queryString);
		@SuppressWarnings("rawtypes")
		List temp = q.list();
		session.flush();
		session.disconnect();
		session.close();

		List<SemateTimCountReportBean> ret = new ArrayList<>();
		for (Object object : temp) {

			try {
				Object[] tmp = (Object[]) object;
				SemateTimCountReportBean record = new SemateTimCountReportBean();

				if (tmp[0] != null) {
					String nam = tmp[0].toString();
					record.setSemat(nam);
				} else {
					record.setSemat("مجموع");
				}

				record.setTedad(((BigInteger) tmp[1]).intValue());

				ret.add(record);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return ret;
	}

}
