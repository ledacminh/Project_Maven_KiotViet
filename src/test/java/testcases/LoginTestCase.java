package testcases;

import actions.LoginActions;
import actions.commons.BaseTest;
import actions.commons.PageGenerateManager;
import actions.commons.ReportListener;
import commons.Log;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import ultilities.ListDataLoginProperties;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

@Listeners(ReportListener.class)
public class LoginTestCase extends BaseTest {
    private LoginActions login;

    @Parameters({"browser", "url"})
    @BeforeMethod
    public void initBrowser(String browserName, String url) {
        WebDriver driver = getBrowserDriver(browserName, url);
        login = PageGenerateManager.getLoginPage(driver);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Đăng nhập thành công")
    @Step("Login thành công")
    @Test(description = "Đăng nhập thành công!")
    public void Test_Login_01() {
        //Step 1: Nhập tên gian hàng
        login.nhapTenGianHang(ListDataLoginProperties.getString("ten_gian_hang"));
        Log.info("[Test_Login_01] Step 1: Nhập tên gian hàng");

        //Step 2: Nhập tên đăng nhập
        login.nhapTenDangNhap(ListDataLoginProperties.getString("ten_dang_nhap"));
        Log.info("[Test_Login_01] Step 2: Nhập tên đăng nhập");
        //Step 3: Nhập mật khẩu
        login.nhapMatKhau(ListDataLoginProperties.getString("mat_khau"));
        Log.info("[Test_Login_01] Step 3: Nhập mật khẩu");
        //Step 4: Click button Login
        login.clickButtonLogin();
        Log.info("[Test_Login_01] Step 4: Click button login");
    }

    @AfterMethod(alwaysRun = true)
    public void AfterTest(ITestResult iTestResult) {
        CloseBrowser();
    }
}
