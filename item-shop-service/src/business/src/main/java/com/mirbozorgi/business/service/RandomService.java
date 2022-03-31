package com.mirbozorgi.business.service;

public interface RandomService {

  /**
   * form 1 to end
   */
  int nextInt(int end);

  /**
   * from start to end
   */
  int nextInt(int start, int end);

  /**
   * from 1 to priority length
   */
  int nextInt(int[] priority);

  /**
   * possibility true or false
   **/
  boolean possibility(int percent);

}
