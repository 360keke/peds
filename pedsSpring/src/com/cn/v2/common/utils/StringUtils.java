package com.cn.v2.common.utils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.SystemException;

public class StringUtils {
	public static String[] getGBK(String[] source) {
		if (source == null) {
			return null;
		}
		String[] buffer = new String[source.length];
		for (int i = 0; i < source.length; ++i) {
			buffer[i] = getGBK(source[i]);
		}
		return buffer;
	}

	/**
	 * 转换星期几
	 * 
	 * @param num
	 * @return
	 */
	public static String TransNumToChinese(int num) {
		String result = "";
		switch (num) {
		case 1:
			result = "星期一";
			break;
		case 2:
			result = "星期二";
			break;
		case 3:
			result = "星期三";
			break;
		case 4:
			result = "星期四";
			break;
		case 5:
			result = "星期五";
			break;
		case 6:
			result = "星期六";
			break;
		case 7:
			result = "星期日";
			break;
		}
		return result;
	}

	public static String getGBK(String source) {
		if (source == null) {
			return null;
		}
		String target = null;
		try {
			byte[] b = source.getBytes("ISO8859_1");
			target = new String(b, "GBK");
		} catch (Exception e) {
		}
		return target;
	}

	public static String LongToString(Long value, String formatter) {
		Date date = new Date(value);
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}

	public static String DateToString(Date date, String formatter) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}

	public static String[] getISO8859_1(String[] source) {
		String[] buffer = new String[source.length];
		for (int i = 0; i < source.length; ++i) {
			buffer[i] = getISO8859_1(source[i]);
		}
		return buffer;
	}

	public static String getISO8859_1(String source) {
		String target = null;
		try {
			byte[] b = source.getBytes("GBK");
			target = new String(b, "ISO8859_1");
		} catch (Exception e) {
		}
		return target;
	}

	public static String replace(String mainStr, String oldStr, String newStr) {
		StringBuffer buffer = new StringBuffer(mainStr);
		int index = 0;
		int begin = 0;
		while ((index = buffer.toString().indexOf(oldStr, begin)) > -1) {
			buffer.replace(index, index + oldStr.length(), newStr);
			begin = index + newStr.length();
		}
		return buffer.toString();
	}

	public static Object convert(Class type, String var) throws SystemException {
		if (type.getName().equalsIgnoreCase("java.lang.String")) {
			return var;
		}
		if (type.getName().equalsIgnoreCase("java.math.BigDecimal")) {
			return new BigDecimal(var);
		}
		if ((type.getName().equalsIgnoreCase("java.sql.Date")) || (type.getName().equalsIgnoreCase("java.util.Date"))) {
			try {
				return new SimpleDateFormat("yyyy-MM-dd").parse(var);
			} catch (ParseException e) {
			}
		}
		if ((type.getName().equalsIgnoreCase("java.lang.Long")) || (type.getName().equalsIgnoreCase("long"))) {
			return new Long(var);
		}
		if ((type.getName().equalsIgnoreCase("java.lang.Integer")) || (type.getName().equalsIgnoreCase("int"))) {
			return new Integer(var);
		}
		if ((type.getName().equalsIgnoreCase("java.lang.Boolean")) || (type.getName().equalsIgnoreCase("boolean"))) {
			return new Boolean(var);
		}

		return null;
	}

	public static boolean contains(String value, String[] array) {
		if (array != null) {
			for (int i = 0; i < array.length; ++i) {
				if ((value != null) && (array[i] != null) && (array[i].equals(value))) {
					return true;
				}
			}
		}
		return false;
	}

	public static String crypt(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] digestArr = md.digest();
			String aaa = byte2hex(digestArr);
			return aaa;
		} catch (Exception e) {
		}
		return "error!";
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; ++n) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}

		}

		return hs.toUpperCase();
	}

	public static String getRandom(int flag) {
		String randomStr = "";
		for (int j = 1; j < flag; ++j) {
			int i = (int) (Math.random() * 10.0D);
			randomStr = randomStr + String.valueOf(i);
		}
		return randomStr;
	}

	public static String StringArrayToString(String[] array, String token) {
		if (array != null) {
			String separator = (token == null) ? "," : token;
			StringBuffer buffer = new StringBuffer();
			int i = 0;
			while (true) {
				if (i > 0) {
					buffer.append(separator);
				}
				buffer.append(array[i]);

				++i;
				if (i >= array.length) {
					return buffer.toString();
				}
			}
		}
		return null;
	}

	public static String[] StringToStringArray(String var, String token) {
		String separator = (token == null) ? "," : token;
		if (var != null) {
			StringTokenizer st = new StringTokenizer(var, separator);
			String[] buffer = new String[st.countTokens()];
			int t = 0;
			while (true) {
				buffer[t] = st.nextToken();
				++t;

				if (!(st.hasMoreTokens())) {
					return buffer;
				}
			}
		}
		return null;
	}

	public static String toSqlString(String strSource) {
		String strResult;
		if (strSource == null) {
			strResult = "null";
		} else {
			strResult = replace(strSource, "'", "''");
			strResult = replace(strResult, "&lt;", "<");
			strResult = replace(strResult, "&gt;", ">");
			strResult = "'" + strResult + "'";
		}
		return strResult;
	}

	public static String changeNullToStr(Object obj, String str) {
		if ((obj == null) || (obj.toString().equals("null"))) {
			return str;
		}

		return obj.toString();
	}

	public static String changeNullToStr(Object obj) {
		if (obj == null) {
			return "";
		}

		return obj.toString();
	}

	public static String firstCharToUpperCase(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String firstCharToLowerCase(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

//	public static Clob converStringToClob(String str) throws SystemException {
//		if (str == null)
//			return null;
//		try {
//			CLOB clob = CLOB.empty_lob();
//			clob.setBytes(str.getBytes());
//			return clob;
//		} catch (SQLException e) {
//			throw new SystemException(e);
//		}
//	}
//
//	public static String converClobToString(Clob clob) throws SystemException {
//		CLOB clobvalue = (CLOB) clob;
//		if (clob == null) {
//			return null;
//		}
//		byte[] bytes = clobvalue.getBytes();
//		return new String(bytes);
//	}

	public static String[] split(String source, String div) {
		int arynum = 0;
		int intIdx = 0;
		int intIdex = 0;
		int div_length = div.length();
		if (source.compareTo("") != 0) {
			if (source.indexOf(div) != -1) {
				intIdx = source.indexOf(div);
				for (int intCount = 1;; ++intCount)
					if (source.indexOf(div, intIdx + div_length) != -1) {
						intIdx = source.indexOf(div, intIdx + div_length);
						arynum = intCount;
					} else {
						arynum += 2;
						break;
					}
			} else {
				arynum = 1;
			}
		} else {
			arynum = 0;
		}

		intIdx = 0;
		intIdex = 0;
		String[] returnStr = new String[arynum];

		if (source.compareTo("") != 0) {
			if (source.indexOf(div) != -1) {
				intIdx = source.indexOf(div);
				returnStr[0] = source.substring(0, intIdx);
				for (int intCount = 1;; ++intCount)
					if (source.indexOf(div, intIdx + div_length) != -1) {
						intIdex = source.indexOf(div, intIdx + div_length);
						returnStr[intCount] = source.substring(intIdx + div_length, intIdex);

						intIdx = source.indexOf(div, intIdx + div_length);
					} else {
						returnStr[intCount] = source.substring(intIdx + div_length, source.length());

						break;
					}
			}

			returnStr[0] = source.substring(0, source.length());
			return returnStr;
		}

		return returnStr;
	}

	public static String formatStringToHtml(String str) {
		if (str == null) {
			return "";
		}
		str = replace(str, "\n", "<br>");
		str = replace(str, "'", "\\'");
		str = replace(str, "\"", "\\\"");
		str = replace(str, "  ", "&nbsp;");
		return str;
	}

	public static boolean isBlank(String str) {
		return ((str == null) || (str.equals("")));
	}

	public static String[] getStringArray(HttpServletRequest oRequest, String strParamName, String strDefaultValue) {
		if ((oRequest == null) || (isBlank(strParamName))) {
			return null;
		}
		String[] strParameterValues = oRequest.getParameterValues(strParamName);
		if (strParameterValues == null) {
			strParameterValues = new String[0];
		}
		for (int i = 0; i < strParameterValues.length; ++i) {
			if ((strParameterValues[i] != null) && (!(strParameterValues[i].equals(""))))
				continue;
			strParameterValues[i] = strDefaultValue;
		}

		return strParameterValues;
	}

	public static String capitalToLowercase(String str, int flag) {
		String[] lowercass = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
				"v", "w", "x", "y", "z" };

		String[] capital = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); ++i) {
			String sub_str = String.valueOf(str.charAt(i));
			int j;
			String replace_str;
			if (flag == 1) {
				for (j = 0; j < lowercass.length; ++j) {
					replace_str = lowercass[j];
					if (sub_str.equalsIgnoreCase(replace_str)) {
						sb.append(replace_str);
					}
				}

			} else if (flag == 2) {
				for (j = 0; j < capital.length; ++j) {
					replace_str = capital[j];
					if (sub_str.equalsIgnoreCase(replace_str)) {
						sb.append(replace_str);
					}
				}
			}
		}
		return sb.toString();
	}

	public static Map ConvertStringToProperties(String initString) {
		HashMap properties = new HashMap();
		if (initString == null) {
			return properties;
		}
		StringTokenizer pst = new StringTokenizer(initString, ";");
		String property = null;
		String name = null;
		String value = null;
		StringTokenizer nst = null;
		while (pst.hasMoreTokens()) {
			property = pst.nextToken();
			nst = new StringTokenizer(property, "=");
			if (nst.countTokens() == 2) {
				name = nst.nextToken().trim().toLowerCase();
				value = nst.nextToken().trim();
				properties.put(name, value);
			}
		}
		return properties;
	}

	public static String formatDateStringToChinese(String dateString) {
		return formatDateStringToChinese(dateString, "-");
	}

	public static String formatDateStringToChinese(String dateString, String separator) {
		StringBuffer chinese = null;
		if (dateString != null) {
			StringTokenizer stk = new StringTokenizer(dateString, separator);
			if (stk.hasMoreTokens()) {
				chinese = new StringBuffer();
				chinese.append(stk.nextToken());
				chinese.append("年");
				if (stk.hasMoreTokens()) {
					chinese.append(stk.nextToken());
					chinese.append("月");
					if (stk.hasMoreTokens()) {
						chinese.append(stk.nextToken());
						chinese.append("日");
					}
				}
			}

			return chinese.toString();
		}
		return null;
	}

	public static boolean isNumber(String str) {
		return ("0123456789".indexOf(str.substring(0, 1)) != -1);
	}

	public static boolean isLetter(String str) {
		return ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".indexOf(str.substring(0, 1)) != -1);
	}

	public static boolean isSymbol(String str) {
		return ("!@#$%^&*()_+-=|[]{}'\",./?<>\\".indexOf(str.substring(0, 1)) != -1);
	}

	public static int ArrayIndexOf(String[] array, String str) {
		int index = -1;
		if (array == null) {
			return index;
		}
		for (int i = 0; i < array.length; ++i) {
			if (array[i].equals(str)) {
				index = i;
				break;
			}
		}
		return index;
	}

	public static String converObjectToString(Object obj) throws SystemException {
		if (obj == null) {
			return "";
		}
		Class typeName = obj.getClass();
		if (typeName.getName().equalsIgnoreCase("java.lang.String")) {
			return ((String) obj);
		}
		if (typeName.getName().equalsIgnoreCase("java.lang.Integer")) {
			return String.valueOf(obj);
		}
		if (typeName.getName().equalsIgnoreCase("java.lang.Long")) {
			return String.valueOf(obj);
		}
		if (typeName.getName().equalsIgnoreCase("java.lang.Float")) {
			return String.valueOf(obj);
		}
		if (typeName.getName().equalsIgnoreCase("java.lang.Double")) {
			return String.valueOf(obj);
		}
		if (typeName.getName().equalsIgnoreCase("java.sql.Date")) {
			return obj + "";
		}
		if (typeName.getName().equalsIgnoreCase("java.util.Date")) {
			return obj + "";
		}
		if (typeName.getName().equalsIgnoreCase("java.math.BigDecimal")) {
			return obj.toString();
		}
		if (typeName.getName().equalsIgnoreCase("java.sql.Timestamp")) {
			return obj.toString();
		}
//		if (typeName.getName().equalsIgnoreCase("com.beststar.framework.common.dao.StringClob")) {
//			return ((StringClob) obj).getValue();
//		}

		return null;
	}

	public static String numberFormat(int num, int scale) {
		String formatStr = Integer.toString(num);
		for (int i = formatStr.length(); i < scale; ++i) {
			formatStr = "0" + formatStr;
		}
		return formatStr;
	}

	public static int birthdayToAge(String csrq) {
		int age = 0;
		int csrq_year = Integer.parseInt(csrq.substring(0, 4));
		int csrq_month = Integer.parseInt(csrq.substring(5, 7));
		int csrq_days = Integer.parseInt(csrq.substring(8, 10));
		String now = getCurrentDate();
		int year = Integer.parseInt(now.substring(0, 4));
		int month = Integer.parseInt(now.substring(5, 7));
		int days = Integer.parseInt(now.substring(8, 10));

		if (now.compareTo(csrq) > 0) {
			if (year != csrq_year) {
				if ((month == csrq_month) && (days == csrq_days)) {
					age = year - csrq_year;
				} else if (month != csrq_month) {
					if (month >= csrq_month) {
						age = year - csrq_year + 1;
					} else if (month < csrq_month) {
						age = year - csrq_year - 1;
					}
				} else if (month == csrq_month) {
					if (days >= csrq_days) {
						age = year - csrq_year + 1;
					} else if (days < csrq_days) {
						age = year - csrq_year - 1;
					}
				}
				return age;
			}
			if (year == csrq_year) {
				age = 0;
			}
		}
		return age;
	}

	public static String getCurrentDate() {
		long now = System.currentTimeMillis();
		Date CurrTime = new Date(now);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String rq = dateformat.format(CurrTime);
		return rq;
	}
}