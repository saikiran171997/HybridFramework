package driverfactory;

import org.testng.Reporter;
import org.testng.annotations.Test;
import commonfunctions.FunctionalitiesLiberaries;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class Driverscript extends AppUtil {
	
	String Inputpath = "D:\\HybridFrameWork\\InputFolder\\DataEngine.xlsx";
	String Outputpath="D:\\HybridFrameWork\\OutputFolder\\HybridResults.xlsx";
	String TCsheet="Testcases";
	String TSsheet=	"Teststeps";
	@Test
	public void starttest()throws Throwable{
		
		boolean res =false;
		String tcres="";
		ExcelFileUtil xl=new ExcelFileUtil(Inputpath);
		int TCcount=xl.rowscount(TCsheet);
		int TScount=xl.rowscount(TSsheet);
		Reporter.log(TCcount+"  "+TScount,true);
		
		for (int i = 1; i <=TCcount; i++) {
			String Executionmode=xl.getcelldata(TCsheet, i, 2);
			if (Executionmode.equalsIgnoreCase("Y")) {
				String tcid=xl.getcelldata(TCsheet, i, 0);
				for (int j = 1; j <=TScount; j++) {
					String tsid=xl.getcelldata(TSsheet, j, 0);
					if (tcid.equalsIgnoreCase(tsid)) {
						String keyword=xl.getcelldata(TSsheet, j, 3);
						if (keyword.equalsIgnoreCase("ADMINLOGIN")) {
							String para1=xl.getcelldata(TSsheet, j, 5);
							String para2=xl.getcelldata(TSsheet, j, 6);
							res=FunctionalitiesLiberaries.verifylogin(para1, para2);
							
						}
						else if (keyword.equalsIgnoreCase("NEWBRANCH")) {
							String para1=xl.getcelldata(TSsheet, j, 5);
							String para2=xl.getcelldata(TSsheet, j, 6);
							String para3=xl.getcelldata(TSsheet, j, 7);
							String para4=xl.getcelldata(TSsheet, j, 8);
							String para5=xl.getcelldata(TSsheet, j, 9);
							String para6=xl.getcelldata(TSsheet, j, 10);
							String para7=xl.getcelldata(TSsheet, j, 11);
							String para8=xl.getcelldata(TSsheet, j, 12);
							String para9=xl.getcelldata(TSsheet, j, 13);
							FunctionalitiesLiberaries.clickbranches();
							res=FunctionalitiesLiberaries.verifynewbranch(para1, para2, para3, para4, para5, para6, para7, para8, para9);
							
						}
						else if (keyword.equalsIgnoreCase("UPDATEBRANCH")) {
							String para1=xl.getcelldata(TSsheet, j, 5);
							String para2=xl.getcelldata(TSsheet, j, 6);
							String para3=xl.getcelldata(TSsheet, j, 9);
							String para4=xl.getcelldata(TSsheet, j, 10);
							FunctionalitiesLiberaries.clickbranches();
							res=FunctionalitiesLiberaries.verifybranchupdation(para1, para2, para3, para4);
							
						}
						else if (keyword.equalsIgnoreCase("ADMINLOGOUT")) {
							res=FunctionalitiesLiberaries.verifylogout();
							
						}
						String tsres="";
						if (res) {
							tsres="pass";
							xl.SetCellValue(TSsheet, j, 4, tcres, Outputpath);
							
						}
						else {
							tsres="Fail";
							xl.SetCellValue(TSsheet, j, 4, tsres, Outputpath);
							
						}
						tcres=tsres;
						
					}
					xl.SetCellValue(TCsheet, i, 3, tcres, Outputpath);
				}
				
			}
			else {
				xl.SetCellValue(TCsheet, i, 3, "blocked", Outputpath);
				
			}
		}
		
	}
			
	
	
	

}
