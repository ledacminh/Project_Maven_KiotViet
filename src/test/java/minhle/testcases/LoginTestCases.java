package minhle.testcases;
import minhle.actions.LoginActions;
import minhle.actions.commons.BaseTest;
import minhle.actions.commons.GlobalConstants;
import minhle.actions.commons.PageGeneraterManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTestCases extends BaseTest {
    WebDriver driver;
    LoginActions login;

    @Parameters({"browser", "url"})
    @BeforeMethod
    public void initBrowser(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        login = PageGeneraterManager.getLoginPage(driver);
    }

    @Test
    public void Test() {
        //Step 1: Nhập tên gian hàng
        login.nhapTenGianHang(GlobalConstants.TEN_GIAN_HANG);

        //Step 2: Nhập tên đăng nhập
        login.nhapTenDangNhap(GlobalConstants.TEN_DANG_NHAP);

        //Step 3: Nhập mật khẩu
        login.nhapMatKhau(GlobalConstants.MAT_KHAU);

        //Step 4: Click button Login
        login.clickButtonLogin();


    }

    @AfterMethod
    public void AfterTest() {
        CloseBrowser();
    }
}
