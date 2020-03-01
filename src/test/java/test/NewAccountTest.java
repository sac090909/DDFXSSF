
package test;

import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page.DashBoardPage;
import page.LoginPage;
import page.NewAccountPage;
import util.BrowserFactory;
import util.ExcelReader;

public class NewAccountTest {

	WebDriver driver;

	ExcelReader reader = new ExcelReader("./data/data.xlsx");
	String browser = reader.getCellData("Sheet1", "BrowserName", 2);
	String username = reader.getCellData("Sheet1", "UserName", 2);
	String password = reader.getCellData("Sheet1", "Password", 2);
	String rndAccountNameExpected = reader.getCellData("Sheet2", "AccountTitle", 2) + new Random().nextInt(999);
	String rndDescriptionExpected = reader.getCellData("Sheet2", "Description", 2);
	String rndInitialBalance = reader.getCellData("Sheet2", "InitialBalance", 2);

	@BeforeTest
	public void initialization() {
		BrowserFactory browserFactory = new BrowserFactory();
		driver = browserFactory.startBrowser(browser);
	}

	@Test(priority = 1)
	public void addNewAccount() throws InterruptedException {

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		String expectedTitle = "Login - TechFios Test Application - Billing";
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Login Page M!Smatched ..");
		loginPage.login(username, password);

		DashBoardPage dashboardPage = PageFactory.initElements(driver, DashBoardPage.class);
		dashboardPage.waitForDashboardPage();
		dashboardPage.goToNewAccountPage();

		Thread.sleep(1000);

		NewAccountPage newAccountPage = PageFactory.initElements(driver, NewAccountPage.class);
		String expectedTitleNewAccountPage = "Accounts- TechFios Test Application - Billing";
		String actualTitleNewAccountPage = newAccountPage.getNewAccountPageTitle();
		Assert.assertEquals(actualTitleNewAccountPage, expectedTitleNewAccountPage, "New Account Page M!Smatched ..");

		newAccountPage.fillAccountInformationForm(rndAccountNameExpected, rndDescriptionExpected, rndInitialBalance);
		Thread.sleep(1000);

		Assert.assertTrue(newAccountPage.displayAcccountCreateSuccessMessageActual().isDisplayed(),
				"Account Created Successfully Not Displayed !!");

		// Scroll to bottom need to delete account
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);

		// js.executeScript("window.scrollTo(0,0)"); //Scroll top//
		// Page Object Page Factory design pattern - not the same way. Issue to define
		// WebElement with Variable @FindBy. What to do ?//

		// Without delay ElementNotInteractableException comes. Disable Implicit wait
		// and Explicit wait to see this//
		newAccountPage.clickOnAccountDeleteButton(rndAccountNameExpected);
		newAccountPage.waitForAccountDeleteConfirmatioOkButton();
		newAccountPage.clickOnAccountDeleteConfirmationOkButton();

		// Validation of Account Delete Successfull by Assert and visually//
		Assert.assertTrue(newAccountPage.displayAcccountDeleteSuccessMessageActual().isDisplayed(),
				"Account Delete Successfully Not Displayed !!");
		Thread.sleep(3000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);

	}

	@AfterTest
	public void exitBrowser() {

		driver.close();
		driver.quit();

	}

}
