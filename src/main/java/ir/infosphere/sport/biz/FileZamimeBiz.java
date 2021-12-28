package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.FileZamimeDao;
import ir.infosphere.sport.entity.FileZamimeEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;

@Component
public class FileZamimeBiz {
	@Autowired
	private FileZamimeDao fileZamimeDao;

	@Transactional
	public void createFile(FileZamimeEntity file) {
		fileZamimeDao.create(file);
	}

	@Transactional
	public void updateFile(FileZamimeEntity file) {
		fileZamimeDao.update(file);
	}
	
	@Transactional
	public void deleteFile(FileZamimeEntity file) {
		fileZamimeDao.delete(file);
	}

	public static String getFilePath() {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream inputStream = classLoader
				.getResourceAsStream("aksPath.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty("filepath");
	}
	
	public static String getStaticFilePath() {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream inputStream = classLoader
				.getResourceAsStream("aksPath.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty("staticfilepath");
	}
	
	public static FileZamimeEntity getFileZamimeEntityAndSaveStaticImage(String fileName, byte[] file, String onvan) {
		String filePath = getStaticFilePath();
		return saveEntityAndFile(fileName, file, filePath, onvan);
	}

	/*
	public static FileZamimeEntity getFileZamimeEntityAndSave(String fileName, byte[] file) {
		String filePath = getFilePath();
		return saveEntityAndFile(fileName, file, filePath, "");
	}
	*/
	
	private static FileZamimeEntity saveEntityAndFile(String fileName, byte[] file, String filePath, String onvan)
	{
		FileZamimeEntity fileEntity = null;
		if (fileName != null) {
			File pdfFile = null;
			pdfFile = new File(filePath + fileName);
			for (int i = 2; pdfFile.exists(); i++)
				pdfFile = new File(filePath + FileZamimeBiz.getPrefix(fileName) + "-" + i
						+ FileZamimeBiz.getExtension(fileName));
			try {
				FileOutputStream fos = new FileOutputStream(pdfFile);
				fos.write(file);
				fos.close();
			} catch (Exception e) {
			}
			fileEntity = new FileZamimeEntity();
			//fileEntity.setNameFileZamime(pdfFile.getPath());
			fileEntity.setNameFileZamime(filePath + getPrefix(pdfFile.getName()) + getExtension(fileName));
			fileEntity.setOnvanFileZamime(onvan);
			ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
			ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			FileZamimeBiz fileZamimeBiz = (FileZamimeBiz) appContext.getBean("fileZamimeBiz");
			fileZamimeBiz.createFile(fileEntity);
			return fileEntity;
		}
		return fileEntity;
	}

	/*
	public static FileZamimeEntity getFileZamimeEntityAndSave(String fileName, byte[] file) {
		String filePath = FileZamimeBiz.getFilePath();
		FileZamimeEntity fileEntity = null;
		if (fileName != null) {
			File pdfFile = null;
			pdfFile = new File(filePath + fileName);
			for (int i = 2; pdfFile.exists(); i++)
				pdfFile = new File(filePath + FileZamimeBiz.getPrefix(fileName) + "-" + i
						+ FileZamimeBiz.getExtension(fileName));
			try {
				FileOutputStream fos = new FileOutputStream(pdfFile);
				fos.write(file);
				fos.close();
			} catch (Exception e) {
			}
			fileEntity = new FileZamimeEntity();
			fileEntity.setNameFileZamime(pdfFile.getPath());
			ServletContext servletContext = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
			ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			FileZamimeBiz fileZamimeBiz = (FileZamimeBiz) appContext.getBean("fileZamimeBiz");
			fileZamimeBiz.createFile(fileEntity);
			return fileEntity;
		}
		return fileEntity;
	}
	*/
	
	private static String getPrefix(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf('.'));
	}

	private static String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf('.'));
	}
	
	@Transactional
	public FileZamimeEntity retrieveById(Integer id) {
		return fileZamimeDao.retrieveByID(id);
	}
}
