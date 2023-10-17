package br.com.matheus.todolist.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDateTime {
  private Date currentDate;
  private String formattedDateTime;

  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

  public CurrentDateTime() {
    this.currentDate = new Date();
    this.formattedDateTime = sdf.format(currentDate);
  }

  public String getFormattedDateTime() {
    return formattedDateTime;
  }
}
