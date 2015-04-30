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
		new Location(11, 4), new Location(17, 5), new Location(17, 6), new Location(17, 7), new Location(17, 8), new Location(17, 9), new Location(11, 10),
		new Location(11, 11), new Location(17, 12), new Location(17, 13), new Location(17, 14), new Location(17, 15), new Location(17, 16), new Location(11, 17)
	};
	
	/** Locations for minor tasks **/
	private static final Location[] MINOR = new Location[] {
		new Location(2, 2), new Location(3, 2), new Location(4, 2), new Location(5, 2), new Location(6, 2)
	};
	
	/** Locations for video tasks **/
	private static final Location[] VIDEO = new Location[] {
		new Location(21, 4), new Location(21, 5), new Location(21, 6), new Location(21, 7), new Location(21, 8), new Location(21, 9),
		new Location(21, 11), new Location(21, 12), new Location(21, 13), new Location(21, 14), new Location(21, 15), new Location(21, 16)
	};
	
	private static final Location special = new Location(16, 19);
	private static final Location delay = new Location(17, 19);
	
	private static Logger logger = LoggerFactory.getLogger(CrusadeAnalyzer.class);
	
	private static File file = new File("crusade.xlsx");
	
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
		System.out.print("\t");
		Cell specialCell = readCell(sheet, special);
		Cell delayCell = readCell(sheet, delay);
		System.out.println(specialCell.getStringCellValue() + "\t" + delayCell.getStringCellValue());
	}
	
	public void readMinor(Sheet sheet) {
		List<Cell> cells = readRegion(sheet, MINOR);
		for (Cell cell : cells) {
			System.out.print(cell.getStringCellValue() + "\t");
		}
		System.out.println();
	}
	
	public void readVideo(Sheet sheet) {
		List<Cell> cells = readRegion(sheet, VIDEO);
		for (Cell cell : cells) {
			System.out.print(cell.getStringCellValue() + "\t");
		}
		System.out.println();
	}
	
	public void analyzeMajor(Sheet sheet) {
		List<Cell> cells = readRegion(sheet, MAJOR);
		int owedUnits = 0;
		for (Cell cell : cells) {
			owedUnits += extractIntegerUnitFromParenthesis(cell.getStringCellValue());
		}
		owedUnits -= extractIntegerAfterColon(readCell(sheet, special).getStringCellValue());
		System.out.println(owedUnits + " major units owed in total.");
	}
	
	public void analyzeMinor(Sheet sheet) {
		List<Cell> cells = readRegion(sheet, MINOR);
		int owedUnits = 0;
		for (Cell cell : cells) {
			owedUnits += extractIntegerUnitFromParenthesis(cell.getStringCellValue());
		}
		System.out.println(owedUnits + " minor units owed in total.");
	}
	
	public void analyzeVideo(Sheet sheet) {
		float owedUnits = 0;
		List<Cell> cells = readRegion(sheet, VIDEO);
		for (Cell cell : cells) {
			owedUnits += extractFloatUnitFromParenthesis(cell.getStringCellValue());
		}
		System.out.println(owedUnits + " video units owed in total.");
	}
	
	private int extractIntegerUnitFromParenthesis(String str) {
		int startIndex = str.indexOf('(');
		int endIndex = str.indexOf(')');
		if (startIndex != -1 && endIndex != -1) {
			return Integer.parseInt(str.substring(startIndex + 1, endIndex));
		}
		return 0;
	}
	
	private int extractIntegerAfterColon(String str) {
		int colonIndex = str.indexOf(':');
		if (colonIndex != -1) {
			return Integer.parseInt(str.substring(colonIndex + 1).trim());
		}
		return 0;
	}
	
	private float extractFloatUnitFromParenthesis(String str) {
		int startIndex = str.indexOf('(');
		int endIndex = str.indexOf(')');
		if (startIndex != -1 && endIndex != -1) {
			return Float.parseFloat(str.substring(startIndex + 1, endIndex));
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
			ma.readVideo(sheet);
			ma.analyzeMajor(sheet);
			ma.analyzeMinor(sheet);
			ma.analyzeVideo(sheet);
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
