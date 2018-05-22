/*
 * @(#)com.enci.ecp.common.common.utils
 * @(#)UUIDGenerator.java	2012-1-13
 * 
 * Copyright © 2001-2012, All rights reserved.
 * iSoftStone Information Technology ( Group ) Co., Ltd.
 */
package com.wxp.util;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <P>Description: 生成UUID业务类</P>
 * @ClassName: UUIDGenerator
 * @author Asdpboy Yan  2012-1-13 下午02:24:24
 * @see
 */
public class UUIDGenerator {
	/**
	 * <p>Title: createKey</p>
	 * <p>Description: 生成UUID公用业务方法</p>
	 * @return
	 * @author Asdpboy Yan  2012-1-13 下午02:25:11
	 */
	public static String createKey() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 
	 * <p>Title: create32Key</p>
	 * <p>Description: 创建32位的UUID</p>
	 * @return
	 * @author 许世选  2012-1-16 下午03:29:32
	 */
	public static String create32Key() {
		return UUID.randomUUID().toString().replaceAll("\\-", "");
	}
	
	private static int[] NUM_ARR = {0,1,2,3,4,5,6,7,8,9};
	public static int createRandom(int length) {
		StringBuffer sb = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			sb.append(NUM_ARR[(int)(Math.random() * (i == 0 ? 9 : 10))]);
		}
		return Integer.parseInt(sb.toString());
	}

	public static String createRandomStr(int length) {
		StringBuffer sb = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			sb.append(NUM_ARR[(int)(Math.random() * 10)]);
		}
		return sb.toString();
	}
	
	public static int getPassWordLevel(String password){
		int passwordType = 0;
		boolean isDigit = false;// 定义一个boolean值，用来表示是否包含数字
		boolean isLetter = false;// 定义一个boolean值，用来表示是否包含字母
		boolean isWord = false;// 定义一个boolean值，用来表示是否包含特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(password);
		isWord = m.find();
		if (password.length() >= 6 && password.length() <= 20) {
			for (int i = 0; i < password.length(); i++) {
				if (Character.isDigit(password.charAt(i))) { // 用char包装类中的判断数字的方法判断每一个字符
					isDigit = true;
					continue;
				}
				if (Character.isLetter(password.charAt(i))) { // 用char包装类中的判断字母的方法判断每一个字符
					isLetter = true;
					continue;
				}
			}
			if (isDigit && isLetter == false) {
				passwordType = 1;
			}
			if (isDigit == false && isLetter == true) {
				passwordType = 1;
			}
			if ((isDigit == true && isLetter == true)
					|| (isDigit == true && isWord == true)
					|| (isLetter == true && isWord == true)) {
				passwordType = 2;
			}
			if (isDigit == true && isLetter == true && isWord == true) {
				passwordType = 3;
			}
		} else {
			passwordType = 0;
		}
		return passwordType;
	}

	public static void main(String[] args) {
		/*System.out.println(UUIDGenerator.create32Key());
		System.out.println(UUIDGenerator.create32Key());
		System.out.println(UUIDGenerator.create32Key());*/
//		System.out.println(UUIDGenerator.create32Key());
		Date date = new Date();
		String code = "86000705";
		System.out.println(code + "_" + date.getTime() + ".html");
	}
	
}
