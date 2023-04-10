package testcases;

import actions.LoginActions;
import actions.commons.BaseTest;
import actions.commons.PageGenerateManager;
import actions.commons.ReportListener;
import commons.Log;
import readPropertyFiles.ListDataLoginProperties;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

@Listeners({ReportListener.class})
public class LoginTestCase extends BaseTest {
    private LoginActions login;

    @Parameters({"browserName"})
    @BeforeMethod
    public void initBrowser(String browserName) {
        WebDriver driver = getBrowserDriver(browserName);
        login = PageGenerateManager.getLoginPage(driver);
    }

    @Test
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
    @Test
    public void Test_Login_02() {
        //Step 1: Nhập tên gian hàng
        login.nhapTenGianHang(ListDataLoginProperties.getString("ten_gian_hang"));
        Log.info("[Test_Login_02] Step 1: Nhập tên gian hàng");

        //Step 2: Nhập tên đăng nhập
        login.nhapTenDangNhap(ListDataLoginProperties.getString("ten_dang_nhap"));
        Log.info("[Test_Login_02] Step 2: Nhập tên đăng nhập");
        //Step 3: Nhập mật khẩu
        login.nhapMatKhau(ListDataLoginProperties.getString("mat_khau"));
        Log.info("[Test_Login_02] Step 3: Nhập mật khẩu");
        //Step 4: Click button Login
        login.clickButtonLogin();
        Log.info("[Test_Login_02] Step 4: Click button login");
    }

    public void Test_Login_03() {
        //Step 1: Nhập tên gian hàng
        login.nhapTenGianHang(ListDataLoginProperties.getString("ten_gian_hang"));
        Log.info("[Test_Login_03] Step 1: Nhập tên gian hàng");

        //Step 2: Nhập tên đăng nhập
        login.nhapTenDangNhap(ListDataLoginProperties.getString("ten_dang_nhap"));
        Log.info("[Test_Login_03] Step 2: Nhập tên đăng nhập");
        //Step 3: Nhập mật khẩu
        login.nhapMatKhau(ListDataLoginProperties.getString("mat_khau"));
        Log.info("[Test_Login_03] Step 3: Nhập mật khẩu");
        //Step 4: Click button Login
        login.clickButtonLogin("");
        Log.info("[Test_Login_03] Step 4: Click button login");
    }
    @AfterMethod(alwaysRun = true)
    public void AfterTest(ITestResult iTestResult) {
        CloseBrowser();
    }
}
