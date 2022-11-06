package Page;

import Base.BasePage;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;


public class ProductDetailPage extends BasePage {

    private Logger logger = Logger.getLogger(ProductDetailPage.class);

    By selectSizeLocator = By.id("sizes");
    By selectSize = By.cssSelector("span.m-variation__item");
    By addCartLocator = By.id("addBasket");
    By productName = By.cssSelector("span.o-productDetail__description");
    By productPrice = By.id("priceNew");
    By goToCartLocator = By.cssSelector("svg[class='icon icon-cart icon-cart-active']");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    private List<WebElement> findAllProducts(){
        return findAll(selectSize);
    }

    public void selectSize() {
        boolean isElementFound = false;
        for(WebElement element:findAllProducts()){
            if(!element.getAttribute("class").contains("disabled")){
                element.click();
                isElementFound=true;
                break;
            }
        }
        Assertions.assertTrue(isElementFound,"The size can not selected!");
        logger.info("Size selected");
    }

    public void clickAddToCart() {
        Assertions.assertTrue(isDisplayed(selectSizeLocator),"Not on the detail page!");
        try {
            getInformationAboutProduct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        click(addCartLocator);
    }

    public void clickGoToCart() {
        click(goToCartLocator);
    }


    public void getInformationAboutProduct() throws Exception {
        String name = find(productName).getText().trim();
        String price = find(productPrice).getText().trim();

        File file = new File("ProductDetail.txt");
        if (file.exists()) {
                file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file,false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(name+price);
        bWriter.close();
        logger.info("Product information saved");
    }

}
