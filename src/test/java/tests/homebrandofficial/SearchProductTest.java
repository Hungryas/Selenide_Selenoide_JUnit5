package tests.homebrandofficial;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * Selenium
 */
//@Epic("Проверки поиска товара.")
public class SearchProductTest {
    private WebDriver driver;

//    @Before
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

//    @Test
    @DisplayName("Успешная проверка поиска товара по названию.")
    public void checkingSearchProduct() {
        String productName = "ФУТБОЛКА ПОЛО ЧЕРНАЯ (М)";
        Integer productPrice = 2800;
        Integer productCount = 1;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Поиск продукта
        WebElement searchFilter = driver.findElement(By.cssSelector(".js-store-filter-search"));
        searchFilter.sendKeys(productName + Keys.ENTER);

        // Проверка количества найденных
        WebElement prodsNumber = driver.findElement(By.cssSelector(".js-store-filters-prodsnumber"));
        wait.until(visibilityOf(prodsNumber));
        String actualCount = prodsNumber.getText();
        assertEquals(String.valueOf(productCount), actualCount);

        // Проверка цены
        WebElement productPriceVal = driver.findElement(By.cssSelector(".js-store-prod-price-val"));
        Integer actualPrice = Integer.parseInt(productPriceVal.getAttribute("data-product-price-def"));
        assertEquals(productPrice, actualPrice);
    }

//    @After
    public void teardown() {
        driver.quit();
    }
}
