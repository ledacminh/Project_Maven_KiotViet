package actions.commons;

import org.testng.Assert;

public class CustomAssert {

    public static boolean verifyEqual(String actual, String expected) {
        try {
            Assert.assertEquals(actual, expected);
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

}
