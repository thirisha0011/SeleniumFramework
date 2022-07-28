package com.excelSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class DataProviders {
	
	private static FileInputStream inputStream;
	private static Workbook workbook;
	private static Sheet sheet;
	private static String[][] values; 
	private static final Map<String, String> map = new HashMap<String, String>();
	private static boolean isgetDataExecuted = true;
	
	//private static DataProviders dp;
	
	/*public synchronized static DataProviders getInstance(WebDriver driver) {
		if(dp==null)
			dp = new DataProviders();
		drivers = driver;
		return dp;
	}*/
	
	private synchronized Map<String, String> getExcelSheet(String filePath, String sheetName) throws IOException {
		inputStream = new FileInputStream(new File(filePath));
		workbook = new HSSFWorkbook(inputStream);
		sheet = workbook.getSheet(sheetName);
		String value = null;
		int totalcolumns = 2;
		int totalRows = sheet.getPhysicalNumberOfRows();
		System.out.println("Total No Of Rows "+totalRows);
		values = new String[totalRows][2];
		for (int i=1; i<totalRows; i++) {
			Row rows = sheet.getRow(i);
			//int totalcolumns = sheet.getRow(i).getPhysicalNumberOfCells();
			for(int j=0;j<totalcolumns;j++) {
				Cell cell = rows.getCell(j);
				switch (cell.getCellType()) {
				case NUMERIC:
					int numericCellValue = (int)cell.getNumericCellValue();
					value = String.valueOf(numericCellValue);
					values[i][j] = value;
				break;
				
				case STRING:
					value = cell.getStringCellValue();
					values[i][j] = value;
				break;
 				
				case BOOLEAN:
					value = String.valueOf(cell.getBooleanCellValue());
					values[i][j] = value;
				break;
				}
			}
		}
		Map<String, String> getvalueAsMap = getvalueAsMap(values);
		isgetDataExecuted = false;
		return getvalueAsMap;
	}
	
	private static Map<String, String> getvalueAsMap(String[][] list ) {
		
	    for (String[] m : list) {
	      if (map.put(m[0], m[1]) != null) {
	    	  //throw new IllegalStateException("Duplicate key");
	      }
	    }
	    return map;
	}
	
	public synchronized String getData(String key) throws IOException {
		if(isgetDataExecuted)
			getExcelSheet(Parameter.CONFIG_FILE_NAME.getKey(), Parameter.CONFIG_SHEET_NAME.getKey());
		return map.get(key);
	}
	
	public synchronized String getQuery(String key) throws IOException {
		if(isgetDataExecuted)
			getExcelSheet(Parameter.DATABASE_FILE_NAME.getKey(), Parameter.DATABASE_SHEET__NAME.getKey());
		return map.get(key);
	}
}