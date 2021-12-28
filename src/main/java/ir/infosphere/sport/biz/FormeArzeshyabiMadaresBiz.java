package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.FormeArzeshyabiMadreseDao;
import ir.infosphere.sport.entity.FormeArzeshyabiMadaresEntity;
import ir.infosphere.sport.entity.MadreseEntity;
import ir.infosphere.sport.entity.OzveMadreseEntity;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;

@Component
public class FormeArzeshyabiMadaresBiz {
	@Autowired
	private FormeArzeshyabiMadreseDao formeArzeshyabiMadreseDao;
	
	@Transactional
	public List<FormeArzeshyabiMadaresEntity> getAll(){
		List<FormeArzeshyabiMadaresEntity> list = formeArzeshyabiMadreseDao.retrieveAll();
		return list;
	}
	
	@Transactional
	public List<FormeArzeshyabiMadaresEntity> retrieveByCriteria(DetachedCriteria criteria) {
		List<FormeArzeshyabiMadaresEntity> list = formeArzeshyabiMadreseDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public Integer getFinalScore (FormeArzeshyabiMadaresEntity form) {
		
		ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
		ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		ZaminTamrinMadreseBiz zaminTamrinMadreseBiz = (ZaminTamrinMadreseBiz) appContext.getBean("zaminTamrinMadreseBiz");
		EmkanatMadreseBiz emkanatMadreseBiz = (EmkanatMadreseBiz) appContext.getBean("emkanatMadreseBiz");
		VaziateRakhtkanMadreseBiz vaziateRakhtkanMadreseBiz = (VaziateRakhtkanMadreseBiz) appContext.getBean("vaziateRakhtkanMadreseBiz");
		EstedadMadreseBiz estedadMadreseBiz = (EstedadMadreseBiz) appContext.getBean("estedadMadreseBiz");
		TashilatMadreseBiz tashilatMadreseBiz = (TashilatMadreseBiz) appContext.getBean("tashilatMadreseBiz");
		VaziateZaherMorabiMadreseBiz vaziateZaherMorabiMadreseBiz = (VaziateZaherMorabiMadreseBiz) appContext.getBean("vaziateZaherMorabiMadreseBiz");
		VaziateHoghoghiVaEdariMadreseBiz vaziateHoghoghiVaEdariMadreseBiz = (VaziateHoghoghiVaEdariMadreseBiz) appContext.getBean("vaziateHoghoghiVaEdariMadreseBiz");
		MalzomatEdariMadreseBiz malzomatEdariMadreseBiz = (MalzomatEdariMadreseBiz) appContext.getBean("malzomatEdariMadreseBiz");
		RadeFootballAmozanMadreseBiz radeFootballAmozanMadreseBiz = (RadeFootballAmozanMadreseBiz) appContext.getBean("radeFootballAmozanMadreseBiz");
		OzveMadreseBiz ozveMadreseBiz = (OzveMadreseBiz) appContext.getBean("ozveMadreseBiz");
		
		Integer FinalScore = 0;
		FinalScore += form.getHamkariMoases().getMeghdar();
		FinalScore += zaminTamrinMadreseBiz.getScore(form.getZaminTamrinMadrese());
		FinalScore += form.getMalekiat().getMeghdar();
		FinalScore += form.getNoeZamin().getMeghdar();
		FinalScore += emkanatMadreseBiz.getScore(form.getEmkanatMadrese());
		FinalScore += vaziateRakhtkanMadreseBiz.getScore(form.getRakhtkanMadrese());
		FinalScore += estedadMadreseBiz.getScore(form.getEstedadMadrese());
		FinalScore += tashilatMadreseBiz.getScore(form.getTashilatMadrese());
		FinalScore += vaziateZaherMorabiMadreseBiz.getScore(form.getVaziateHoghoghiMadrese());
		FinalScore += vaziateHoghoghiVaEdariMadreseBiz.getScore(form.getVaziateHoghoghiMadrese());
		FinalScore += form.getKarmandEdari().getMeghdar();
		FinalScore += form.getLogo().getMeghdar();
		FinalScore += form.getSabegheMadrese().getMeghdar();
		
		int itemScore = 0;
		for (OzveMadreseEntity morabi : ozveMadreseBiz.getAllMorabianMadrese(form.getMadrese())) {
			int rowScore = 0;
			rowScore += morabi.getDarajeMorabiGari().getMeghdar();
			if (morabi.getTarbiatBadani()) rowScore += morabi.getMadrakeTahsili().getMeghdar() + 2; 
			else rowScore += morabi.getMadrakeTahsili().getMeghdar();
			itemScore += rowScore;
		}
		itemScore += form.getSarparast().getMeghdar();
		itemScore += form.getPezeshk().getMeghdar();
		itemScore += form.getTadarokat().getMeghdar();
		if (itemScore > 100) itemScore = 100;
		FinalScore += itemScore;
		FinalScore += malzomatEdariMadreseBiz.getScore(form.getMalzomatEdariMadrese());
		FinalScore += form.getVaziateMadreseDarSaleGozashte().getMeghdar();
		FinalScore += form.getSherkatMosabeghat().getMeghdar();
		FinalScore += radeFootballAmozanMadreseBiz.getScore(form.getRadeFootballAmozan());
		
		return FinalScore;
	}
	
	@Transactional
	public FormeArzeshyabiMadaresEntity retrieveById(Integer id) {
		return formeArzeshyabiMadreseDao.retrieveByID(id);
	}
	
	@Transactional
	public void create(FormeArzeshyabiMadaresEntity form) {
		formeArzeshyabiMadreseDao.create(form);
	}
	
	@Transactional
	public void update(FormeArzeshyabiMadaresEntity form) {
		formeArzeshyabiMadreseDao.update(form);
	}
	
	@Transactional
	public void delete(Integer id) {
		FormeArzeshyabiMadaresEntity form = formeArzeshyabiMadreseDao.retrieveByID(id);
		formeArzeshyabiMadreseDao.delete(form);
		
		ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
		ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		ZaminTamrinMadreseBiz zaminTamrinMadreseBiz = (ZaminTamrinMadreseBiz) appContext.getBean("zaminTamrinMadreseBiz");
		EmkanatMadreseBiz emkanatMadreseBiz = (EmkanatMadreseBiz) appContext.getBean("emkanatMadreseBiz");
		VaziateRakhtkanMadreseBiz vaziateRakhtkanMadreseBiz = (VaziateRakhtkanMadreseBiz) appContext.getBean("vaziateRakhtkanMadreseBiz");
		EstedadMadreseBiz estedadMadreseBiz = (EstedadMadreseBiz) appContext.getBean("estedadMadreseBiz");
		TashilatMadreseBiz tashilatMadreseBiz = (TashilatMadreseBiz) appContext.getBean("tashilatMadreseBiz");
		VaziateZaherMorabiMadreseBiz vaziateZaherMorabiMadreseBiz = (VaziateZaherMorabiMadreseBiz) appContext.getBean("vaziateZaherMorabiMadreseBiz");
		VaziateHoghoghiVaEdariMadreseBiz vaziateHoghoghiVaEdariMadreseBiz = (VaziateHoghoghiVaEdariMadreseBiz) appContext.getBean("vaziateHoghoghiVaEdariMadreseBiz");
		MalzomatEdariMadreseBiz malzomatEdariMadreseBiz = (MalzomatEdariMadreseBiz) appContext.getBean("malzomatEdariMadreseBiz");
		RadeFootballAmozanMadreseBiz radeFootballAmozanMadreseBiz = (RadeFootballAmozanMadreseBiz) appContext.getBean("radeFootballAmozanMadreseBiz");
		
		zaminTamrinMadreseBiz.delete(form.getZaminTamrinMadrese());
		emkanatMadreseBiz.delete(form.getEmkanatMadrese());
		vaziateRakhtkanMadreseBiz.delete(form.getRakhtkanMadrese());
		estedadMadreseBiz.delete(form.getEstedadMadrese());
		tashilatMadreseBiz.delete(form.getTashilatMadrese());
		vaziateZaherMorabiMadreseBiz.delete(form.getZaherMorabiMadrese());
		vaziateHoghoghiVaEdariMadreseBiz.delete(form.getVaziateHoghoghiMadrese());
		malzomatEdariMadreseBiz.delete(form.getMalzomatEdariMadrese());
		radeFootballAmozanMadreseBiz.delete(form.getRadeFootballAmozan());
	}
	
	@Transactional
	public List<FormeArzeshyabiMadaresEntity> getAllFormeArzeshyabiByMadrese(MadreseEntity madrese) {
		if (madrese == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(FormeArzeshyabiMadaresEntity.class);
		criteria.add(Restrictions.eq("madrese", madrese));
		List<FormeArzeshyabiMadaresEntity> list = formeArzeshyabiMadreseDao.retrieveAllByCriteria(criteria);
		return list;
	}
	@Transactional
	public List<FormeArzeshyabiMadaresEntity> getAllFormByMadreseAndDate(MadreseEntity madrese,Date start,Date end) {
		if (madrese == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria.forClass(FormeArzeshyabiMadaresEntity.class);
		criteria.add(Restrictions.eq("madrese", madrese));
		criteria.add(Restrictions.ge("tarikh",start));
		criteria.add(Restrictions.le("tarikh",end));
		List<FormeArzeshyabiMadaresEntity> list = formeArzeshyabiMadreseDao.retrieveAllByCriteria(criteria);
		return list;
	}
}
