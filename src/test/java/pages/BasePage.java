package pages;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BasePage {

    WebDriver driver ;

    public BasePage(WebDriver driver){
        this.driver = driver ;
    }
    public static String currentDir = System.getProperty("user.dir");

    public Cell dataFetching(int row, int column) throws IOException {
        String dosyaYolu=currentDir+"\\src\\test\\java\\DataPackage\\BeymenProducts.xlsx";
        FileInputStream fis = new FileInputStream(dosyaYolu);
        Workbook workbook= WorkbookFactory.create(fis);
        return workbook.getSheet("Sheet1").getRow(row).getCell(column);
    }

    public WebElement waitTheElement(WebElement element){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public  void writeFile(String text) {
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter(currentDir+"\\src\\test\\java\\DataPackage\\product.txt",true));
            writer.newLine();
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<String> readFile() {
        File file1=new File(currentDir+"\\src\\test\\java\\DataPackage\\product.txt");
        List<String> lines=new ArrayList<>();
        try {
            Scanner reader=new Scanner(file1);
            while (reader.hasNextLine())
            {
                String line=reader.nextLine();
                lines.add(line);

            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    public void jsClick(WebElement element){
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30));
        WebElement clickElement1=wait.until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click();",element);

    }

    public void hardWait(int time){
        try {
            Thread.sleep(time* 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void explicitlyWaiting(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


}
