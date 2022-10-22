package eCommerce_appium_tests;

import TestUtils.FrameworkInitialize;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.BrowserLandingPage;
import pageObjects.CartPage;
import pageObjects.ProductsPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class eCommerce_tc_1 extends FrameworkInitialize {

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\eCommerce.json");
//        return new Object[][]{ {"Sarah Adams", "Female", "Brazil"} , {"Evan", "Male", "Estonia"} , {"Joli", "Female", "France"} };
        return new Object[][] { {data.get(0)},{data.get(1)}};
    }

    @Test(dataProvider = "getData", groups = {"Smoke","Regression"})
//    public void fillForm(String name, String gender, String countryName) {
        public void fillForm(HashMap<String,String> input ) {
        formPage.setName(input.get("name"));
        formPage.setCountry(input.get("countryName"));
        formPage.setGender(input.get("gender"));
        ProductsPage productsPage = formPage.submitForm();
        productsPage.addProductsByIndex(0);
        productsPage.addProductsByIndex(0);
        CartPage cartPage = productsPage.goToCart();
        double expectedTotalPrice = cartPage.sumProductsPrices();
        double actualTotalPrice = cartPage.getActualTotalPrice();
        Assert.assertEquals(expectedTotalPrice, actualTotalPrice);
        cartPage.markSubscriptionBox();
        cartPage.viewTermsMessage();
        String TermsMessage = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        String actualTermsMessage = cartPage.getTermsMessageText();
        Assert.assertEquals(actualTermsMessage, TermsMessage);
        cartPage.submitPayment();
        BrowserLandingPage browserLandingPage = new BrowserLandingPage(driver);
        browserLandingPage.switchToBrowserView();
        browserLandingPage.searchFor("Amazon");
        browserLandingPage.switchToNativeAppView();
    }

}
