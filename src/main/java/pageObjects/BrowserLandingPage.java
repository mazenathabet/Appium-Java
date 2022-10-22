package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utils.AndroidActions;

import java.util.Set;

public class BrowserLandingPage extends AndroidActions {
    private final By googleSearchField = By.name("q");

    public BrowserLandingPage(AndroidDriver driver) {
        super(driver);
    }

    public void switchToBrowserView() {
        Set<String> contextNames = driver.getContextHandles();  // to retrieve all the context handles
        for (String contextName : contextNames) System.out.printf(contextName + "\n");
        driver.context("WEBVIEW_com.androidsample.generalstore");
    }

    public void searchFor(String name) {
        driver.findElement(googleSearchField).click();
        driver.findElement(googleSearchField).sendKeys(name);
        driver.findElement(googleSearchField).sendKeys(Keys.ENTER);
    }

    public void switchToNativeAppView() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));  // back to the native app again
        driver.context("NATIVE_APP");
    }
}
