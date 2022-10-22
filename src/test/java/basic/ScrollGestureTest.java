package basic;

import TestUtils.BasicBaseClass;
import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class ScrollGestureTest extends BasicBaseClass {

    @Test
    public void LongPressGesture() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
//         Option 1 to scroll :
//        if you know where to scroll
//        scrollIntoViewUsingAndroidUiAutomator("WebView");

//         Option 2 to scroll :
//        if you don't know to which element you should scroll
//        Supported arguments
//        elementId: The id of the element to be scrolled. If the element id is missing then scroll bounding area must be provided. If both the element id and the scroll bounding area are provided then this area is effectively ignored.
//        left: The left coordinate of the scroll bounding area
//        top: The top coordinate of the scroll bounding area
//        width: The width of the scroll bounding area
//        height: The height of the scroll bounding area
//        direction: Scrolling direction. Mandatory value. Acceptable values are: up, down, left and right (case-insensitive)
//        percent: The size of the scroll as a percentage of the scrolling area size. Valid values must be float numbers greater than zero, where 1.0 is 100%. Mandatory value.
//        speed: The speed at which to perform this gesture in pixels per second. The value must not be negative. The default value is 5000 * displayDensity
        scrollToEndAction();
    }
}
