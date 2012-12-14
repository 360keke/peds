package com.cn.v2.thirdCertificy.dbank;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

import nsp.support.common.MD5;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.cn.v2.common.utils.HttpClientUtil;
import com.cn.v2.common.utils.JsonUtils;
import com.cn.v2.thirdCertificy.OAuthUtil;
import com.cn.v2.thirdCertificy.OauthConstants;

public class DbankService {
	static final Log log = LogFactory.getLog(DbankService.class);
	String appid = "56618";
	String appSecret = "FL7wOonHEXqh6f5warFp5svK1aQwUa2p";
	String oauth_signature_method = "HMAC-SHA1";
	String requestTokenUrl = "http://login.dbank.com/oauth1/request_token";
	String authorizeUrl = "http://login.dbank.com/oauth1/authorize";
	Map params = new HashMap();

	public DbankService() {
		this.params.put(OauthConstants.OAUTH_CONSUMER_KEY, this.appid);
		this.params.put(OauthConstants.OAUTH_VERSION, "1.0");
		this.params.put(OauthConstants.OAUTH_SIGNATURE_METHOD, "HMAC-SHA1");
	}

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

	public JSONObject executeRequestToken() {
		OAuthUtil oauth = new OAuthUtil();
		String oauth_nonce = UUID.randomUUID().toString().replaceAll("-", "");
		this.params.put(OauthConstants.OAUTH_NONCE, oauth_nonce);
		this.params.put(OauthConstants.OAUTH_TIMESTAMP, new Date().getTime() / 1000 + "");
		this.params.put(OauthConstants.OAUTH_CONSUMER_KEY, this.appid);
		oauth.setRequestTokenUrl("http://login.dbank.com/oauth1/request_token");
		oauth.setKey(this.appid);
		oauth.setSecret(this.appSecret);
		JSONObject json = null;
		try {
			String result = HttpClientUtil.executeUrl(oauth.createRequestTokenUrl(this.params));
			json = new JSONObject(result);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return json;
	}

	public String executeAuthorize() {
		JSONObject responseRequestToken = this.executeRequestToken();
		String oauth_token = (String) JsonUtils.get(responseRequestToken, "oauth_token");
		String url = this.authorizeUrl + "?oauth_token=" + oauth_token;
		String responseOauthrize = HttpClientUtil.executeUrl(url);
		return responseOauthrize;

	}

	public static void main(String[] args) {
		OAuthUtil oauth = new OAuthUtil();
		long ts = System.currentTimeMillis();
		Map params = new TreeMap<String, String>();
		params.put("nsp_svc", "nsp.auth.createClient");
		params.put("type", "1");
		params.put("nsp_app", "56636");
		params.put("nsp_ts", ts+"");
		params.put("nsp_fmt", "JSON");

		String baseString = oauth.getBaseString(params);
		System.out.println(baseString);
		MD5 md5 = new MD5("wXF0mCYSK9ZbESSPMOqHj3aqUe4aIWxd" + baseString.replaceAll("=", "").replaceAll("&", ""));
		String url = "http://api.dbank.com/rest.php?" + baseString + "&nsp_key=" + md5.asHex().toUpperCase();
		System.out.println(HttpClientUtil.executeUrl(url));
//		System.out.println(url);
	}

}
