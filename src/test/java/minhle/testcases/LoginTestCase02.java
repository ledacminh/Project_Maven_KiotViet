package minhle.testcases;

import minhle.actions.LoginActions;
import minhle.actions.commons.BaseTest;
import minhle.actions.commons.PageGenerateManager;
import minhle.commons.DeleteFiles;
import minhle.commons.GlobalConstants;
import minhle.ultilities.ListDataLoginProperties;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class LoginTestCase02 extends BaseTest {
    private LoginActions login;

    @BeforeClass
    public void beforeClass() {
        DeleteFiles.deleteFolder(GlobalConstants.TAKE_SCREENSHOTS_PATH);
        DeleteFiles.deleteFolder(GlobalConstants.TAKE_VIDEO_PATH);
        startRecording();
    }

    @Parameters({"browser", "url"})
    @BeforeMethod
    public void initBrowser(String browserName, String url) {
        WebDriver driver = getBrowserDriver(browserName, url);
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

    @Test
    public void Test_Login_02() {
        //Step 1: Nhập tên gian hàng
        login.nhapTenGianHang(ListDataLoginProperties.getString("ten_gian_hang"));

        //Step 2: Nhập tên đăng nhập
        login.nhapTenDangNhap(ListDataLoginProperties.getString("ten_dang_nhap"));

        //Step 3: Nhập mật khẩu
        login.nhapMatKhau(ListDataLoginProperties.getString("mat_khau"));

        //Step 4: Click button Login
        login.clickButtonLogin();


    }


    @AfterMethod
    public void AfterTest(ITestResult iTestResult) {
        takeScreenshots(iTestResult);
        CloseBrowser();
    }


    @AfterClass
    public void afterClass() {
        stopRecording();
    }
}
