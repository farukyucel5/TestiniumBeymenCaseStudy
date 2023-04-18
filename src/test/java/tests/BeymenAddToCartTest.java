package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import pages.*;

import java.io.IOException;

public class BeymenAddToCartTest extends BaseTest {

    HomePage homePage ;
    BasePage basePage;

    SearchResultPage searchResultPage;
    ProductDetailPage productDetailPage;

    CartPage cartPage;

    @Test
    @Order(1)
    public void verifyHomePageIsVisible(){
        homePage = new HomePage(driver);
        Assertions.assertEquals(homePage.expectedUrl, homePage.actualUrl);
    }
    @Test
    @Order(2)
    public void addToCartTest() throws IOException {
        homePage =new HomePage(driver);
        basePage=new BasePage(driver);
        searchResultPage=new SearchResultPage(driver);
        productDetailPage=new ProductDetailPage(driver);
        cartPage=new CartPage(driver);

        homePage.handleCokies();
        homePage.setGender();
        homePage.passTheProductNameInTheSearchAndCleanTheSearchBox(basePage.dataFetching(0,0));
        homePage.passTheSecondProductInTheSearchBoxAndHitEnter(basePage.dataFetching(1,0));
        searchResultPage.writeTheProductInfoAndPriceIntoTxtFile();
        searchResultPage.goToProductDetails();
        productDetailPage.chooseAsize();
        productDetailPage.clickAddToCartButton();
        productDetailPage.clickOnTheCartIcon();
        cartPage.verificationOfThePrice();

    }
    @Test
    @Order(3)
    public void productQuantityIncrementTest(){
        cartPage=new CartPage(driver);
        cartPage.changeTheQuantity(2);
        cartPage.verificationOfTheQuantityIncrement();
    }

    @Test
    @Order(4)
    public void deleteProductTest(){
        cartPage.deleteTheProduct();
        cartPage.verifyThatTheProductHasBeenDeleted();
    }





}
