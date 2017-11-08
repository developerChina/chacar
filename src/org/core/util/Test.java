package org.core.util;

import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		 O2MoreOnlyMap<Integer,Integer> moreMap = new O2MoreOnlyMap<>();  
	        moreMap.put(1,12);  
	        moreMap.put(1,12);
	        moreMap.put(1,13);
	        moreMap.put(2,13);  
	        for (int i = 0; i < moreMap.getSize(); i++) {  
	        	System.out.println(moreMap.getkey(i));
	        	for( Iterator<Integer>   it = moreMap.getvalue(i).iterator();  it.hasNext(); )
	            {             
	                System.out.println("value="+it.next());            
	            }  
	        }  
	        //System.out.println(moreMap.getAll()); 
	}
}
