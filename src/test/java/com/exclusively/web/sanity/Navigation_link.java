
package com.exclusively.web.sanity;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Navigation_link {
    //list to save visited links
    static List<String> linkAlreadyVisited = new ArrayList<String>();
    WebDriver driver;

    public Navigation_link(WebDriver driver) {
        this.driver = driver;
    }

    public void linkTest() {
        // loop over all the a elements in the page
        for(WebElement link : driver.findElements(By.xpath(".//*[@class='level-top']"))) {
        	
        	try{
            // Check if link is displayed and not previously visited
            if (link.isDisplayed() 
                        && !linkAlreadyVisited.contains(link.getText())) {
            	
            	
                // add link to list of links already visited
                linkAlreadyVisited.add(link.getText());
                
                System.out.println(link.getText());
                for(WebElement sublink : link.findElements(By.tagName("a") )) {
                
                // click on the link. This opens a new page
                //if(link.getAttribute("href").contains("http://staging.exclusively.net/")){
                	
                sublink.click();
                	 driver.navigate().back();
               // }
               
               
                // call recursiveLinkTest on the new page
                new RecursiveLinkTest(driver).linkTest();
            }
        	}
        	}
        	catch(Exception e){
        		
        		continue;
        		
        	}
        }
        //driver.navigate().back();
    }

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://staging.exclusively.net");
        driver.manage().window().maximize();
		//waitgetForPageLoad(30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='root-wrapper']/div/div/div[7]/div[1]")));
		WebElement loginPopupClose =driver.findElement(By.xpath(".//*[@id='root-wrapper']/div/div/div[7]/div[1]"));
		loginPopupClose.click();
        // start recursive linkText
        new Navigation_link(driver).linkTest();
    }
}