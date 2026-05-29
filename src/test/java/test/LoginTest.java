package test;
import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageClass.POMClass;
import reports.Listenerclass;
import utilities.dataProviderClass;
import utilities.retryClass;
public class LoginTest  
{
  WebElement element;
  WebDriver driver1;
  WebDriverWait wait;
  POMClass lp;
  public LoginTest(WebDriver driver)
  {
	  driver1=driver;
	  wait=new WebDriverWait(driver1,Duration.ofSeconds(10));	
	  lp = new POMClass(driver1);
  }
  @Test(groups="group_standard_user" ,dataProviderClass=dataProviderClass.class,dataProvider="productdetails")
  public  void standard_user(String username,String pwd) 
  {	  	  
	  lp.enterUsername(username);
	  Listenerclass.test.get().info("Entered user");  
	  System.out.println("entered username");
      lp.enterPassword(pwd);
      Listenerclass.test.get().info("Entered password");  
      System.out.println("entered pwd");
	  lp.clickLogin();
	  Listenerclass.test.get().info("clicked  on submit"); 
	  System.out.println("clicked Submit");
	  element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title']")));
	  Assert.assertEquals(element.getText(),"Products");
	  System.out.println("Products logo verified"); 
}
  
  //KAN-6 TC_02_Lockedout user_Login
  @Test(groups="group_lockedout_user" ,retryAnalyzer = retryClass.class,dataProviderClass=dataProviderClass.class,dataProvider="login_lockedout_user")
  public  void lockedout_user(String user,String pwd)
  {	    	
	  lp.enterUsername(user);
	  Listenerclass.test.get().info("Entered  user");     			  
      lp.enterPassword(pwd);
      Listenerclass.test.get().info("Entered  password");          
	  lp.clickLogin();
	  Listenerclass.test.get().info("clicked  on submit"); 
	  String  expectedmessage="Epic sadface: Sorry, this user has been locked out.";
	  element=driver1.findElement(By.xpath("//h3"));
	  String actualmessage=element.getText();	  
	  Assert.assertEquals(expectedmessage,actualmessage);
	  Listenerclass.test.get().fail("Locked user validation successful");
	  
}
  
  @Test(groups="group_invalid_login",/*retryAnalyzer = retryClass.class,*/dataProviderClass=dataProviderClass.class,dataProvider ="login_invalid_user")
  public  void invalid_user(String user,String pwd) throws IOException
  {	  	  	  	  
	  lp.enterUsername(user);
	  Listenerclass.test.get().info("Entered user");     			  
      lp.enterPassword(pwd);
      Listenerclass.test.get().info("Entered password");          
	  lp.clickLogin();
	  Listenerclass.test.get().info("clicked  on submit"); 
	  element=driver1.findElement(By.xpath("//h3"));
	  String expectedmessage="Epic sadface: Username and password do not match any user in this service";
	  String actualmessage=element.getText();
	  Assert.assertEquals(actualmessage,expectedmessage);
	  Listenerclass.test.get().pass("Invalid user validation successful");	  
	  
}
  @Test(groups="group_invalid_login",retryAnalyzer = retryClass.class,dataProviderClass=dataProviderClass.class,dataProvider="login_invalid_password")
  public  void invalid_password(String  user,String pwd) throws IOException
  {	  
	  lp.enterUsername(user);
	  Listenerclass.test.get().info("Entered user");     			  
      lp.enterPassword(pwd);
      Listenerclass.test.get().info("Entered password");          
	  lp.clickLogin();
	  Listenerclass.test.get().info("clicked  on submit"); 
	  element=driver1.findElement(By.xpath("//h3"));
	  String expectedmessage="Epic sadface: Username and password do not match any user in this service";
	  String actualmessage=element.getText();
	  Assert.assertEquals(actualmessage,expectedmessage);
	  Listenerclass.test.get().pass("Invalid password validation successful");	  	  
}
  
  
  @Test(groups="group_empty_credentials",retryAnalyzer = retryClass.class,dataProviderClass=dataProviderClass.class,dataProvider="login_empty_user")
  public  void Empty_user(String  user,String pwd)
  {	  
	  lp.enterUsername(user);
	  Listenerclass.test.get().info("Entered user");     			  
      lp.enterPassword(pwd);
      Listenerclass.test.get().info("Entered password");          
	  lp.clickLogin();
	  Listenerclass.test.get().info("clicked  on submit"); 
	  element=driver1.findElement(By.xpath("//h3"));
	  String expectedmessage="Epic sadface: Username and password do not match any user in this service";
	  String actualmessage=element.getText();
	  Assert.assertEquals(actualmessage,expectedmessage);
	  Listenerclass.test.get().pass("Empty User validation successful");	  	  
}
  
  @Test(groups="group_empty_credentials",retryAnalyzer = retryClass.class,dataProviderClass=dataProviderClass.class,dataProvider="login_empty_password")
  public  void Empty_password(String  user,String pwd)
  {	  
	  lp.enterUsername(user);
	  Listenerclass.test.get().info("Entered user");     			  
      lp.enterPassword(pwd);
      Listenerclass.test.get().info("Entered password");          
	  lp.clickLogin();
	  Listenerclass.test.get().info("clicked  on submit"); 
	  element=driver1.findElement(By.xpath("//h3"));
	  String expectedmessage="Epic sadface: Username and password do not match any user in this service";
	  String actualmessage=element.getText();
	  Assert.assertEquals(actualmessage,expectedmessage);
	  Listenerclass.test.get().pass("Empty password validation successful");	  	  
}
  
  @Test(groups="group_empty_credentials",retryAnalyzer = retryClass.class,dataProviderClass=dataProviderClass.class,dataProvider="login_empty_username_password")
  public  void Empty_username_password(String  user,String pwd)
  {	  
	  lp.enterUsername(user);
	  Listenerclass.test.get().info("Entered user");     			  
      lp.enterPassword(pwd);
      Listenerclass.test.get().info("Entered password");          
	  lp.clickLogin();
	  Listenerclass.test.get().info("clicked  on submit"); 
	  element=driver1.findElement(By.xpath("//h3"));
	  String expectedmessage="Epic sadface: Username and password do not match any user in this service";
	  String actualmessage=element.getText();
	  Assert.assertEquals(actualmessage,expectedmessage);
	  Listenerclass.test.get().pass("Empty username and password validation successful");	  	  
}
  
  @Test(groups="group_Performance_user",retryAnalyzer= retryClass.class,dataProviderClass=dataProviderClass.class,dataProvider="login_performance_glitch_user")
  public  void performance_glitch_user(String  user,String pwd) 
  {	  
	  lp.enterUsername(user);
	  Listenerclass.test.get().info("Entered user");     			  
      lp.enterPassword(pwd);
      Listenerclass.test.get().info("Entered password");  
      long starttime=System.currentTimeMillis();
	  lp.clickLogin();
	  Listenerclass.test.get().info("clicked  on submit"); 	  
	  WebElement element1=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title']")));
	  long endtime=System.currentTimeMillis();
	  String  expectedmessage="Products";
	  String actualmessage=element1.getText();	  
	  if((endtime-starttime)>8000)
	  {
		  Listenerclass.test.get().fail("navigated to Inventory page in more than threshold time of 3 seconds:So failed");
		  System.out.println("failed threadshold time exceeded");
	  }
	  else 
	  {
		Assert.assertEquals(actualmessage,expectedmessage);
		Listenerclass.test.get().pass("navigated to Inventory page in less than threshold time of 3 seconds:So passed");
		System.out.println("passed within threadshold time ");
		}
	  }
}
