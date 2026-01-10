package CrossWordPageTesting;

import CrossWordPageTesting.AbstractPage.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultPage extends AbstractPage {
    WebDriver driver;
    public SearchResultPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //  By ProductBy=By.xpath("//li[contains(@class,'wizzy-result-product wizzy-product')]//div[@class='result-product-item-info']/p[1]");
    //        List<WebElement> Product=driver.findElements(By.xpath("//ul[@class='wizzy-search-results-list']/li"));
    By Product=By.xpath ("//li[contains(@class,'wizzy-result-product wizzy-product')]");
    public List<WebElement> getproduct(){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        int count=0;
        int CurrentCount=driver.findElements(Product).size();
        while(CurrentCount>count) {
            count = CurrentCount;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            getproductload(Product);
            CurrentCount=driver.findElements(Product).size();;
        }
        return driver.findElements(Product);
    }
    public Boolean VerifyProduct(){
        for(WebElement product:getproduct()){
            if(!product.isDisplayed()){
                return false;
            }
        }
        return true;
    }

    @FindBy(css = ".wizzy-common-select-selector")
    WebElement SortBy;
    By options=By.cssSelector(".wizzy-common-select-options div");
    public void getSortBy(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        SortBy.click();
//        driver.findElement(By.cssSelector(".wizzy-common-select-options")).getDomAttribute("style");
        List<WebElement>dropdown=driver.findElements(options);
        for(WebElement option:dropdown){
            if(option.getText().trim().equalsIgnoreCase("Price: Low to High")){
                option.click();
                break;
            }
        }

    }
}


//li[contains(@class,'wizzy-result-product wizzy-product')]
//li[contains(@class,'wizzy-result-product wizzy-product')]//div[@class='result-product-item-info']/p[1]