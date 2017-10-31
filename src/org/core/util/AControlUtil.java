package org.core.util;

import org.wiegand.at8000.WgUdpCommShort;

/**
 * 门禁下发控制
 * 
 * @author sunjunhu
 *
 */
public class AControlUtil {

	public static void main(String[] args) {
		long controllerSN = 433104811;
		String controllerIP = "192.168.1.8";
		long cardNO = 0xE5C97C;
		byte era=0x20;
		byte endYeaar = 0x29;
		byte endMonth = 0x12;
		byte endDay = 0x31;
		int authority[] = { 1, 1, 0, 1}; 
        //截止日期: 2029年12月31日  一、二、四门有权限
		AddUserCard(controllerSN,controllerIP, cardNO,era,endYeaar, endMonth,endDay,authority);
		//删除卡权限
		DeleUserCard(controllerSN, controllerIP,cardNO);
	}

	/**
	 * 给门禁授权
	 * 
	 * @param controllerSN
	 *            门禁控制器序列号（硬件上查询）
	 * @param controllerIP
	 *            门禁控制器IP
	 * @param cardNO
	 *            IC/身份证号
	 * @param era
	 *            纪元  20年，30年         
	 * @param endYeaar
	 *            有效期年
	 * @param endMonth
	 *            有效期月
	 * @param endDay
	 *            有效期日
	 * @param authority
	 *            控制门权限 ，数组每一位代表一门
	 * @return
	 */
	public static int AddUserCard(long controllerSN, String controllerIP, long cardNO,byte era,byte endYeaar, byte endMonth,
			byte endDay, int[] authority) {
		byte[] recvBuff;
		int success = 0;
		WgUdpCommShort pkt = new WgUdpCommShort();
		pkt.iDevSn = controllerSN;
		// 打开udp连接
		pkt.CommOpen(controllerIP);
		pkt.Reset();
		pkt.functionID = (byte) 0x50;
		pkt.iDevSn = controllerSN;
		long cardNOOfPrivilege = cardNO;
		System.arraycopy(WgUdpCommShort.longToByte(cardNOOfPrivilege), 0, pkt.data, 0, 4);
		// 20 10 01 01 起始日期: 2010年01月01日 (必须大于2001年)
		pkt.data[4] = era;
		pkt.data[5] = 0x10;
		pkt.data[6] = 0x01;
		pkt.data[7] = 0x01;
		// 20 29 12 31 截止日期: 2029年12月31日
		pkt.data[8] = 0x20;
		pkt.data[9] = endYeaar;
		pkt.data[10] = endMonth;
		pkt.data[11] = endDay;
		// 默认都是关闭
		pkt.data[12] = 0x00;
		pkt.data[13] = 0x00;
		pkt.data[14] = 0x00;
		pkt.data[15] = 0x00;
		// 每一位代表一个门
		if (authority[0] == 1) {
			pkt.data[12] = 0x01;
		}
		if (authority[1] == 1) {
			pkt.data[13] = 0x01;
		}
		if (authority[2] == 1) {
			pkt.data[14] = 0x01;
		}
		if (authority[3] == 1) {
			pkt.data[15] = 0x01;
		}
		recvBuff = pkt.run();
		success = 0;
		if (recvBuff != null) {
			if (WgUdpCommShort.getIntByByte(recvBuff[8]) == 1) {
				success = 1;
			}
		}
		// 关闭udp连接
		pkt.CommClose();
		return success;
	}
    /**
     * 删除卡权限
     * @param controllerSN
     * @param controllerIP
     * @param cardNO
     * @return
     */
	public static int DeleUserCard(long controllerSN, String controllerIP,long cardNO) {
		byte[] recvBuff;
		int success = 0;
		WgUdpCommShort pkt = new WgUdpCommShort();
		pkt.iDevSn = controllerSN;
		// 打开udp连接
		pkt.CommOpen(controllerIP);
		pkt.Reset();
		pkt.functionID = (byte) 0x52;
		pkt.iDevSn = controllerSN;
		long cardNOOfPrivilegeToDelete = cardNO;
		System.arraycopy(WgUdpCommShort.longToByte(cardNOOfPrivilegeToDelete), 0, pkt.data, 0, 4);
		recvBuff = pkt.run();
		success = 0;
		if (recvBuff != null) {
			if (WgUdpCommShort.getIntByByte(recvBuff[8]) == 1) {
				success = 1;
			}
		}
		// 关闭udp连接
		pkt.CommClose();
		return success;
	}
}
