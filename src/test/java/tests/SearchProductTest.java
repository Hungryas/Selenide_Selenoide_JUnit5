package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchProductTest {
    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.edgedriver().setup();
        Configuration.browser = "edge";
        Configuration.driverManagerEnabled = true;
        Configuration.headless = false;
        Configuration.browserSize = "1920x1080";
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://homebrandofficial.ru/wear");
    }

    @Test
    public void checkingSearchProduct() {
        String productName = "ФУТБОЛКА ПОЛО ЧЕРНАЯ (М)";
        Integer productPrice = 2800;
        Integer productCount = 1;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Поиск продукта
        WebElement searchFilter = driver.findElement(By.className("js-store-filter-search"));
        // .t-store__search-wrapper input
        // //div[@class='t-store__search-wrapper']/input
        searchFilter.sendKeys(productName + Keys.ENTER);

        // Проверка количества найденных
        WebElement prodsNumber = driver.findElement(By.className("js-store-filters-prodsnumber"));
        // .js-store-filters-prodsnumber
        // //span[@class='js-store-filters-prodsnumber']
        wait.until(ExpectedConditions.visibilityOf(prodsNumber));
        String actualCount = prodsNumber.getText();
        Assert.assertEquals(String.valueOf(productCount), actualCount);

        // Проверка цены
        WebElement productPriceVal = driver.findElement(By.className("js-store-prod-price-val"));
        // .js-store-prod-price-val
        // //div[contains(@class, 'js-store-prod-price-val')]
        Integer actualPrice = Integer.parseInt(productPriceVal.getAttribute("data-product-price-def"));
        Assert.assertEquals(productPrice, actualPrice);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
