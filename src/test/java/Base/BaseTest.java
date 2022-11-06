package Base;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sun.org.slf4j.internal.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class BaseTest {

    public WebDriver driver;
    private  Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected String selectBrowser = "chrome";
    private final String baseUrl="https://www.beymen.com";

    @BeforeAll
    public void setUp() {

        PropertyConfigurator.configure("log4j.properties");
        if(selectBrowser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get(baseUrl);
        }else if(selectBrowser.equalsIgnoreCase("firefox")){
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-notifications");
            System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
            driver.get(baseUrl);
        } else{
            Assertions.fail("Driver can not found!");
        }


    }
}
