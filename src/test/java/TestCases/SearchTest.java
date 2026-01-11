package TestCases;

import Base.BaseTest;
import CrossWordPageTesting.SearchResultPage;
import CrossWordPageTesting.SortByPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    String SearchName="games";
    @Test
    public void Search(){
        SortByPage sort=new SortByPage(driver);
        SearchResultPage search=land.getsearch(SearchName);
//        Boolean product=search.VerifyProduct();
        Boolean product=search.VerifyProduct();
        Assert.assertTrue(product,"Result are shown");
        System.out.println(search.getproduct().size());
        search.getSortBy();
        Boolean verifyPrice=search.VerifyPrice();
        Assert.assertTrue(verifyPrice);
        Boolean verifyBoxItem=search.BoxItemCount();
        Assert.assertTrue(verifyBoxItem);
//        driver.close();
    }
}
