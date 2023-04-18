package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CartPage extends BasePage {
    BasePage basePage=new BasePage(driver);
    int amountOfproduct=1;
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }


    @FindBy(xpath = "//span[@class='m-productPrice__salePrice']")
    public WebElement productPriceInTheBasket;
    String beforeIncrement;
    public void verificationOfThePrice(){
        Assertions.assertEquals(basePage.readFile().get(2),productPriceInTheBasket.getText());
    }

    @FindBy(xpath = "//select[@id='quantitySelect0-key-0']")
    public WebElement quantityDropdown;

    public void changeTheQuantity(int quantity){
        beforeIncrement=priceOfNproductInTheBasket.getText();
        Select select=new Select(quantityDropdown);
        select.selectByValue(String.valueOf(quantity));
        amountOfproduct=quantity;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @FindBy(xpath = "//span[@class='m-productPrice__salePrice']")
    public WebElement priceOfNproductInTheBasket;
    public void verificationOfTheQuantityIncrement(){
        String stringPriceBeforeIncrement =beforeIncrement.
                replaceAll("[A-Z]","").replaceAll("\\s","");
        System.out.println(stringPriceBeforeIncrement);
        int beforeIncrementProductPrice =Integer.parseInt(stringPriceBeforeIncrement.
                replace(".","").replace(",",""));



        String stringPriceAfterIncrement=priceOfNproductInTheBasket.getText().
                replaceAll("[A-Z]","").replaceAll("\\s","");
        int afterIncrementProductPrice=Integer.parseInt(stringPriceAfterIncrement.
                replace(".","").replace(",",""));

        Assertions.assertEquals(beforeIncrementProductPrice*amountOfproduct,afterIncrementProductPrice);
    }

    @FindBy(xpath = "//*[@*='removeCartItemBtn0-key-0']")
    public WebElement deleteButton;
    public void deleteTheProduct(){
        deleteButton.click();
    }

    @FindBy(xpath = "//*[text()='Sepetinizde Ürün Bulunmamaktadır']")
    public WebElement deleteMessage;
    public void verifyThatTheProductHasBeenDeleted(){
        Assertions.assertTrue(waitTheElement(deleteMessage).isDisplayed());
    }
}
