package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility 
{
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	 public CellStyle style;
	 String path;
	 
	 

	public  XLUtility(String path)
	 {
	 	this.path=path;
	 }
	 
	 public int getRowCount(String sheetName) throws IOException
	 {
		 fi=new FileInputStream(path);
		 workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			int rowcount=sheet.getLastRowNum();
			workbook.close();
			fi.close();
			return rowcount;
	 }
	 
	 public  int getCellCount(String sheetName,int rownum) throws IOException
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rownum);
			int cellcount=row.getLastCellNum();
			workbook.close();
			fi.close();
			return cellcount;
		}

	 public String getCellData(String sheetName,int rownum,int colnum) throws IOException
		{
			fi=new FileInputStream(path);
			workbook=new XSSFWorkbook(fi);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rownum);
			cell=row.getCell(colnum);
			String data;
			try 
			{
				DataFormatter formatter = new DataFormatter();
	            String cellData = formatter.formatCellValue(cell);
	            return cellData;
			}
			catch (Exception e) 
			{
				data="";
			}
			workbook.close();
			fi.close();
			return data;
		}
	 
	 public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
	 {
		 File x1file=new File(path);
		 if(!x1file.exists())        //if file not exits then create new file
		 {
			 workbook=new XSSFWorkbook();
			 fo=new FileOutputStream(path);
			 workbook.write(fo);
		 }
		 
		 fi= new FileInputStream(path);
		 workbook=new XSSFWorkbook(fi);
		 
		 if(workbook.getSheetIndex(sheetName)==-1)  // If sheet not exits then create new Sheet
			 workbook.createSheet(sheetName);
		 sheet=workbook.getSheet(sheetName);
		 
		 if(sheet.getRow(rownum)==null)
			 sheet.createRow(rownum);
		 row=sheet.getRow(rownum);
		 
		 cell=row.createCell(colnum);
		 cell.setCellValue(data);
		 fo=new FileOutputStream(path);
		 workbook.write(fo);
		 workbook.close();
		 fi.close();
		 fo.close();
	 }
}


