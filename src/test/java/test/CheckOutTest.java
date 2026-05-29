package test;
import static org.testng.Assert.fail;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import reports.Listenerclass;
class CheckOutTest 
{
	WebDriver driver;
	WebDriverWait wait;
public CheckOutTest(WebDriver driver1)
{
	driver=driver1;
	wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	
}

public void checkout_products() throws InterruptedException
{
    System.out.println("this is  in checkout page");
	driver.findElement(By.xpath("//a[@data-test='shopping-cart-link']")).click();
	
	Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/cart.html"));
    
	{
		List  <WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cart_item']"));
		if(cartItems.isEmpty())
		{
			Assert.assertFalse(driver.findElement(By.id("checkout")).isEnabled(),
				    "Checkout should be disabled when cart is empty");
	     }
		else 
		{
			Thread.sleep(5000);
			driver.findElement(By.id("checkout")).click();	
			checkout_pagevalidations();
		}
	}
}

public void checkout_pagevalidations()
{
	Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/checkout-step-one.html"));
	String strfirstname,strlastname,strpostalcode;
	List <WebElement> errormessage;
	driver.findElement(By.id("first-name")).sendKeys("Lakshmi");	
	driver.findElement(By.id("last-name")).sendKeys("priyanka");
	driver.findElement(By.id("postal-code")).sendKeys("562106");	
	driver.findElement(By.id("continue")).click();
	errormessage=driver.findElements(By.xpath("//h3[@data-test='error']"));			
	if(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/checkout-step-one.html"))
	{
	strfirstname=driver.findElement(By.id("first-name")).getAttribute("value");	
	strlastname= driver.findElement(By.id("last-name")).getAttribute("value");
	strpostalcode=driver.findElement(By.id("postal-code")).getAttribute("value");
	if(strfirstname.isEmpty())
	{
		Assert.assertTrue(errormessage.size()>0,"First name field is empty:But Error not displayed");
		Assert.assertEquals(errormessage.get(0).getText(),"Error: First Name is required");
		Listenerclass.test.get().fail("Entering user Firstname is mandatory while check out");
		Assert.fail("Firstname is empty while checkout:so method failed");
	}		
	else if(strlastname.isEmpty())
	{
		errormessage=driver.findElements(By.xpath("//h3[@data-test='error']"));
		Assert.assertTrue(errormessage.size()>0,"last name field is empty:But Error not displayed");	
		Assert.assertEquals(errormessage.get(0).getText(),"Error: Last Name is required");
		Listenerclass.test.get().fail("Entering user lastname is mandatory while check out");
		Assert.fail("Lastname is empty whille checkout:so method failed");
	}
	else if(strpostalcode.isEmpty())
	{
		errormessage=driver.findElements(By.xpath("//h3[@data-test='error']"));
		Assert.assertTrue(errormessage.size()>0,"Postal Code field is empty:But Error not displayed");
		Assert.assertEquals(errormessage.get(0).getText(),"Error: Postal Code is required");
		Listenerclass.test.get().fail("Entering user postalcode is mandatory while check out");
		Assert.fail("Postalcode  is empty while checkout:so method failed");
	}
	}
	if(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/checkout-step-two.html"))
      {
		
		JavascriptExecutor js=(JavascriptExecutor )driver;
		WebElement  finish = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
		js.executeScript("arguments[0].scrollIntoView(true)",finish);
		finish.click();
     }
	if(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/checkout-complete.html"))
    {
		WebElement message=driver.findElement(By.xpath("//div[@class='complete-text']"));
		if(message.getText().equalsIgnoreCase("Your order has been dispatched, and will arrive just as fast as the pony can get there!"))
		Listenerclass.test.get().pass("sucessfully  Order placed");
		JavascriptExecutor js=(JavascriptExecutor )driver;
		js.executeScript("window.scrollBy(0,-320)");
		
    }
	
		
	
}
}



