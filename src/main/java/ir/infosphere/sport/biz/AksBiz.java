package ir.infosphere.sport.biz;

import ir.infosphere.sport.dao.AksDao;
import ir.infosphere.sport.entity.AksEntity;
import ir.infosphere.sport.util.PropertiesUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;

@Component
public class AksBiz {
	@Autowired
	private AksDao aksDao;

	@Transactional
	public void createAks(AksEntity aks) {
		aksDao.create(aks);
	}

	@Transactional
	public void updateAks(AksEntity aks) {
		aksDao.update(aks);
	}

	@Transactional
	public void delete(AksEntity aks) {
		aksDao.delete(aks);
	}

	private static final String propertyFileName = "aksPath.properties";

	private static String getAksPath() {
		return PropertiesUtil.readFromProperties(AksBiz.propertyFileName,
				"path");
	}

	private static String getPreStaticAksPath() {
		return PropertiesUtil.readFromProperties(AksBiz.propertyFileName,
				"preStaticPath");
	}

	private static String getPostStaticAksPath() {
		return PropertiesUtil.readFromProperties(AksBiz.propertyFileName,
				"postStaticPath");
	}

	public static AksEntity getAksEntityAndSaveStaticImage(String fileName,
			byte[] picture) {
		String preAksPath = AksBiz.getPreStaticAksPath();
		String postAksPath = AksBiz.getPostStaticAksPath();
		return saveEntityAndAks(fileName, picture, preAksPath, postAksPath);
	}

	public static AksEntity getAksEntityAndSave(String fileName, byte[] picture) {
		String aksPath = AksBiz.getAksPath();
		return saveEntityAndAksWithMathAlgorithm(fileName, picture, aksPath);
	}

	public static void updateAksEntityAndSave(AksEntity aks, byte[] picture) {
		updateImage(picture, aks.getNameFileAks());
	}

	private static Long getFileName() {
		final Integer randomCount = 4;
		Long timeStamp = (new Date()).getTime();
		Double randomLowerBound = Math.pow(10, randomCount - 1);
		Double randomUpperBound = Math.pow(10, randomCount) - 1;
		Integer randomProductUpper = randomUpperBound.intValue() + 1;

		Double doubleRandom = (Math.random() * (randomUpperBound - randomLowerBound))
				+ randomLowerBound;
		Integer random = doubleRandom.intValue();
		Long fileNameNumber = timeStamp * randomProductUpper + random;
		return fileNameNumber;
	}

	private static String getFolderName(Long fileNameNumber) {
		Long mod11 = fileNameNumber % 11;
		Long mod12 = fileNameNumber % 12;
		Long mod13 = fileNameNumber % 13;
		return mod11 + "/" + mod12 + "/" + mod13 + "/";
	}

	public static AksEntity changeUserAks(AksEntity aksEntity,
			String originalFileName, byte[] picture) {
		String aksPath = AksBiz.getAksPath();
		AksBiz aksBiz = getAksBiz();
		String imagePath = saveImageAndGetImagePath(originalFileName, picture,
				aksPath);
		if (aksEntity == null) {
			aksEntity = new AksEntity();
			aksEntity.setNameFileAks(imagePath);
			aksBiz.createAks(aksEntity);
			return aksEntity;
		} else {
			String oldPath = aksEntity.getNameFileAks();
			File oldFile = new File(oldPath);
			try {
				oldFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			aksEntity.setNameFileAks(imagePath);
			aksBiz.updateAks(aksEntity);
			return aksEntity;
		}
	}

	public static AksEntity changeStaticAks(AksEntity aksEntity,
			String originalFileName, byte[] picture) {
		String preAksPath = AksBiz.getPreStaticAksPath();
		String postAksPath = AksBiz.getPostStaticAksPath();
		AksBiz aksBiz = getAksBiz();
		String newFileName = getFileName().toString()
				+ getExtension(originalFileName);
		saveStaticImage(originalFileName, newFileName, picture, preAksPath
				+ postAksPath);
		String imagePathForRead = postAksPath + newFileName;
		if (aksEntity == null) {
			aksEntity = new AksEntity();
			aksEntity.setNameFileAks(imagePathForRead);
			aksBiz.createAks(aksEntity);
			return aksEntity;
		} else {
			String oldPath = aksEntity.getNameFileAks();
			File oldFile = new File(preAksPath + oldPath);
			try {
				oldFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			aksEntity.setNameFileAks(imagePathForRead);
			aksBiz.updateAks(aksEntity);
			return aksEntity;
		}
	}

	private static String saveImageAndGetImagePath(String originalFileName,
			byte[] picture, String aksPath) {
		Long fileNameNumber = getFileName();
		String folderName = getFolderName(fileNameNumber);

		File imageFile = new File(aksPath + folderName + fileNameNumber
				+ getExtension(originalFileName));
		try {
			FileOutputStream fos = new FileOutputStream(imageFile);
			fos.write(picture);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageFile.getPath();
	}

	private static void updateImage(byte[] picture, String aksAddress) {
		File imageFile = new File(aksAddress);
		try {
			FileOutputStream fos = new FileOutputStream(imageFile);
			fos.write(picture);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void saveStaticImage(String originalFileName,
			String newFileName, byte[] picture, String aksPath) {
		File imageFile = new File(aksPath + newFileName);
		try {
			FileOutputStream fos = new FileOutputStream(imageFile);
			fos.write(picture);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static AksBiz getAksBiz() {
		ServletContext servletContext = Executions.getCurrent().getDesktop()
				.getWebApp().getServletContext();
		ApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		return appContext.getBean(AksBiz.class);
	}

	private static AksEntity saveEntityAndAksWithMathAlgorithm(
			String originalFileName, byte[] picture, String aksPath) {
		String imagePath = saveImageAndGetImagePath(originalFileName, picture,
				aksPath);
		AksEntity aksEntity = new AksEntity();
		aksEntity.setNameFileAks(imagePath);
		AksBiz aksBiz = getAksBiz();
		aksBiz.createAks(aksEntity);
		return aksEntity;
	}

	private static AksEntity saveEntityAndAks(String originalFileName,
			byte[] picture, String preAksPath, String postAksPath) {
		AksEntity aksEntity = null;
		if (originalFileName != null) {
			String newFileName = getFileName() + getExtension(originalFileName);
			saveStaticImage(originalFileName, newFileName, picture, preAksPath
					+ postAksPath);
			aksEntity = new AksEntity();
			aksEntity.setNameFileAks(postAksPath + newFileName);
			// String prePath = "/img/static/";
			// aksEntity.setNameFileAks(prePath
			// + AksBiz.getPrefix(imageFile.getName())
			// + AksBiz.getExtension(fileName));
			AksBiz aksBiz = getAksBiz();
			aksBiz.createAks(aksEntity);
			return aksEntity;
		}
		return aksEntity;
	}

	private static String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf('.'));
	}

	@Transactional
	public AksEntity retrieveById(Integer id) {
		return aksDao.retrieveByID(id);
	}
}
