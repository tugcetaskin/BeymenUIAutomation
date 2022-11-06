package Page;

import Base.BasePage;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ProductPage extends BasePage {

    private Logger logger = Logger.getLogger(ProductPage.class);

    By productLocator = By.cssSelector("div.m-productImageList");
    By filterButtonLocator = By.id("productFilterOpenButton");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private List<WebElement> findAllProducts(){
        return findAll(productLocator);
    }

    public int selectRandomProduct() {
        Random random = new Random();
        int randomNumberOfProduct = random.nextInt(findAllProducts().size());
        logger.info(randomNumberOfProduct+". product selected.");
        return randomNumberOfProduct;
    }

    public void selectProduct() {
        Assertions.assertTrue(isDisplayed(filterButtonLocator),"Not on the Product page!");
        int randomNumberOfProduct = selectRandomProduct();
        findAllProducts().get(randomNumberOfProduct).click();
        logger.info(randomNumberOfProduct+". product selected");
    }
}
