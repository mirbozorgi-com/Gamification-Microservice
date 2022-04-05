package com.mirbozorgi.zuul.service.impl;


import com.mirbozorgi.zuul.service.TimeService;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class TimeServiceImpl implements TimeService {

  @Override
  public LocalDateTime now() {
    return LocalDateTime.now(ZoneOffset.UTC);
  }

  @Override
  public LocalDateTime now(ZoneOffset zone) {
    return LocalDateTime.now(zone);
  }

  @Override
  public LocalDate nowDate() {
    return LocalDate.now(ZoneOffset.UTC);
  }

  @Override
  public LocalDate nowDate(ZoneOffset zone) {
    return LocalDate.now(zone);
  }

  @Override
  public long nowUnix() {
    return LocalDateTime.now(ZoneOffset.UTC).toEpochSecond(ZoneOffset.UTC);
  }

  @Override
  public long nowUnix(ZoneOffset zone) {
    return LocalDateTime.now().toEpochSecond(zone);
  }

  @Override
  public LocalDateTime convertUnixTimeToLocalDateTimeUtc(long time) {
    return LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneOffset.UTC);
  }

  @Override
  public Long convertLocalDateTimeToUnixUtc(LocalDateTime localDateTime) {
    return localDateTime.toEpochSecond(ZoneOffset.UTC);
  }

  @Override
  public LocalDateTime convertUnixTimeToLocalDateTime(long time, ZoneOffset zone) {
    return LocalDateTime.ofInstant(Instant.ofEpochSecond(time), zone);
  }

  @Override
  public Long convertLocalDateTimeToUnix(LocalDateTime localDateTime, ZoneOffset zone) {
    return localDateTime.toEpochSecond(zone);
  }

  @Override
  public long getMidNightDiffFromNowInSecond(ZoneOffset zone) {
    LocalDateTime now = LocalDateTime.now(zone);

    LocalTime midnight = LocalTime.MIDNIGHT;
    LocalDate today = LocalDate.now(ZoneOffset.UTC);
    LocalDateTime midnightLocalDateTime = LocalDateTime.of(today, midnight).plusDays(1);

    return ChronoUnit.SECONDS.between(now, midnightLocalDateTime);
  }

  @Override
  public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
    return dateToConvert.toInstant()
        .atZone(ZoneOffset.UTC)
        .toLocalDateTime();
  }

}
