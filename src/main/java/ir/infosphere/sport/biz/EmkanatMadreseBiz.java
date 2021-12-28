package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.EmkanatMadreseDao;
import ir.infosphere.sport.entity.EmkanatMadreseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmkanatMadreseBiz {
	@Autowired
	private EmkanatMadreseDao emkanatMadreseDao;

	@Transactional
	public EmkanatMadreseEntity retrieveById(Integer id) {
		return emkanatMadreseDao.retrieveByID(id);
	}
	
	@Transactional
	public void update(EmkanatMadreseEntity entity) {
		emkanatMadreseDao.update(entity);
	}
	
	@Transactional
	public void delete(Integer id) {
		emkanatMadreseDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void create(EmkanatMadreseEntity entity) {
		emkanatMadreseDao.create(entity);
	}
	
	@Transactional
	public int getScore(int id)
	{
		int FinalScore = 0;
		EmkanatMadreseEntity temp = retrieveById(id);
		FinalScore += temp.getTirDarvazeh().getMeghdar();
		FinalScore += temp.getToreMonaseb().getMeghdar();
		FinalScore += temp.getParchamKorner().getMeghdar();
		FinalScore += temp.getTopeStandard().getMeghdar();
		FinalScore += temp.getLavazemKomaki().getMeghdar();
		FinalScore += temp.getDarvazehMoshtarak().getMeghdar();
		FinalScore += temp.getKover().getMeghdar();
		FinalScore += temp.getAyabZahab().getMeghdar();
		FinalScore += temp.getBimeHavades().getMeghdar();
		FinalScore += temp.getLebasShakhsi().getMeghdar();
		FinalScore += temp.getAlameZamin().getMeghdar();
		return FinalScore;
	}
}
