package utilities;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
public class screenshotClass 
{
	static   WebDriver  driver;
	public static void getScreenshot(WebDriver driver1 ) throws IOException
	{
	driver=driver1;
	if(driver!=null)
	{
	TakesScreenshot  ts=(TakesScreenshot) driver;
	File target=ts.getScreenshotAs(OutputType.FILE);
	String date =new java.text.SimpleDateFormat("dd-MM-yyyy_HH-mm-ss_a").format(new java.util.Date());
	String filename="created time_"+date+ " " +"screen_onfailure.jpeg";
	String Folderpath=System.getProperty("user.home")+File.separator+"failed_screenshots";
	File folder=new File(Folderpath);
	if(!folder.exists())
	{
		folder.mkdirs();
	}
	File src=new File(folder ,filename);
	FileHandler.copy(target,src);
	}
	else
		System.out.println("screenshot failed:Driver is not  instantiated");
	}	
}


