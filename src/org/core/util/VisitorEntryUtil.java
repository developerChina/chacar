package org.core.util;

import java.util.Iterator;
import java.util.List;
import org.core.domain.webapp.Access;
import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.Passageway;

public class VisitorEntryUtil {

	/**
	 * 访客登记后下发权限
	 * @param cardno  访客对象身份证物理卡号
	 * @param mj 门禁对象列表
	 * @param dt 电梯对象列表
	 * @param td 通道对象列表
	 */
	public static void inPermissionControl(String cardno, List<Access>  mj, List<Elevator> dt, List<Passageway> td) {
		// 1: 门禁  2: 电梯
		GrantAuthorization(mj,cardno,dt);
		//3:通道
		O2MoreOnlyMap<String, String> moreTDMap = new O2MoreOnlyMap<>();
		for (Passageway dto : td) {
			moreTDMap.put(dto.getControllerSN() + "," + dto.getControllerIP(), dto.getPtype());// 0:出  1：进
		}
		InitTDGrant(moreTDMap,cardno);
	}
	/**
	 * 
	 * @param moreMap
	 * @param cardno
	 */
	public static void GrantAuthorization(List<Access> la,String cardno,List<Elevator> dt) {
		O2MoreOnlyMap<Integer, String> moreDTMap = new O2MoreOnlyMap<>();// 某个人的“去重”后的所有 电梯  权限
		O2MoreOnlyMap<String, Integer> moreMJMap = new O2MoreOnlyMap<>();// 某个人的“去重”后的所有  门禁 权限
		for (Access ac : la) {
			moreDTMap.put(ac.getFloorno(), ac.getCsn() + "," + ac.getCip() + "," + cardno + "," + ac.getAcno());
			moreMJMap.put(ac.getCsn() + "," + ac.getCip() + "," + cardno + "," +ac.getFloorno(), ac.getAcno());
		}
		System.out.println("44444444444:"+moreMJMap.getAll().toString());
		InitMJGrant(moreMJMap);
		System.out.println("55555555555:"+moreDTMap.getAll().toString());
		InitDTGrant(moreDTMap,dt);
	}
	public static void InitMJGrant(O2MoreOnlyMap<String,Integer> moreMap) {
		int authority[] = { 0, 0, 0, 0 };
		long cardno = 0;
		String sn="",ip="";
		if(moreMap !=null &&  moreMap.getSize()>0) {
			for (int i = 0; i < moreMap.getSize(); i++) {
				String[] key = moreMap.getkey(i).split(",");
				sn=key[0];
				ip=key[1];
				cardno = Long.valueOf(key[2]);
				for (Iterator<Integer> it = moreMap.getvalue(i).iterator(); it.hasNext();) {
					authority[it.next() - 1] = 1;
				}
	    		AControlUtil.AddUserCard(Long.valueOf(sn),ip,Long.valueOf(cardno),(byte) 0x20, (byte) 0x29, (byte) 0x12, (byte) 0x31,authority);
				authority[0]=0; authority[1]=0; authority[2]=0;authority[3]=0;
			}
		}
	}
	public static void InitDTGrant(O2MoreOnlyMap<Integer, String> moreMap,List<Elevator> dt) {
		int layOne = 0, layTwo = 0, layThree = 0, layFour = 0, layFive = 0;
		long cardno = 0;
		if(moreMap !=null &&  moreMap.getSize()>0) {
			for (int i = 0; i < moreMap.getSize(); i++) {// 一个控制器，一层，一个人
				Integer key = moreMap.getkey(i);
				if (key >= 1 && key <= 8) {
					layOne = layOne + DTConstants.getFloor(key);
				}
				if (key >= 9 && key <= 16) {
					layTwo = layTwo + DTConstants.getFloor(key);
				}
				if (key >= 17 && key <= 24) {
					layThree =layThree + DTConstants.getFloor(key);
				}
				if (key >= 25 && key <= 32) {
					layFour =layFour + DTConstants.getFloor(key);
				}
				if (key >= 33 && key <= 40) {
					layFive = layFive + DTConstants.getFloor(key);
				}
				for (Iterator<String> it = moreMap.getvalue(i).iterator(); it.hasNext();) {
					String[] value = it.next().split(",");
					cardno = Long.valueOf(value[2]);
					if(cardno!=0) break;
				}
			}
			System.out.println("666666666:"+layOne);
			System.out.println("777777777:"+dt.toString());
			GrantDianTi(layOne, layTwo, layThree, layFour, layFive, cardno,dt);
		}
	}
	public static void GrantDianTi(int layOne, int layTwo, int layThree, int layFour, int layFive, Long cardno,List<Elevator> elevators) {
		for (Elevator el : elevators) {
			System.out.println("88888888:"+cardno+"---------"+layOne);
			LadderControlUtil.LadderControlUserCard(Long.valueOf(el.getControllerSN()), el.getControllerIP(), cardno, 1,
					(byte) 0x20, (byte) 0x29, (byte) 0x12, (byte) 0x31, layOne, layTwo, layThree, layFour, layFive);
		}
	}
	public static void InitTDGrant(O2MoreOnlyMap<String,String> moreMap,String cardno) {
		int authority[] = { 0, 0, 0, 0 };
		String sn="",ip="";
		if(moreMap !=null &&  moreMap.getSize()>0) {
			for (int i = 0; i < moreMap.getSize(); i++) {
				String[] key = moreMap.getkey(i).split(",");
				sn=key[0];
				ip=key[1];
				for (Iterator<String> it = moreMap.getvalue(i).iterator(); it.hasNext();) {
					authority[Integer.parseInt(it.next())] = 1;
				}
				AControlUtil.AddUserCard(Long.valueOf(sn),ip,Long.valueOf(cardno),(byte) 0x20, (byte) 0x29, (byte) 0x12, (byte) 0x31,authority);
				authority[0]=0; authority[1]=0; authority[2]=0;authority[3]=0;
			}
		}
	}
}
