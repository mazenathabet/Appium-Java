package mobile_browser_app;

import TestUtils.MobileBrowserBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class mobileBrowser_tc_1 extends MobileBrowserBase {

    @Test(groups = {"Smoke"})
    public void browserTest(){
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        System.out.println(driver.getTitle());
        driver.findElement(By.className("navbar-toggler-icon")).click();
        driver.findElement(By.cssSelector("a[routerlink*='products']")).click(); // * is like contains
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)", ""); // you need to scroll down so the element be in the current view
        String text = driver.findElement(By.cssSelector("a[href*='products/3']")).getText();
        Assert.assertEquals(text, "Devops");
    }
}
