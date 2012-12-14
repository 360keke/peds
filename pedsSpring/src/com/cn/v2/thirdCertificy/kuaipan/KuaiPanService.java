package com.cn.v2.thirdCertificy.kuaipan;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.cn.v2.thirdCertificy.OAuthUtil;
import com.cn.v2.thirdCertificy.OauthConstants;

public class KuaiPanService {
	public static String getRandom() {
		Random rd1 = new Random();
		String[] c = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
				"Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			buffer.append(c[rd1.nextInt(c.length)]);
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
		OAuthUtil oauth = new OAuthUtil();
		Map params = new HashMap<String, String>();

		String url = "";
		try {
			// url = oauth.createRequestTokenUrl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(url);
	}
}
