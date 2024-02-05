package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.cssSelector;

public class CallbackTestWebDriverManager {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
//  библиотека webdriver manager автоматически определяет ОС и версию браузера, скачивает и устанавливает подходящий файл драйвера
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
//  запуск браузера в режиме headless - отключаем графический интерфейс
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
//  завершение работы браузера
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldSent() {
        driver.findElement(cssSelector("[data-test-id=name] input")).sendKeys("Денис Дорошенко");
        driver.findElement(cssSelector("[data-test-id=phone] input")).sendKeys("+77777777777");
        driver.findElement(cssSelector("[data-test-id=agreement] .checkbox__box")).click();
        driver.findElement(cssSelector(".form-field .button__content")).click();
        assertTrue(driver.findElement(cssSelector("[data-test-id=order-success]")).isEnabled());
    }

    @Test
    public void shouldWarnIfAllFieldsEmpty() {
        driver.findElement(cssSelector(".form-field .button__content")).click();
        assertTrue(driver.findElement(cssSelector(".input_invalid")).isEnabled());
    }

    @Test
    public void shouldWarnIfNameFieldsEmpty() {
        driver.findElement(cssSelector("[data-test-id=phone] input")).sendKeys("+77777777777");
        driver.findElement(cssSelector("[data-test-id=agreement] .checkbox__box")).click();
        driver.findElement(cssSelector(".form-field .button__content")).click();
        assertTrue(driver.findElement(cssSelector(".input_invalid")).isEnabled());
    }

    @Test
    public void shouldWarnIfTelephoneFieldsEmpty() {
        driver.findElement(cssSelector("[data-test-id=name] input")).sendKeys("Денис Дорошенко");
        driver.findElement(cssSelector("[data-test-id=agreement] .checkbox__box")).click();
        driver.findElement(cssSelector(".form-field .button__content")).click();
        assertTrue(driver.findElement(cssSelector(".input_invalid")).isEnabled());
    }

    @Test
    public void shouldWarnIfCheckboxFieldsEmpty() {
        driver.findElement(cssSelector("[data-test-id=name] input")).sendKeys("Денис Дорошенко");
        driver.findElement(cssSelector("[data-test-id=phone] input")).sendKeys("+77777777777");
        driver.findElement(cssSelector(".form-field .button__content")).click();
        assertTrue(driver.findElement(cssSelector(".input_invalid")).isEnabled());
    }

    @Test
    public void shouldWarnIfBadName() {
        driver.findElement(cssSelector("[data-test-id=name] input")).sendKeys("kek lol");
        driver.findElement(cssSelector("[data-test-id=phone] input")).sendKeys("+77777777777");
        driver.findElement(cssSelector("[data-test-id=agreement] .checkbox__box")).click();
        driver.findElement(cssSelector(".form-field .button__content")).click();
        assertTrue(driver.findElement(cssSelector(".input_invalid")).isEnabled());
    }

    @Test
    public void shouldWarnIfBadTelephone1() {
        driver.findElement(cssSelector("[data-test-id=name] input")).sendKeys("Денис Дорошенко");
        driver.findElement(cssSelector("[data-test-id=phone] input")).sendKeys("+12345");
        driver.findElement(cssSelector("[data-test-id=agreement] .checkbox__box")).click();
        driver.findElement(cssSelector(".form-field .button__content")).click();
        assertTrue(driver.findElement(cssSelector(".input_invalid")).isEnabled());
    }

    @Test
    public void shouldWarnIfBadTelephone2() {
        driver.findElement(cssSelector("[data-test-id=name] input")).sendKeys("Денис Дорошенко");
        driver.findElement(cssSelector("[data-test-id=phone] input")).sendKeys("+123456789012");
        driver.findElement(cssSelector("[data-test-id=agreement] .checkbox__box")).click();
        driver.findElement(cssSelector(".form-field .button__content")).click();
        assertTrue(driver.findElement(cssSelector(".input_invalid")).isEnabled());
    }

    @Test
    public void shouldWarnIfBadTelephone3() {
        driver.findElement(cssSelector("[data-test-id=name] input")).sendKeys("Денис Дорошенко");
        driver.findElement(cssSelector("[data-test-id=phone] input")).sendKeys("+abcdefghijk");
        driver.findElement(cssSelector("[data-test-id=agreement] .checkbox__box")).click();
        driver.findElement(cssSelector(".form-field .button__content")).click();
        assertTrue(driver.findElement(cssSelector(".input_invalid")).isEnabled());
    }
}
