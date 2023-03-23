package actions.commons;

import actions.TongQuanActions;
import actions.LoginActions;
import org.openqa.selenium.WebDriver;

public class PageGenerateManager {

    public static LoginActions getLoginPage(WebDriver driver) {
        return new LoginActions(driver);
    }

    public static TongQuanActions getTongQuanPage(WebDriver driver) {
        return new TongQuanActions(driver);
    }

}
