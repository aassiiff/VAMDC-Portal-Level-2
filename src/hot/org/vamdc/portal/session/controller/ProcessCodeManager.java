package org.vamdc.portal.session.controller;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;
import java.util.*;

@Name("processCodeManager")
@Scope(ScopeType.APPLICATION)
public class ProcessCodeManager {

	private List<ProcessDefinition> processCodesList = new ArrayList<ProcessDefinition>();

	private String processSpreadSheet;

	public String getProcessSpreadSheet() {
		return processSpreadSheet;
	}

	public void setProcessSpreadSheet(String processSpreadSheet) {
		this.processSpreadSheet = processSpreadSheet;
	}

	public List<ProcessDefinition> getProcessCodesList() {
		return processCodesList;
	}

	public void setProcessCodesList(List<ProcessDefinition> processCodesList) {
		this.processCodesList = processCodesList;
	}

	public ProcessCodeManager() {

	}

	public void readProcessSpreadSheet() {
		processSpreadSheet = System.getProperty("processSpreadSheet");
		System.out.println("processSpreadSheet: " + processSpreadSheet);
		if (processCodesList.size() == 0) {
			if (processSpreadSheet != null && !processSpreadSheet.equals("")) {
				Workbook wb1 = null;
				try {
					wb1 = new XSSFWorkbook(processSpreadSheet);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Sheet sheet = wb1.getSheetAt(0);
				Row row;
				Cell cell;

				int rows; // No of rows
				rows = sheet.getPhysicalNumberOfRows();

				int cols = 0; // No of columns
				int tmp = 0;

				// This trick ensures that we get the data properly even if it
				// doesn't start from first few rows
				for (int i = 0; i < 10 || i < rows; i++) {
					row = sheet.getRow(i);
					if (row != null) {
						tmp = sheet.getRow(i).getPhysicalNumberOfCells();
						// out.println("tmp value"+tmp);
						if (tmp > cols) {
							cols = tmp;
						}

					}
				}

				ProcessDefinition tempProcessCode;
				for (int r1 = 0; r1 < rows; r1++) {
					tempProcessCode = new ProcessDefinition();

					row = sheet.getRow(r1);
					if (row != null) {
						if (row.getCell(0) != null) {
							for (int counter = 0; counter < cols; counter++) {
								cell = row.getCell((short) counter);
								// cell = row.getCell(1);
								if (counter == 0) {
									if (cell != null) {
										tempProcessCode.setProcessName(cell
												.getStringCellValue());
									} else {
										tempProcessCode.setProcessName("");
									}
								} else if (counter == 1) {
									if (cell != null) {
										tempProcessCode.setProcessCode(cell
												.getStringCellValue());
									} else {
										tempProcessCode.setProcessCode("");
									}
								} else if (counter == 2) {
									if (cell != null) {
										tempProcessCode.setIaeaCode(cell
												.getStringCellValue());
									} else {
										tempProcessCode.setIaeaCode("");
									}
								} else if (counter == 3) {
									if (cell != null) {
										tempProcessCode
												.setProcessDescription(cell
														.getStringCellValue());
										// System.out.println(tempProcessCode.getProcessDescription());
									} else {
										tempProcessCode
												.setProcessDescription("");
										// System.out.println("Process Description EMpty");
									}
								}

							}
						}

					} else {
						rows++;
					}
					processCodesList.add(tempProcessCode);
				}

			}
		}
		System.out.println(processCodesList.size());
	}

}
