package midtransDemo.uiAuto;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import midtransDemo.Helpers.BaseDriver;
import midtransDemo.Helpers.Constants;
import midtransDemo.Helpers.Utils;
import midtransDemo.Pages.CheckoutPage;
import midtransDemo.Pages.HomePage;

public class Testcase_Assignment extends BaseDriver{

	public WebDriver driver= null;
	HomePage homePage1;
	CheckoutPage checkoutPage;
	Utils utils = new Utils();
 
	static ExtentTest test;
	static ExtentReports report;

	@BeforeTest
	public void initiateBrowser() throws FileNotFoundException, IOException {
		driver = getDriver();	
		report = new ExtentReports(System.getProperty("user.dir")+"/ExtentReportResults.html", false);
		test = report.startTest(this.getClass().getName());
		utils.implicitWait(driver, 5);
		driver.get(Constants.webURL);
		driver.manage().window().maximize();	
	}

	@Test(priority=1)
	public void checkoutSuccessFlow() throws FileNotFoundException, IOException, InterruptedException {
		homePage1 = new HomePage(driver);
		checkoutPage = new CheckoutPage(driver);

		System.out.println("Payment success flow with valid credit card details:");
		homePage1.clickBuyNowButton();

		String totalAmount = homePage1.getTotalAmountFromCart();
		System.out.println("Total amount entered on shopping cart: "+totalAmount);
		
		homePage1.clickBuyCheckoutButton();

		driver.switchTo().frame(0);
		Thread.sleep(1000);
		
		checkoutPage.clickContinueButton();

		checkoutPage.clickCreditCardPayButton();

		checkoutPage.enterCreditCardDetailsSuccessTransaction();

		Assert.assertEquals(checkoutPage.getAmounttoBePaid(), totalAmount);

		checkoutPage.clickPayNowButton();

		driver.switchTo().frame(0);

		checkoutPage.enterCreditCardPasword();

		checkoutPage.clickOKButton();

		driver.switchTo().defaultContent();

		Assert.assertEquals(checkoutPage.validatePaymentSuccessMessage(), "Thank you for your purchase.");

	}

	@Test(priority=2)
	public void checkoutFailFlow() throws FileNotFoundException, IOException, InterruptedException {
		homePage1 = new HomePage(driver);
		System.out.println();
		System.out.println("Payment fail flow with blocked card details and invalid card number entered:");
		homePage1.clickBuyNowButton();

		String totalAmount = homePage1.getTotalAmountFromCart();
		System.out.println("Total amount entered on shopping cart: "+totalAmount);

		homePage1.clickBuyCheckoutButton();

		driver.switchTo().frame(0);
		Thread.sleep(1000);
		
		checkoutPage.clickContinueButton();

		checkoutPage.clickCreditCardPayButton();

		checkoutPage.enterCreditCardDetailsFailTransaction();

		Assert.assertEquals(checkoutPage.getAmounttoBePaid(), totalAmount);

		Assert.assertEquals(checkoutPage.getInvalidCardErrorMessage(), "Invalid card number");

		// Below block of statements will work if the card is un-blocked and entered card number is invalid	
		/*	
		checkoutPage.clickPayNowButton();

		driver.switchTo().frame(0);

		checkoutPage.enterCreditCardPasword();

		checkoutPage.clickOKButton();

		driver.switchTo().defaultContent();

		Assert.assertEquals(checkoutPage.validateTransactioFailedErrorMessage(), "Transaction failed");

		 */	
	}


	@AfterMethod
	public void getResult(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			test.log(LogStatus.FAIL, "Test Method name: "+ result.getName());
			test.log(LogStatus.FAIL, "Test Method status: "+ "Test "+LogStatus.FAIL.toString().toLowerCase()+"ed");
			String screenShotPath = Utils.capture(driver, "Fail-screenShotName");
			test.log(LogStatus.FAIL, result.getThrowable());
			test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(screenShotPath));
		}

		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(LogStatus.PASS, "Test Method name: "+ result.getName());
			test.log(LogStatus.PASS, "Test Method status: "+ "Test "+LogStatus.PASS.toString().toLowerCase()+"ed");
			String screenShotPath = Utils.capture(driver, "Pass-screenShotName");
			test.log(LogStatus.PASS, "Snapshot below: " + test.addScreenCapture(screenShotPath));
		}
		else if(result.getStatus() == ITestResult.SKIP){
			test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
	}

	@AfterTest
	public void closeBrowser() {
		report.endTest(test);
		report.flush();
		report.close();
		driver.quit();
	}


}
