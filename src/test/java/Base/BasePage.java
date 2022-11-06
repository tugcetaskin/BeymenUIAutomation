package Base;
import Utils.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.apache.log4j.Logger;


import java.util.List;

public class BasePage {

    WebDriver driver;
    private Logger logger= Logger.getLogger(BasePage.class);


    public BasePage(WebDriver driver){
        this.driver=driver;
    }

    public void waitUntil(By locator){
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForIncreaseAmount(By locator,String firstPrice){

        for(int i=0 ; i<10 ; i++){
            if(!driver.findElement(locator).getText().equals(firstPrice)){
                logger.info("Waited for the amount be increased");
                break;
            }
            wait(300);
            logger.info("Waiting five seconds for the amount be increased");
        }
    }


    public WebElement find(By locator) {
        waitUntil(locator);
        return driver.findElement(locator);
    }

    public List<WebElement> findAll(By locator) {
        waitUntil(locator);
        return driver.findElements(locator);
    }

    public void click(By locator) {
        find(locator).click();
        logger.info(locator+"Element was clicked");
    }

    public void select(By locator,String option) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(option);
        logger.info("The "+option+" option has been made");
    }


    public void type(By locator, String text) {
        waitUntil(locator);
        find(locator).sendKeys(text);
        logger.info("Product name written"+ text);
    }

    public boolean isDisplayed(By locator) {
        return find(locator).isDisplayed();
    }

    public boolean isElementPresent(By locator){
        return driver.findElements(locator).size()!=0;
    }

    public void deleteText(By locator) {
        waitUntil(locator);
        find(locator).sendKeys(Keys.CONTROL + "a");
        find(locator).sendKeys(Keys.DELETE);
        logger.info("Text deleted "+locator);
    }

    public int getSelectedOptionsValue(By locator){
        Select sizeSelectBox = new Select(driver.findElement(locator));
        return Integer.parseInt(sizeSelectBox.getFirstSelectedOption().getAttribute("value"));
    }

    public void wait (int millis) {
        try {
            Thread.sleep(millis);

        }catch (Exception e){

        }
    }
}
