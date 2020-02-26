package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import page.LoginPage;
import util.BrowserFactory;
import util.ExcelReader;

public class LoginTest {
	
    WebDriver driver;
    ExcelReader reader = new ExcelReader("./data/data.xlsx");
   // String browser1 = reader.getCellData("Sheet1", "BrowserName", 2);
    String username =reader.getCellData("Sheet1", "UserName", 2);
    String password = reader.getCellData("Sheet1", "Password", 2);	
    
    
    @Test
    public void validUserShouldBeAbleToLogin() throws InterruptedException {
    	driver= BrowserFactory.startBrowser();
    	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);    
    	loginPage.login(username, password);
    	driver.close();
    	driver.quit();
    }

}
