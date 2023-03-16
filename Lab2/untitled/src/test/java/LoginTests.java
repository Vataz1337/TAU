import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

public class LoginTests {
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

  @Test
  public void CorrectLoginTest() {
    driver.get("https://www.saucedemo.com/");
    driver.manage().window().setSize(new Dimension(974, 1032));
    WebElement usernameField = driver.findElement(By.id("user-name"));
    WebElement passwordField = driver.findElement(By.id("password"));
    WebElement loginButton = driver.findElement(By.id("login-button"));
    usernameField.sendKeys("standard_user");
    passwordField.sendKeys("secret_sauce");
    loginButton.click();
    WebElement inventoryTitle = driver.findElement(By.cssSelector(".title"));
    assertTrue(inventoryTitle.getText().contains("Products"));
  }

  @Test
  public void loginWithInvalidCredentialsTest() {
    driver.get("https://www.saucedemo.com/");
    driver.manage().window().setSize(new Dimension(974, 1032));

    WebElement usernameField = driver.findElement(By.id("user-name"));
    WebElement passwordField = driver.findElement(By.id("password"));
    WebElement loginButton = driver.findElement(By.id("login-button"));
    usernameField.sendKeys("invalid_user");
    passwordField.sendKeys("invalid_password");
    loginButton.click();

    WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
    assertTrue(errorMessage.isDisplayed());
  }

  @Test
  public void loginWithLockedOutUserTest() {
    driver.get("https://www.saucedemo.com/");
    driver.manage().window().setSize(new Dimension(974, 1032));

    WebElement usernameField = driver.findElement(By.id("user-name"));
    WebElement passwordField = driver.findElement(By.id("password"));
    WebElement loginButton = driver.findElement(By.id("login-button"));
    usernameField.sendKeys("locked_out_user");
    passwordField.sendKeys("secret_sauce");
    loginButton.click();

    WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
    assertTrue(errorMessage.isDisplayed());
  }

  @Test
  public void loginWithBlankUsernameTest() {
    driver.get("https://www.saucedemo.com/");
    driver.manage().window().setSize(new Dimension(974, 1032));

    WebElement usernameField = driver.findElement(By.id("user-name"));
    WebElement passwordField = driver.findElement(By.id("password"));
    WebElement loginButton = driver.findElement(By.id("login-button"));
    usernameField.sendKeys("");
    passwordField.sendKeys("secret_sauce");
    loginButton.click();

    WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
    assertTrue(errorMessage.isDisplayed());
  }

  @Test
  public void loginWithBlankPasswordTest() {
    driver.get("https://www.saucedemo.com/");
    driver.manage().window().setSize(new Dimension(974, 1032));

    WebElement usernameField = driver.findElement(By.id("user-name"));
    WebElement passwordField = driver.findElement(By.id("password"));
    WebElement loginButton = driver.findElement(By.id("login-button"));
    usernameField.sendKeys("standard_user");
    passwordField.sendKeys("");
    loginButton.click();

    WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
    assertTrue(errorMessage.isDisplayed());
  }

  @Test
  public void loginWithSpecialCharactersTest() {
    driver.get("https://www.saucedemo.com/");
    driver.manage().window().setSize(new Dimension(974, 1032));

    WebElement usernameField = driver.findElement(By.id("user-name"));
    WebElement passwordField = driver.findElement(By.id("password"));
    WebElement loginButton = driver.findElement(By.id("login-button"));
    usernameField.sendKeys("!@#$%^&*()");
    passwordField.sendKeys("!@#$%^&*()");
    loginButton.click();

    WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
    assertTrue(errorMessage.isDisplayed());
  }

  @Test
  public void invalidLoginTest() {
    driver.get("https://www.saucedemo.com/");
    driver.manage().window().setSize(new Dimension(974, 1032));

    WebElement usernameField = driver.findElement(By.id("user-name"));
    WebElement passwordField = driver.findElement(By.id("password"));
    WebElement loginButton = driver.findElement(By.id("login-button"));
    usernameField.sendKeys("invalid_user");
    passwordField.sendKeys("invalid_password");
    loginButton.click();

    WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
    assertTrue(errorMessage.isDisplayed());
  }

  @Test
  public void emptyFieldsLoginTest() {
    driver.get("https://www.saucedemo.com/");
    driver.manage().window().setSize(new Dimension(974, 1032));

    WebElement loginButton = driver.findElement(By.id("login-button"));
    loginButton.click();
    
    WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
    assertTrue(errorMessage.isDisplayed());
  }
}