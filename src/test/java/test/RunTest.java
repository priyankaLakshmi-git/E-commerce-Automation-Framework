package test;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import utilities.dataProviderClass;

public class RunTest extends BaseClass
{
	  
@Test(dataProviderClass=dataProviderClass.class,dataProvider="login_standard_user")
public void Login(String user,String  pwd) 
{
	LoginTest loginObj=new LoginTest(BaseClass.getDriver());
	loginObj.standard_user(user,pwd);
}


@Test(priority = 1,dataProviderClass = dataProviderClass.class,dataProvider="productdetails")
public void AddProducts(String[] pname) throws InvalidFormatException, IOException, InterruptedException 
{	
	ProductsTest pTest=new ProductsTest(BaseClass.getDriver());
	int cartcount=0;
	  for(int i=0;i<pname.length;i++)
	  {
	       cartcount=pTest.Add_Products(pname[i]);
	  }
	  if(cartcount==0)
	  System.out.println("cart is empty.Please add  any  item to proceed to check out");
	  Thread.sleep(5000);	  
}

@Test(priority = 2)
public void checkoutProducts() throws InvalidFormatException, IOException, InterruptedException 
{	
	CheckOutTest checkTest=new CheckOutTest(BaseClass.getDriver());
	checkTest.checkout_products();
}	

}
