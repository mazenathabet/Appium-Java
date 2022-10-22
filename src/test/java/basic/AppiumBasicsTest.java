package basic;

import TestUtils.BasicBaseClass;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppiumBasicsTest extends BasicBaseClass {

    @Test
    public void WifiSettingName() {
        // xpath, id, accessibilityId, Classname, AndroidUIAutomator
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String AlertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        String actualTitle = "WiFi settings";
        Assert.assertEquals(actualTitle, AlertTitle, String.format("The Alert title is not correct but it was %s instead", actualTitle));
        driver.findElement(By.id("android:id/edit")).sendKeys("Mazen Wifi");
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
    }
}
