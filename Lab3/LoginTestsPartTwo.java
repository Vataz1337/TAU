import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTestsPartTwo {
    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public void loadSite() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(974, 1032));
    }

    public void logIn(String username, String password) {
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();
    }

    public void loadAndLogIn(String username, String password) {
        loadSite();
        logIn(username, password);
    }

    public void addToCart() {
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-backpack\"]")).click();
    }

    @Test
    public void loadSiteTest(){
        loadSite();
        assertThat(driver.findElement(By.cssSelector(".login_logo")).getText(), is("Swag Labs"));
    }

    @Test
    public void loginTest() {
        correctLoginTest();
        loginWithInvalidCredentialsTest();
        loginWithLockedOutUserTest();
        loginWithBlankUsernameTest();
        loginWithBlankPasswordTest();
        loginWithSpecialCharactersTest();
        correctPasswordWrongUsername();
        correctUsernameWrongPassword();
    }

    @Test
    public void cartTest() {
        addItemToCartTest();
        removeItemFromCartTest();
        addMultipleItemsToCartTest();
        addAndRemoveItemFromCartTest();
    }

    public void correctLoginTest() {
        loadAndLogIn("standard_user", "secret_sauce");
        List<WebElement> elements = driver.findElements(By.cssSelector(".header_secondary_container"));
        assert (elements.size() > 0);
    }

    public void loginWithInvalidCredentialsTest() {
        loadAndLogIn("invalid_user", "invalid_password");
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username and password do not match any user in this service"));
    }

    public void loginWithLockedOutUserTest() {
        loadAndLogIn("locked_out_user", "secret_sauce");
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Sorry, this user has been locked out."));
    }

    public void loginWithBlankUsernameTest() {
        loadAndLogIn("", "secret_sauce");
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username is required"));
    }

    public void loginWithBlankPasswordTest() {
        loadAndLogIn("standard_user", "");
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Password is required"));
    }

    public void loginWithSpecialCharactersTest() {
        loadAndLogIn("!@#$%^&*()", "!@#$%^&*()");
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username and password do not match any user in this service"));
    }

    public void correctPasswordWrongUsername() {
        loadAndLogIn("invalid_user", "secret_sauce");
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username and password do not match any user in this service"));
    }

    public void correctUsernameWrongPassword() {
        loadAndLogIn("standard_user", "invalid_password");
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username and password do not match any user in this service"));
    }

    public void addItemToCartTest() {
        loadAndLogIn("standard_user", "secret_sauce");
        addToCart();
        assertThat(driver.findElement(By.cssSelector(".shopping_cart_badge")).getText(), is("1"));
    }

    public void removeItemFromCartTest() {
        loadAndLogIn("standard_user", "secret_sauce");
        addToCart();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.cssSelector(".cart_button")).click();
        assertThat(driver.findElement(By.cssSelector(".shopping_cart_badge")).getText(), is(""));
    }

    public void addMultipleItemsToCartTest() {
        loadAndLogIn("standard_user", "secret_sauce");
        addToCart();
        addToCart();
        assertThat(driver.findElement(By.cssSelector(".shopping_cart_badge")).getText(), is("2"));
    }

    @Test
    public void addAndRemoveItemFromCartTest() {
        loadAndLogIn("standard_user", "secret_sauce");
        addToCart();
        driver.findElement(By.cssSelector("*[data-test=\"remove-sauce-labs-backpack\"]")).click();
        assertThat(driver.findElements(By.cssSelector("*[data-test=\"remove-sauce-labs-backpack\"]")).size(), is(0));
    }
}