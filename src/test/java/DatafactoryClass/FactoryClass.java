package DatafactoryClass;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FactoryClass
{
       private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	    public static void setupDriver(String browser) 
	    {
	        if (browser.equalsIgnoreCase("chrome"))
	        {
	    		ChromeOptions chromeoptions=new ChromeOptions();
	        	Map <String,Object>prefs=new HashMap<>();
	        	prefs.put("credentials_enable_service", false); 
	        	prefs.put("profile.password_manager_enabled", false); 
	        	prefs.put("profile.password_manager_leak_detection", false);
	        	chromeoptions.setExperimentalOption("prefs", prefs);       	
	        	driver.set(new ChromeDriver(chromeoptions));
	        	System.out.println("driver setup to chrome");
	    		}
	    	else if(browser.equalsIgnoreCase("Firefox"))
	    	{
	        	FirefoxOptions firefoxoptions=new FirefoxOptions();
	        	firefoxoptions.addPreference("signon.rememberSignons", false);
	    		firefoxoptions.addPreference("signon.autofillForms", false);
	    		firefoxoptions.addPreference("signon.management.page.breach-alerts.enabled", false);
	    		driver.set(new FirefoxDriver(firefoxoptions));	
	    		System.out.println("driver setup to firefox");
	    	}   	
	    	else if(browser.equalsIgnoreCase("edge"))  	
	    	{
	    	    driver.set(new EdgeDriver());
	    	    System.out.println("driver setup to edge");
	    	}
	    	else 
	    	{
	            throw new RuntimeException("Invalid browser");
	        }
	        driver.get().manage().window().maximize();
	    }

	    public static WebDriver getDriver()
	    {
	        return driver.get();
	    }

	    public static void removeDriver()
	    {
	        driver.remove();
	    }
	}

