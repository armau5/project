package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.RadeFootballAmozanMadreseDao;
import ir.infosphere.sport.entity.RadeFootballAmozanMadreseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RadeFootballAmozanMadreseBiz {
	@Autowired
	private RadeFootballAmozanMadreseDao radeFootballAmozanMadreseDao;

	@Transactional
	public RadeFootballAmozanMadreseEntity retrieveById(Integer id) {
		return radeFootballAmozanMadreseDao.retrieveByID(id);
	}
	
	@Transactional
	public void update(RadeFootballAmozanMadreseEntity entity) {
		radeFootballAmozanMadreseDao.update(entity);
	}
	
	@Transactional
	public void delete(Integer id) {
		radeFootballAmozanMadreseDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void create(RadeFootballAmozanMadreseEntity entity) {
		radeFootballAmozanMadreseDao.create(entity);
	}
	
	@Transactional
	public int getScore(int id)
	{
		int FinalScore = 0;
		RadeFootballAmozanMadreseEntity temp = retrieveById(id);
		FinalScore += getValue(temp.getZire_8());
		FinalScore += getValue(temp.getBeyne8_9());
		FinalScore += getValue(temp.getBeyne10_11());
		FinalScore += getValue(temp.getBeyne12_13());
		FinalScore += getValue(temp.getBeyne14_16());
		return FinalScore;
	}
	
	private int getValue(int number)
	{
		int score = 0;
		if (number > 24)
			score = 6;
		else if (number >= 18 && number <= 24)
			score = 4;
		else if (number >= 12 && number <= 17)
			score = 2;
		else if (score < 12)
			score = 0;
		return score;
	}
}
