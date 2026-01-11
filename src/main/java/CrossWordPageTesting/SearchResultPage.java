package CrossWordPageTesting;

import CrossWordPageTesting.AbstractPage.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends AbstractPage
{
    WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    By Product = By.xpath("//li[contains(@class,'wizzy-result-product wizzy-product')]");

    public List<WebElement> getproduct() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int count1 = 0;
        int CurrentCount = driver.findElements(Product).size();
        while (CurrentCount > count1) {
            count1 = CurrentCount;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            getproductload(Product);
            CurrentCount = driver.findElements(Product).size();
        }
        return driver.findElements(Product);
    }

    public Boolean VerifyProduct() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        List<WebElement>Searchproduct=getproduct();
        for (WebElement product : Searchproduct) {
            if (!product.isDisplayed()) {
                return false;
            }
        }
        return true;
    }
    @FindBy(css = ".wizzy-common-select-selector")
    WebElement SortBy;
    By options=By.cssSelector(".wizzy-common-select-options div");

    @FindBy(xpath = "//div[@title='Format']")
    WebElement format;


    public void getSortBy(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        SortBy.click();
        List<WebElement> dropdown=driver.findElements(options);
        for(WebElement option:dropdown){
            if(option.getText().trim().equalsIgnoreCase("Price: Low to High")){
                option.click();
                break;
            }
        }
        waitForElements(SortProductBy);
    }
    By SortProductBy=By.xpath("//ul[@class='wizzy-search-results-list']/li");
    public void getAftersortProduct(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int Previouscount = 0;
        int CurrentCount = driver.findElements(SortProductBy).size();
        while (CurrentCount > Previouscount) {
            Previouscount = CurrentCount;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            getproductload(SortProductBy);
            CurrentCount = driver.findElements(SortProductBy).size();
        }
    }

    By PriceBy=By.cssSelector(".wizzy-product-item-price");
    public List<Integer> getPrice(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        waitForElements(PriceBy);
        List<WebElement>PriceList=driver.findElements(PriceBy);
        List<Integer> Price=new ArrayList<>();
        int n=PriceList.size();
        for(int i=0;i<n;i++){
            String text=PriceList.get(i)
                    .getText()
                    .replace("₹","")
                    .replace(",","").trim();
            Price.add(Integer.parseInt(text));
        }
        return Price;
    }
    public boolean VerifyPrice(){
        getAftersortProduct();
        List<Integer>Price=getPrice();
        for(int i=0;i<Price.size()-1;i++){
            if(Price.get(i)>=Price.get(i+1)){
                return false;
            }
        }
        return true;
    }


    By formatitemBy=By.xpath("//div[@class='wizzy-facet-body facet-body-product_books_binding_custom']//ul/li");
    By BoxitemBy=By.xpath("//ul[@class='wizzy-search-results-list']/li");
    public List<WebElement> getfilter(){
        format.click();
        getclickFormat(formatitemBy);
        List<WebElement> formatitem=driver.findElements(formatitemBy);
        for(WebElement item: formatitem){
            if(item.getText().equalsIgnoreCase("Box set")){
                item.click();
                break;
            }
        }
        waitForElements(BoxitemBy);
        List<WebElement>BoxItem=driver.findElements(BoxitemBy);
        return BoxItem;
    }
    By parameter=By.xpath("//span[text()='Box Set']/ancestor::li//span[contains(@class,'wizzy-facet-list-item-count')]");
    public int getBoxparameter(){
        String getValue=driver.findElement(parameter).getText().replaceAll("[^0-9]","");
        return Integer.parseInt(getValue);
    }

    public boolean BoxItemCount() {
        int get=getBoxparameter();
        List<WebElement> BoxItem1 = getfilter();
        int size=BoxItem1.size();
        if(size!=get){
            return false;
        }
        return true;
    }
}

//li[contains(@class,'wizzy-result-product wizzy-product')]
//li[contains(@class,'wizzy-result-product wizzy-product')]//div[@class='result-product-item-info']/p[1]
//div[@class='wizzy-facet-body facet-body-product_books_binding_custom']//ul/li[3]