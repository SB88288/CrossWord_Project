package CrossWordPageTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {
    WebDriver driver;
    public  LandingPage(WebDriver driver){
        this.driver=driver;
    }
    public void getpageURL(){
        driver.get("https://www.crossword.in/");
    }
    public SearchResultPage getsearch(String name){
        driver.findElement(By.name("q")).sendKeys(name);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        return new SearchResultPage(driver);
    }
}
