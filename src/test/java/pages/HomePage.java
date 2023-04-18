package pages;

import org.apache.poi.ss.usermodel.Cell;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }
    BasePage basePage=new BasePage(driver);
    @FindBy(xpath = "//*[@id='onetrust-accept-btn-handler']")
    public WebElement cookies;
    public void handleCokies(){
       basePage.waitTheElement(cookies).click();
    }

    @FindBy(xpath = "//*[@id='genderManButton']")
    public WebElement chooseTheGender;
    public void setGender(){

       basePage.waitTheElement(chooseTheGender).click();
    }

    @FindBy(xpath = "//*[@class='default-input o-header__search--input']")
    public WebElement searchBox;
    public void passTheProductNameInTheSearchAndCleanTheSearchBox(Cell productName){
        searchBox.sendKeys(productName.toString());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        searchBox.sendKeys(Keys.BACK_SPACE);
    }
    public void passTheSecondProductInTheSearchBoxAndHitEnter(Cell productName) {
        searchBox.sendKeys(productName.toString(),Keys.ENTER);
    }
    public String expectedUrl="https://www.beymen.com/";
    public String actualUrl=driver.getCurrentUrl();





}
