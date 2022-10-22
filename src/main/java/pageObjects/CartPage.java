package pageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AndroidActions;

import java.util.List;

public class CartPage extends AndroidActions {

    private final WebElement totalProductsPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"));
    private final WebElement termsButton = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
    private final By termsPopup = By.id("com.androidsample.generalstore:id/parentPanel");
    private final By termsAndConditionsMessage = By.id("android:id/message");
    private final By closeTermsButton = By.id("android:id/button1");
    private final By subscriptionCheckBox = AppiumBy.className("android.widget.CheckBox");
    private final By checkoutButton = By.id("com.androidsample.generalstore:id/btnProceed");

    public CartPage(AndroidDriver driver) {
        super(driver);
    }

    public double sumProductsPrices() {
        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        int count = productPrices.size();
        double totalSum = 0;
        for (int i = 0; i < count; i++) {
            String productPrice = productPrices.get(i).getText();
            double actualPrice = getFormattedAmount(productPrice); // $160.97 -> 160.97 the $ in index 0 will be removed
            totalSum = totalSum + actualPrice;
        }
        return totalSum;
    }

    public double getActualTotalPrice() {
        String stringActualTotalPrice = totalProductsPrice.getText();
        return getFormattedAmount(stringActualTotalPrice);
    }

    public void markSubscriptionBox() {
        driver.findElement(subscriptionCheckBox).click();

    }

    public void viewTermsMessage() {
        longPressAction(termsButton);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(termsPopup)));
    }

    public String getTermsMessageText() {
        return driver.findElement(termsAndConditionsMessage).getText();
    }

    public void submitPayment() {
        driver.findElement(closeTermsButton).click();
        driver.findElement(checkoutButton).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
