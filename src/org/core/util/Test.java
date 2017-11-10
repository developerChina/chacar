package org.core.util;

import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		 O2MoreOnlyMap<Integer,String> moreMap = new O2MoreOnlyMap<>();  
	        moreMap.put(1,"2 层  -> 3门,433104927,192.168.1.2");  
	        moreMap.put(1,"2 层  -> 3门,433104927,192.168.1.2");
	        moreMap.put(1,"3 层  -> 3门,433104927,192.168.1.2");
	        moreMap.put(2,"4 层  -> 3门,433104927,192.168.1.2");  
	        for (int i = 0; i < moreMap.getSize(); i++) {  
	        	System.out.println(moreMap.getkey(i));
	        	for( Iterator<String>   it = moreMap.getvalue(i).iterator();  it.hasNext(); )
	            {             
	                System.out.println("value="+it.next());            
	            }  
	        }  
	        //System.out.println(moreMap.getAll()); 
	}
}
