package config;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtil {
	public static WebDriver driver;
	public static Properties p;
	@BeforeSuite
	public static void setup()throws Throwable{
		p=new Properties();
		p.load(new FileInputStream("D:\\HybridFrameWork\\PropertyFile\\Hybrid.properties"));
		if (p.getProperty("browser").equalsIgnoreCase("chrome")) {
			
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(p.getProperty("url"));
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		}
		else if (p.getProperty("browser").equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
			driver.manage().deleteAllCookies();
			driver.get(p.getProperty("url"));
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		}
		else {
			
			System.out.println("browser not mathcing");
		}
		
	}
	@AfterSuite
	public static void teardown(){
		driver.close();
	}

}
