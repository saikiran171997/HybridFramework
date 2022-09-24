package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.jws.soap.SOAPBinding.Style;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	
	XSSFWorkbook wb;
	// Create constructor for path 
	public ExcelFileUtil(String ExcelPath) throws Throwable {
		FileInputStream fi = new FileInputStream(ExcelPath);
		wb=new XSSFWorkbook(fi);
	}
	//no of rows to count
	public int rowscount(String SheetName) {
		return wb.getSheet(SheetName).getLastRowNum();
	}
	
	//no of cells to count
	public int getcolumn(String SheetName) {
		return wb.getSheet(SheetName).getRow(0).getLastCellNum();
		
	}
	// get cell data
	public String getcelldata(String SheetName,int row, int coloumn) {
		
		String data =" ";
		if(wb.getSheet(SheetName).getRow(row).getCell(coloumn).getCellType()==Cell.CELL_TYPE_NUMERIC) {
			int celldata =(int) wb.getSheet(SheetName).getRow(row).getCell(coloumn).getNumericCellValue();
			data= String.valueOf(celldata);
		}
		else {
			data = wb.getSheet(SheetName).getRow(row).getCell(coloumn).getStringCellValue();
		}
		return data;
		
		
	}
	// method for writing 
	public String SetCellValue(String SheetName, int row, int coloum,String Status , String writeExcel) throws Throwable {
		// get sheet
		XSSFSheet ws = wb.getSheet(SheetName);
		//get row
		XSSFRow r =ws.getRow(row);
		//get cell
		XSSFCell cell =r.createCell(coloum);
		//write cell value
		cell.setCellValue(Status);
		if(Status.equalsIgnoreCase("pass")) {
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.DARK_GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(coloum).setCellStyle(style);
		}
		else if (Status.equalsIgnoreCase("fail")) {
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.DARK_RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(coloum).setCellStyle(style);
			
		}
		else if (Status.equalsIgnoreCase("fail")) {
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(coloum).setCellStyle(style);
			
			
		}

		FileOutputStream fo = new FileOutputStream(writeExcel);
		wb.write(fo);
		return Status;
	}
    
		public static void main(String[] args) throws Throwable {
		ExcelFileUtil xl = new ExcelFileUtil("D:\\Marks.xlsx");
		int rc= xl.rowscount("Sheet1");
		int cc =xl.getcolumn("Sheet1");
		System.out.println(rc+" "+cc);
		for (int i = 1; i<=rc; i++) {
			String user = xl.getcelldata("Sheet1", i, 0);
			String pass = xl.getcelldata("Sheet1", i, 1);
			System.out.println(user+ "  " +pass);
			xl.SetCellValue("Sheet1", i, 2, "pass", "D:\\Markss.xlsx");
			//xl.SetCellValue("Sheet1", i, 2, "fail", "D:\\Markss.xlsx");
			//xl.SetCellValue("Sheet1", i, 2, "blocked", "D:\\Markss.xlsx");
			
		}
			
			
		}
		
	
}
