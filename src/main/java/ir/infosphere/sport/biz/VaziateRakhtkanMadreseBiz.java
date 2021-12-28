package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.VaziateRakhtkanMadreseDao;
import ir.infosphere.sport.entity.VaziateRakhtkanMadreseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VaziateRakhtkanMadreseBiz {
	@Autowired
	private VaziateRakhtkanMadreseDao vaziateRakhtkanMadreseDao;

	@Transactional
	public VaziateRakhtkanMadreseEntity retrieveById(Integer id) {
		return vaziateRakhtkanMadreseDao.retrieveByID(id);
	}
	
	@Transactional
	public void update(VaziateRakhtkanMadreseEntity entity) {
		vaziateRakhtkanMadreseDao.update(entity);
	}
	
	@Transactional
	public void delete(Integer id) {
		vaziateRakhtkanMadreseDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void create(VaziateRakhtkanMadreseEntity entity) {
		vaziateRakhtkanMadreseDao.create(entity);
	}
	
	@Transactional
	public int getScore(int id)
	{
		int FinalScore = 0;
		VaziateRakhtkanMadreseEntity temp = retrieveById(id);
		FinalScore += temp.getTedadRakhtkan().getMeghdar();
		FinalScore += temp.getSandaliNimkat().getMeghdar();
		FinalScore += temp.getKomod().getMeghdar();
		FinalScore += temp.getTakhteWhiteBoard().getMeghdar();
		FinalScore += temp.getTahvieMonaseb().getMeghdar();
		FinalScore += temp.getTakhtePezeshki().getMeghdar();
		FinalScore += temp.getBehdashtOmomi().getMeghdar();
		FinalScore += temp.getDosh().getMeghdar();
		FinalScore += temp.getServiceBehdashti().getMeghdar();
		return FinalScore;
	}
}
