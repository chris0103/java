package org.chris.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrusadeAnalyzer {

	/** Locations for major tasks **/
	private static final Location[] MAJOR = new Location[] {
		new Location(2, 5), new Location(8, 6), new Location(8, 7), new Location(8, 8), new Location(8, 9), new Location(8, 10),
		new Location(2, 12), new Location(8, 13), new Location(8, 14), new Location(8, 15), new Location(8, 16), new Location(8, 17)
	};
	
	/** Locations for minor tasks **/
	private static final Location[] MINOR = new Location[] {
		new Location(2, 2), new Location(3, 2), new Location(4, 2), new Location(5, 2), 
	};
	
	private static Logger logger = LoggerFactory.getLogger(CrusadeAnalyzer.class);
	
	private static File file = new File("C:\\Users\\IBM_ADMIN\\Desktop\\concentrism.xlsx");
	
	// private static File file = new File("scheduler.xlsx");
	
	public void readAllContent(Sheet sheet) {
		logger.info("Open sheet 1, which contains {} rows", (sheet.getLastRowNum() + 1));
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				if (cell == null) {
					System.out.print("\t");
					continue;
				}
	            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    System.out.print(cell.getRichStringCellValue().getString() + "\t");
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        System.out.print(cell.getDateCellValue() + "\t");
                    } else {
                        System.out.print(cell.getNumericCellValue() + "\t");
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    System.out.print(cell.getBooleanCellValue() + "\t");
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    System.out.print(cell.getCellFormula() + "\t");
                    break;
                default:
                    System.out.print("\t");
            }
			}
			System.out.println();
		}
	}
	
	public List<Cell> readRegion(Sheet sheet, Location[] region) {
		List<Cell> cells = new ArrayList<Cell>();
		for (Location location : region) {
			Cell cell = sheet.getRow(location.getRow()).getCell(location.getColumn());
			cells.add(cell);
		}
		return cells;
	}
	
	public Cell readCell(Sheet sheet, Location location) {
		return sheet.getRow(location.getRow()).getCell(location.getColumn());
	}
	
	public static String getColor(Cell cell) {
		String label = "";
		XSSFColor color = (XSSFColor) cell.getCellStyle().getFillBackgroundColorColor();
		label = String.valueOf(color.getTheme());
		return label;
	}
	
	public void readMajor(Sheet sheet) {
		List<Cell> cells = readRegion(sheet, MAJOR);
		for (Cell cell : cells) {
			System.out.print(cell.getStringCellValue() + "\t");
		}
		System.out.println();
	}
	
	public void readMinor(Sheet sheet) {
		List<Cell> cells = readRegion(sheet, MINOR);
		for (Cell cell : cells) {
			System.out.print(cell.getStringCellValue() + "\t");
		}
		System.out.println();
	}
	
	public float analyzeMajor(Sheet sheet) {
		List<Cell> cells = readRegion(sheet, MAJOR);
		int owedUnits = 0;
		for (Cell cell : cells) {
			owedUnits += extractIntegerUnitFromParenthesis(cell.getStringCellValue());
		}
		System.out.println(owedUnits + " major units owed in total.");
		return owedUnits;
	}
	
	public float analyzeMinor(Sheet sheet) {
		List<Cell> cells = readRegion(sheet, MINOR);
		int owedUnits = 0;
		for (Cell cell : cells) {
			owedUnits += extractIntegerUnitFromParenthesis(cell.getStringCellValue());
		}
		System.out.println(owedUnits + " minor units owed in total.");
		return owedUnits;
	}
	
	private int extractIntegerUnitFromParenthesis(String str) {
		int startIndex = str.indexOf('(');
		int endIndex = str.indexOf(')');
		if (startIndex != -1 && endIndex != -1) {
			return Integer.parseInt(str.substring(startIndex + 1, endIndex));
		}
		return 0;
	}

	private static class Location {
		
		private int row;
		private int column;
		
		public Location(int row, int column) {
			this.row = row;
			this.column = column;
		}

		public int getRow() {
			return row;
		}

		public int getColumn() {
			return column;
		}
	}
	
	public static void main(String[] args) throws IOException {
		CrusadeAnalyzer ma = new CrusadeAnalyzer();
		FileInputStream fis = null;
		try {
			logger.info("Openning file: {}...", file.getCanonicalPath());
			fis = new FileInputStream(file);
			Workbook book = WorkbookFactory.create(fis);
			Sheet sheet = book.getSheetAt(0);
			System.out.println("Units for : " + sheet.getSheetName());
			// ma.readAllContent(sheet);
			ma.readMajor(sheet);
			ma.readMinor(sheet);
			float major = ma.analyzeMajor(sheet);
			float minor = ma.analyzeMinor(sheet);
			System.out.println("Total: " + (major + minor));
		} catch (FileNotFoundException e) {
			logger.error("File not found.", e);
		} catch (IOException e) {
			logger.error("IO exception occured.", e);
		} catch (InvalidFormatException e) {
			logger.error("Invalid file format.", e);
		} finally {
			fis.close();
		}
	}
}
