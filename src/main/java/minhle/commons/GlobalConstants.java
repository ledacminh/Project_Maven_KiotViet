package minhle.commons;

import minhle.ultilities.ListDataLoginProperties;

import java.io.File;

public class GlobalConstants {
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final long SHORT_TIMEOUT = 30;
    public static final long LONG_TIMEOUT = 50;
    public static final String SLASH = File.separator;
    public static final String CONFIG_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "minhle" + SLASH+  "datas" + SLASH + "data.xlsx";
    public static final String FILE_DATA_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "minhle" + SLASH+  "datas" + SLASH + "config.properties";
    public static final String LOGIN_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "minhle" + SLASH+  "datas" + SLASH + "login.properties";
    public static final String HANG_HOA_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "minhle" + SLASH+  "datas" + SLASH + "hanghoa.properties";
    public static final String DOI_TAC_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "minhle" + SLASH+  "datas" + SLASH + "doitac.properties";



    public static enum SHEET {
        LOGIN, TONG_QUAN, HANG_HOA, PHONG_BAN, DOI_TAC
    }

}
