package TestUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import pageObjects.FormPage;
import utils.AppiumUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class FrameworkInitialize extends AppiumUtils {

    public AndroidDriver driver;
    public FormPage formPage;
    public AppiumDriverLocalService service;

    @BeforeMethod(alwaysRun = true)  // it has to run everytime even if it doesn't have the tag in testng xml
    public void startAppium() throws IOException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/data.properties");
        properties.load(fis);
        String ipAddress = System.getProperty("ipAddress") !=null ? System.getProperty("ipAddress") : properties.getProperty("ipAddress");
        // the ip address from system prop is not null ? if true execute this : if false execute this
//      service = startAppiumServer("127.0.0.1",4723);
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(properties.getProperty("AndroidDeviceName")); // name of the device or the emulator
        options.setApp(System.getProperty("user.dir")+properties.getProperty("GeneralStoreApkPath")); // path of the apk
        options.setChromedriverExecutable(System.getProperty("user.dir")+properties.getProperty("ChromeDriverPath")); // is a Must for configuring the mobile browser
        driver = new AndroidDriver(new URL("http://"+ipAddress+":"+Integer.parseInt(properties.getProperty("port"))), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        formPage = new FormPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
//      service.stop();
    }

}
