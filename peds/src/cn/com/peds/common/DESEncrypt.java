package cn.com.peds.common;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class DESEncrypt {

	private static Key getKey(String strKey) {
		Key key = null;

		try {
			KeyGenerator gen = KeyGenerator.getInstance("DES");
			gen.init(new SecureRandom(strKey.getBytes()));
			key = gen.generateKey();
			gen = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

	public static byte[] getEncCode(byte[] byteE, String strKey) {
		byte[] byteFina = null;
		Cipher cipher = null;

		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, getKey(strKey));
			byteFina = cipher.doFinal(byteE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}

		return byteFina;
	}

	public static byte[] getDecCode(byte[] byteD, String strKey) {
		byte[] byteFina = null;
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, getKey(strKey));
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}

		return byteFina;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(new DES().decrypt("[B@1f1680f"));

	}
}
