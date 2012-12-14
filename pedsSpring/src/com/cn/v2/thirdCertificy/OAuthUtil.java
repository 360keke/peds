package com.cn.v2.thirdCertificy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class OAuthUtil {

	private static final String ENCODE = "utf-8";
	private String baseStringCommand = "oauth_consumer_key={oauth_consumer_key}&oauth_nonce={oauth_nonce}&" + "oauth_signature_method=HMAC-SHA1&oauth_timestamp={oauth_timestamp}"
			+ "&oauth_version=1.0";

	private String requestTokenUrl = "";
	private String authorizeUrl = "http://reg.163.com/oauth1/UserAuth.jsp";
	private String accessTokenUrl = "http://reg.163.com/services/oauth1/access_token";
	/**
	 * 根据实际的应用配置下面变量
	 */
	private String httpMethod = "GET";
	private String oauthConsumerKey = "xcrYIe7VSeA7uDu1";
	private String oauthConsumerSecret = "hNpEgR74aTUPjFP4";

	/**
	 * 版本号可选，如果有必须为“1.0”
	 */
	public void setRequestTokenUrl(String url) {
		this.requestTokenUrl = url;
	}

	public void setAuthorizeUrl(String url) {
		this.authorizeUrl = url;
	}

	public void setAccessTokenUrl(String url) {
		this.accessTokenUrl = url;
	}

	public void setKey(String key) {
		this.oauthConsumerKey = key;
	}

	public void setSecret(String secret) {
		this.oauthConsumerSecret = secret;
	}

	public static void main(String[] args) throws Exception {
		// Map params = new HashMap<String, String>();
		// params.put(OauthConstants.OAUTH_CONSUMER_KEY, "xcrYIe7VSeA7uDu1");
		// String oauth_nonce = UUID.randomUUID().toString().replace("-", "");
		// params.put(OauthConstants.OAUTH_NONCE, oauth_nonce);
		// params.put(OauthConstants.OAUTH_TIMESTAMP, new Date().getTime() / 1000 + "");
		//
		OAuthUtil oauth = new OAuthUtil();
		// oauth.baseParams = params;
		// String url = oauth.createRequestTokenUrl();
		// System.out.println(url);
	}

	public String createRequestTokenUrl(Map map) throws Exception {
		String baseString = this.getBaseString(map);
		String secret = oauthConsumerSecret + "&";
		String signature = getSignature(this.httpMethod + "&" + URLEncoder.encode(this.requestTokenUrl, ENCODE) + "&" + URLEncoder.encode(baseString, ENCODE), secret);
		StringBuffer sb = new StringBuffer();
		sb.append(this.requestTokenUrl).append("?").append(baseString).append("&" + OauthConstants.OAUTH_SIGNATURE).append("=" + signature);
		return sb.toString();
	}

	/**
	 * 生成步骤2 “请求用户对Request Token授权”的URL
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public String createAuthnoirzedUrl(String token) throws Exception {
		String baseUrl = authorizeUrl;
		String oauth_token = OauthConstants.OAUTH_TOKEN + "=" + token;
		String resultUrl = baseUrl + "?" + oauth_token;
		return resultUrl;
	}

	/**
	 * 生成步骤3 “使用授权后的Request Token换取Access Token”的URL
	 * 
	 * @param oauth_token
	 * @param token_secret
	 * @return
	 * @throws Exception
	 */
	// public String createAccessTokenUrl(String oauth_token, String token_secret) throws Exception {
	// String token = OAUTH_TOKEN + "=" + oauth_token;
	// String consumerKey = OAUTH_CONSUMER_KEY + "=" + this.oauthConsumerKey;
	// String signMethod = OAUTH_SIGNATURE_METHOD + "=" + this.oauthSignatureMethod;
	// String times = OAUTH_TIMESTAMP + "=" + +(System.currentTimeMillis() / 1000);
	// // 可以使用其他随机序列生成方式
	// String nonce = OAUTH_NONCE + "=" + System.nanoTime();
	// String version = OAUTH_VERSION + "=" + this.oauthVersion;
	//
	// String baseString = httpMethod + "&" + URLEncoder.encode(base_url_access_token, ENCODE) + "&"
	// + URLEncoder.encode(consumerKey + "&" + nonce + "&" + signMethod + "&" + times + "&" + token + "&" + version, ENCODE);
	//
	// String secret = oauthConsumerSecret + "&" + token_secret;
	// String signature = getSignature(baseString, secret);
	//
	// StringBuffer sb = new StringBuffer();
	// sb.append(base_url_access_token).append("?").append(consumerKey).append("&").append(signMethod).append("&").append(times).append("&").append(nonce).append("&").append(version).append("&")
	// .append(token).append("&").append(OAUTH_SIGNATURE + "=").append(URLEncoder.encode(signature, ENCODE));
	//
	// System.out.println("Access token:");
	// System.out.println("baseString:\t" + baseString);
	// System.out.println("signature:\t" + signature);
	// System.out.println("url:\t\t" + sb.toString());
	// System.out.println();
	//
	// return sb.toString();
	// }
	/**
	 * 生成步骤4 “使用Access Token获取用户Id”的URL
	 * 
	 * @param oauth_token
	 * @param token_secret
	 * @return
	 * @throws Exception
	 */
	// public String createGetUserInfoUrl(String oauth_token, String token_secret) throws Exception {
	// String token = OAUTH_TOKEN + "=" + oauth_token;
	// String consumerKey = OAUTH_CONSUMER_KEY + "=" + this.oauthConsumerKey;
	// String signMethod = OAUTH_SIGNATURE_METHOD + "=" + this.oauthSignatureMethod;
	// String times = OAUTH_TIMESTAMP + "=" + +(System.currentTimeMillis() / 1000);
	// // 可以使用其他随机序列生成方式
	// String nonce = OAUTH_NONCE + "=" + System.nanoTime();
	// String version = OAUTH_VERSION + "=" + this.oauthVersion;
	// String baseString = httpMethod + "&" + URLEncoder.encode(base_url_get_user_info, ENCODE) + "&"
	// + URLEncoder.encode(consumerKey + "&" + nonce + "&" + signMethod + "&" + times + "&" + token + "&" + version, ENCODE);
	// String secret = oauthConsumerSecret + "&" + token_secret;
	// String signature = getSignature(baseString, secret);
	// StringBuffer sb = new StringBuffer();
	// sb.append(base_url_get_user_info).append("?").append(consumerKey).append("&").append(signMethod).append("&").append(times).append("&").append(nonce).append("&").append(version).append("&")
	// .append(token).append("&").append(OAUTH_SIGNATURE + "=").append(URLEncoder.encode(signature, ENCODE));
	// return sb.toString();
	// }
	public byte[] computeSignature(String baseString, String keyString) throws GeneralSecurityException, UnsupportedEncodingException {
		SecretKey key = null;
		byte[] keyBytes = keyString.getBytes(ENCODE);
		key = new SecretKeySpec(keyBytes, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(key);
		byte[] text = baseString.getBytes(ENCODE);
		return mac.doFinal(text);
	}

	public String getSignature(String baseString, String key) throws UnsupportedEncodingException, GeneralSecurityException {
		BASE64Encoder base64encoder = new BASE64Encoder();
		String signature = base64encoder.encode(computeSignature(baseString, key));
		return URLEncoder.encode(signature, ENCODE);
	}

	public String getBaseString(Map map) {
		String temp = "";
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = (String) map.get(key);
			temp += key + "=" + value + "&";
		}
		if (temp.endsWith("&")) {
			temp = temp.substring(0, temp.length() - 1);
		}
		return temp.toString();

	}
	// public String getBaseString(Map map) {
	// // 通过ArrayList构造函数把map.entrySet()转换成list
	// List<Map.Entry<String, String>> mappingList = new ArrayList<Map.Entry<String, String>>(map.entrySet());
	// // 通过比较器实现比较排序
	// Collections.sort(mappingList, new Comparator<Map.Entry<String, String>>() {
	// public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2) {
	// return mapping2.getValue().compareTo(mapping1.getValue());
	// }
	// });
	// String temp = "";
	// for (Map.Entry<String, String> list : mappingList) {
	// temp += list.getKey() + "=" + list.getValue() + "&";
	// }
	// if (temp.endsWith("&")) {
	// temp = temp.substring(0, temp.length()-1);
	// }
	// return temp.toString();
	//		
	// }

}
