package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.RandomService;
import java.util.Random;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class RandomServiceImpl implements RandomService {

  private Random rand = new Random();

  @Override
  public int nextInt(int end) {
    return rand.nextInt(end) + 1;
  }

  @Override
  public int nextInt(int start, int end) {
    if ((end - start) <= 0) {
      return 0;
    }

    return rand.nextInt((end + 1) - start) + start;
  }

  @Override
  public int nextInt(int[] priority) {
    int sum = IntStream.of(priority).sum();
    if (sum == 0) {
      return nextInt(1, priority.length);
    }
    int total = 0;
    int rnd = nextInt(sum);
    for (int i = 0; i < priority.length; i++) {
      total += priority[i];
      if (rnd <= total && priority[i] != 0) {
        return i + 1;
      }
    }
    return 1;
  }

  @Override
  public boolean possibility(int percent) {

    return nextInt(100) < percent ? true : false;
  }

}