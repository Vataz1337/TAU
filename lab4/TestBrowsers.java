package lab4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBrowsers {
    private WebBrowsersDriver chromeDriver;
    private WebBrowsersDriver firefoxDriver;
    private WebBrowsersDriver edgeDriver;

    @Before
    public void setUp() {
        chromeDriver = new WebBrowsersDriver("chrome");
        firefoxDriver = new WebBrowsersDriver("firefox");
        edgeDriver = new WebBrowsersDriver("edge");
    }

    @After
    public void tearDown() {
        chromeDriver.driverQuit();
        firefoxDriver.driverQuit();
        edgeDriver.driverQuit();
    }

    @Test
    public void testLoginWithId() {
        // Test with Chrome
        chromeDriver.setSite("https://www.saucedemo.com/");
        chromeDriver.fillInputById("user-name", "standard_user");
        chromeDriver.fillInputById("password", "secret_sauce");
        chromeDriver.clickById("login-button");
        chromeDriver.assertById("inventory_container", "");

        // Test with Firefox
        firefoxDriver.setSite("https://www.saucedemo.com/");
        firefoxDriver.fillInputById("user-name", "standard_user");
        firefoxDriver.fillInputById("password", "secret_sauce");
        firefoxDriver.clickById("login-button");
        firefoxDriver.assertById("inventory_container", "");

        // Test with Edge
        edgeDriver.setSite("https://www.saucedemo.com/");
        edgeDriver.fillInputById("user-name", "standard_user");
        edgeDriver.fillInputById("password", "secret_sauce");
        edgeDriver.clickById("login-button");
        edgeDriver.assertById("inventory_container", "");
    }

    @Test
    public void testLoginWithCssSelector() {
        // Test with Chrome
        chromeDriver.setSite("https://www.saucedemo.com/");
        chromeDriver.fillInputByCssSelector("#user-name", "standard_user");
        chromeDriver.fillInputByCssSelector("#password", "secret_sauce");
        chromeDriver.clickByCssSelector("#login-button");
        chromeDriver.assertByCssSelector(".inventory_container", "");

        // Test with Firefox
        firefoxDriver.setSite("https://www.saucedemo.com/");
        firefoxDriver.fillInputByCssSelector("#user-name", "standard_user");
        firefoxDriver.fillInputByCssSelector("#password", "secret_sauce");
        firefoxDriver.clickByCssSelector("#login-button");
        firefoxDriver.assertByCssSelector(".inventory_container", "");

        // Test with Edge
        edgeDriver.setSite("https://www.saucedemo.com/");
        edgeDriver.fillInputByCssSelector("#user-name", "standard_user");
        edgeDriver.fillInputByCssSelector("#password", "secret_sauce");
        edgeDriver.clickByCssSelector("#login-button");
        edgeDriver.assertByCssSelector(".inventory_container", "");
    }

    @Test
    public void testLoginWithXPath() {
        // Test with Chrome
        chromeDriver.setSite("https://www.saucedemo.com/");
        chromeDriver.fillInputByXPath("//input[@id='user-name']", "standard_user");
        chromeDriver.fillInputByXPath("//input[@id='password']", "secret_sauce");
        chromeDriver.clickByXPath("//input[@id='login-button']");
        chromeDriver.assertByXPath("//div[@class='inventory_container']", "");

        // Test with Firefox
        firefoxDriver.setSite("https://www.saucedemo.com/");
        firefoxDriver.fillInputByXPath("//input[@id='user-name']", "standard_user");
        firefoxDriver.fillInputByXPath("//input[@id='password']", "secret_sauce");
        firefoxDriver.clickByXPath("//input[@id='login-button']");
        firefoxDriver.assertByXPath("//div[@class='inventory_container']", "");

        // Test with Edge
        edgeDriver.setSite("https://www.saucedemo.com/");
        edgeDriver.fillInputByXPath("//input[@id='user-name']", "standard_user");
        edgeDriver.fillInputByXPath("//input[@id='password']", "secret_sauce");
        edgeDriver.clickByXPath("//input[@id='login-button']");
        edgeDriver.assertByXPath("//div[@class='inventory_container']", "");
    }

}