package minhle.actions.commons;

import minhle.actions.LoginActions;
import minhle.actions.TongQuanActions;
import org.openqa.selenium.WebDriver;

public class PageGeneraterManager {

    public static LoginActions getLoginPage(WebDriver driver) {
        return new LoginActions(driver);
    }

    public static TongQuanActions getTongQuanPage(WebDriver driver) {
        return new TongQuanActions(driver);
    }

}
