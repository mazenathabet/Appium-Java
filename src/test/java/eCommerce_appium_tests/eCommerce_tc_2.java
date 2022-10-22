package eCommerce_appium_tests;

import TestUtils.FrameworkInitialize;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerce_tc_2 extends FrameworkInitialize {
    @Test (groups = {"Smoke"})
    public void fillForm_ErrorValidation() {
        formPage.setCountry("Afghanistan");
        formPage.setGender("Female");
        formPage.submitForm();
        String toastMessage = formPage.getToastMessageText();
        Assert.assertEquals(toastMessage, "Please enter your name", "The Toast Message is incorrect");
    }
}
