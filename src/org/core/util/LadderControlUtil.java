package org.core.util;

import org.wiegand.at8000.WgUdpCommShort;

/**
 * 电梯控制工具类
 * 
 * @author sunjunhu
 *
 */
public class LadderControlUtil {

	public static void main(String[] args) {
		long controllerSN = 173100788;
		String controllerIP = "192.168.1.6";
		long cardNO = 0xE9A97AEC;
		int ifAddOrDelete = 0;
		byte era = 0x20;
		byte endYeaar = 0x29;
		byte endMonth = 0x12;
		byte endDay = 0x31;

		int r = LadderControlUserCard(controllerSN, controllerIP, cardNO, ifAddOrDelete, era, endYeaar, endMonth,
				endDay, 192, 0, 0, 0, 0);
		System.out.println("授权是否成功" + r);
	}

	/**
	 * 给梯控下发、删除权限
	 * 
	 * @param controllerSN
	 *            控制器SN
	 * @param controllerIP
	 *            控制器IP
	 * @param cardNO
	 *            IC/身份证号
	 * @param ifAddOrDelete
	 *            1代表授权，0 删除
	 * @param era
	 *            纪元 20年，30年
	 * @param endYeaar
	 *            有效期年
	 * @param endMonth
	 *            有效期月
	 * @param endDay
	 *            有效期日
	 * @param layOne
	 *            1-8 8层：10000000 1-8层：11111111 一层8位，如果授权设置为1，不授权设置为0
	 * @param layTwo
	 *            9-16层
	 * @param layThree
	 *            17-24层
	 * @param layFour
	 *            25-33层
	 * @param layFive
	 *            34-40层
	 * @return
	 */
	public static int LadderControlUserCard(long controllerSN, String controllerIP, long cardNO, int ifAddOrDelete,
			byte era, byte endYeaar, byte endMonth, byte endDay, int layOne, int layTwo, int layThree,
			int layFour, int layFive) {
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
		pkt.data[4] = 0x20;
		pkt.data[5] = 0x10;
		pkt.data[6] = 0x01;
		pkt.data[7] = 0x01;
		// 20 29 12 31 截止日期: 2029年12月31日
		pkt.data[8] = era;
		pkt.data[9] = endYeaar;
		pkt.data[10] = endMonth;
		pkt.data[11] = endDay;
		int doorNO = ifAddOrDelete;
		pkt.data[12] = (byte) (doorNO & 0xff);
		// 1-8 8层：10000000 1-8层：11111111
		pkt.data[13] = (byte) (layOne & 0xff);
		// 9-16
		pkt.data[14] = (byte) (layTwo & 0xff);
		// 17-24
		pkt.data[15] = (byte) (layThree & 0xff);
		// 25-33
		pkt.data[16] = (byte) (layFour & 0xff);
		// 34-40
		pkt.data[17] = (byte) (layFive & 0xff);

		recvBuff = pkt.run();
		success = 0;
		if (recvBuff != null) {
			if (WgUdpCommShort.getIntByByte(recvBuff[8]) == 1) {
				success = 1;
			}
		}
		pkt.CommClose();
		return success;
	}
}
