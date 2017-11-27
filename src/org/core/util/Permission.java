package org.core.util;

public class Permission {
	/******************************************
	 * 这是做测试相当于从数据库里取出的权限值 /一般根据一个模块id 取出它的crud 值就可以了 
	 * 
	 * 删除A---0 修改A---1 添加A---2
	 * 删除B---3 修改B---4 添加B---5 ...... 理论上可以有N个操作
	 * 
	 * 用户有权限：添加A---2;删除B---3;修改B---4 2的2次方+2的3次方+2的4次方=28 purview权限值
	 * 
	 *******************************************/
	static int purView = 28;
	// 需要判断的权限值
	static int bdel = 3;
	static int bupd = 4;
	static int badd = 5;

	public static void main(String[] args) {
		// 判断是否有权限
		System.out.println(judgePermission(purView, bdel));
		System.out.println(judgePermission(purView, bupd));
		System.out.println(judgePermission(purView, badd));
		// 为用户添加权限
		// 例如 为用户添加 修改A---1 权限
		
		for(int i=1;i<33;i++) {
			int newPurView = addPermission(purView, i);  
			System.out.println(i+  "   --   " + newPurView);
		}
//		System.out.println(judgePermission(newPurView, 1));
//		System.out.println(judgePermission(newPurView, bdel));
//		System.out.println(judgePermission(newPurView, badd));
//
//		// 修改用户的权限，返回新权限
//		// 例如减去莫个权限 减去修改A---1 权限
//		int newPurView2 = fixPermission(newPurView, 1);
//		System.out.println(newPurView2);
//		System.out.println(judgePermission(newPurView2, 1));
//		System.out.println(judgePermission(newPurView2, bdel));
//		System.out.println(judgePermission(newPurView2, badd));

	}

	public static boolean judgePermission(int purView, int p) {
		int i = 1 << p;
		// System.out.println( purView&i);
		return (purView & i) == i ? true : false;
	}

	public static int addPermission(int purView, int p) {
		int addP = 1 << p;
		return purView + addP;
	}

	public static int fixPermission(int purView, int p) {
		// 不含有改权限的时候，返回原权限值
		if (!judgePermission(purView, p)) {
			return purView;
		}
		int subP = 1 << p;
		return purView - subP;
	}

}