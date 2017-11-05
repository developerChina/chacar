package org.core.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.core.domain.Example;

public class BeanUtil {
	 /**		
	  * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
	  * @param o
	  * @param exceptFields 例外字段（field1,field2）
	  * @return
	  * @throws IllegalArgumentException
	  * @throws IllegalAccessException
	  */
   public static Map<String, Object> getFiledsInfo(Object o,String exceptFields){
    	Field[] fields=o.getClass().getDeclaredFields();
       	Map<String, Object> infoMap=new HashMap<String, Object>();
    	for(Field field:fields){
    		if(Arrays.asList(exceptFields.split(",")).contains(field.getName())) continue;
    		field.setAccessible(true);
    		try {
    			if(field.get(o)!=null && !"".equals(field.get(o))){
    				infoMap.put(field.getName(), field.get(o));
    				System.out.println(field.getName()+"="+field.get(o)+"----"+field.get(o).getClass().getName());
    			}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
    	}
    	return infoMap;
    }
   

   
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Example e=new Example();
		e.setId("1111");;			// id
		e.setUsername("zhanghaixing");;	// 用户名
		e.setStatus(1);		// 状态
		e.setCreateDate(new Date());;	// 建档日期

		Map<String, Object> map=getFiledsInfo(e,"serialVersionUID");
		for (Map.Entry<String, Object> entry : map.entrySet()) {  
		    System.out.println(entry.getKey()+":"+entry.getValue());
		}  		
	}  
}
