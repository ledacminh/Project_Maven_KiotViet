package minhle.actions.commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;

    private enum BROWSER {
        CHROME, EDGE, FIREFOX, HCHROME, HEDGE, HFIREFOX
    }

    public WebDriver getBrowserDriver(String browserName, String url){
        BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
        switch (browser) {
            case CHROME :
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;
            case EDGE :
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                driver = new EdgeDriver(edgeOptions);
                break;
            case FIREFOX :
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

    public void CloseBrowser() {
        driver.quit();
    }

}
