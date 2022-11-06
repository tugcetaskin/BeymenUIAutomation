package Test;

import Base.BaseTest;
import Page.CartPage;
import Page.HomePage;
import Page.ProductDetailPage;
import Page.ProductPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AddToCartProduct extends BaseTest {

    @Test
    public void startTest() throws IOException {

        HomePage homePage = new HomePage(driver);
        homePage.closeInformationPopup();
        homePage.searchProduct();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectProduct();

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.selectSize();
        productDetailPage.clickAddToCart();
        productDetailPage.clickGoToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.increaseAmount();
        cartPage.deleteProducts();
    }
}
