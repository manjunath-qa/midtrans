package midtransDemo.Helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseDriver {
	public WebDriver driver=null;
	
	Utils utils = new Utils();
	
	public WebDriver getDriver() throws FileNotFoundException, IOException {
		String browserName=utils.readyProperty().getProperty("browser");
		System.out.println(browserName);
		
		switch(browserName) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", Constants.chromeDriverPath);
			driver = new ChromeDriver();
			break;

		case "Firefox":
			System.setProperty("webdriver.gecko.driver", Constants.firefoxDriverPath);
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("Please enter valid browser name");
			break;

		}

		return driver;
	}
	
}
