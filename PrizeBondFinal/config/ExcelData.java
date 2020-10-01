package com.config;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	
	public static ArrayList getExcelData(String sheetName) throws Throwable {
		FileInputStream fis = new FileInputStream("./DataDriven/BondNumbers.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> columns = firstRow.cellIterator();
				int k = 0;
				int columnNum = 0;
				while (columns.hasNext()) {
					Cell value = columns.next();
					if (value.getStringCellValue().equalsIgnoreCase("BondNum"))
						;
					{
						columnNum = k;
					}
					k++;
				}
				while (rows.hasNext()) {
					Row rowData = rows.next();					
					Iterator<Cell> cv = rowData.cellIterator();
					while (cv.hasNext()) {
						Cell c = cv.next();
						if (c.getCellType() == CellType.STRING) {
							list.add(c.getStringCellValue());
						} else if (c.getCellType() == CellType.NUMERIC) {
							list.add(NumberToTextConverter.toText(c.getNumericCellValue()));
						}
					}
				}
			}
		}
		return list;
	}

}
