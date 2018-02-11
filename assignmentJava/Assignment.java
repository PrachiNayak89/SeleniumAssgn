package assignmentJava;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import org.openqa.selenium.JavascriptExecutor;

public class Assignment extends FileDownloadVerify{

	// wait until objects got fully rendered

	public void waitForPageLoad(WebDriver driver) {

		Wait<WebDriver> wait = new WebDriverWait(driver, 60);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				System.out.println("[logger]: Current Window State  : "
						+ String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	// setup method

	WebDriver setUp() throws InterruptedException {
		 System.setProperty("webdriver.chrome.driver",
		 "C:\\ProgramData\\jee-oxygen\\chromedriver.exe");
		 ChromeOptions chromeOptions = new ChromeOptions();
		 chromeOptions.addArguments("--start-maximized");
		 WebDriver driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://data.oecd.org/price/inflation-cpi.htm");
		Thread.sleep(10000);
		waitForPageLoad(driver);
		return driver;
	}

	
	public static void main(String[] args) throws ElementNotFoundException, InterruptedException {

		Assignment control = new Assignment();

		// Initiate Object repository object

		StoreWebelements objRepo = new StoreWebelements();

		// Step 1: Instantiate the WebDriver control and launch the URL
		WebDriver driver = control.setUp();

		// Step 2: Click on the 'Table' link next to chart tab
		WebElement tableLink = objRepo.getTableLink(driver);
		tableLink.click();

		// Step 3: Click on second column header “Jan 2011” to sort it
		WebElement headerJan2011 = objRepo.getHeaderJan2011(driver);
		headerJan2011.click();

		// Step 4: Verify that numeric values are sorted in descending order

		List descendingColData = objRepo.getJan2011ColumnData(driver);
		control.VerifyListInDescendingOrder(descendingColData);

		// Step 5: Again click on second column header “Jan 2011” to sort it in reverse
		// order
		headerJan2011.click();

		// Step 6: Verify that numeric values are sorted in ascending order

		List ascendingColData = objRepo.getJan2011ColumnData(driver);
		control.VerifyListInAscendingOrder(ascendingColData);
		
		//Step 7 : Click on India link in Location column
		WebElement indiaLocLink = objRepo.getIndiaLocationLink(driver);
		indiaLocLink.click();
		
		//Step 8 : Click download ->  Selected data only (.csv) option
		WebElement selectDataOnlyCsv = objRepo.downLoadCsvFile(driver);
		selectDataOnlyCsv.click();
		
		//Step 9 : Verify that downloaded csv file contains at least 10 rows with string IND
		control.verifyCsvFileHas10INDrows();
		
		//Step 10: Click on Countries dropdown present below the table
		WebElement countriesDropdown = objRepo.getCountriesDropdownBtn(driver);
		countriesDropdown.click();
		
		//Step 11: Click on Remove All link
		WebElement removeAllLink = objRepo.getRemoveAllLink(driver);
		removeAllLink.click();
		
		//Step 12: Click on a close icon displayed at the right top corner to close the popup menu
		WebElement closeIcon = objRepo.getCloseIcon(driver);
		closeIcon.click();
		
		//Step 13: Close the browser
		
		System.out.println("[logger]: Test Completed!");
		driver.quit();

	}

}
