package ir.infosphere.sport.dao;

import ir.infosphere.sport.bean.SandaliMaliReportBean;
import ir.infosphere.sport.bean.SandaliTedadiReportBean;
import ir.infosphere.sport.entity.VaziateSandaliEntity;

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
public class VaziateSandaliDao extends BaseDao<VaziateSandaliEntity, Integer> {

	@Override
	protected Class<VaziateSandaliEntity> getEntityType() {
		return VaziateSandaliEntity.class;
	}

	public List<SandaliMaliReportBean> getSandaliMaliReportForBazi(
			Integer shenaseyeBazi, Short kodeSandalieForookhteShode) {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Query q = session
				.createSQLQuery("select mogheiyyat.namemogheiyyat as nam, sum(IF(shomareyemelli is not null and ismizban = true, 1, 0)) as tedadbamellimizban, sum(IF(shomareyemelli is not null and ismizban = false, 1, 0)) as tedadbamellimihman, sum(IF(shomareyemelli is null, 0, 1)) as tedadbamellikol, max(gheymatbakartemelli) as fimelli, sum(IF(shomareyemelli is not null, gheymatbakartemelli, 0)) as gheymatmelli, sum(IF(shomareyemelli is null and ismizban = true, 1, 0)) as tedadbakartmizban, sum(IF(shomareyemelli is null and ismizban = false, 1, 0)) as tedadbakartmihman, sum(IF(shomareyemelli is null, 1, 0)) as tedadbakartkol, max(gheymat) as fi, sum(IF(shomareyemelli is null, gheymat, 0)) as gheymatkart, count(*) as tedadkol, sum(IF(shomareyemelli is null, gheymat, gheymatbakartemelli)) as gheymat from vaziatesandali join mogheiyyat on mogheiyyat.shenaseyemogheiyyat = vaziatesandali.shenaseyemogheiyyat where shenaseyebazi = "
						+ shenaseyeBazi
						+ " and vaziatesandali = "
						+ kodeSandalieForookhteShode
						+ " group by mogheiyyat.namemogheiyyat with rollup");
		@SuppressWarnings("rawtypes")
		List temp = q.list();
		session.flush();
		session.disconnect();
		session.close();

		List<SandaliMaliReportBean> ret = new ArrayList<>();
		for (Object object : temp) {

			try {
				Object[] tmp = (Object[]) object;
				SandaliMaliReportBean record = new SandaliMaliReportBean();

				if (tmp[0] != null) {
					String nam = tmp[0].toString();
					record.setNameMogheiyat(nam);
				} else {
					record.setNameMogheiyat("مجموع");
				}

				record.setTedadMelliMizban(((BigDecimal) tmp[1]).intValue());
				record.setTedadMelliMihman(((BigDecimal) tmp[2]).intValue());
				record.setTedadMelli(((BigDecimal) tmp[3]).intValue());
				record.setFiMelli(((Integer) tmp[4]));
				record.setGheymatMelli(((BigDecimal) tmp[5]).longValue());

				record.setTedadKartMizban(((BigDecimal) tmp[6]).intValue());
				record.setTedadKartMihman(((BigDecimal) tmp[7]).intValue());
				record.setTedadKart(((BigDecimal) tmp[8]).intValue());
				record.setFiKart(((Integer) tmp[9]));
				record.setGheymatKart(((BigDecimal) tmp[10]).longValue());

				record.setTedadKol(((BigInteger) tmp[11]).intValue());
				record.setGheymatKol(((BigDecimal) tmp[12]).longValue());

				ret.add(record);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public List<SandaliTedadiReportBean> getSandaliTedadiReportForBazi(
			Integer shenaseyeBazi, Short kodeSandalieForookhteShode) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query q = session
				.createSQLQuery("select mogheiyyat.namemogheiyyat as nam,sum(IF(shomareyemelli is null, 0, 1)) as tedadbamellikol," +
								" sum(IF(shomareyemelli is null, 1, 0)) as tedadbakartkol," +
								" count(*) as tedadkol  from bilozvakart.vaziatesandali join bilozvakart.mogheiyyat on mogheiyyat.shenaseyemogheiyyat = vaziatesandali.shenaseyemogheiyyat where ShenaseyeBazi ="
						+ shenaseyeBazi	+ " and vaziatesandali = "
						+ kodeSandalieForookhteShode + " group by mogheiyyat.namemogheiyyat with rollup");
		@SuppressWarnings("rawtypes")
		List temp = q.list();
		session.flush();
		session.disconnect();
		session.close();

		List<SandaliTedadiReportBean> ret = new ArrayList<>();
		for (Object object : temp) {

			try {
				Object[] tmp = (Object[]) object;
				SandaliTedadiReportBean record = new SandaliTedadiReportBean();

				if (tmp[0] != null) {
					String nam = tmp[0].toString();
					record.setNameMogheiyat(nam);
				} else {
					record.setNameMogheiyat("مجموع");
				}

				record.setTedadMelli(((BigDecimal) tmp[1]).intValue());

				record.setTedadKart(((BigDecimal) tmp[2]).intValue());

				record.setTedadKol(((BigInteger) tmp[3]).intValue());

				ret.add(record);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	public List<SandaliMaliReportBean> getSandaliMaliReportForBaziByDate(Integer shenaseyeBazi,
			Short kodeSandalieForookhteShode, Date azTarikh, Date taTarikh) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd");
		Query q;
		if (shenaseyeBazi != 0)
			q = session.createSQLQuery(
					"select mogheiyyat.namemogheiyyat as nam, sum(IF(shomareyemelli is not null and ismizban = true, 1, 0)) as tedadbamellimizban, sum(IF(shomareyemelli is not null and ismizban = false, 1, 0)) as tedadbamellimihman, sum(IF(shomareyemelli is null, 0, 1)) as tedadbamellikol, max(gheymatbakartemelli) as fimelli, sum(IF(shomareyemelli is not null, gheymatbakartemelli, 0)) as gheymatmelli, sum(IF(shomareyemelli is null and ismizban = true, 1, 0)) as tedadbakartmizban, sum(IF(shomareyemelli is null and ismizban = false, 1, 0)) as tedadbakartmihman, sum(IF(shomareyemelli is null, 1, 0)) as tedadbakartkol, max(gheymat) as fi, sum(IF(shomareyemelli is null, gheymat, 0)) as gheymatkart, count(*) as tedadkol, sum(IF(shomareyemelli is null, gheymat, gheymatbakartemelli)) as gheymat from vaziatesandali join mogheiyyat on mogheiyyat.shenaseyemogheiyyat = vaziatesandali.shenaseyemogheiyyat where vaziatesandali.shenaseyebazi = "
							+ shenaseyeBazi + " and vaziatesandali.vaziatesandali = " + kodeSandalieForookhteShode
							+ " and vaziatesandali.ZamaneAkharinTaghir >='"
							+ sdfDestination.format(azTarikh)
							+ "' and vaziatesandali.ZamaneAkharinTaghir <='"
							+ sdfDestination.format(taTarikh)
							+ "' group by mogheiyyat.namemogheiyyat with rollup");
		else
			q = session.createSQLQuery(
					"select mogheiyyat.namemogheiyyat as nam, sum(IF(shomareyemelli is not null and ismizban = true, 1, 0)) as tedadbamellimizban, sum(IF(shomareyemelli is not null and ismizban = false, 1, 0)) as tedadbamellimihman, sum(IF(shomareyemelli is null, 0, 1)) as tedadbamellikol, max(gheymatbakartemelli) as fimelli, sum(IF(shomareyemelli is not null, gheymatbakartemelli, 0)) as gheymatmelli, sum(IF(shomareyemelli is null and ismizban = true, 1, 0)) as tedadbakartmizban, sum(IF(shomareyemelli is null and ismizban = false, 1, 0)) as tedadbakartmihman, sum(IF(shomareyemelli is null, 1, 0)) as tedadbakartkol, max(gheymat) as fi, sum(IF(shomareyemelli is null, gheymat, 0)) as gheymatkart, count(*) as tedadkol, sum(IF(shomareyemelli is null, gheymat, gheymatbakartemelli)) as gheymat from vaziatesandali join mogheiyyat on mogheiyyat.shenaseyemogheiyyat = vaziatesandali.shenaseyemogheiyyat where vaziatesandali.vaziatesandali="
							+ kodeSandalieForookhteShode
							+ " and vaziatesandali.ZamaneAkharinTaghir >='"
							+ sdfDestination.format(azTarikh)
							+ "' and vaziatesandali.ZamaneAkharinTaghir <='"
							+ sdfDestination.format(taTarikh)
							+ "' group by mogheiyyat.namemogheiyyat with rollup");
		@SuppressWarnings("rawtypes")
		List temp = q.list();
		session.flush();
		session.disconnect();
		session.close();

		List<SandaliMaliReportBean> ret = new ArrayList<>();
		for (Object object : temp) {

			try {
				Object[] tmp = (Object[]) object;
				SandaliMaliReportBean record = new SandaliMaliReportBean();

				if (tmp[0] != null) {
					String nam = tmp[0].toString();
					record.setNameMogheiyat(nam);
				} else {
					record.setNameMogheiyat("مجموع");
				}

				record.setTedadMelliMizban(((BigDecimal) tmp[1]).intValue());
				record.setTedadMelliMihman(((BigDecimal) tmp[2]).intValue());
				record.setTedadMelli(((BigDecimal) tmp[3]).intValue());
				record.setFiMelli(((Integer) tmp[4]));
				record.setGheymatMelli(((BigDecimal) tmp[5]).longValue());

				record.setTedadKartMizban(((BigDecimal) tmp[6]).intValue());
				record.setTedadKartMihman(((BigDecimal) tmp[7]).intValue());
				record.setTedadKart(((BigDecimal) tmp[8]).intValue());
				record.setFiKart(((Integer) tmp[9]));
				record.setGheymatKart(((BigDecimal) tmp[10]).longValue());

				record.setTedadKol(((BigInteger) tmp[11]).intValue());
				record.setGheymatKol(((BigDecimal) tmp[12]).longValue());

				ret.add(record);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}



}
