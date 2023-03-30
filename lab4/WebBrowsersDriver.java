package lab4;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.WebStorage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class WebBrowsersDriver {
    private WebDriver driver;
    private final WebStorage webStorage;

    public WebBrowsersDriver(String driverType) {
        switch (driverType) {
            case "chrome" ->{
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                this.driver = new ChromeDriver(options);
            }
            case "edge" -> this.driver = new EdgeDriver();
            case "firefox" -> this.driver = new FirefoxDriver();
        }
        this.webStorage = (WebStorage) driver;
    }
    public void driverQuit() {
        driver.quit();
    }

    public void setSite(String address) {
        driver.get(address);
    }

    public void setWindow(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public void fillInputByCssSelector(String cssSelector, String value) {
        clickByCssSelector(cssSelector);
        driver.findElement(By.cssSelector(cssSelector)).sendKeys(value);
    }

    public void clearLocalStorage(String item) {
        webStorage.getLocalStorage().removeItem(item);
    }

    public void fillInputById(String id, String value) {
        driver.findElement(By.id(id)).sendKeys(value);
    }

    public void fillInputByXPath(String xpath, String value) {
        driver.findElement(By.xpath(xpath)).sendKeys(value);
    }

    public void clickById(String id) {
        driver.findElement(By.id(id)).click();
    }

    public void clickByXPath(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void clickByCssSelector(String cssSelector) {
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void assertById(String id, String expectedText) {
        String actualText = driver.findElement(By.id(id)).getText();
        Assert.assertEquals(expectedText, actualText);
    }

    public void assertByXPath(String xpath, String expectedText) {
        String actualText = driver.findElement(By.xpath(xpath)).getText();
        Assert.assertEquals(expectedText, actualText);
    }

    public void assertByCssSelector(String cssSelector, String expectedText) {
        String actualText = driver.findElement(By.cssSelector(cssSelector)).getText();
        Assert.assertEquals(expectedText, actualText);
    }
}