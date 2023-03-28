package actions.commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import commons.GlobalConstants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import readPropertyFiles.DevEnvironmentConfig;
import readPropertyFiles.StagingEnvironmentConfig;
import readPropertyFiles.TestingEnvironmentConfig;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    private enum BROWSER {
        CHROME, EDGE, FIREFOX, HCHROME, HEDGE, HFIREFOX
    }

    private enum ENVIRONMENT {
        DEV, TESTING, STAGING, PRODUCTION
    }

    public WebDriver getBrowserDriver(String browserName, String url) {
        BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                driver.set(new EdgeDriver(edgeOptions));
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case HEDGE:
                edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                edgeOptions.setHeadless(true);
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver(edgeOptions));
                break;
            case HCHROME:
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.setHeadless(true);
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case HFIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                firefoxOptions.setHeadless(true);
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
            default:
                throw new RuntimeException("Please enter correct browser name");
        }
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.get().manage().window().maximize();
        driver.get().get(url);
        return driver.get();
    }

    public WebDriver getBrowserDriver(String browserName) {
        BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
        //Get environment from commandline
        String env = System.getProperty("env");
        //Get remote from commandline
        boolean isRemote = Boolean.parseBoolean(System.getProperty("remote"));
        if (!isRemote) {
            switch (browser) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    driver.set(new EdgeDriver(edgeOptions));
                    break;
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;
                case HEDGE:
                    edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    edgeOptions.setHeadless(true);
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver(edgeOptions));
                    break;
                case HCHROME:
                    chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.setHeadless(true);
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                case HFIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.setHeadless(true);
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;
                default:
                    throw new RuntimeException("Please enter correct browser name");
            }
        } else {
            DesiredCapabilities caps = getCapability(true, env);
            try {
                WebDriver driverTempt = new RemoteWebDriver(new URL(GlobalConstants.BROWSER_STACK_URL), caps);
                driver.set(driverTempt);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.get().manage().window().maximize();
        if (env.equalsIgnoreCase(ENVIRONMENT.DEV.toString())) {
            driver.get().get(DevEnvironmentConfig.getString("url"));
        } else if (env.equalsIgnoreCase(ENVIRONMENT.STAGING.toString())) {
            driver.get().get(StagingEnvironmentConfig.getString("url"));
        } else if (env.equalsIgnoreCase(ENVIRONMENT.TESTING.toString())) {
            driver.get().get(TestingEnvironmentConfig.getString("url"));
        }
        return driver.get();
    }

    public DesiredCapabilities getCapability(boolean isRemote, String environment) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if (isRemote) {
            if (environment.equalsIgnoreCase(ENVIRONMENT.DEV.toString())) {
                desiredCapabilities.setCapability("os", DevEnvironmentConfig.getString("os"));
                desiredCapabilities.setCapability("os_version", DevEnvironmentConfig.getString("os_version"));
                desiredCapabilities.setCapability("browser", DevEnvironmentConfig.getString("browser"));
                desiredCapabilities.setCapability("browser_version", DevEnvironmentConfig.getString("browser_version"));
                desiredCapabilities.setCapability("browserstack.local", DevEnvironmentConfig.getString("browserstack_local"));
                desiredCapabilities.setCapability("browserstack.selenium_version", DevEnvironmentConfig.getString("browserstack_selenium_version"));
                desiredCapabilities.setCapability("browserstack.user", GlobalConstants.BROWSER_STACK_USERNAME);
                desiredCapabilities.setCapability("browserstack.key", GlobalConstants.BROWSER_STACK_KEY);
            } else if (environment.equalsIgnoreCase(ENVIRONMENT.TESTING.toString())) {
                desiredCapabilities.setCapability("os", TestingEnvironmentConfig.getString("os"));
                desiredCapabilities.setCapability("os_version", TestingEnvironmentConfig.getString("os_version"));
                desiredCapabilities.setCapability("browser", TestingEnvironmentConfig.getString("browser"));
                desiredCapabilities.setCapability("browser_version", TestingEnvironmentConfig.getString("browser_version"));
                desiredCapabilities.setCapability("browserstack.local", TestingEnvironmentConfig.getString("browserstack_local"));
                desiredCapabilities.setCapability("browserstack.selenium_version", TestingEnvironmentConfig.getString("browserstack_selenium_version"));
                desiredCapabilities.setCapability("browserstack.user", GlobalConstants.BROWSER_STACK_USERNAME);
                desiredCapabilities.setCapability("browserstack.key", GlobalConstants.BROWSER_STACK_KEY);
            } else if (environment.equalsIgnoreCase(ENVIRONMENT.STAGING.toString())) {
                desiredCapabilities.setCapability("os", StagingEnvironmentConfig.getString("os"));
                desiredCapabilities.setCapability("os_version", StagingEnvironmentConfig.getString("os_version"));
                desiredCapabilities.setCapability("browser", StagingEnvironmentConfig.getString("browser"));
                desiredCapabilities.setCapability("browser_version", StagingEnvironmentConfig.getString("browser_version"));
                desiredCapabilities.setCapability("browserstack.local", StagingEnvironmentConfig.getString("browserstack_local"));
                desiredCapabilities.setCapability("browserstack.selenium_version", StagingEnvironmentConfig.getString("browserstack_selenium_version"));
                desiredCapabilities.setCapability("browserstack.user", GlobalConstants.BROWSER_STACK_USERNAME);
                desiredCapabilities.setCapability("browserstack.key", GlobalConstants.BROWSER_STACK_KEY);
            }
        }
        return desiredCapabilities;
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public void takeScreenshots(ITestResult iTestResult) {
        if (ITestResult.SUCCESS == iTestResult.getStatus()) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver.get();
                File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
                File directory = new File(GlobalConstants.TAKE_SCREENSHOTS_PATH);
                if (!directory.exists()) {
                    boolean isCreated = directory.mkdirs();
                    if (!isCreated) {
                        throw new RuntimeException("[BaseTest][takeScreenshots] Created unsuccessfully");
                    }
                }
                FileHandler.copy(file, new File(GlobalConstants.TAKE_SCREENSHOTS_PATH + iTestResult.getName() + "_" + GlobalConstants.CURRENT_DATE_TIME + ".png"));
            } catch (Exception e) {
                throw new RuntimeException("[BaseTest][takeScreenshots] Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    public void takeScreenshots(WebDriver driver, ITestResult iTestResult) {
        if (ITestResult.SUCCESS == iTestResult.getStatus()) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
                File directory = new File(GlobalConstants.TAKE_SCREENSHOTS_PATH);
                if (!directory.exists()) {
                    boolean isCreated = directory.mkdirs();
                    if (!isCreated) {
                        throw new RuntimeException("[BaseTest][takeScreenshots] Created unsuccessfully");
                    }
                }
                FileHandler.copy(file, new File(GlobalConstants.TAKE_SCREENSHOTS_PATH + iTestResult.getName() + "_" + GlobalConstants.CURRENT_DATE_TIME + ".png"));
            } catch (Exception e) {
                throw new RuntimeException("[BaseTest][takeScreenshots] Exception while taking screenshot " + e.getMessage());
            }
        }
    }

//    public void startRecording() {
//        try {
//            recorder = new ATUTestRecorder(GlobalConstants.TAKE_VIDEO_PATH, this.getClass().getName() + "_" + GlobalConstants.CURRENT_DATE_TIME, false);
//            recorder.start();
//        } catch (ATUTestRecorderException e) {
//            throw new RuntimeException("[BaseTest][startRecording] " + e.getMessage());
//        }
//    }

//    public void stopRecording() {
//        try {
//            recorder.stop();
//        } catch (ATUTestRecorderException e) {
//            throw new RuntimeException("[BaseTest][stopRecording] " + e.getMessage());
//        }
//    }

    public void CloseBrowser() {
        driver.get().quit();
        //  driver.remove();
    }

}
