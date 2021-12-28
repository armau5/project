package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.VaziateHoghoghiVaEdariMadreseDao;
import ir.infosphere.sport.entity.VaziateHoghoghiVaEdariMadreseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VaziateHoghoghiVaEdariMadreseBiz {
	@Autowired
	private VaziateHoghoghiVaEdariMadreseDao vaziateHoghoghiVaEdariMadreseDao;

	@Transactional
	public VaziateHoghoghiVaEdariMadreseEntity retrieveById(Integer id) {
		return vaziateHoghoghiVaEdariMadreseDao.retrieveByID(id);
	}
	
	@Transactional
	public void update(VaziateHoghoghiVaEdariMadreseEntity entity) {
		vaziateHoghoghiVaEdariMadreseDao.update(entity);
	}
	
	@Transactional
	public void delete(Integer id) {
		vaziateHoghoghiVaEdariMadreseDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void create(VaziateHoghoghiVaEdariMadreseEntity entity) {
		vaziateHoghoghiVaEdariMadreseDao.create(entity);
	}
	
	@Transactional
	public int getScore(int id)
	{
		int FinalScore = 0;
		VaziateHoghoghiVaEdariMadreseEntity temp = retrieveById(id);
		FinalScore += temp.getSabteEtelaateSahih().getMeghdar();
		FinalScore += temp.getKarteOzviat().getMeghdar();
		FinalScore += temp.getDaftarEdari().getMeghdar();
		return FinalScore;
	}
}
