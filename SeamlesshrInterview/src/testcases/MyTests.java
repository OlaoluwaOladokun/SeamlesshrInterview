package testcases;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class MyTests {
	WebDriver driver;
	ExtentTest logger;
	ExtentReports extent;
	
	@BeforeMethod
	public void setUp()
	{
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\OLAOLUWA OLADOKUN\\Downloads\\chromedriver_win32\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("https://the-internet.herokuapp.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@BeforeTest
	public void setUpReport()
	{
		// start reporters
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		
		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		// creates a toggle for the given test, adds all log events under it
		logger = extent.createTest("SeamlesshrInterviewTest", "My Report");
	}
	
	@Test
	 public void validLoginTestCase() {
	    driver.findElement(By.linkText("Form Authentication")).click();
	    String loginPage = driver.findElement(By.cssSelector("h2")).getText();
	    assertTrue(loginPage.contains("Login Page"), "String did not contain text Login Page.");
	    
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("tomsmith");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
	    driver.findElement(By.cssSelector(".fa")).click();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    String loginText = driver.findElement(By.cssSelector("#flash")).getText();
	    assertTrue(loginText.contains("You logged into a secure area!"), "String did not contain text You logged into a secure area!.");
	    
	     
	  }
	
	@Test
	public void invalidLoginTestCase() {
	    driver.findElement(By.linkText("Form Authentication")).click();
	    String loginPage = driver.findElement(By.cssSelector("h2")).getText();
	    assertTrue(loginPage.contains("Login Page"), "String did not contain text Login Page.");
	    
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("thomas");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).sendKeys("SecretPassword!");
	    driver.findElement(By.cssSelector(".fa")).click();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    String loginText = driver.findElement(By.cssSelector("#flash")).getText();
	    assertTrue(loginText.contains("Your username is invalid!"), "String did not contain text Your username is invalid!.");
	    
	    
	    
	  }
	
	@AfterMethod
	public void afterMyMethod(ITestResult result)
	{
		if (result.getStatus()==ITestResult.SUCCESS) {
           String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Passed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS ,m);
        }
        else if (result.getStatus()==ITestResult.FAILURE) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Failed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            logger.log(Status.FAIL,m);
	}
	
	extent.flush();
	
	{
	driver.quit();	
	}
	

}
	
	
}
