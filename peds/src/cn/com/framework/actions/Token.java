package cn.com.framework.actions;

import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.com.framework.exception.SystemException;

public class Token
{
  private String token;

  public Token(HttpServletRequest req)
    throws SystemException
  {
    HttpSession session = req.getSession(true);
    long systime = System.currentTimeMillis();
    byte[] time = new Long(systime).toString().getBytes();
    byte[] id = session.getId().getBytes();
    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(id);
      md5.update(time);
      this.token = toHex(md5.digest());
    }
    catch (Exception ex) {
      throw new SystemException(ex);
    }
  }

  public String toString() {
    return this.token;
  }

  private String toHex(byte[] digest) {
    StringBuffer buf = new StringBuffer();

    for (int i = 0; i < digest.length; ++i) {
      buf.append(Integer.toHexString(digest[i] & 0xFF));
    }

    return buf.toString();
  }
}