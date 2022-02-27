package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestScenario3 {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "MacOS Catalina");
        caps.setCapability("browserName", "Safari");
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
        
        driver.get("https://www.lambdatest.com/selenium-playground/");
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Input Form Submit')]")).click();
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

        driver.findElement(By.id("name")).sendKeys("TestScenario3");
        driver.findElement(By.id("inputEmail4")).sendKeys("TestScenario3@test.com");
        driver.findElement(By.id("inputPassword4")).sendKeys("Test1234");
        driver.findElement(By.id("company")).sendKeys("LambdaTest");
        driver.findElement(By.id("websitename")).sendKeys("lambdatest.com");
        driver.findElement(By.name("country")).click();
        {
            WebElement dropdown = driver.findElement(By.name("country"));
            dropdown.findElement(By.xpath("//option[. = 'United States']")).click();
        }
        driver.findElement(By.id("inputCity")).sendKeys("Regina");
        driver.findElement(By.id("inputAddress1")).sendKeys("Rae St");
        driver.findElement(By.id("inputAddress2")).sendKeys("4045");
        driver.findElement(By.id("inputState")).sendKeys("SK");
        driver.findElement(By.id("inputZip")).sendKeys("S4S4H4");
        
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

        System.out.println("validate the success message “Thanks for contacting us, we will get back to you shortly.” on the screen.");
        Assert.assertEquals(driver.findElement(By.cssSelector(".success-msg")).getText(), "Thanks for contacting us, we will get back to you shortly.");

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