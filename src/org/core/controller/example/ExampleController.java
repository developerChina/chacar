package org.core.controller.example;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.core.domain.Example;
import org.core.service.example.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 例子 -》 请求控制器
 * 
 * 浏览器访问： http://127.0.0.1:8080/chacar/example/forwardExample
 */
@Controller
public class ExampleController {
	/**
	 * 自动注入exampleService
	 */
	@Autowired
	@Qualifier("exampleService")
	private ExampleService exampleService;

	@RequestMapping(value = "/example/forwardExample")
	public ModelAndView forwardExample(ModelAndView mv) {
		// 设置客户端跳转到查询请求
		mv.setViewName("example/example");
		// 返回ModelAndView
		return mv;
	}
	/**
	 * 处理查询请求
	 */
	@RequestMapping(value = "/example/selectExample")
	public String selectExample(Integer pageIndex, @ModelAttribute Example example, Model model) {
		/** 查询用户信息 */
		List<Example> examples = exampleService.findExample(example);
		model.addAttribute("examples", examples);
		return "user/user";
	}

	/**
	 * 处理添加请求
	 */
	@RequestMapping(value = "/example/addExample")
	public ModelAndView addUser(String flag, @ModelAttribute Example example, ModelAndView mv) {
		// 执行添加操作
		exampleService.addExample(example);
		// 设置客户端跳转到查询请求
		 mv.setViewName("redirect:/example/selectExample");
		// 返回
		return mv;
	}

	@RequestMapping(value = "/example/updateExample")
	public ModelAndView updateExample(String flag, @ModelAttribute Example example, ModelAndView mv) {
		// 执行修改操作
		exampleService.modifyExample(example);
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/user/selectUser");
		// 返回
		return mv;
	}
	/**
	 * 处理删除用户请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/example/removeExample")
	 public ModelAndView removeExample(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除员工
			exampleService.removeExampleById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/user/selectExample");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * Excel文件下载
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/example/exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response){
		OutputStream out = null;
		try {
			out = response.getOutputStream();  
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			String fileName = "测试";//sheet名称
			//区分IE浏览器和其他浏览器
			if (request.getHeader("User-Agent").contains("MSIE")||request.getHeader("User-Agent").contains("Trident")) {
				fileName = java.net.URLEncoder.encode((fileName + ".xls"), "UTF-8");
			} else {
				fileName = new String((fileName + ".xls").getBytes("UTF-8"),"ISO-8859-1");
			}
			response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
			// 声明一个工作薄
			HSSFWorkbook workbook = new HSSFWorkbook();
			
			String sheetName = "第一个表格";//sheet名称
			HSSFSheet sheet = workbook.createSheet(sheetName);
			for (int i=0;i<10;i++) {
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
			
			workbook.write(out);
		}catch (IOException e) {
        	e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}finally {
        	try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 文件上传----\\chacar\\WebContent\\js\\upload.jsp
	 * @param request
	 * @param response
	 * @param file
	 */
	@RequestMapping(value="/example/uploadExcel")
	public void uploadExcel(HttpServletRequest request,HttpServletResponse response,@RequestParam(value ="file",required=false) MultipartFile file){
		System.out.println("开始");  
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();  
        
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  		
            targetFile.mkdirs();  
        }  
  
//	   try {
//		   InputStream is = new FileInputStream(targetFile);
//		   HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
//		   
//	   } catch (IOException e1) {
//			e1.printStackTrace();
//	   }
			 
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
        System.out.println("地址-----"+request.getContextPath()+"/upload/"+fileName);
	}
}
