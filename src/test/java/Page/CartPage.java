package Page;

import Base.BasePage;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class CartPage extends BasePage {

    private Logger logger = Logger.getLogger(ProductPage.class);

    By quantityLocator = By.id("quantitySelect0-key-0");
    By increaseLocator = By.xpath("//select/option[2]");
    By removeItemLocator = By.id("removeCartItemBtn0-key-0");
    By emptyCartLocator = By.id("emtyCart");
    By nameLocator = By.cssSelector("span[class='m-basket__productInfoName']");
    By priceLocator = By.cssSelector("span[class='m-productPrice__salePrice']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getInfoOnCartPage(){
        String nameOnCart = find(nameLocator).getText().trim();
        String priceOnCart = find(priceLocator).getText().trim();
        String info2 = nameOnCart+priceOnCart;
        logger.info("Product information read from cart");
        return info2;
    }


    public String readTxtFile() throws IOException {
        File file = new File("ProductDetail.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        String line = br.readLine();
        logger.info("Product information read from file");
        return line;
    }

    public boolean isTheProductCorrect() throws IOException {
        String info1 = readTxtFile();
        String info2 = getInfoOnCartPage();
        return info1.contains(info2);
    }

    public void increaseAmount() throws IOException {
        String priceOnCart = find(priceLocator).getText().trim();
        Assertions.assertTrue(isTheProductCorrect(),"The Product Is Not Correct!");
        Assertions.assertTrue(isElementPresent(increaseLocator),"There is not enough stock of products");
        select(quantityLocator,"2");
        Assertions.assertEquals(2,getSelectedOptionsValue(quantityLocator),"The product can't increased!");
        waitForIncreaseAmount(priceLocator,priceOnCart);
        logger.info("The number of product has been increased");
    }

    public void deleteProducts() {
        click(removeItemLocator);
        waitUntil(emptyCartLocator);
        Assertions.assertTrue(isDisplayed(emptyCartLocator),"The Product Not Deleted!");
        logger.info("The product deleted");
    }
}
