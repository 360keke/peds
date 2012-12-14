package com.beststar.framework.common.crypt;

import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * 加密解密工具
 */
public class CryptUtil {
	private static DES des;
	static {
		try{
			des = new DES();
		}
		catch(Exception e){
			
		}
	}
	
/**
 * md5 加密
 * @param str
 * @return
 */
    public static String crypt(String str){
        String aaa;
        try{
         MessageDigest md = MessageDigest.getInstance("MD5");
          md.update(str.getBytes());
          byte digestArr[] = md.digest();
          aaa = byte2hex(digestArr);
          return aaa;
        }catch(Exception ex){
        	ex.printStackTrace();
        }
        return "error!";
    }
    

    public static String byte2hex(byte b[]){
        String hs = "";
        String stmp = "";
        for(int n = 0; n < b.length; n++){
            stmp = Integer.toHexString(b[n] & 0xff);
            if(stmp.length() == 1)
                hs = (new StringBuilder(String.valueOf(hs))).append("0").append(stmp).toString();
            else
                hs = (new StringBuilder(String.valueOf(hs))).append(stmp).toString();
        }

        return hs.toUpperCase();
    }
	/**
	 * 解密函数
	 * @param encryptString String 已经加密的字符串
	 * @return String 解密后的字符串
	 */
	public static String decrypt(String encryptString){
		if(des != null){
			try{
				return des.decrypt(encryptString);
			}
			catch(Exception e){
				return encryptString;
			}
		}
		else{
			return encryptString;
		}
	}
	
	/**
	 * 加密函数
	 * @param originString String 原始字符串，即需要加密的字符串
	 * @return String 加密后的字符串
	 */
	public static String encrypt(String originString){
		if(des != null){
			try{
				return des.encrypt(originString);
			}
			catch(Exception e){
				return originString;
			}
		}
		else{
			return originString;
		}
	}
	
	public static boolean verifySHA(String ldappw, String inputpw){
		try{
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
		}catch(Exception ex){
			ex.printStackTrace();
		}
        return false;
    }
	
	public static boolean encryptSHA( String inputpw){
		try{
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		
		/*if (ldappw.startsWith("{SSHA}")) {
		    ldappw = ldappw.substring(6);
		} else if (ldappw.startsWith("{SHA}")) {
		    ldappw = ldappw.substring(5);
		}*/
		
		
		/*
		byte[] ldappwbyte = (new BASE64Encoder()).encodeBuffer(inputpw.getBytes());
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
		}*/
		

		md.update(inputpw.getBytes());

		//md.update(salt);
		

		byte[] inputpwbyte = md.digest();
		
		String encode=(new BASE64Encoder()).encodeBuffer(inputpwbyte);
		System.out.println("{SHA}"+encode);
		
		return true;//MessageDigest.isEqual(shacode, inputpwbyte);
		}catch(Exception ex){
			ex.printStackTrace();
		}
        return false;
    }
	public static void main(String[] args) {
//		String originString = "abcdefg";
//		String encryptString = CryptUtil.encrypt(originString);
//		System.out.println("原始串："+originString);
//		System.out.println("加密后："+encryptString);
//		encryptString="71fb29972e16be9c0cecc9f688d7cd28";
		System.out.println("解密后："+CryptUtil.encrypt("venustech123"));
		
//		encryptSHA("windows1");
		
//		System.out.println(verifySHA("{SHA}LWmiuDWXjZLZafLOtiu1k4P4jx4=", "windows1"));
	}
}
//{SHA}bARAqlwtw0dfT2GmOFybf1f0I/k=
//{SHA}0pprDw/7fZ3RXgJwu6rNNH5TXSY=