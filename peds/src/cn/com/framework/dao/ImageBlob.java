package cn.com.framework.dao;

import java.io.File;

public class ImageBlob
{
  private File value;

  public ImageBlob(String filePath)
  {
    this.value = new File(filePath);
  }

  public ImageBlob(File file) {
    this.value = file;
  }

  public File getValue() {
    return this.value;
  }

  public void setValue(File value) {
    this.value = value;
  }
}