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

    @Test
    public void testLoginWithId() {
        // Test with Chrome
        chromeDriver.setSite("https://example.com/login");
        chromeDriver.fillInputById("username", "testuser");
        chromeDriver.fillInputById("password", "testpass");
        chromeDriver.clickById("login-button");
        chromeDriver.assertById("welcome-message", "Welcome, testuser!");

        // Test with Firefox
        firefoxDriver.setSite("https://example.com/login");
        firefoxDriver.fillInputById("username", "testuser");
        firefoxDriver.fillInputById("password", "testpass");
        firefoxDriver.clickById("login-button");
        firefoxDriver.assertById("welcome-message", "Welcome, testuser!");

        // Test with Edge
        edgeDriver.setSite("https://example.com/login");
        edgeDriver.fillInputById("username", "testuser");
        edgeDriver.fillInputById("password", "testpass");
        edgeDriver.clickById("login-button");
        edgeDriver.assertById("welcome-message", "Welcome, testuser!");
    }

    @Test
    public void testLoginWithCssSelector() {
        // Test with Chrome
        chromeDriver.setSite("https://example.com/login");
        chromeDriver.fillInputByCssSelector("#username", "testuser");
        chromeDriver.fillInputByCssSelector("#password", "testpass");
        chromeDriver.clickByCssSelector("#login-button");
        chromeDriver.assertByCssSelector("#welcome-message", "Welcome, testuser!");

        // Test with Firefox
        firefoxDriver.setSite("https://example.com/login");
        firefoxDriver.fillInputByCssSelector("#username", "testuser");
        firefoxDriver.fillInputByCssSelector("#password", "testpass");
        firefoxDriver.clickByCssSelector("#login-button");
        firefoxDriver.assertByCssSelector("#welcome-message", "Welcome, testuser!");

        // Test with Firefox
        edgeDriver.setSite("https://example.com/login");
        edgeDriver.fillInputByCssSelector("#username", "testuser");
        edgeDriver.fillInputByCssSelector("#password", "testpass");
        edgeDriver.clickByCssSelector("#login-button");
        edgeDriver.assertByCssSelector("#welcome-message", "Welcome, testuser!");
    }

    @Test
    public void testLoginWithXPath() {
        // Test with Chrome
        chromeDriver.setSite("https://example.com/login");
        chromeDriver.fillInputByXPath("//input[@id='username']", "testuser");
        chromeDriver.fillInputByXPath("//input[@id='password']", "testpass");
        chromeDriver.clickByXPath("//button[@id='login-button']");
        chromeDriver.assertByXPath("//div[@id='welcome-message']", "Welcome, testuser!");

        // Test with Firefox
        firefoxDriver.setSite("https://example.com/login");
        firefoxDriver.fillInputByXPath("//input[@id='username']", "testuser");
        firefoxDriver.fillInputByXPath("//input[@id='password']", "testpass");
        firefoxDriver.clickByXPath("//button[@id='login-button']");
        firefoxDriver.assertByXPath("//h1[@id='welcome-message']", "Welcome back, myusername!");

        // Test with Firefox
        edgeDriver.setSite("https://example.com/login");
        edgeDriver.fillInputByXPath("//input[@id='username']", "testuser");
        edgeDriver.fillInputByXPath("//input[@id='password']", "testpass");
        edgeDriver.clickByXPath("//button[@id='login-button']");
        edgeDriver.assertByXPath("//h1[@id='welcome-message']", "Welcome back, myusername!");
    }

    @After
    public void tearDown() {
        chromeDriver.driverQuit();
        firefoxDriver.driverQuit();
        edgeDriver.driverQuit();
    }
}