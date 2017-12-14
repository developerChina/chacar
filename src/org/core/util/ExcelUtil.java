package org.core.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

@SuppressWarnings("deprecation")
public class ExcelUtil {

	/**
	 * 将组织好的wordbook写入到指定文件名的Excel
	 * @param request 
	 * @param response 
	 * @param workbook 组织好的wordbook
	 * @param fileName 文件名
	 * @throws IOException
	 */
	public static void write(HttpServletRequest request,HttpServletResponse response,HSSFWorkbook workbook,String fileName) throws IOException{
		OutputStream out = null;
		try {
			out = response.getOutputStream();  
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			//区分IE浏览器和其他浏览器
			if (request.getHeader("User-Agent").contains("MSIE")||request.getHeader("User-Agent").contains("Trident")) {
				fileName = java.net.URLEncoder.encode((fileName + ".xls"), "UTF-8");
			} else {
				fileName = new String((fileName + ".xls").getBytes("UTF-8"),"ISO-8859-1");
			}
			response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
			workbook.write(out);
		}catch (IOException e) {
        	e.printStackTrace();
        	throw e;
        } catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
        	try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	/**
	* 设置单元格边框（解决合并单元格显示部分边框问题）
	* @param sheet 
	* @param region
	* @param cs
	*/
	public static void setRegionStyle(HSSFSheet sheet, Region region, HSSFCellStyle cs) {
		 for (int i = region.getRowFrom(); i <= region.getRowTo(); i++) {
			  HSSFRow row = HSSFCellUtil.getRow(i, sheet);
			  for (int j = region.getColumnFrom(); j <= region.getColumnTo(); j++) {
				   HSSFCell cell = HSSFCellUtil.getCell(row, (short) j);
				   cell.setCellStyle(cs);
			  }
		 }
	}
	
	/**
	 *  适用于合并标题
	 * 	水平垂直居中<br/>
	 *  上下左右边框<br/>
	 *  字体 较大,加粗
	 * @param workbook
	 * @return HSSFCellStyle
	 */
	public static HSSFCellStyle createTitleStyle(HSSFWorkbook workbook){
		HSSFCellStyle cellStyle = workbook.createCellStyle(); //样式对象    
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平击中
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框    
        HSSFFont font = workbook.createFont();//设置字体
        font.setFontName("Arial");    
        font.setFontHeightInPoints((short) 20);//设置字体大小  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗 
        cellStyle.setFont(font);
        return cellStyle;
	}
	
	/***
	 *  适用于标准内容单元格
	 * 	水平垂直居中<br/>
	 *  上下左右边框<br/>
	 *  字体正常<br/>
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle createTextStyle(HSSFWorkbook workbook){
		HSSFCellStyle cellStyle_T = workbook.createCellStyle(); // 样式对象    
	    cellStyle_T.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直    
	    cellStyle_T.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平    
	    cellStyle_T.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
	    cellStyle_T.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
	    cellStyle_T.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
	    cellStyle_T.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
	    HSSFFont font_t = workbook.createFont();//设置字体
	    font_t.setFontName("Arial");    
	    font_t.setFontHeightInPoints((short) 11);//设置字体大小  
	    cellStyle_T.setFont(font_t);
        return cellStyle_T;
	}
	
	
	/***
	 *  适用于内容较长单元格
	 * 	垂直居中<br/>
	 *  上下左右边框<br/>
	 *  字体正常<br/>
	 *  自动换行
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle createContextStyle(HSSFWorkbook workbook){
		HSSFCellStyle cellStyle_C = workbook.createCellStyle(); // 样式对象    
	    cellStyle_C.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
	    cellStyle_C.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
	    cellStyle_C.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
	    cellStyle_C.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
	    cellStyle_C.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
	    HSSFFont font_c = workbook.createFont();//设置字体
	    font_c.setFontName("Arial");
	    font_c.setFontHeightInPoints((short) 11);//设置字体大小  
	    cellStyle_C.setFont(font_c);
	    cellStyle_C.setWrapText(true);// 自动换行 
        return cellStyle_C;	
	}
	
	
	
	public static List<Map<Integer, String>> readSheet(Sheet sheet,int colNum){
		List<Map<Integer, String>> list=new ArrayList<Map<Integer, String>>();
		int rowNum = sheet.getLastRowNum();
	    Row row = sheet.getRow(0);
	    // 正文内容应该从第二行开始,第一行为表头的标题
	    for (int i = 1; i <= rowNum; i++) {
	        row = sheet.getRow(i);
	        int j = 0;
	        Map<Integer, String> map=new HashMap<Integer, String>();
	        //删除execl物理空行（第一列为空认为读取excel完成）
	        if(StringUtils.isBlank(row.getCell(0).getStringCellValue())){
	        	break;
	        }
	        while (j < colNum) {
	        	Cell cell=row.getCell(j);
	        	if(cell!=null){
	        		cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
	                String content = cell.getStringCellValue();
		        	map.put(Integer.valueOf(j), content);	
	        	}
	        	j++;
            }
	        list.add(map);
	    }
		return list;
	}
}
