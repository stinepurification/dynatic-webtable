package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class ListContactsTest {
	WebDriver driver;

	@Test
	public void specificContactShouldExist() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://techfios.com/test/billing/?ng=admin/");
		login("techfiosdemo@gmail.com", "abc123");
		driver.findElement(By.xpath("//span[contains(text(), 'CRM')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("List Contacts")).click();

		String expectedName = "Stine";

//		String name1 = "Stine";
//		String name2 = "Stanley";
//		String name3 = "Raju";
//		List<String> names = Arrays.asList("Stine", "Stanley", "Raju");	
//		System.out.println(names.get(2));
//		
//		WebElement expectedEmail = driver.findElement(By.xpath("//table//tr[8]//td[4]"));
//		
//		System.out.println(expectedEmail.getText());

		List<WebElement> nameElements = driver.findElements(By.xpath("//table//td[2]"));

//		for (WebElement w : nameElements) {
//			System.out.println(w.getText());
//		}
		Assert.assertTrue("Expected Name " + expectedName + " not found in list!", 
				isNameExist(nameElements, expectedName));
//		for(int i=0; i< nameElements.size() ; i++) {
//			System.out.println(nameElements.get(i).getText());
//		}
		// Assert.assertTrue("Fail!", does the list of names consist of David Math);
	}

	private boolean isNameExist(List<WebElement> list, String name) {
		for (int i = 0; i < list.size(); i++) {
			//String expectedName = name;
			//String listName = list.get(i).getText();
			if (list.get(i).getText().equalsIgnoreCase(name)) {
				System.out.println("Name found " + name);
				return true;
			}
		}
		return false;
	}

	public void login(String userID, String password) throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("login")).click();
	}

}
