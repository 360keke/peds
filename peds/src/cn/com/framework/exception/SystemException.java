package cn.com.framework.exception;

public class SystemException extends BasicException
{
  public SystemException(String errorMessage)
  {
    super(errorMessage);
  }

  public SystemException(Exception sourceException) {
    super(sourceException);
  }

  public SystemException(String errorMessage, Exception sourceException) {
    super(errorMessage, sourceException);
  }
}