import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class NormalCalculator   {
	WebDriver driver;
	@BeforeTest
	public  void browserInvocation() {
		
		System.setProperty("webdriver.chrome.driver","F:\\Downloads\\automation\\Newfolder\\chrome98\\chromedriver.exe\\");
		driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.get("https://www.calculator.net/");
		
	}
	
	public ArrayList<Integer> arraylist(int Number)
	{
		// This helper method takes a number and 
		// Returns all of its digits in an arraylist
		
		int temp=Number;
		int reminder;
		ArrayList<Integer> al=new ArrayList<Integer>();
		while(temp!=0)
		{
			reminder=temp%10;
			al.add(reminder);
			temp=temp/10;
		}
		return al;
	}
	
	
	 @Parameters({"Number1", "Number2","Operator","Expected"})
	@Test
	public void calculations(int Number1, int Number2,String Operator,float Expected)
	{
		// Using arraylist to manipulate xpath for digits in number
		ArrayList<?> al_num1=new ArrayList<Object>();
		ArrayList<?> al_num2=new ArrayList<Object>();
	    
		al_num1 = arraylist(Number1);
		al_num2 = arraylist(Number2);
		
		// Entering number 1
		for(int i=al_num1.size()-1;i>=0;i--)
		{
			driver.findElement(By.xpath("//span[@onclick='r("+al_num1.get(i)+")']")).click();	
		}
		
		// Decision based on Operator Input 		
		if (Operator.equals("Addition"))
		{
		    driver.findElement(By.xpath("//span[(text()='+')]")).click();
		}
		else if(Operator.equals("Subtraction"))
		{
			 driver.findElement(By.xpath("//span[@onclick=\"r('-')\"]")).click();
		}
		else if(Operator.equals("Multiplication"))
		{
			 driver.findElement(By.xpath("//span[@onclick=\"r('*')\"]")).click();
		}
		else if(Operator.equals("Division"))
		{
			 driver.findElement(By.xpath("//span[@onclick=\"r('/')\"]")).click();
		}
		else
		{
			System.out.println("Invalid Argument");
		}
		
		// Entering number 2
		for(int i=al_num2.size()-1;i>=0;i--)
		{
			driver.findElement(By.xpath("//span[@onclick='r("+al_num2.get(i)+")']")).click();	
		}
		
		// Validation
		String out;
		out = driver.findElement(By.xpath("//div[@id='sciOutPut']")).getText().trim();
		Float output = Float.parseFloat(out);
		Assert.assertTrue(Expected == output);
		
	}
	 

	@AfterTest
    public void closeBrowser() throws InterruptedException
    {
    	Thread.sleep(5000);
    	//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	driver.close();
    }

}
