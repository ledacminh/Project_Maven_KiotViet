package minhle.actions;

import minhle.actions.commons.BasePage;
import minhle.actions.commons.PageGenerateManager;
import minhle.interfaces.LoginInterface;
import minhle.interfaces.TongQuanInterface;
import minhle.ultilities.ListDataLoginProperties;
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
        SleepInSecond(1);
    }

    public void nhapTenDangNhap(String tenDangNhap) {
        enterTextToElement(driver, LoginInterface.TEN_DANG_NHAP, tenDangNhap);
        SleepInSecond(1);
    }

    public void nhapMatKhau(String matKhau) {
        enterTextToElement(driver, LoginInterface.MAT_KHAU, matKhau);
        SleepInSecond(1);
    }

    public void clickButtonLogin() {
        clickToElement(driver, LoginInterface.BUTTON_QUAN_LY);
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
