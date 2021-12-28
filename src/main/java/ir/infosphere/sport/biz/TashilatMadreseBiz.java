package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.TashilatMadreseDao;
import ir.infosphere.sport.entity.TashilatMadreseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TashilatMadreseBiz {
	@Autowired
	private TashilatMadreseDao tashilatMadreseDao;

	@Transactional
	public TashilatMadreseEntity retrieveById(Integer id) {
		return tashilatMadreseDao.retrieveByID(id);
	}
	
	@Transactional
	public void update(TashilatMadreseEntity entity) {
		tashilatMadreseDao.update(entity);
	}
	
	@Transactional
	public void delete(Integer id) {
		tashilatMadreseDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void create(TashilatMadreseEntity entity) {
		tashilatMadreseDao.create(entity);
	}
	
	@Transactional
	public int getScore(int id)
	{
		int FinalScore = 0;
		TashilatMadreseEntity temp = retrieveById(id);
		FinalScore += temp.getEtayePayanDore().getMeghdar();
		FinalScore += temp.getTashkilParvandeh().getMeghdar();
		FinalScore += temp.getTahieJozve().getMeghdar();
		FinalScore += temp.getNamayeshFilm().getMeghdar();
		FinalScore += temp.getTahieFilm().getMeghdar();
		FinalScore += temp.getVasayeleSamiee().getMeghdar();
		FinalScore += temp.getMoshaverin().getMeghdar();
		FinalScore += temp.getDidarAzMosabeghat().getMeghdar();
		FinalScore += temp.getPardakhteBeMoghe().getMeghdar();
		FinalScore += temp.getTashkileAnjoman().getMeghdar();
		return FinalScore;
	}
}
