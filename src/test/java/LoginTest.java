import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class LoginTest {

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "file:///" + System.getProperty("user.dir") + "/src/test/resources";
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    public void testSuccessfulLogin() {
        open("/login.html");

        $(By.id("username")).setValue("testuser");
        $(By.id("password")).setValue("password123");
        $(By.tagName("button")).click();

        $(By.id("message")).shouldHave(text("Login successful!"), Duration.ofSeconds(5)).shouldHave(cssValue("color", "rgba(0, 128, 0, 1)"));
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        open("/login.html");

        $(By.id("username")).setValue("wronguser");
        $(By.id("password")).setValue("wrongpassword");
        $(By.tagName("button")).click();

        $(By.id("message")).shouldHave(text("Invalid username or password!"), Duration.ofSeconds(5)).shouldHave(cssValue("color", "rgba(255, 0, 0, 1)"));
    }

    @Test
    public void testLoginWithEmptyUsername() {
        open("/login.html");

        $(By.id("username")).setValue("");
        $(By.id("password")).setValue("password123");
        $(By.tagName("button")).click();

        $(By.id("message")).shouldHave(text("Username cannot be empty!"), Duration.ofSeconds(5)).shouldHave(cssValue("color", "rgba(255, 0, 0, 1)"));
    }

    @Test
    public void testLoginWithEmptyPassword() {
        open("/login.html");

        $(By.id("username")).setValue("testuser");
        $(By.id("password")).setValue("");
        $(By.tagName("button")).click();

        $(By.id("message")).shouldHave(text("Password cannot be empty!"), Duration.ofSeconds(5)).shouldHave(cssValue("color", "rgba(255, 0, 0, 1)"));
    }
}