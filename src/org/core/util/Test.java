package org.core.util;

public class Test {
    
	public static void main(String[] args) {
		System.out.println(isPower(2));
		System.out.println(nextRight(2));
		
		Integer totle=1;
		for(int i=0;i<60;i++) {
			totle=totle+addRight(totle,nextRight(totle));
			System.out.println(totle);
		}
	    
        
	    System.out.println(isRight(totle,3));
	}
	/**
	 * 获取下一个权限值
	 * @param currentRight
	 * @return
	 */
	public static Integer nextRight(Integer currentRight) {
		if(isPower(currentRight)) 
		  return currentRight << 1;//乘   16 >> 1除
		else
		  return null;
	}
	/**
	 * 判断是否  2的整数幂
	 * @param number
	 * @return
	 */
	public static boolean isPower(Integer number) {
		String temp = Integer.toBinaryString(number);
        if (temp.lastIndexOf('1') !=0) {
            return false;
        } else {
            return true;
        }
    }
	/**
	 * 判断是否有权限
	 * @param rightSum
	 * @param right
	 * @return
	 */
	public static boolean isRight(Integer rightSum,Integer right) {
		return ((rightSum & right) == right);
	}
	/**
	 * 添加权限
	 * @param rightSum
	 * @param right
	 */
    public static Integer addRight(Integer rightSum,Integer right) {
		if(isRight(rightSum,right)) {
		    return rightSum;
		}else {
			return (rightSum | right);
		}
	}
    /**
     * 去掉权限
     * @param rightSum
     * @param right
     * @return
     */
    public static Integer delRight(Integer rightSum,Integer right) {
		if(isRight(rightSum,right)) {
		    return (rightSum & (~right));
		}else {
			return rightSum;
		}
	}
}
