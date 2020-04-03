package Automation.demo.maven.automationpractice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class orderPlacement {
	
	public WebDriver driver;
	public String TotalPrice , actualPrice;
	
	@BeforeTest
	public void LaunchWebsite()
	{
		
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\DELL\\eclipse-workspace\\automationpractice\\src\\main\\java\\BrowserExe\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\BrowserExe\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
		
		
	}
	
	@Test(priority=1)
	public void login() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// 1.a. Click on Sign In to create Account
		driver.findElement(By.xpath("//*[contains(text(),'Sign in')]")).click();
		
		// 1.b. Enter Email ID for Register
		driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("Adhinath.Godse@gmail.com");
		
		//1.c . Create a Account Button
		driver.findElement(By.xpath("//*[@id='SubmitCreate']/span")).click();
		
		/* ---------------Register----------------*/
		
		//Title
				driver.findElement(By.xpath("//*[@id='id_gender1']")).click();
				
				//First name *
				driver.findElement(By.xpath("//*[@id='customer_firstname']")).sendKeys("Adhinath");
				
				//Last name *
				driver.findElement(By.xpath("//*[@id='customer_lastname']")).sendKeys("Kudikyal");
				
				//Passsword *
				driver.findElement(By.xpath("//*[@id='passwd']")).sendKeys("Adhinath");
				
				//Date of Birth
				Select day =new Select(driver.findElement(By.id("days")));
				day.selectByIndex(4);
				
				Select month =new Select(driver.findElement(By.id("months")));
				month.selectByIndex(4);
				
				Select year =new Select(driver.findElement(By.id("years")));
				year.selectByIndex(4);
				
				// Company Name
				driver.findElement(By.xpath("//*[@id='company']")).sendKeys("IIFL");
				
				//Address
				driver.findElement(By.xpath("//*[@id='address1']")).sendKeys("Nagpada");
				
				//City *
				driver.findElement(By.xpath("//*[@id='city']")).sendKeys("Mumbai");
				
				//State *
				Select state =new Select(driver.findElement(By.id("id_state")));
				state.selectByIndex(4);
				
				//Zip/Postal Code *
				driver.findElement(By.xpath("//*[@id='postcode']")).sendKeys("00001");
				
				Thread.sleep(5000);
				//Country
				/*Select country =new Select(driver.findElement(By.id("//*[@id='id_country']")));
				Thread.sleep(2000);
				country.selectByIndex(1);
				Thread.sleep(2000);  */
				
				//Additional information
				driver.findElement(By.xpath("//*[@id='other']")).sendKeys("UMumba");
				
				
				//Home phone
				driver.findElement(By.xpath("//*[@id='phone']")).sendKeys("9870243877");
				
				//Mobile phone *
				driver.findElement(By.xpath("//*[@id='phone_mobile']")).sendKeys("9224555491");
				
				//Assign an address alias
				driver.findElement(By.xpath("//*[@id='alias']")).sendKeys("Assigning Test");
				
				//Register
				driver.findElement(By.xpath("//*[@id='submitAccount']/span")).click();
						
	}
	
	@Test(priority=2)
	public void myOrderPlacement() throws InterruptedException
	{
		//After login process 
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@title='Women']")).click();
		WebElement productTitle= driver.findElement(By.xpath("//span[contains(text(),'Condition')]"));
		scrollToElement(productTitle);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@title='Blouse']")).click();
		Thread.sleep(3000);		
		
		WebElement productFrame= driver.findElement(By.xpath("//*[contains(@id,'fancybox-frame15')]"));
		driver.switchTo().frame(productFrame);
		
		driver.findElement(By.xpath("//input[@id='quantity_wanted']")).clear();
		driver.findElement(By.xpath("//input[@id='quantity_wanted']")).sendKeys("2");
		
		driver.findElement(By.xpath("//button[@name='Submit']")).click();
		//driver.findElement(By.name("Submit")).click();
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.xpath("//a[@class='btn btn-default button button-medium']")).click();
		
		
		//WebElement proceedToCheckout1= driver.findElement(By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']"));
		
		Thread.sleep(2000);
		//Summary tab
		WebElement proceedToCheckout= driver.findElement(By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']"));
		scrollToElement(proceedToCheckout);
		proceedToCheckout.click();
		
		//Address tab
		WebElement AddressTabCheckoutbtn= driver.findElement(By.xpath("//button[@name='processAddress']"));
		scrollToElement(AddressTabCheckoutbtn);
		AddressTabCheckoutbtn.click();
		
		//Shipping tab
		WebElement shippingTabCheckoutbtn= driver.findElement(By.xpath("//button[@name='processCarrier']"));
		
		//Check the shipping tab checkbox
		scrollToElement(shippingTabCheckoutbtn);
		driver.findElement(By.xpath("//div[@id='uniform-cgv']")).click();
		shippingTabCheckoutbtn.click();		
		
		//Payment Tab
		
		//Scroll to pay by cheque option
		WebElement payByCheque=driver.findElement(By.xpath("//a[@class='cheque']"));
		scrollToElement(payByCheque);
		//Get the Total value
		TotalPrice= driver.findElement(By.xpath("//span[@id='total_price']")).getText();
		payByCheque.click();		
		
		//Payment tab		
		WebElement paymentTabOrderConfrimation =driver.findElement(By.xpath("//span[contains(text(),'I confirm my order')]"));
		scrollToElement(paymentTabOrderConfrimation);
		paymentTabOrderConfrimation.click();
		
		
		WebElement backtoOrder= driver.findElement(By.xpath("//a[@class='button-exclusive btn btn-default']"));
		scrollToElement(backtoOrder);
		
		System.out.println("My Payment amount is >> " +TotalPrice);
	}
	
	@Test(priority=3)
	public void orderVerification()
	{
		//Click on view my customer account
		driver.findElement(By.xpath("//a[@class='account']")).click();
		
		//order history and details
		driver.findElement(By.xpath("//span[contains(text(),'Order history and details')]")).click();
		
		WebElement expectedData= driver.findElement(By.xpath("//span[contains(text(),'"+TotalPrice+"')]"));
		
		
		
		if(expectedData.isDisplayed()==true)
		{
			System.out.println("Order placed successfully");
		}
		else
		{
			System.out.println("Order note placed successfully");
		}
		
	}
	
	@AfterTest
	public void closeBrowser() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.close();
	}
	
	public void scrollToElement(WebElement element)
	{
		Actions actions= new Actions(driver);
		actions.moveToElement(element).build().perform();
	}


}
