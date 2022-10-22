package basic;

import TestUtils.BasicBaseClass;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragDropTest extends BasicBaseClass {


    @Test
    public void LongPressGesture() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement sourceElement = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        dragDropElement(sourceElement,
                623,
                560);
        String result = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(result, "Dropped!");
    }
}
