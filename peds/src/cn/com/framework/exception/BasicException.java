package cn.com.framework.exception;

import java.io.Serializable;

public class BasicException extends Exception
  implements Serializable
{
  Exception sourceException = null;

  public BasicException() { }

  public BasicException(String errorMessage, Exception sourceException) { super(errorMessage);
    if (sourceException != null)
      this.sourceException = sourceException;
  }

  public BasicException(String errorMessage) {
    super(errorMessage);
  }

  public BasicException(Exception sourceException) {
    this.sourceException = sourceException;
  }

  public Exception getSourceException() {
    return this.sourceException; }

  public void setSourceException(Exception sourceException) {
    this.sourceException = sourceException;
  }
}