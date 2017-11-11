package org.core.util;

import java.util.List;
import org.core.domain.webapp.Access;
import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.Passageway;

public class VisitorEntryUtil {

	/**
	 * 访客登记后下发权限
	 * @param cardno  访客对象身份证物理卡号
	 * @param mj 门禁对象
	 * @param dt 电梯对象列表
	 * @param td 通道对象列表
	 */
	public static void inPermissionControl(String cardno, Access mj, List<Elevator> dt, List<Passageway> td) {
		// 1: 门禁
		int authority[] = { 0, 0, 0, 0 };
		if (mj.getAcno() != null) {
			authority[mj.getAcno() - 1] = 1;
		}
		AControlUtil.AddUserCard(Long.valueOf(mj.getCsn()), mj.getCip(), Long.valueOf(cardno), (byte) 0x20, (byte) 0x29,
				(byte) 0x12, (byte) 0x31, authority);
		// 2: 电梯
		int floorno = mj.getFloorno();
		for (Elevator dto : dt) {
			int lay[] = { 0, 0, 0, 0, 0 };
			if (floorno >= 1 && floorno <= 8)
				lay[0] = lay[0] + DTConstants.getFloor(floorno);
			if (floorno >= 9 && floorno <= 16)
				lay[1] = lay[1] + DTConstants.getFloor(floorno);
			if (floorno >= 17 && floorno <= 24)
				lay[2] = lay[2] + DTConstants.getFloor(floorno);
			if (floorno >= 25 && floorno <= 32)
				lay[3] = lay[3] + DTConstants.getFloor(floorno);
			if (floorno >= 33 && floorno <= 40)
				lay[4] = lay[4] + DTConstants.getFloor(floorno);
			LadderControlUtil.LadderControlUserCard(Long.valueOf(dto.getControllerSN()), dto.getControllerIP(), Long.valueOf(cardno), 1,
					(byte) 0x20, (byte) 0x29, (byte) 0x12, (byte) 0x31, lay[0], lay[1], lay[2], lay[3], lay[4]);
		}
		//3:通道
		for (Passageway dto : td) {
			int autd[] = { 0, 0, 0, 0 };
			//0:离开 -》1门      1：进入 -》2，门
			if (dto.getPtype() != null) {
				authority[Integer.parseInt(dto.getPtype())] = 1;
			}
			AControlUtil.AddUserCard(Long.valueOf(mj.getCsn()), mj.getCip(), Long.valueOf(cardno), (byte) 0x20, (byte) 0x29,
					(byte) 0x12, (byte) 0x31, autd);
		}
	}
}
