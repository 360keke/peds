package cn.com.framework.exception;

public class LoginTimeOutException extends SystemException
{
  public LoginTimeOutException()
  {
    super("登录时间超常!请重新登录!"); }

  public LoginTimeOutException(String str) {
    super(str);
  }
}