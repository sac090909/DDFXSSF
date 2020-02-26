package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page.LoginPage;
import util.BrowserFactory;
import util.ExcelReader;

public class LoginTest {

	WebDriver driver;
	ExcelReader reader = new ExcelReader("./data/data.xlsx");
	String browser = reader.getCellData("Sheet1", "BrowserName", 2);
	String username = reader.getCellData("Sheet1", "UserName", 2);
	String password = reader.getCellData("Sheet1", "Password", 2);

	@BeforeTest
	public void initialization() {

		driver = BrowserFactory.startBrowser(browser);
	}

	@Test
	public void validUserShouldBeAbleToLogin() throws InterruptedException {

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		Thread.sleep(2000);
		loginPage.login(username, password);
		driver.close();
	}

}
