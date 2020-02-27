package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage{

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;

	}

	@FindBy(how = How.ID, using = "username")
	WebElement UserName;
	@FindBy(how = How.ID, using = "password")
	WebElement Password;
	@FindBy(how = How.NAME, using = "login")
	WebElement SignInButton;

	public String getLoginPageTitle() {
		return driver.getTitle();

	}
	
	public void login(String username, String password) {
		
		UserName.sendKeys(username);
		Password.sendKeys(password);
		SignInButton.click();
	
	}
//
//	public void getDashboardPageHeaderTitleElement() {
//		waitForElement(driver, 10, )
//	}
//
	

}
