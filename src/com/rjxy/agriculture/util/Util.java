package com.rjxy.agriculture.util;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class Util {

	/**
	 * 拆分IP
	 */
	public static List<String> breakIp(String ip) {
		List<String> ipList = new ArrayList<String>();
		String mIp = ip;
		for (int i = 0; i < 4; i++) {
			int index = mIp.indexOf(".");
			if (i == 3) {
				ipList.add(mIp);
			} else {
				ipList.add(mIp.substring(0, index));
				mIp = mIp.substring(index + 1, mIp.length());
			}
			Log.i("part", ipList.get(i));
		}
		return ipList;
	}

	/**
	 * 验证Ip
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean provingIP(String ip) {
		boolean result = false;
		try {
			for (int i = 0; i < 4; i++) {
				if (i < 3) {
					int index = ip.indexOf(".");
					try {
						int z = Integer.parseInt(ip.substring(0, index));
						if (z < 0 || z > 255) {
							return false;
						}
						ip = ip.substring(index + 1, ip.length());
					} catch (Exception e) {
						return false;
					}
				} else {
					try {
						int z = Integer.parseInt(ip);
						if (z < 0 || z > 255) {
							return false;
						}
						result = true;
					} catch (Exception e) {
						return false;
					}
				}
			}
		} catch (Exception e1) {
			return false;
		}
		return result;
	}

	/**
	 * 验证用户名格式 必须为6-12位纯字母，忽略大小写
	 */
	public static boolean provingUserName(String username) {
		boolean result = false;
		for (int i = 0; i < username.length(); i++) {
			if (!(username.charAt(i) >= 'a' && username.charAt(i) <= 'z'
					&& username.length() > 5 && username.length() < 13)) {
				return result;
			}
		}
		result = true;
		return result;
	}

	/**
	 * 验证密码
	 */
	public static boolean provingPassword(String password) {
		int length = password.length();
		if (length < 3 || length > 6) {
			return false;
		} else {
			boolean result_1 = provingExistNumber(password);
			boolean result_2 = provingExistCase(password);
			boolean result_3 = provingSope(password);
			return result_1 && result_2 && result_3;
		}
	}

	private static boolean provingExistNumber(String password) {
		for (int i = 0; i < password.length(); i++) {
			char index = password.charAt(i); // 字符串上的对应的字符
			if (index >= '0' && index <= '9') {
				return true;
			}
		}
		return false;
	}

	private static boolean provingExistCase(String password) {
		password = password.toLowerCase();
		for (int i = 0; i < password.length(); i++) {
			char index = password.charAt(i); // 字符串上的对应的字符
			if (index >= 'a' && index <= 'z') {
				return true;
			}
		}
		return false;
	}

	private static boolean provingSope(String password) {
		password = password.toLowerCase();
		for (int i = 0; i < password.length(); i++) {
			char index = password.charAt(i); // 字符串上的对应的字符
			if (!((index >= 'a' && index <= 'z') || (index >= '0' && index <= '9'))) {// 不在这个范围为假
				return false;
			}
		}
		return true;
	}

	/**
	 * 验证邮箱格式
	 */
	public static boolean provingEmail(String email) {
		boolean result = false;
		int index;
		if ((index = email.indexOf("@")) != -1) {
			if ((index + 1) != email.length()) {
				if (email.charAt(index + 1) != '.') {
					email = email.substring(index + 1, email.length());
					if (email.contains(".")) {
						result = true;
					}
				}
			}
		}
		return result;
	}
}
