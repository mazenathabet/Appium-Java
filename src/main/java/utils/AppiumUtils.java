package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AppiumUtils {

    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    AppiumDriverLocalService service;

    // Actions that are common in both IOS and Android
    protected Double getFormattedAmount(String amount) {
        return Double.parseDouble(amount.substring(1));
    }

    protected void waitForElementAttributeToBe(WebElement webElement, String attribute, String value, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains((webElement), attribute, value));
    }
    public List<HashMap<String,String>> getJsonData(String jsonFilePath) throws IOException {
        // convert json file content to json string
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);
        // map the retrieved json content to a hashmap of Strings
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>() {});
    }

    public AppiumDriverLocalService startServer(String ipAddress,int port){
        String node_path = "C:/Program Files/nodejs/node.exe";
        String Appium_path = "C:\\Users\\mthabet\\node_modules\\appium\\build\\lib\\main.js";
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File(node_path))
                .withAppiumJS(new File(Appium_path))
                .withIPAddress(ipAddress)
                .usingPort(port));
        System.out.println("Starting the Appium server ... "+"\n"+df.format(new Date())+
                "\n----------------------------------------------------------------");
        service.start();
        return service;
    }

    public String getScreenshotPath(String testcaseName, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"//Reports//"+testcaseName+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }
}
