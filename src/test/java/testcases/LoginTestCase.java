package testcases;

import actions.LoginActions;
import actions.commons.BaseTest;
import actions.commons.PageGenerateManager;
import commons.DeleteFiles;
import commons.GlobalConstants;
import commons.Log;
import ultilities.ListDataLoginProperties;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class LoginTestCase extends BaseTest {
    private LoginActions login;

    @Parameters({"isDeletedImage", "isDeletedVideo", "isRecorded"})
    @BeforeClass
    public void beforeClass(boolean isDeletedImage, boolean isDeletedVideo, boolean isRecorded) {
        if (isDeletedImage) {
            DeleteFiles.cleanDirectory(GlobalConstants.TAKE_SCREENSHOTS_PATH);
        }
        if (isDeletedVideo) {
            DeleteFiles.cleanDirectory(GlobalConstants.TAKE_VIDEO_PATH);
        }
        if (isRecorded) {
            startRecording();
        }
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

    @AfterMethod
    public void AfterTest(ITestResult iTestResult) {
        //  takeScreenshots(iTestResult);
        CloseBrowser();
    }


    @Parameters("isRecorded")
    @AfterClass
    public void afterClass(boolean isRecorded) {
        if (isRecorded) {
            stopRecording();
        }

    }
}
