package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.MatlabFileDao;
import ir.infosphere.sport.entity.FileZamimeEntity;
import ir.infosphere.sport.entity.MatlabEntity;
import ir.infosphere.sport.entity.MatlabFileEntity;

import java.io.File;
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
public class MatlabFileBiz {
	@Autowired
	private MatlabFileDao matlabFileDao;
	
	@Transactional
	public List<MatlabFileEntity> getAllFileHayeMataleb(){
		List<MatlabFileEntity> list = matlabFileDao.retrieveAll();
		return list;
	}
	
	@Transactional
	public List<MatlabFileEntity> getAllFileByMatlab(MatlabEntity matlab) {
		if (matlab == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MatlabFileEntity.class);
		criteria.add(Restrictions.eq("matlab", matlab));
		return matlabFileDao.retrieveAllByCriteria(criteria);
	}
	
	@Transactional
	public Long getCountByMatlabAndFile(MatlabEntity matlab, FileZamimeEntity file) {
		if (matlab == null || file == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MatlabFileEntity.class);
		criteria.add(Restrictions.eq("matlab", matlab));
		criteria.add(Restrictions.eq("file", file));
		return matlabFileDao.getCountByCriteria(criteria);
	}
	
	@Transactional
	public MatlabFileEntity getByMatlabAndFile(MatlabEntity matlab, FileZamimeEntity file) {
		if (matlab == null || file == null)
			return null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(MatlabFileEntity.class);
		criteria.add(Restrictions.eq("matlab", matlab));
		criteria.add(Restrictions.eq("file", file));
		return matlabFileDao.retrieveAllByCriteria(criteria).get(0);
	}
	
	@Transactional
	public List<MatlabFileEntity> retrieveByCriteria(DetachedCriteria criteria){
		List<MatlabFileEntity> list = matlabFileDao.retrieveAllByCriteria(criteria);
		return list;
	}
	
	@Transactional
	public MatlabFileEntity retrieveById(Integer id) {
		return matlabFileDao.retrieveByID(id);
	}
	
	@Transactional
	public void update(MatlabFileEntity entity) {
		matlabFileDao.update(entity);
	}
	
	@Transactional
	public void delete(Integer id) {
		matlabFileDao.delete(retrieveById(id));
	}
	
	@Transactional
	public void deleteAllByMatlab(MatlabEntity matlab) {
		List<MatlabFileEntity> filehayeKhabar = getAllFileByMatlab(matlab);
		for (MatlabFileEntity matlabFileEntity : filehayeKhabar) {
			File file = new File(matlabFileEntity.getFile().getNameFileZamime());
			if (file.exists())
				file.delete();
			Integer IDFile = matlabFileEntity.getFile().getId();
			delete(matlabFileEntity.getId());
			ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
			ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			FileZamimeBiz fileZamimeBiz = (FileZamimeBiz) appContext.getBean("fileZamimeBiz");
			fileZamimeBiz.deleteFile(fileZamimeBiz.retrieveById(IDFile));	
		}
	}
	
	@Transactional
	public void create(MatlabFileEntity entity) {
		matlabFileDao.create(entity);
	}
}
