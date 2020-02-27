package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewAccountPage extends BasePage {

	WebDriver driver;

	public NewAccountPage(WebDriver driver) {

		this.driver = driver;

	}

	@FindBy(how = How.XPATH, using = "//title[contains(text(),'Accounts')]")
	WebElement newAccountPageTitle;
	@FindBy(how = How.ID, using = "account")
	WebElement accountTitle;
	@FindBy(how = How.ID, using = "description")
	WebElement description;
	@FindBy(how = How.ID, using = "balance")
	WebElement initialBalance;
	@FindBy(how = How.XPATH, using = "//button[@type='submit' and @class='btn btn-primary']")
	WebElement submitButton;

	@FindBy(how = How.XPATH, using = "//div[@class='alert alert-success fade in']//child::i")
	WebElement acccountCreateSuccessMessage;

//  FindBy does not take variable
//	@FindBy(how = How.XPATH, using = "//td[contains(text(),'"+rndAccountNameExpected+"')]//following-sibling::*[2]//child::a[2]")
//	WebElement AccountDeleteButton;

	@FindBy(how = How.XPATH, using = "//table[@class='table table-striped table-bordered']")
	WebElement accountDeleteButton;

	@FindBy(how = How.XPATH, using = "//button[@data-bb-handler='confirm']")
	WebElement accountDeleteConfirmationOkButton;

	@FindBy(how = How.XPATH, using = "//div[@class='alert alert-success fade in']//child::i")
	WebElement accountDeleteSuccessMessage;

	public String getNewAccountPageTitle() {
		return driver.getTitle();
	}

	public void setAccountTitle(String accountName) {
		accountTitle.sendKeys(accountName);
	}

	public void setDescription(String descriptions) {
		description.sendKeys(descriptions);
	}

	public void setInitialBalance(String initialBalances) {
		initialBalance.sendKeys(initialBalances);
	}

	public void clickSubmitButton() {
		submitButton.click();
	}

	public WebElement displayAcccountCreateSuccessMessageActual() {
		return acccountCreateSuccessMessage;
	}


	public void clickOnAccountDeleteButton(String dynamicAccount) {
		 accountDeleteButton.findElement(
				By.xpath("//table[@class='table table-striped table-bordered']/descendant::td[contains(text(),'"
						+ dynamicAccount + "')]//following-sibling::*[2]//child::a[2]")).click();
		
	}
	
	public void waitForAccountDeleteConfirmatioOkButton() {
		waitForElement(driver, 10, accountDeleteConfirmationOkButton);

	}

	public void clickOnAccountDeleteConfirmationOkButton() {
		accountDeleteConfirmationOkButton.click();
	}

	public WebElement displayAcccountDeleteSuccessMessageActual() {
		return accountDeleteSuccessMessage;
	}

}
