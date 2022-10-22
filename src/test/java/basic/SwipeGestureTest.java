package basic;

import TestUtils.BasicBaseClass;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SwipeGestureTest extends BasicBaseClass {

    @Test
    public void LongPressGesture() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='1. Photos']")).click();
        WebElement firstImage = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
        Assert.assertEquals(firstImage.getAttribute("focusable"), "true");
        // Swipe
//        Supported arguments
//        elementId: The id of the element to be swiped. If the element id is missing then swipe bounding area must be provided. If both the element id and the swipe bounding area are provided then the area is effectively ignored.
//        left: The left coordinate of the swipe bounding area
//        top: The top coordinate of the swipe bounding area
//        width: The width of the swipe bounding area
//        height: The height of the swipe bounding area
//        direction: Swipe direction. Mandatory value. Acceptable values are: up, down, left and right (case insensitive)
//        percent: The size of the swipe as a percentage of the swipe area size. Valid values must be float numbers in range 0..1, where 1.0 is 100%. Mandatory value.
//        speed: The speed at which to perform this gesture in pixels per second. The value must not be negative. The default value is 5000 * displayDensity

        swipeElement(firstImage, "left");

        Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "false");
    }
}
