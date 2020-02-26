package util;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String path;
	public FileInputStream fis = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	public ExcelReader(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getCellData(String sheetName, String colName, int rowNum) {

		int index = workbook.getSheetIndex(sheetName);
		int colNum = 0;
		sheet = workbook.getSheetAt(index);

		row = sheet.getRow(0);
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().contentEquals(colName.trim())) {
				colNum = i;
			}
		}

		row = sheet.getRow(rowNum - 1);
		cell = row.getCell(colNum);
		return cell.getStringCellValue();
	}

}
