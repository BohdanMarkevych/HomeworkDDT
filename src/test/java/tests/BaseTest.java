package tests;

import model.SearchFilters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import pages.HomePage;
import pages.SearchResultPage;
import pages.ShoppingCartPage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class BaseTest {
    WebDriver driver;

    private static final String ROZETKA_URL = "https://rozetka.com.ua/";

    @BeforeTest
    public void setTest() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }


    @DataProvider(name = "useFilterData", parallel = true)
    public static Object[][] useFilterData() throws JAXBException {
        File file = new File("src/main/resources/searchFilters.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(SearchFilters.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        SearchFilters searchFilters = (SearchFilters) unmarshaller.unmarshal(file);
        Object[][] searchFiltersArray = searchFilters.getSearchFilterList().stream()
                .map(x -> new Object[]{
                        x.getType(), x.getExpectedSum()
                }).toArray(Object[][]::new);
        return searchFiltersArray;
    }

    public WebDriver getDriver() { return driver;  }

    public HomePage getHomePage(){ return new HomePage(getDriver()); }

    public SearchResultPage getSearchResultPage(){ return new SearchResultPage(getDriver()); }

    public ShoppingCartPage getShoppingCartPage(){ return new ShoppingCartPage(getDriver()); }

}
