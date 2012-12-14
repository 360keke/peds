package com.cn.v2.common.utils;

import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密解密工具
 */
public class CryptUtil {
	private static DES des;
	static {
		try {
			des = new DES();
		} catch (Exception e) {

		}
	}

	/**
	 * 解密函数
	 * 
	 * @param encryptString
	 *            String 已经加密的字符串
	 * @return String 解密后的字符串
	 */
	public static String decrypt(String encryptString) {
		if (des != null) {
			try {
				return des.decrypt(encryptString);
			} catch (Exception e) {
				return encryptString;
			}
		} else {
			return encryptString;
		}
	}

	/**
	 * 加密函数
	 * 
	 * @param originString
	 *            String 原始字符串，即需要加密的字符串
	 * @return String 加密后的字符串
	 */
	public static String encrypt(String originString) {
		if (des != null) {
			try {
				return des.encrypt(originString);
			} catch (Exception e) {
				return originString;
			}
		} else {
			return originString;
		}
	}

	public static boolean verifySHA(String ldappw, String inputpw) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");

			if (ldappw.startsWith("{SSHA}")) {
				ldappw = ldappw.substring(6);
			} else if (ldappw.startsWith("{SHA}")) {
				ldappw = ldappw.substring(5);
			}

			byte[] ldappwbyte = (new BASE64Decoder()).decodeBuffer(ldappw);
			byte[] shacode;
			byte[] salt;

			if (ldappwbyte.length <= 20) {
				shacode = ldappwbyte;
				salt = new byte[0];
			} else {
				shacode = new byte[20];
				salt = new byte[ldappwbyte.length - 20];
				System.arraycopy(ldappwbyte, 0, shacode, 0, 20);
				System.arraycopy(ldappwbyte, 20, salt, 0, salt.length);
			}

			md.update(inputpw.getBytes());

			md.update(salt);

			byte[] inputpwbyte = md.digest();

			return MessageDigest.isEqual(shacode, inputpwbyte);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static String encryptSHA(String inputpw) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");

			md.update(inputpw.getBytes());

			byte[] inputpwbyte = md.digest();

			String encode = (new BASE64Encoder()).encodeBuffer(inputpwbyte);
			result = "{SHA}" + encode;
			;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 文件名加密类
	 * 
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		// 遍历，对源字符串每一位进行转码
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			// 数字，字符不需要转码
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			// ascil码转码
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else
			// 其他转码
			{
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * 文件名解密类
	 * 
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		// 查找%,进行解码
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			// 是经过转玛的字符
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u')// 是中文
				{
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else
				// 是ascil码
				{
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else
			// 不需要解码
			{
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	public static void main(String[] args) {
		System.out.println(CryptUtil.encrypt("admin"));

	}
}
