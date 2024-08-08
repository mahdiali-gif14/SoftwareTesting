package mytest;

import java.awt.Dimension;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class program1 {
	 WebDriver driver;
	 private Map<String, Object> vars;
	 JavascriptExecutor js;
	
	@BeforeMethod
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\syed shabab ali\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver=new ChromeDriver();
		 
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.ebay.com");
		js = (JavascriptExecutor) driver;
	    vars = new HashMap<String, Object>();
		
	}
	@AfterMethod
	  public void tearDown() {
	    driver.quit();
	  }
	 public String waitForWindow(int timeout) {
	        try {
	            Thread.sleep(timeout);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        Set<String> whNow = driver.getWindowHandles();
	        Set<String> whThen = (Set<String>) vars.get("window_handles");
	        if (whNow.size() > whThen.size()) {
	            whNow.removeAll(whThen);
	        }
	        return whNow.iterator().next();
	    }
    @Test(priority=1)
	public void openHomepage() 
    {
        // Wait for a specific element on the homepage to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement homepageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gh-logo")));

     System.out.printf("homepage opened successfully");
    }
	@Test(priority=2)
    public void varifyCart()
    {
    	driver.get("https://www.ebay.com/");
        {
          WebElement element = driver.findElement(By.cssSelector(".gh-rvi-menu > .gh-cart-icon"));
          Actions builder = new Actions(driver);
          builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector(".gh-rvi-menu > .gh-cart-icon")).click();
       
    }
	@Test(priority=3)
	public void searchProduct() 
	{
		driver.get("https://www.ebay.com/");
	   
	    driver.findElement(By.id("gh-ac")).click();
	    driver.findElement(By.id("gh-ac")).sendKeys("laptop");
	    driver.findElement(By.id("gh-ac")).sendKeys(Keys.ENTER);
	}
	@Test(priority=4)
    public void navigatetoProduct()
	{
		searchProduct();
	    vars.put("window_handles", driver.getWindowHandles());
	    driver.findElement(By.cssSelector("#item26dab1124c img")).click();
	    vars.put("win103", waitForWindow(2000));
	    driver.switchTo().window(vars.get("win103").toString());
	}
	@Test(priority=5)
    public void addToCart()
    {
		navigatetoProduct(); 
	    driver.findElement(By.cssSelector("#atcBtn_btn_1 .ux-call-to-action__text")).click();
    }
	@Test(priority=6)
	public void verifyCart()
	{
		addToCart();
	    driver.findElement(By.cssSelector(".btn--large")).click();
	    
	    
	}
	 
	@Test(priority=7)
    public void categorySearch()
    {
		driver.get("https://www.ebay.com/");
	  
	    driver.findElement(By.id("gh-shop-a")).click();
	    driver.findElement(By.linkText("Antiques")).click();
	    driver.findElement(By.cssSelector(".b-module:nth-child(1) .b-visualnav__tile:nth-child(3) .b-img")).click();
	    driver.findElement(By.cssSelector(".carousel__snap-point:nth-child(1) > .b-guidancecard__link--noimg > .b-guidancecard__title")).click();
	    driver.findElement(By.cssSelector(".brwrvr__item-card:nth-child(1) .brwrvr__item-card__image")).click();
	       
    }
	@Test(priority=8)
	public void filterBysize10()
	{
		 driver.get("https://www.ebay.com/");
	    
	    driver.findElement(By.id("gh-ac")).click();
	    driver.findElement(By.id("gh-ac")).sendKeys("shoes");
	    driver.findElement(By.id("gh-ac")).sendKeys(Keys.ENTER);
	    driver.findElement(By.cssSelector("#s0-60-0-12-8-4-3-0-3-0-5\\[0\\]-0-2-0-2-0-0-2-list > .carousel__snap-point:nth-child(5) .srp-carousel-list__item-link > div")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector("#s0-60-0-12-8-4-3-0-3-0-5\\[0\\]-0-2-0-2-0-0-2-list > .carousel__snap-point:nth-child(4) .srp-carousel-list__item-link > div"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	} 
	@Test(priority=9)
	public void FilterByBrandAddidas()
	{
		filterBysize10();
	    driver.findElement(By.cssSelector("#s0-60-0-12-8-4-3-0-3-0-5\\[0\\]-0-2-0-2-0-0-2-list > .carousel__snap-point:nth-child(4) .srp-carousel-list__item-link > div")).click();
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	 }
}


	 


	
	

