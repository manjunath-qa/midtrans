package midtransDemo.Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class Utils {
	
	
	public Properties readyProperty() throws FileNotFoundException, IOException{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(Constants.propertyFilePath);
		prop.load(fis);

		return prop;
	}
	

	public void implicitWait(WebDriver driver, int timeInSeconds) throws FileNotFoundException, IOException {
		driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
	}

	
	public boolean isElementPresent(WebDriver driver, WebElement elementName, int timeout){
        try{
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(elementName));
            return true;
        }catch(Exception e){
            return false;
        }
    }
	
	public static String capture(WebDriver driver,String screenShotName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") +"/Screenshots/"+System.currentTimeMillis()+
											screenShotName+".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);        

		return dest;
	}

}
