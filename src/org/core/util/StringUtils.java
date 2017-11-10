package org.core.util;

public class StringUtils {
	 public static boolean isBlank(String str)
	 {
	   int strLen;
	   if ((str == null) || ((strLen = str.length()) == 0))
	     return true;
	   for (int i = 0; i < strLen; i++) {
	     if (!Character.isWhitespace(str.charAt(i))) {
	       return false;
	     }
	   }
	   return true;
	 }
	
	 public static boolean isNotBlank(String str)
	 {
	   return !isBlank(str);
	 }
	 
	 public static String join(String[] array,String separator){
		 if(array==null || array.length<=0){
			 return "";
		 }
		 StringBuilder buf = new StringBuilder();
		 for (int i=0;i<array.length;i++) {
			 if(array.length-1==i){
				 buf.append(array[i]); 
			 }else{
				 buf.append(array[i]).append(separator); 
			 }
		 }
		 return buf.toString();
	 }
}
