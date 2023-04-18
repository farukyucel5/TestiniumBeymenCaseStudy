package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductDetailPage extends BasePage{
    BasePage basePage=new BasePage(driver);
    SearchResultPage searchResultPage=new SearchResultPage(driver);
    public ProductDetailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath = "//div[@class='m-variation']/span")
    public List<WebElement> sizeList;
    public void chooseAsize(){
        sizeList.forEach(n->{
            if (n.getText().equals("XL"))
                basePage.jsClick(n);
        });

    }
    @FindBy(xpath = "//button[@id='addBasket']")
    public WebElement addToCartButton;
    public void clickAddToCartButton(){
        addToCartButton.click();
    }

    @FindBy(xpath = "//*[@*='icon icon-cart icon-cart-active']")
    public WebElement goToTheCart;
    public void clickOnTheCartIcon(){
        goToTheCart.click();
    }



}
