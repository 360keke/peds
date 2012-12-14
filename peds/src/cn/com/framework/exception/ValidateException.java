package cn.com.framework.exception;

public class ValidateException extends Exception
{
  private String sourceForm;

  public ValidateException(String sourceForm)
  {
    this.sourceForm = sourceForm;
  }

  public String getSourceForm()
  {
    return ((this.sourceForm == null) ? "error-page" : this.sourceForm); }

  public void setSourceForm(String sourceForm) {
    this.sourceForm = sourceForm;
  }
}