package pages;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends BasePage {
    BasePage basePage=new BasePage(driver);
    public SearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath = "//h3[text()=' Comfort Fit Haki Gömlek']")
    public WebElement productInfo;
    @FindBy(xpath = "(//span[text()='1.799,00 TL'])[1]")
    public WebElement productPrice;

    public void writeTheProductInfoAndPriceIntoTxtFile(){
        basePage.writeFile(productInfo.getText());
        basePage.writeFile(productPrice.getText());
    }

    public void goToProductDetails(){
        productInfo.click();
    }



}
