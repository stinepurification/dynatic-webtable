package test;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class P1 {
	WebDriver driver;

	@Test
	public void createNewUser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");

		// Page display validation - validate title of the page
		String expectedTitle = "Automation Exercise";
		String actualTitle = driver.getTitle();
		System.out.println("Expected title: " + expectedTitle + "\nActual Title: " + actualTitle);
		Assert.assertTrue(
				"Page did not display!" + "Expected title: " + expectedTitle + "\nActual Title: " + actualTitle,
				expectedTitle.equalsIgnoreCase(actualTitle));

		driver.findElement(By.xpath("//a[contains(text(),' Signup / Login')]")).click();

		// Another title validation
		Assert.assertTrue("SignUp Page did not display!", driver.getTitle().contains("Login"));

		Random random = new Random();
		String expectedname = "test_" + random.nextInt(999);

		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(expectedname);
		driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(expectedname + "@gmail.com");
		driver.findElement(By.xpath("//button[contains(text(),'Signup')]")).click();
		Thread.sleep(1000);
		// WebElement text=driver.findElement(By.xpath("//b[contains(text(),'Enter
		// Account Information'"));
		// System.out.println(text);

		driver.findElement(By.xpath("//input[@id='id_gender1']")).click();

		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scroll(0,400)");

		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("abc123");

		By DayOfBirthLocator = By.xpath("//select[@id='days']");

		selectByIndexFromDropDown(DayOfBirthLocator, 3);

		// WebElement DayOfBirthElement = driver.findElement(DayOfBirthLocator);
		// Select select1=new Select(DayOfBirthElement);
		// select1.selectByIndex(3);

		By MonthOfBirthLocator = By.xpath("//select[@id='months']");
		selectByIndexFromDropDown(MonthOfBirthLocator, 3);

		// WebElement MonthOfBirthElement = driver.findElement(MonthOfBirthLocator);
		// Select select2=new Select(MonthOfBirthElement);
		// select2.selectByIndex(3);

		By YearOfBirthLocator = By.xpath("//select[@id='years']");
		selectByIndexFromDropDown(YearOfBirthLocator, 3);

		// WebElement YearOfBirthElement = driver.findElement(YearOfBirthLocator);
		// Select select3=new Select(YearOfBirthElement);
		// select3.selectByIndex(3);

		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scroll(0,400)");

		driver.findElement(By.xpath("//input[@id='newsletter']")).click();
		driver.findElement(By.xpath("//input[@id='optin']")).click();
		driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys(expectedname + "_firstName");
		driver.findElement(By.xpath("//input[@id='last_name']")).sendKeys(expectedname + "_lastName");
		driver.findElement(By.xpath("//input[@id='company']")).sendKeys(expectedname + "_companyName");
		driver.findElement(By.xpath("//input[@id='address1']")).sendKeys(expectedname + "_address1");
		driver.findElement(By.xpath("//input[@id='address2']")).sendKeys(expectedname + "_address2");

		By CountryLocator = By.xpath("//select[@id='country']");
		WebElement CountryElement = driver.findElement(CountryLocator);
		Select select4 = new Select(CountryElement);
		select4.selectByIndex(4);

		driver.findElement(By.xpath("//input[@id='state']")).sendKeys(expectedname + "state");
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys(expectedname + "city");
		driver.findElement(By.xpath("//input[@id='zipcode']")).sendKeys("10459");
		driver.findElement(By.xpath("//input[@id='mobile_number']")).sendKeys("1232569845");
		driver.findElement(By.xpath("//button[contains(text(),'Create Account')]")).click();
		// Thread.sleep(3000);

		// Assert.assertTrue("Fail!", driver.getPageSource().contains("Account
		// Created!"));
		driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
		System.out.println();
		
		List<WebElement> elements = driver.findElements(By.tagName("iframe"));
		
		for(WebElement e: elements) {
			System.out.println(e.getAttribute("id"));
		}
		
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='aswift_0']"));
		driver.switchTo().frame(frame1);
		// Assert.assertTrue("Fail",driver.getPageSource().contains(" Logged in as "));
		Thread.sleep(3000);
		WebElement closePopoup = driver.findElement(By.xpath("//div[@id='dismiss-button']"));
		
		
		Actions action = new Actions(driver);
		action.click(driver.findElement(By.xpath("//div[@id='dismiss-button']")));

		// driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();

		// driver.switchTo().alert().dismiss();

		driver.findElement(By.xpath("//a[contains(text(),' Delete Account')]")).click();

		Assert.assertTrue("Fail", driver.getPageSource().contains("Account Deleted!"));
		driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();

		
		
	}
	
	@After
	public void after() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

	public void selectByIndexFromDropDown(By by, int index) {
		WebElement DayOfBirthElement = driver.findElement(by);
		Select select1 = new Select(DayOfBirthElement);
		select1.selectByIndex(index);
	}

}
