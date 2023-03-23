package actions.commons;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener extends BaseTest implements ITestListener {


    @Override
    public void onStart(ITestContext result) {
        System.out.println("Bắt đầu thực hiện chạy test case " + result.getName());

    }

    @Override
    public void onFinish(ITestContext result) {
        System.out.println("Kết thúc thực hiện chạy test case " + result.getName());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Đây là test case chạy thất bại " + result.getName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenshots(getDriver(), result);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub

    }

}
