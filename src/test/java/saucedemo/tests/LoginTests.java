package saucedemo.tests;

import saucedemo.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.open();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        assertTrue(loginPage.isLoginSuccessful());
    }

    @Test
    public void testLoginWithInvalidPassword() {
        loginPage.open();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("wrong_pass");
        loginPage.clickLogin();
        assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

