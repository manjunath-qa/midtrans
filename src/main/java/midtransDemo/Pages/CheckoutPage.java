package midtransDemo.Pages;

import java.io.FileNotFoundException;
import java.io.IOException;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import midtransDemo.Helpers.Utils;


public class CheckoutPage {

	WebDriver driver;
	Utils utils = new Utils();

	public CheckoutPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath="//*[@id=\"container\"]/div/div/div[1]/div[2]/div/div/a")
	public WebElement buyNowBtn;

	@FindBy(className="cart-checkout")
	public WebElement checkoutBtn;

	@FindBy(xpath="/html/body/div/div/div/div[2]/div[1]/div[2]/table/tbody/tr[2]/td[3]")
	public WebElement totalAmount;

	@FindBy(className="button-main-content")
	public WebElement continueBtn;

	@FindBy(xpath="//*[@id=\"payment-list\"]/div[1]/a")
	public WebElement creditCardPayBtn;


	@FindBy(name="cardnumber")
	public WebElement enterCardNumber;


	@FindBy(xpath="//*[@id=\"application\"]/div[3]/div/div/div/form/div[2]/div[2]/input")
	public WebElement enterExpiryDate;

	@FindBy(xpath="/html/body/div[3]/div/div[3]/div/div/div/form/div[2]/div[3]/input")
	public WebElement enterExpiryDateFailTransaction;


	@FindBy(xpath="//*[@id=\"application\"]/div[3]/div/div/div/form/div[2]/div[3]/input")
	public WebElement enterCVV;

	@FindBy(xpath="/html/body/div[3]/div/div[3]/div/div/div/form/div[2]/div[4]/input")
	public WebElement enterCVVFailTransaction;


	@FindBy(xpath="//*[@id=\"application\"]/div[1]/a/div[1]")
	public WebElement payNowBtn;


	@FindBy(name="promo")
	public WebElement promoCheckBox;


	@FindBy(id="PaRes")
	public WebElement enterCardPassword;

	@FindBy(xpath="//*[@id=\"acsForm\"]/div[6]/div/button[1]")
	public WebElement okBtn;

	@FindBy(xpath="/html/body/div/div/div/div[1]/div[2]/div/div[2]/div/span[1]")
	public WebElement successMessage;

	@FindBy(xpath="/html/body/div[3]/div/div[3]/div/div/div/form/div[1]/div/div[1]/div[2]/span[2]")
	public WebElement totalAmounttobePaid;

	@FindBy(xpath="/html/body/div[3]/div/div[3]/div/div/div/div/div/div[1]/span")
	public WebElement transactionFailedMessage;

	@FindBy(xpath="/html/body/div[3]/div/div[1]/a/div/span")
	public WebElement RetryButtonText;


	@FindBy(xpath="/html/body/div[3]/div/div[3]/div/div/div/form/div[2]/div[1]/div/span")
	public WebElement invalidCardNumberErrorMessage;



	public void clickBuyNowButton() throws FileNotFoundException, IOException{
		buyNowBtn.click();
	}

	public String getTotalAmountFromCart() {
		return (totalAmount.getText().toString());
	}

	public void clickBuyCheckoutButton() throws FileNotFoundException, IOException, InterruptedException{

		if(utils.isElementPresent(driver, checkoutBtn, 10)) {
			checkoutBtn.click();
		}
	}

	public void clickContinueButton() throws FileNotFoundException, IOException{
		if(utils.isElementPresent(driver, continueBtn, 10)) {
			continueBtn.click();
		}
	}

	public void clickCreditCardPayButton() throws FileNotFoundException, IOException, InterruptedException{
		if(utils.isElementPresent(driver, creditCardPayBtn, 10)) {
			creditCardPayBtn.click();
		}
	}


	public void enterCreditCardDetailsSuccessTransaction() throws FileNotFoundException, IOException {
		if(utils.isElementPresent(driver, enterCardNumber, 10)) {
			enterCardNumber.sendKeys(utils.readyProperty().getProperty("validCerditCardNumber"));
			enterExpiryDate.sendKeys(utils.readyProperty().getProperty("dateOfExpiry"));
			enterCVV.sendKeys(utils.readyProperty().getProperty("cvv"));
			enterCVV.sendKeys(Keys.TAB);
		}
	}

	public void enterCreditCardDetailsFailTransaction() throws FileNotFoundException, IOException {
		if(utils.isElementPresent(driver, enterCardNumber, 10)) {
			enterCardNumber.sendKeys(utils.readyProperty().getProperty("invalidCerditCardNumber"));	
			enterExpiryDateFailTransaction.sendKeys(utils.readyProperty().getProperty("dateOfExpiry"));
			enterCVVFailTransaction.sendKeys(utils.readyProperty().getProperty("dateOfExpiry"));
			enterCVVFailTransaction.sendKeys(Keys.TAB);
		}

	}

	public String getInvalidCardErrorMessage() {
		if(utils.isElementPresent(driver,invalidCardNumberErrorMessage,10)) {
			System.out.println(invalidCardNumberErrorMessage.getText().toString());
		}
		return invalidCardNumberErrorMessage.getText().toString();
	}


	public String getAmounttoBePaid() throws FileNotFoundException, IOException, InterruptedException{
		if(utils.isElementPresent(driver, promoCheckBox, 10)) {
			promoCheckBox.sendKeys(Keys.SPACE);
		}

		String getTotalPayingAmount = totalAmounttobePaid.getText().toString();
		return getTotalPayingAmount;

	}



	public void clickPayNowButton() throws FileNotFoundException, IOException, InterruptedException{
		if(utils.isElementPresent(driver, payNowBtn, 10)) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", payNowBtn);
		}

	}

	public void enterCreditCardPasword() throws InterruptedException {	
		if(utils.isElementPresent(driver, enterCardPassword, 10)) {
			enterCardPassword.click();
			enterCardPassword.sendKeys("112233");
		}
	}


	public void clickOKButton() throws FileNotFoundException, IOException, InterruptedException{
		okBtn.click();

	}

	public String validatePaymentSuccessMessage() throws FileNotFoundException, IOException{
		if(utils.isElementPresent(driver, successMessage, 10)) {
			System.out.println("Success Message: "+ successMessage.getText().toString());
		}
		return (successMessage.getText().toString());
	}

	public String validateTransactioFailedErrorMessage() throws InterruptedException {	
		if(utils.isElementPresent(driver, transactionFailedMessage, 10) && (RetryButtonText.isDisplayed())) {
			System.out.println(transactionFailedMessage.getText().toString());
		}

		return transactionFailedMessage.getText().toString();
	}


}
