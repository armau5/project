package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.EstedadMadreseDao;
import ir.infosphere.sport.entity.EstedadMadreseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EstedadMadreseBiz {
	@Autowired
	private EstedadMadreseDao estedadMadreseDao;

	@Transactional
	public EstedadMadreseEntity retrieveById(Integer id) {
		return estedadMadreseDao.retrieveByID(id);
	}
	
	@Transactional
	public void update(EstedadMadreseEntity entity) {
		estedadMadreseDao.update(entity);
	}
	
	@Transactional
	public void delete(Integer id) {
		estedadMadreseDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void create(EstedadMadreseEntity entity) {
		estedadMadreseDao.create(entity);
	}
	
	@Transactional
	public int getScore(int id)
	{
		int FinalScore = 0;
		EstedadMadreseEntity temp = retrieveById(id);
		FinalScore += temp.getEstedadBashgah().getMeghdar();
		FinalScore += temp.getEstedadTimMelli().getMeghdar();
		return FinalScore;
	}
}
