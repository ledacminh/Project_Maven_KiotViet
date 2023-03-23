package minhle.ultilities;

import minhle.commons.GlobalConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ListDataConfigProperties {
    private static final Map<String, String> mapDataProperties = new HashMap<String, String>();
    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(GlobalConstants.DOI_TAC_PROPERTY_PATH));
            Enumeration<?> enumeration = properties.propertyNames();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                String value = properties.getProperty(key);
                mapDataProperties.put(key, value);
            }
        } catch (IOException e) {
            throw new RuntimeException("[ListDataConfigProperties] " + e.getMessage());
        }
    }

    public static String getString(String key) {
        String value = "";
        try {
            value = mapDataProperties.get(key);
        } catch (Exception e) {
            throw new RuntimeException("[ListDataConfigProperties].[getString]" + e.getMessage());
        }
        return value;
    }
}
