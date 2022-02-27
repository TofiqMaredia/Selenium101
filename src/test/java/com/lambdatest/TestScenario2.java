package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestScenario2 {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "Selenium 101");
        caps.setCapability("name", m.getName() + " - " + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        ChromeOptions options = new ChromeOptions();
        options.merge(caps);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
        //driver = new ChromeDriver(options);

    }

    @Test
    public void basicTest() throws InterruptedException {

        driver.get("https://www.lambdatest.com/selenium-playground/");

        driver.findElement(By.linkText("Drag & Drop Sliders")).click();

        {
            WebElement element = driver.findElement(By.linkText("Bootstrap Modals"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".sp__range-success > .sp__range"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".sp__range-success > .sp__range"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".sp__range-success > .sp__range"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
          
        String actual = driver.findElement(By.cssSelector("#rangeSuccess")).getText();
        Assert.assertEquals(actual, "95");

        Status = "passed";
        Thread.sleep(150);

        System.out.println("Test Scenario 2 Finsihed");

    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}