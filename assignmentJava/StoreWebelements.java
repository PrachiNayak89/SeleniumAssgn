package assignmentJava;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class StoreWebelements {

	public WebElement getTableLink(WebDriver driver) throws ElementNotFoundException {

		WebElement tableLink = (new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"indicator-chart\"]/div[1]/div/ul/li[3]/a")));
		return tableLink;
	}

	public WebElement getHeaderJan2011(WebDriver driver) {
		WebElement headerJan2011 = (new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"indicator-chart\"]/div[2]/div[2]/div[2]/table/tr/th[2]/a")));
		return headerJan2011;

	}

	public WebElement getIndiaLocationLink(WebDriver driver) {
		String indiaStr = "India";
		WebElement indiaLink = null;
		int locationRowCount = driver
				.findElements(
						By.xpath("//*[@id=\"indicator-chart\"]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr"))
				.size();
		String firstPath = "//*[@id=\"indicator-chart\"]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr[";
		String secondPath = "]/th";
		for (int i = 1; i <= locationRowCount; i++) {
			String finalPath = firstPath + i + secondPath;
			String countryName = driver.findElement(By.xpath(finalPath)).getText();

			if (countryName.equals(indiaStr)) {
				indiaLink = driver.findElement(By.xpath(finalPath));
				break;
			}
		}
		
		//scroll page down
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0,250)");
		return indiaLink;

	}

	public WebElement downLoadCsvFile(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"indicator-chart\"]/div[1]/ul/li[3]/div/a")).click();
		WebElement selectDataOnlyCsv = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id=\"indicator-chart\"]/div[1]/ul/li[3]/div/div/ul/li[1]/a")));
		return selectDataOnlyCsv;
	}

	public WebElement getCountriesDropdownBtn(WebDriver driver) {
		//scroll page down
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0,700)");
				
		WebElement countriesDropdownBtn = (new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"indicator-chart\"]/div[3]/div[3]/div/a")));
		return countriesDropdownBtn;
	}

	public WebElement getRemoveAllLink(WebDriver driver) {
		WebElement removeAllLink = (new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"indicator-chart\"]/div[3]/div[3]/div/div/div/div[2]/p/a")));
		return removeAllLink;
	}
	
	public WebElement getCloseIcon(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0,-300)");
		WebElement closeIcon = (new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"indicator-chart\"]/div[3]/div[3]/div/div/a/i")));
		return closeIcon;
	}
	
	// fetch the Jan2011 column data
	public List getJan2011ColumnData(WebDriver driver) {

		// fetch num rows
		int row_count = driver
				.findElements(
						By.xpath("//*[@id=\"indicator-chart\"]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr"))
				.size();

		// fetch num cols
		int col_count = driver
				.findElements(By
						.xpath("//*[@id=\"indicator-chart\"]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr[1]/td"))
				.size();

		String firstPath = "//*[@id=\"indicator-chart\"]/div[2]/div[2]/div[1]/div[1]/div/div/table/tbody/tr[";
		String secondPath = "]/td[1]/span";

		List jan2011ColList = new ArrayList();

		for (int i = 1; i <= row_count; i++) {
			String finalPath = firstPath + i + secondPath;

			String colVal = driver.findElement(By.xpath(finalPath)).getText();
			if (colVal != null && !colVal.isEmpty()) {
				float colData = Float.valueOf(colVal);
				jan2011ColList.add(colData);
			}

		}

		return jan2011ColList;

	}

}
