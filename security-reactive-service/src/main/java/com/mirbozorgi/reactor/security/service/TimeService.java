package com.mirbozorgi.reactor.security.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * default time zone is UTC
 */
public interface TimeService {

  LocalDateTime now();

  LocalDate nowDate();

  long nowUnix();

  LocalDateTime now(ZoneOffset zone);

  LocalDate nowDate(ZoneOffset zone);

  long nowUnix(ZoneOffset zone);

  LocalDateTime convertUnixTimeToLocalDateTimeUtc(long time);

  long getNowUnixFromInstantClass();

  Long convertLocalDateTimeToUnixUtc(LocalDateTime localDateTime);

  LocalDateTime convertUnixTimeToLocalDateTime(long time, ZoneOffset zone);

  Long convertLocalDateTimeToUnix(LocalDateTime localDateTime, ZoneOffset zone);

  long getMidNightDiffFromNowInSecond(ZoneOffset zone);

}