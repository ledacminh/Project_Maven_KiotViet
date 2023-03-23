package minhle.testcases;

import minhle.actions.LoginActions;
import minhle.actions.commons.BaseTest;
import minhle.actions.commons.PageGeneraterManager;
import minhle.ultilities.ListDataLoginProperties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTestCases extends BaseTest {
    private WebDriver driver;
    private LoginActions login;

    @Parameters({"browser", "url"})
    @BeforeMethod
    public void initBrowser(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        login = PageGeneraterManager.getLoginPage(driver);
    }

    @Test
    public void Test() {
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
    public void AfterTest() {
        CloseBrowser();
    }
}
