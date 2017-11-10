package org.core.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PermissionControlUtils {

	public static void main(String[] args) {
		

	}
	/**
	 * 某一个人的门禁，梯控的权限
	 * @param list
	 */
	public static void batchMjAuth(List<Map<String, Object>> mjList , List<Map<String, Object>> dtList){
		int authority[] = {0, 0, 0, 0};
		String lay[]={"0","0","0","0","0","0","0","0",
				      "0","0","0","0","0","0","0","0",
				      "0","0","0","0","0","0","0","0",
				      "0","0","0","0","0","0","0","0",
				      "0","0","0","0","0","0","0","0"};
		O2MoreOnlyMap<Integer,Integer> moreMap = new O2MoreOnlyMap<>();
		for(Map<String, Object> map:mjList) {
			moreMap.put((int) map.get("dtno"),(int) map.get("acno")); 
        }
		for (int i = 0; i < moreMap.getSize(); i++) {
			Integer key=moreMap.getkey(i);
			lay[key]="1";
			Set<Integer> value=moreMap.getvalue(i);
			for(Iterator<Integer> it = value.iterator(); it.hasNext();)
            {             
				authority[(it.next()-1)]= 1;
            }  
	    }
		
		/**
		 * 门禁下发权限
		 */
		//AControlUtil.AddUserCard((Long) map.get("controllerSN"),(String) map.get("ip"),(Long) map.get("cardNO"),(byte)0x20,(byte)0x29,(byte)0x12,(byte)0x31,authority);
		/**
		 * 电梯下发权限,如果是通道那么不授权电梯
		 */
//		if(dtList != null) {
//			for(Map<String, Object> dtmap: dtList) {
////				LadderControlUtil.LadderControlUserCard((Long) dtmap.get("controllerSN"), (String) dtmap.get("ip"), (Long) map.get("cardNO"),1,(byte)0x20,(byte)0x29,(byte)0x12,(byte)0x31,
////				lay[0]+lay[1]+lay[2]+lay[3]+lay[4]+lay[5]+lay[6]+lay[7],
////				lay[8]+lay[9]+lay[10]+lay[11]+lay[12]+lay[13]+lay[14]+lay[15],
////				lay[16]+lay[17]+lay[18]+lay[19]+lay[20]+lay[21]+lay[22]+lay[23], 
////				lay[24]+lay[25]+lay[26]+lay[27]+lay[28]+lay[29]+lay[30]+lay[31],
////				lay[32]+lay[33]+lay[34]+lay[35]+lay[36]+lay[37]+lay[38]+lay[39]);
//			}
//		}
		

	}
}
