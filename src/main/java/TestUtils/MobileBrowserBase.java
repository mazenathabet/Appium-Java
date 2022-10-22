package TestUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.AppiumUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class MobileBrowserBase extends AppiumUtils {

    public AndroidDriver driver;

    @BeforeClass(alwaysRun = true)
    public void configureAppium() throws MalformedURLException {
        // configuration to handle mobile browsers
        UiAutomator2Options options = new UiAutomator2Options();
        options.setChromedriverExecutable(System.getProperty("user.dir")+"\\src\\main\\java\\drivers\\chromedriver.exe"); // is a Must for configuring the mobile browser
        options.setDeviceName("MazenPhone"); // name of the device or the emulator
        options.setCapability("browserName", "Chrome");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
