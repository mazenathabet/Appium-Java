package basic;

import TestUtils.BasicBaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UsingAppPackageActivityTest extends BasicBaseClass {

    @Test
    public void AppPackageActivityTest() {
        // adb shell dumpsys window | find "mCurrentFocus"   - Windows
        // adb shell dumpsys window | grep -E "mCurrentFocus"  -MAC

        //io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies}
        // before the / is the package name
        // after the / is the activity name
        Activity activity = new Activity("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");
        driver.startActivity(activity);
        //the below steps are not needed anymore since we go directly to the activity but this is not a good practice because this is not mimicking the UX
//        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
//        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();

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
