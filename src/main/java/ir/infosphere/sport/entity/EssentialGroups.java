package ir.infosphere.sport.entity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Properties;

public class EssentialGroups {
	public static String MasooleMadrese;
	public static String FootballAmozMadrese;
	public static String MorabiMadrese;
	
	public static String MasooleTim;
	public static String PezeshkTim;
	public static String BazikonTim;
	public static String MorabiTim;
	public static String SarparastTim;
	public static String KadreFanniTim;
	public static String SarMorabiTim;

	static {
		try {
			Properties properties = new Properties();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream("essential_groups.properties");
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			properties.load(inputStreamReader);
			for (Field field : EssentialGroups.class.getDeclaredFields()) {
				field.set(EssentialGroups.class, properties.get(field.getName()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
