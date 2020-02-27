package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DashBoardPage extends BasePage {

	WebDriver driver;

	public DashBoardPage(WebDriver driver) {
		this.driver = driver;
	}

//	@FindBy(how = How.LINK_TEXT, using = "Dashboard")
//	WebElement dashboardPage;

	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Dashboard ')]")
	WebElement dashboardPage;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Bank & Cash')]")
	WebElement bankCashModule;
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'New Account')]")
	WebElement newAccountPage;

	public void waitForDashboardPage() {
		waitForElement(driver, 10, dashboardPage);

	}

	public void goToNewAccountPage() throws InterruptedException {
		bankCashModule.click();
		Thread.sleep(2000);
		newAccountPage.click();
		Thread.sleep(1000);

	}

}
