package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.AndroidActions;

public class ProductsPage extends AndroidActions {
    private final By addToCartButton = By.xpath("//android.widget.TextView[@text='ADD TO CART']");
    private final By cartButton = By.id("com.androidsample.generalstore:id/appbar_btn_cart");
    private final By cartPageTitle = By.id("com.androidsample.generalstore:id/toolbar_title");

    public ProductsPage(AndroidDriver driver) {
        super(driver);
    }

    public void addProductsByIndex(int index) {
        driver.findElements(addToCartButton).get(index).click();
    }

    public CartPage goToCart() {
        driver.findElement(cartButton).click();
//        waitForElementAttributeToBe(driver.findElement(cartPageTitle), "text", "Cart");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new CartPage(driver);
    }
}
