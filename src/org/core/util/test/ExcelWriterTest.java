package org.core.util.test;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;


  
/** 
 * 从excel读取数据/往excel中写入 excel有表头，表头每列的内容对应实体类的属性 
 *  
 * @author nagsh 
 *  
 */  
public class ExcelWriterTest {  


    public static void main(String[] args) throws IOException {  
    	String excelPath = "d:\\Downloads\\test.xls";
        
        // 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		String sheetName = "第一个表格";//sheet名称
		HSSFSheet sheet = workbook.createSheet(sheetName);
		
		
		CellRangeAddress c = CellRangeAddress.valueOf("A1:J1");  //里的A1：R1，表示是从哪里开始，哪里结束这个筛选框
		sheet.setAutoFilter(c);
		HSSFRow row0 =sheet.createRow(0);
		for (int j=0;j<10;j++) {
			HSSFCell cell = row0.createCell(j);
			cell.setCellValue("标题");
		}
		
		
		
		for (int i=1;i<10;i++) {
			HSSFRow row = sheet.createRow(i);
			for (int j=0;j<10;j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue((i+1)+"行,"+(j+1)+"列");
			}
		}
		
		
		String sheetName2 = "第二个表格";//sheet名称
		HSSFSheet sheet2 = workbook.createSheet(sheetName2);
		for (int i=0;i<10;i++) {
			HSSFRow row = sheet2.createRow(i);
			for (int j=0;j<10;j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue((i+1)+"行,"+(j+1)+"列");
			}
		}
        //创建文件流   
        OutputStream stream = new FileOutputStream(excelPath);  
        //写入数据   
        workbook.write(stream);  
        //关闭文件流   
        stream.close();  
    }  
    
    
}
