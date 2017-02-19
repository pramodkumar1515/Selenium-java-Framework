package com.exclusively.web.sanity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScrollForCache {
    //list to save visited links
    static List<String> linkAlreadyVisited = new ArrayList<String>();
    WebDriver driver;

    public ScrollForCache(WebDriver driver) {
        this.driver = driver;
    }
    
    public void Scroll() throws InterruptedException {
    	
    	
     //driver.navigate().to("http://www.exclusively.com/new-arrivals-last-7-days-designer-women?trk=nav");
   	 //Thread.sleep(2000);
   	 JavascriptExecutor jse = (JavascriptExecutor)driver; 
   	 jse.executeScript("window.scrollBy(0,6000)", "");
   	 Thread.sleep(5000);

   	Scroll();

    	
    	
    }
    
    

    

    public void linkTest() throws InterruptedException {
        // loop over all the a elements in the page
    	WebDriverWait wait = new WebDriverWait(driver,60);
    	Actions move = new Actions(driver);
    	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@class='close close-icon']")));
    	driver.findElement(By.xpath(".//*[@class='close close-icon']")).click();
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='nav']/li[1]/a/span[1]")));
    	WebElement nav_link= driver.findElement(By.xpath(".//*[@id='nav']/li[1]/a/span[1]"));
    	move.moveToElement(nav_link).build().perform();
    	Thread.sleep(2000);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='clearer']")));
    	WebElement sub_link= driver.findElement(By.xpath(".//*[@class='clearer']"));
    	//move.moveToElement(toElement)
        for(WebElement link : sub_link.findElements(By.tagName("a"))) {	
        	try{

           // if (link.isDisplayed() 
            		//&& !linkAlreadyVisited.contains(link.getText())) {
        		if(!linkAlreadyVisited.contains(link.getText())){
                // add link to list of links already visited
                linkAlreadyVisited.add(link.getText());
                System.out.println(link.getAttribute("href"));
        		wait.until(ExpectedConditions.elementToBeClickable(link));
                // click on the link. This opens a new page
                //if(link.getAttribute("href").contains("http://staging.exclusively.net/")){
        		move.moveToElement(nav_link).moveToElement(link).click().build().perform();
                	 //link.click();
                	 Thread.sleep(2000);
                	 //JavascriptExecutor jse = (JavascriptExecutor)driver; 
                	 //jse.executeScript("window.scrollBy(0,6000)", "");
                	 //Thread.sleep(3000);
                	 //jse.executeScript("window.scrollBy(0,6000)", "");
                	 //Thread.sleep(3000);
                	 driver.navigate().back();
                	 //Thread.sleep(3000);
                	 

                 	//Actions moveto = new Actions(driver);
                	//WebElement nav_link1= driver.findElement(By.xpath(".//*[@id='nav']/li[1]/a/span[1]"));
                	//Thread.sleep(2000);
                	//moveto.moveToElement(nav_link1).build().perform();
               	 //Thread.sleep(1000);
                //}
               
               
                // call recursiveLinkTest on the new page
                	 new ScrollForCache(driver).linkTest();
            }
        	}
        	catch(Exception e){
        		
        		e.printStackTrace();
        		continue;
        		
        	}
        }
        //driver.navigate().back();
        
    }

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://www.exclusively.com/new-arrivals-last-7-days-designer-women?trk=nav");
        driver.manage().window().maximize();
		//waitgetForPageLoad(30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='root-wrapper']/div/div/div[7]/div[1]")));
		WebElement loginPopupClose =driver.findElement(By.xpath(".//*[@id='root-wrapper']/div/div/div[7]/div[1]"));
		loginPopupClose.click();
        // start recursive linkText
        //new ScrollForCache(driver).linkTest();
        new ScrollForCache(driver).linkTest();
    }
}