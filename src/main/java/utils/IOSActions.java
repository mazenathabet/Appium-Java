package utils;

import io.appium.java_client.ios.IOSDriver;

public class IOSActions extends AppiumUtils {

    public IOSDriver driver;

    public IOSActions(IOSDriver driver) {
        this.driver = driver;
    }
}
