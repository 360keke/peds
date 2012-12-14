package cn.com.framework.exception;

public class BusinessException extends BasicException
{
  public BusinessException()
  {
  }

  public BusinessException(String errorMessage)
  {
    super(errorMessage); }

  public BusinessException(Exception sourceException) {
    super(sourceException);
  }

  public BusinessException(String errorMessage, Exception sourceException) {
    super(errorMessage, sourceException);
  }
}