package org.core.util;

import java.util.HashMap;
import java.util.Map;

public class DTConstants {

	// 电梯常量
	public static Integer getFloor(Integer key){
		Map<Integer, Integer> map=new HashMap<Integer, Integer>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 4);
		map.put(4, 8);
		map.put(5, 16);
		map.put(6, 32);
		map.put(7, 64);
		map.put(8, 128);

		
		map.put(9, 1);
		map.put(10, 2);
		map.put(11, 4);
		map.put(12, 8);
		map.put(13, 16);
		map.put(14, 32);
		map.put(15, 64);
		map.put(16, 128);
		
		map.put(17, 1);
		map.put(18, 2);
		map.put(19, 4);
		map.put(20, 8);
		map.put(21, 16);
		map.put(22, 32);
		map.put(23, 64);
		map.put(24, 128);
		
		
		
		map.put(25, 1);
		map.put(26, 2);
		map.put(27, 4);
		map.put(28, 8);
		map.put(29, 16);
		map.put(30, 32);
		map.put(31, 64);
		map.put(32, 128);
		
		
		map.put(33, 1);
		map.put(34, 2);
		map.put(35, 4);
		map.put(36, 8);
		map.put(37, 16);
		map.put(38, 32);
		map.put(39, 64);
		map.put(40, 128);
		return map.get(key);
	}
}
