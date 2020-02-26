package test;

import org.openqa.selenium.WebDriver;

public class TestBase {

	static WebDriver driver;

	public static void tearBrowser() {
	driver.close();
    driver.quit();
		
	}

}
