package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTableValidation {

	@Test
	public void certainCompanyShouldExist() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/test/web-table-element.php");

		String name1 = "Raju";
		String name2 = "Chisty";
		String name3 = "Parveen";

		ArrayList<String> listOfNames = new ArrayList<>();
		listOfNames.add("Raju");
		listOfNames.add("Chisty");
		listOfNames.add("Parveen");

		// 1. Create a method to get index of a given column header
		// WebElement element =
		// driver.findElements(By.xpath("//table[@class='dataTable']//th"));

		Thread.sleep(3000);
		List<WebElement> tableHeaderElements = driver.findElements(By.xpath("//table[@class='dataTable']//th"));

		System.out.println("Number of Headers: " + tableHeaderElements.size());
		System.out.println("First Web Element of the header: " + tableHeaderElements.get(0));
		System.out.println("First Column Header Name: " + tableHeaderElements.get(0).getText());

		// Gave us the dynamic index of the column header we want to validate
		String index = getHeaderIndex("Company", tableHeaderElements);

		String columnDataXpath = "//table[@class='dataTable']//td[" + index + "]";
		
		List<WebElement> columnDataElements = driver.findElements(By.xpath(columnDataXpath));
		
		String expectedCompanyName = "REC";
		
		for(int i=0; i< columnDataElements.size(); i++) {
			String columnHeader = columnDataElements.get(i).getText();
			if(columnDataElements.get(i).getText().equalsIgnoreCase(expectedCompanyName)) {
				System.out.println("Company name found! Test Pass!");
				break;
			} else if((columnDataElements.size()-1)==i) {
				System.out.println("NOT FOUND! Failed!");
				throw new RuntimeException("Company name not found!");
			}
		}
		
		System.out.println("Test complete!");
		Thread.sleep(1000);
		driver.quit();
	}

	public String getHeaderIndex(String columnHeader, List<WebElement> tableHeaderElements) {
		for (int i = 0; i < tableHeaderElements.size(); i++) {
			if (tableHeaderElements.get(i).getText().equalsIgnoreCase(columnHeader)) {
				return String.valueOf(i + 1);
			}
		}
		return null;
	}
}
