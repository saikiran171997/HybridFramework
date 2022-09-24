package commonfunctions;
import org.openqa.selenium.By;
import org.testng.Reporter;
import config.AppUtil;

public class FunctionalitiesLiberaries extends AppUtil{
	
	public static boolean verifylogin(String username,String password)
	{
		driver.findElement(By.xpath(p.getProperty("objuser"))).sendKeys(username);
		driver.findElement(By.xpath(p.getProperty("objpass"))).sendKeys(password);
		driver.findElement(By.xpath(p.getProperty("objlogin"))).click();
		String expected = "adminflow";
		String actual = driver.getCurrentUrl();
	
		if (actual.toLowerCase().contains(expected)) {
			
			Reporter.log("login success"+expected+"  "+actual);
			return true;
		}
		else {
			Reporter.log("login fail"+expected+""+actual);
			return false;
		}
	}
	public static void clickbranches()throws Throwable{
		driver.findElement(By.xpath(p.getProperty("ObjBranches"))).click();
		Thread.sleep(4000);	
	}
	public static boolean verifynewbranch(String branchname,String address1,String address2,String address3,String area,String zipcode,String country,String state,String city ) {
		
		driver.findElement(By.xpath(p.getProperty("ObjNew"))).click();
		driver.findElement(By.xpath(p.getProperty("ObjBranch"))).sendKeys(branchname);
		driver.findElement(By.xpath(p.getProperty("ObjAddress1"))).sendKeys(address1);
		driver.findElement(By.xpath(p.getProperty("ObjAddress2"))).sendKeys(address2);
		driver.findElement(By.xpath(p.getProperty("ObjAddress3"))).sendKeys(address3);
		driver.findElement(By.xpath(p.getProperty("ObjArea"))).sendKeys(area);
		driver.findElement(By.xpath(p.getProperty("ObjZipcode"))).sendKeys(zipcode);
		driver.findElement(By.xpath(p.getProperty("ObjCountry"))).sendKeys(country);
		driver.findElement(By.xpath(p.getProperty("ObjState"))).sendKeys(city);
		driver.findElement(By.xpath(p.getProperty("ObjCity"))).sendKeys(state);
		driver.findElement(By.xpath(p.getProperty("ObjSubmit"))).click();
		String ExpectedAleart = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		String ActualAleart= "New Branch with";
		if(ExpectedAleart.toLowerCase().contains(ActualAleart)) {
			Reporter.log(ExpectedAleart,true);
			return true;
		}
		else
		{
			Reporter.log("unable to create new branch",true);
			return false;

		}
	}
	public static boolean verifybranchupdation(String branchname,String address1,String area,String zip) {
		driver.findElement(By.xpath(p.getProperty("ObjEdit"))).click();
		driver.findElement(By.xpath(p.getProperty("ObjBranchName"))).sendKeys(branchname);
		driver.findElement(By.xpath(p.getProperty("ObjAddress"))).sendKeys(address1);
		driver.findElement(By.xpath(p.getProperty("ObjArea1"))).sendKeys(area);
		driver.findElement(By.xpath(p.getProperty("ObjZip"))).sendKeys(zip);
		driver.findElement(By.xpath(p.getProperty("ObjUpdate"))).click();
			
			String ExpectedUpdateAleart =driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();
			String ActualUpdateAleart="Branch updated";
		
		if (ExpectedUpdateAleart.toLowerCase().contains(ActualUpdateAleart)) {
			Reporter.log("Updation succesfull",true);
			return true;
		}
		else {
			Reporter.log("Updation Failed",true);
			return false;
		}
	}
		
		public static boolean verifylogout() {
			driver.findElement(By.xpath(p.getProperty("ObjLogout"))).click();
			
			if (driver.findElement(By.xpath(p.getProperty("objlogin"))).isDisplayed()) {
				
				Reporter.log("logout successfull",true);
				return true;
			}
			else {
				Reporter.log("logout fail",true);
				return false;
			}
			
	}
	
}