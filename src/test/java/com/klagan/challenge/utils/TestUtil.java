package com.klagan.challenge.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class TestUtil {
  public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
  public static LocalDateTime getLocalDateTime(String timeStamp) throws ParseException {
//    Date parsedDate = dateFormat.parse(timeStamp);
    return LocalDateTime.parse(timeStamp);
  }
}
