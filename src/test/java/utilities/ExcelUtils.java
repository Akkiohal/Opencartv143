package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
     String path;
	
	public ExcelUtils(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetname) throws IOException 
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetname);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
		
	}
	
	public int getCellCount(String sheetname,int rownum) throws IOException 
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetname);
		row =ws.getRow(rownum);
		int Cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return Cellcount;
		
	}
	
	public String getCellData(String sheetname,int rownum,int column) throws IOException 
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetname);
		row =ws.getRow(rownum);
		cell=row.getCell(column);
		
		String data;
		try
		{
			//data=cell.toString();
			DataFormatter formatter = new DataFormatter();  //returns the formatted cell as a String regardless of the cell types
			data= formatter.formatCellValue(cell);
		}
		catch (Exception e)
		{
			data="";
		}
		
		wb.close();
		fi.close();
		return data;
		
		
	}
	
	public void SetCellData(String sheetname,int rownum,int column,String data) throws IOException 
	{
		File xlfile= new File(path);
		if(!xlfile.exists())
		{
			wb=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			wb.write(fo);
			
		}
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		
		if(wb.getSheetIndex(sheetname)==-1) //If sheet is not exists then create new sheet
			wb.createSheet(sheetname);
		wb.write(fo);
		
		if(ws.getRow(rownum)==null)         //If row is not exists then create new row
			ws.createRow(rownum);
		row=ws.getRow(rownum);
		
		
		
		cell=row.createCell(column);
		cell.setCellValue(data);
	    fo=new FileOutputStream(path);
	    wb.write(fo);
		
		wb.close();
		fi.close();
		fo.close();;
		
		
	}
	
	public  void fillGreenClor(String sheetname,int rownum,int column) throws IOException 
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetname);
		row =ws.getRow(rownum);
		
		cell=row.getCell(column);
		style=wb.createCellStyle();
	   style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	   style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	   
	   cell.setCellStyle(style);
	   fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();;
		
		
	}
	
	public  void fillRedClor(String sheetname,int rownum,int column) throws IOException 
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetname);
		row =ws.getRow(rownum);
		
		cell=row.getCell(column);
		style=wb.createCellStyle();
	   style.setFillForegroundColor(IndexedColors.RED.getIndex());
	   style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	   
	   cell.setCellStyle(style);
	   fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();;
		
		
	}
	
	
	
	
	
	
	
	

}
