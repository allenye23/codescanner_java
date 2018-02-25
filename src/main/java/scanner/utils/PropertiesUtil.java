package scanner.utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties prop;
	private final static String  path = "codescanner.properties";
	static{
		try {
			prop = new Properties();
			prop.load(PropertiesUtil.class.getClassLoader()
					.getResourceAsStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Properties getProp(){
		return prop;
	}

}
