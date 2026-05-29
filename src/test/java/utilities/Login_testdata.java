package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import  java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Login_testdata 
{
 public static Sheet OpenExcel() throws IOException
{
	File file=new File("C:/Users/LENOVO/Desktop/My stuff/Automation stuff/Login_Testdata.xlsx");	
	try(FileInputStream fIStream=new FileInputStream(file)) //try with resources to automatically close  the file and finally not  neeeded
	{
		Workbook w=new XSSFWorkbook(fIStream);
		return w.getSheetAt(0);	
	}
}			
}
          