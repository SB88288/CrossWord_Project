package Base;

import CrossWordPageTesting.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    public  WebDriver driver;
    public LandingPage land;
    public void getvalue(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }
    @BeforeMethod
    public LandingPage getLanding(){
        getvalue();
        land=new LandingPage(driver);
        land.getpageURL();
        return land;

    }
}
