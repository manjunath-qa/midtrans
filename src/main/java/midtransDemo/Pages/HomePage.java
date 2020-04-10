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


public class HomePage {

	WebDriver driver;
	Utils utils = new Utils();

	public HomePage(WebDriver driver){
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

	
}
