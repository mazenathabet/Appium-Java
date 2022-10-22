package basic;

import TestUtils.BasicBaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MiscellaneousAppiumActionsTest extends BasicBaseClass {

    @Test
    public void Miscellaneous() {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        DeviceRotation landScape = new DeviceRotation(0, 0, 90);
        driver.rotate(landScape);
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String AlertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        String actualTitle = "WiFi settings";
        Assert.assertEquals(actualTitle, AlertTitle, String.format("The Alert title is not correct but it was %s instead", actualTitle));
        driver.setClipboardText("Mazen Wifi");
        String pastedText = driver.getClipboardText();
        driver.findElement(By.id("android:id/edit")).sendKeys(pastedText);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
