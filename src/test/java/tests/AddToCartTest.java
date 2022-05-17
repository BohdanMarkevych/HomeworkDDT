package tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import java.time.Duration;

//@RunWith(DataDrivenTestRunner.class)

public class AddToCartTest extends BaseTest {
    private int TIME = 500;
    //private String type = "Велосипед";
    //private int expectedSum = 50000;

    /* @Test
     @DataLoader(filePaths = "src/test/java/resources/dataInput.csv", loaderType = LoaderType.CSV)
     public void verifySum(@Param(name = "type") String type,
                            @Param(name = "expectedSum") int expectedSum)*/
    @Test(dataProvider = "useFilterData")
    public void verifyThatCartContainsBillThatIsLessThanSpecifiedSum (String type, int expectedSum) {
        HomePage homePage = getHomePage();
        homePage.implicitWait(TIME);
        homePage.searchByKeyword(type);
        getSearchResultPage().implicitWait(TIME);
        getSearchResultPage().clickProductSortOptionsButton();
        getSearchResultPage().implicitWait(TIME);
        getSearchResultPage().sortProductByLowestPrice();
        getSearchResultPage().implicitWait(TIME);
        getSearchResultPage().waitForPageLoadComplete(Duration.ofSeconds(TIME));
        getSearchResultPage().addFirstProductToCart();
        getSearchResultPage().implicitWait(TIME);
        getSearchResultPage().clickCartButton();
        getSearchResultPage().implicitWait(TIME);
        getSearchResultPage().waitForVisibilityOfElement(TIME, getShoppingCartPage().getCartReceiptIcon());
        Assert.assertTrue(getShoppingCartPage().getSumInCart() < expectedSum);

    }
}
