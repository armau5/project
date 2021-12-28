package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.VaziateZaherMorabiMadreseDao;
import ir.infosphere.sport.entity.VaziateZaherMorabiMadreseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VaziateZaherMorabiMadreseBiz {
	@Autowired
	private VaziateZaherMorabiMadreseDao vaziateZaherMorabiMadreseDao;

	@Transactional
	public VaziateZaherMorabiMadreseEntity retrieveById(Integer id) {
		return vaziateZaherMorabiMadreseDao.retrieveByID(id);
	}
	
	@Transactional
	public void update(VaziateZaherMorabiMadreseEntity entity) {
		vaziateZaherMorabiMadreseDao.update(entity);
	}
	
	@Transactional
	public void delete(Integer id) {
		vaziateZaherMorabiMadreseDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void create(VaziateZaherMorabiMadreseEntity entity) {
		vaziateZaherMorabiMadreseDao.create(entity);
	}
	
	@Transactional
	public int getScore(int id)
	{
		int FinalScore = 0;
		VaziateZaherMorabiMadreseEntity temp = retrieveById(id);
		FinalScore += temp.getLebasKafsh().getMeghdar();
		FinalScore += temp.getNamayeshHarkat().getMeghdar();
		FinalScore += temp.getTarheDars().getMeghdar();
		FinalScore += temp.getTaeedieSalamat().getMeghdar();
		FinalScore += temp.getKeyfiatAmozesh().getMeghdar();
		return FinalScore;
	}
}
