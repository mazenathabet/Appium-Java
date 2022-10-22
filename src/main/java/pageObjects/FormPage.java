package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.AndroidActions;

public class FormPage extends AndroidActions {
    private final WebElement nameField = driver.findElement(By.id("com.androidsample.generalstore:id/nameField"));
    private final WebElement countryField = driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry"));
    private final WebElement submitButton = driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop"));
    private final WebElement femaleRadio = driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale"));
    private final WebElement maleRadio = driver.findElement(By.id("com.androidsample.generalstore:id/radioMale"));

    public FormPage(AndroidDriver driver) {
        super(driver);
    }

    public void setName(String name) {
        nameField.click();
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setCountry(String countryName) {
        countryField.click();
        scrollIntoViewUsingAndroidUiAutomator(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();
    }

    public ProductsPage submitForm() {
        submitButton.click();
        return new ProductsPage(driver);
    }

    public void setGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            maleRadio.click();
        } else femaleRadio.click();
    }

    public String getToastMessageText() {
        return driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
    }
}
