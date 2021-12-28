package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.ZaminTamrinMadreseDao;
import ir.infosphere.sport.entity.ZaminTamrinMadreseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ZaminTamrinMadreseBiz {
	@Autowired
	private ZaminTamrinMadreseDao zaminTamrinMadreseDao;

	@Transactional
	public ZaminTamrinMadreseEntity retrieveById(Integer id) {
		return zaminTamrinMadreseDao.retrieveByID(id);
	}
	
	@Transactional
	public void update(ZaminTamrinMadreseEntity entity) {
		zaminTamrinMadreseDao.update(entity);
	}
	
	@Transactional
	public void delete(Integer id) {
		zaminTamrinMadreseDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void create(ZaminTamrinMadreseEntity entity) {
		zaminTamrinMadreseDao.create(entity);
	}
	
	@Transactional
	public int getScore(int id)
	{
		int FinalScore = 0;
		ZaminTamrinMadreseEntity temp = retrieveById(id);
		FinalScore += temp.getKeyfiateZamin().getMeghdar();
		FinalScore += temp.getNoreZamin().getMeghdar();
		FinalScore += temp.getAbadeZamin().getMeghdar();
		FinalScore += temp.getVazehBodan().getMeghdar();
		FinalScore += temp.getAshyayeKhatarnak().getMeghdar();
		FinalScore += temp.getHefaz().getMeghdar();
		return FinalScore;
	}
}
