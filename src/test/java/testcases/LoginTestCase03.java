package testcases;

import actions.LoginActions;
import actions.commons.BaseTest;
import actions.commons.PageGenerateManager;
import environmentConfig.Environment;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import readPropertyFiles.ListDataLoginProperties;

public class LoginTestCase03 extends BaseTest {
    private LoginActions login;
    Environment environment;

    @Parameters({"browserName", "env", "isRemote"})
    @BeforeMethod
    public void initBrowser(String browserName, String env, boolean isRemote) {
        WebDriver driver = getBrowserDriver(browserName, env, isRemote);
        login = PageGenerateManager.getLoginPage(driver);
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
