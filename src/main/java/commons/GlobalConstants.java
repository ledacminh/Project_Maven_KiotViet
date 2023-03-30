package commons;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GlobalConstants {
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final long SHORT_TIMEOUT = 15;
    public static final long LONG_TIMEOUT = 20;
    public static final long RETRY_COUNT = 2;

    public static final String SLASH = File.separator;
    public static final String CONFIG_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "datas" + SLASH + "data.xlsx";
    public static final String FILE_DATA_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "datas" + SLASH + "config.properties";
    public static final String LOGIN_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "datas" + SLASH + "login.properties";
    public static final String HANG_HOA_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "datas" + SLASH + "hanghoa.properties";
    public static final String DOI_TAC_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "datas" + SLASH + "doitac.properties";
    public static final String DEV_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "environmentConfig" + SLASH + "dev.properties";
    public static final String TESTING_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "environmentConfig" + SLASH + "testing.properties";
    public static final String STAGING_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "environmentConfig" + SLASH + "staging.properties";
    public static final String PRODUCTION_PROPERTY_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "environmentConfig" + SLASH + "production.properties";
    public static final String TAKE_SCREENSHOTS_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "screenshots" + SLASH;
    public static final String TAKE_VIDEO_PATH = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "videos" + SLASH;
    public static final String DATA_JSON = PROJECT_PATH + SLASH + "src" + SLASH + "test" + SLASH + "java" + SLASH + "datas" + SLASH + "data.json";
    public static final String CURRENT_DATE_TIME = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(Calendar.getInstance().getTime());
    public  static final String BROWSER_STACK_KEY = "vqiybaC9zH4X5RoVTsGB";
    public  static final String BROWSER_STACK_USERNAME = "utkarshgupta_ouddan";
    public  static final String BROWSER_STACK_URL = "https://" + BROWSER_STACK_USERNAME + ":" + BROWSER_STACK_KEY + "@hub.browserstack.com/wd/hub";



    public static enum SHEET {
        LOGIN, TONG_QUAN, HANG_HOA, PHONG_BAN, DOI_TAC
    }

}
