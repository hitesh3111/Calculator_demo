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
	
	
	   public void pressNumber(WebDriver driver, int Number)
	    {
		  driver.findElement(By.xpath("//span[@onclick='r("+Number+")']")).click();
	    }
	 
	 public void pressOperator(WebDriver driver, char Operator)
	 {
		 if (Operator == '+' )
			{
			    driver.findElement(By.xpath("//span[(text()='+')]")).click();
			}
			else if(Operator == '-' )
			{
				 driver.findElement(By.xpath("//span[@onclick=\"r('-')\"]")).click();
			}
			else if(Operator == '*')
			{
				 driver.findElement(By.xpath("//span[@onclick=\"r('*')\"]")).click();
			}
			else if(Operator == '/')
			{
				 driver.findElement(By.xpath("//span[@onclick=\"r('/')\"]")).click();
			}
			else
			{
				System.out.println("Invalid Argument");
			}
	 }
	 
	 
	 @Parameters({"Number1", "Number2","Operator","Expected"})
		@Test
		public void calculations2(int Number1, int Number2,String Operator,float Expected)
	 {
		 String snum1= Integer.toString(Number1);
		 String snum2= Integer.toString(Number2);
		 
		 for(int i=0;i<snum1.length();i++)
		 {
			 if(snum1.charAt(i)== '+' || snum1.charAt(i)== '-' || snum1.charAt(i)== '*' || snum1.charAt(i)== '/')
			 {
				 pressOperator(driver, snum1.charAt(i));
			 }
			 else
			 {
				 int numdigit= Character.getNumericValue(snum1.charAt(i));
				 pressNumber(driver, numdigit);
			 }
		 }
		 
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
		 
		 for(int i=0;i<snum2.length();i++)
		 {
			 if(snum2.charAt(i)== '+' || snum2.charAt(i)== '-' || snum2.charAt(i)== '*' || snum2.charAt(i)== '/')
			 {
				 pressOperator(driver, snum2.charAt(i));
			 }
			 else
			 {
				 int numdigit= Character.getNumericValue(snum2.charAt(i));
				 pressNumber(driver, numdigit);
			 }
		 }
		 //Validation
			String out;
			out = driver.findElement(By.xpath("//div[@id='sciOutPut']")).getText().trim();
			Float output = Float.parseFloat(out);
			Assert.assertTrue(Expected == output);
	 }

	     @AfterTest
         public void closeBrowser() throws InterruptedException
          {
    	   Thread.sleep(5000);
        	driver.close();
          }
    }
