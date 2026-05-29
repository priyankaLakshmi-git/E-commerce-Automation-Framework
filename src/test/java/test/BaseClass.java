package test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import DatafactoryClass.FactoryClass;
@Listeners(reports.Listenerclass.class)
public class BaseClass
{	
	static WebDriver driver;
	@BeforeTest(alwaysRun=true)
    @Parameters({"browser","url"}) 
    public  void setup(String browser,String url) 
    {  
    	if(FactoryClass.getDriver()==null)
    	{
    		FactoryClass.setupDriver (browser);
    		driver=FactoryClass.getDriver();
    		driver.get(url);
        }   	           	
	}
    
    @AfterMethod(alwaysRun=true)
    public   void teardown()
    {
    	
    	FactoryClass.getDriver().quit();
    	FactoryClass.removeDriver();
 
    }
    public static WebDriver getDriver()
    {
    	return driver ;
    	
    }
}