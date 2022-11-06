package Page;

import Base.BasePage;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


import java.io.*;
import java.util.Scanner;

public class HomePage extends BasePage {

    private Logger logger = Logger.getLogger(HomePage.class);

    final By closeInformationLocator = By.id("onetrust-accept-btn-handler");
    final By ourPicksLocator = By.id("scn_9ccf6517c4000");
    final By searchBoxLocator = By.cssSelector("div.input-wrapper input");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void closeInformationPopup() {
        if (isDisplayed(closeInformationLocator)) {
            click(closeInformationLocator);
            logger.info("The popup closed");
        }
    }

    public String readProducts(String productNameFile){

        File file = new File(productNameFile);
        if (file.exists()) {
            logger.info("File Found.");
        }else {
            logger.info("File Not Found.");
        }
        Scanner s = null;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String type = s.nextLine();
        logger.info("Product name read from csv");
        return type;
    }

    public void searchProduct() {
        Assertions.assertTrue(isDisplayed(ourPicksLocator),"Not On The Home Page!");

        type(searchBoxLocator,readProducts("ProductS.csv"));
        logger.info("Sort typed in the search box");
        deleteText(searchBoxLocator);
        type(searchBoxLocator,readProducts("ProductG.csv"));
        logger.info("Gomlek typed in the search box");
        find(searchBoxLocator).sendKeys(Keys.ENTER);
    }
}
