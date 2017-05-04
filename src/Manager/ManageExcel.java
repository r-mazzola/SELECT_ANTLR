package Manager;

import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hdgf.exceptions.HDGFException;

import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hpsf.HPSFException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ManageExcel {
	
	//public static void main(String[] args) throws HDGFException, HPSFException {
	
	
	public ManageExcel(ArrayList body, ArrayList head){
        
		File file123= new File(".\\resources\\output.xls");

        try {
			exportToExcel("Test", head, body, file123);
			System.out.println("\n---Risultati scritti in TABELLA OUTPUT XLS---");
		} catch (HDGFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HPSFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    public static void exportToExcel(String sheetName, ArrayList headers,ArrayList<ArrayList<String>> data, File outputFile) throws HDGFException, HPSFException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);

        int rowIdx = 0;
        short cellIdx = 0;

        // testata
        HSSFRow hssfHeader = sheet.createRow(rowIdx);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (Iterator cells = headers.iterator(); cells.hasNext();) {
            HSSFCell hssfCell = hssfHeader.createCell(cellIdx++);
            hssfCell.setCellStyle(cellStyle);
            hssfCell.setCellValue((String) cells.next());
        }
        
        // corpo
       rowIdx = 1;
       for(ArrayList<String> d : data){
            HSSFRow hssfRow = sheet.createRow(rowIdx++);
            cellIdx = 0;
            for(int colonna=0; colonna<d.size();colonna++) {
                HSSFCell hssfCell = hssfRow.createCell(cellIdx++);
                hssfCell.setCellValue((String) d.get(colonna));
            }
        }

        wb.setSheetName(0, sheetName, HSSFWorkbook.ENCODING_COMPRESSED_UNICODE);
        try {
            FileOutputStream outs = new FileOutputStream(outputFile);
            wb.write(outs);
            outs.close();
        } catch (IOException e) {
            throw new HPSFException(e.getMessage());
        }

    }

}
