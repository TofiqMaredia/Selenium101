package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestScenario1 {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "Selenium 101");
        caps.setCapability("name", m.getName() + " - " + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");
        caps.setCapability("console","true");
	    caps.setCapability("network",true);
	    caps.setCapability("visual",true);

        ChromeOptions options = new ChromeOptions();
        options.merge(caps);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
        //driver = new ChromeDriver(options);

    }

    @Test
    public void basicTest() throws InterruptedException {
        String value = "Welcome to Lambda Test!";

        driver.get("https://www.lambdatest.com/selenium-playground/");

        driver.findElement(By.xpath("//a[contains(text(),'Simple Form Demo')]")).click();
        Boolean containsText = driver.getCurrentUrl().contains("simple-form-demo");
        
        System.out.println("Validate that the URL contains simple-form-demo.");
        Assert.assertTrue(containsText, "The URL does not contain the text simple-form-demo");

        driver.findElement(By.cssSelector("#user-message")).sendKeys(value);
        driver.findElement(By.id("showInput")).click();

        System.out.println("Validate whether the same text message is displayed in the right-hand panel under the “Your Message:” section.");
        Assert.assertEquals(driver.findElement(By.id("message")).getText(), value);

        Status = "passed";
        Thread.sleep(150);

        System.out.println("Test Scenario 1 Finished");

    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}