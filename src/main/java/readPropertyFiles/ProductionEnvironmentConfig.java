package readPropertyFiles;

import commons.GlobalConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ProductionEnvironmentConfig {
    private static final Map<String, String> mapDataProperties = new HashMap<String, String>();
    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(GlobalConstants.PRODUCTION_PROPERTY_PATH));
            Enumeration<?> enumeration = properties.propertyNames();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                String value = properties.getProperty(key);
                mapDataProperties.put(key, value);
            }
        } catch (IOException e) {
            throw new RuntimeException("[ProductionEnvironmentConfig] " + e.getMessage());
        }
    }

    public static String getString(String key) {
        String value = "";
        try {
            value = mapDataProperties.get(key);
        } catch (Exception e) {
            throw new RuntimeException("[ProductionEnvironmentConfig].[getString]" + e.getMessage());
        }
        return value;
    }
}
