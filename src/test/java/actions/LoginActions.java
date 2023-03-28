package actions;

import actions.commons.BasePage;
import actions.commons.PageGenerateManager;
import interfaces.LoginInterface;
import interfaces.TongQuanInterface;
import readPropertyFiles.ListDataLoginProperties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginActions extends BasePage {

    private WebDriver driver;
    private TongQuanActions tongQuan;

    public LoginActions(WebDriver driver) {
        this.driver = driver;
    }

    public void nhapTenGianHang(String tenGianHang) {
        enterTextToElement(driver, LoginInterface.TEN_GIAN_HANG, tenGianHang);
        SleepInSecond(0.5);
    }

    public void nhapTenDangNhap(String tenDangNhap) {
        enterTextToElement(driver, LoginInterface.TEN_DANG_NHAP, tenDangNhap);
        SleepInSecond(0.5);
    }

    public void nhapMatKhau(String matKhau) {
        enterTextToElement(driver, LoginInterface.MAT_KHAU, matKhau);
        SleepInSecond(0.5);
    }

    public void clickButtonLogin() {
        clickToElement(driver, LoginInterface.BUTTON_QUAN_LY);
        SleepInSecond(5);
    }
    public void clickButtonLogin(String text) {
        clickToElement(driver,"");
        SleepInSecond(5);
    }

    public void loginSuccessfully() {
        nhapTenGianHang(ListDataLoginProperties.getString("ten_gian_hang"));
        nhapTenDangNhap(ListDataLoginProperties.getString("ten_dang_nhap"));
        nhapMatKhau(ListDataLoginProperties.getString("mat_khau"));
        clickButtonLogin();
        tongQuan = PageGenerateManager.getTongQuanPage(driver);
        Assert.assertTrue(isDiplayElements(driver, TongQuanInterface.TONG_QUAN_HEADER));
    }

    public boolean verifyLoginSuccessfully() {
        return isDiplayElements(driver, TongQuanInterface.TONG_QUAN_HEADER);
    }

}
