package actions.commons;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
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
import org.testng.ITestResult;

import java.io.File;
import java.time.Duration;

public class BaseTest {
    private static WebDriver driver;
    private ATUTestRecorder recorder;

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
                driver = new ChromeDriver(chromeOptions);
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                driver = new EdgeDriver(edgeOptions);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case HEDGE:
                edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                edgeOptions.setHeadless(true);
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(edgeOptions);
                break;
            case HCHROME:
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.setHeadless(true);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            case HFIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                firefoxOptions.setHeadless(true);
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                throw new RuntimeException("Please enter correct browser name");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void takeScreenshots(ITestResult iTestResult) {
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

    public void startRecording() {
        try {
            recorder = new ATUTestRecorder(GlobalConstants.TAKE_VIDEO_PATH, this.getClass().getName() + "_" + GlobalConstants.CURRENT_DATE_TIME, false);
            recorder.start();
        } catch (ATUTestRecorderException e) {
            throw new RuntimeException("[BaseTest][startRecording] " + e.getMessage());
        }
    }

    public void stopRecording() {
        try {
            recorder.stop();
        } catch (ATUTestRecorderException e) {
            throw new RuntimeException("[BaseTest][stopRecording] " + e.getMessage());
        }
    }

    public void CloseBrowser() {
        driver.quit();
    }

}
