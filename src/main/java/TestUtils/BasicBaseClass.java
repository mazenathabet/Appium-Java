package TestUtils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BasicBaseClass {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void configureAppium() throws MalformedURLException {

//        start the appium server automatically instead of doing it manually
//        AppiumDriverLocalService service = new AppiumServiceBuilder()
//                .withAppiumJS(new File("C:/Users/mthabet/AppData/Roaming/npm/node_modules/appium/build/lib/main.js")) // path of the main.js
//                .withIPAddress("127.0.0.1")  // IP of the appium server
//                .usingPort(4723).build();  // the appium port
//        service.start();

        //Android driver
        // Appium code -> appium server -> Mobile
        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName("MazenPhone"); // name of the device or the emulator
        options.setApp(System.getProperty("user.dir")+"//src//test//java//resources//ApiDemos-debug.apk"); // path of the apk
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
//        service.stop();  // stop the appium server
    }

    //    Scrolling Actions :

    /**
     * mobile: scrollGesture
     * This gesture performs scroll gesture on the given element/area. Available since Appium v1.19
     * Supported arguments
     * elementId: The id of the element to be scrolled. If the element id is missing then scroll bounding area must be provided. If both the element id and the scroll bounding area are provided then this area is effectively ignored.
     * left: The left coordinate of the scroll bounding area
     * top: The top coordinate of the scroll bounding area
     * width: The width of the scroll bounding area
     * height: The height of the scroll bounding area
     * direction: Scrolling direction. Mandatory value. Acceptable values are: up, down, left and right (case-insensitive)
     * percent: The size of the scroll as a percentage of the scrolling area size. Valid values must be float numbers greater than zero, where 1.0 is 100%. Mandatory value.
     * speed: The speed at which to perform this gesture in pixels per second. The value must not be negative. The default value is 5000 * displayDensity
     */
    protected void scrollToEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            ));
        } while (canScrollMore);
    }

    protected void scrollIntoViewUsingAndroidUiAutomator(String text) {
        driver.findElement(AppiumBy.androidUIAutomator(String.format("new UiScrollable(new UiSelector()).scrollIntoView(text(\"%s\"));", text)));
    }

    //    Pressing Actions :

    /**
     * mobile: longClickGesture
     * This gesture performs long click action on the given element/coordinates. Available since Appium v1.19
     * Supported arguments
     * elementId: The id of the element to be clicked. If the element is missing then both click offset coordinates must be provided. If both the element id and offset are provided then the coordinates are parsed as relative offsets from the top left corner of the element.
     * x: The x-offset coordinate
     * y: The y-offset coordinate
     * duration: Click duration in milliseconds. 500 by default. The value must not be negative
     *
     * @param element Web Element we want to click
     */
    protected void longPressAction(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "duration", 2000
        ));
    }

    // Swipe Actions :

    /**
     * mobile: swipeGesture
     * This gesture performs swipe gesture on the given element/area. Available since Appium v1.19
     * Supported arguments
     * elementId: The id of the element to be swiped. If the element id is missing then swipe bounding area must be provided. If both the element id and the swipe bounding area are provided then the area is effectively ignored.
     * left: The left coordinate of the swipe bounding area
     * top: The top coordinate of the swipe bounding area
     * width: The width of the swipe bounding area
     * height: The height of the swipe bounding area
     * direction: Swipe direction. Mandatory value. Acceptable values are: up, down, left and right (case insensitive)
     * percent: The size of the swipe as a percentage of the swipe area size. Valid values must be float numbers in range 0..1, where 1.0 is 100%. Mandatory value.
     * speed: The speed at which to perform this gesture in pixels per second. The value must not be negative. The default value is 5000 * displayDensity
     *
     * @param element   Web Element we want to swipe
     * @param direction Direction of the swipe
     */
    protected void swipeElement(WebElement element, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }

    // Drag-Drop Actions :

    /**
     * mobile: dragGesture
     * This gesture performs drag action from the given element/coordinates to the given point. Available since Appium v1.19
     * Supported arguments
     * elementId: The id of the element to be dragged. If the element id is missing then both start coordinates must be provided. If both the element id and the start coordinates are provided then these coordinates are considered as offsets from the top left element corner.
     * startX: The x-start coordinate
     * startY: The y-start coordinate
     * endX: The x-end coordinate. Mandatory argument
     * endY: The y-end coordinate. Mandatory argument
     * speed: The speed at which to perform this gesture in pixels per second. The value must not be negative. The default value is 2500 * displayDensity
     *
     * @param element WebElement we want to drag
     */
    protected void dragDropElement(WebElement element, int endX, int endY) {
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", endX,
                "endY", endY
        ));
    }
    // Actions that are common in both IOS and Android
    protected Double getFormattedAmount(String amount) {
        return Double.parseDouble(amount.substring(1));
    }

}
