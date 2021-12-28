package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.MalzomatEdariMadreseDao;
import ir.infosphere.sport.entity.MalzomatEdariMadreseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MalzomatEdariMadreseBiz {
	@Autowired
	private MalzomatEdariMadreseDao malzomatEdariMadreseDao;

	@Transactional
	public MalzomatEdariMadreseEntity retrieveById(Integer id) {
		return malzomatEdariMadreseDao.retrieveByID(id);
	}
	
	@Transactional
	public void update(MalzomatEdariMadreseEntity entity) {
		malzomatEdariMadreseDao.update(entity);
	}
	
	@Transactional
	public void delete(Integer id) {
		malzomatEdariMadreseDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void create(MalzomatEdariMadreseEntity entity) {
		malzomatEdariMadreseDao.create(entity);
	}
	
	@Transactional
	public int getScore(int id)
	{
		int FinalScore = 0;
		int OptionScore = 3;
		MalzomatEdariMadreseEntity temp = retrieveById(id);
		if (temp.getKhateTelefon()) FinalScore += OptionScore;
		if (temp.getDastgahFax()) FinalScore += OptionScore;
		if (temp.getRayaneh()) FinalScore += OptionScore;
		if (temp.getDastgahCopy()) FinalScore += OptionScore;
		if (temp.getKomodLavazem()) FinalScore += OptionScore;
		if (temp.getScaner()) FinalScore += OptionScore;
		if (temp.getPrinter()) FinalScore += OptionScore;
		return FinalScore;
	}
}
