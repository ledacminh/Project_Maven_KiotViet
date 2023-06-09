package testcases;

import actions.LoginActions;
import actions.commons.BaseTest;
import actions.commons.PageGenerateManager;
import commons.Log;
import environmentConfig.Environment;
import org.aeonbits.owner.ConfigFactory;
import readPropertyFiles.ListDataLoginProperties;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class LoginTestCase02 extends BaseTest {
    private LoginActions login;
    Environment environment;

    @Parameters({"browserName", "environmentName"})
    @BeforeMethod
    public void initBrowser(String browserName, String environmentName) {
        ConfigFactory.setProperty("environmentConfig", environmentName);
        environment = ConfigFactory.create(Environment.class);
        WebDriver driver = getBrowserDriver(browserName, environment.url());
        login = PageGenerateManager.getLoginPage(driver);
        Log.info("Running on " + environmentName + "...");
    }

    @Test
    public void Test_Login_01() {
        //Step 1: Nhập tên gian hàng
        login.nhapTenGianHang(ListDataLoginProperties.getString("ten_gian_hang"));

        //Step 2: Nhập tên đăng nhập
        login.nhapTenDangNhap(ListDataLoginProperties.getString("ten_dang_nhap"));

        //Step 3: Nhập mật khẩu
        login.nhapMatKhau(ListDataLoginProperties.getString("mat_khau"));

        //Step 4: Click button Login
        login.clickButtonLogin();


    }


    @AfterMethod(alwaysRun = true)
    public void AfterTest(ITestResult iTestResult) {
        takeScreenshots(iTestResult);
        CloseBrowser();
    }

}
