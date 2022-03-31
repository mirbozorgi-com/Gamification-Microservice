package com.mirbozorgi.core.repository.memory.base;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MemoryRepository {

  // String
  String getItem(String key);

  String setItem(String key, String item);

  Boolean setItemNx(String key, String item);

  void removeItem(String key);

  // Dictionary, Hash
  void dictionarySet(String key, String dicKey, String dicValue);

  String dictionaryGet(String key, String dicKey);

  Map<String, String> dictionaryGet(String key);

  Boolean dictionaryExists(String key, String dicKey);

  void dictionaryRemove(String key, String dicKey);

  long dictionaryIncrement(String key, String dicKey, int value);

  // Queue, List
  void queueLPush(String key, String item, Integer size);

  void queueRPush(String key, String item, Integer size);

  String queueLPop(String key);

  String queueRPop(String key);

  List<String> queueLRange(String key, Integer start, Integer end);

  List<String> queueRRange(String key, Integer start, Integer end);

  void queueRemove(String key, String item);

  List<String> queueGet(String key);

  // General
  long incrementItem(String key, Integer count);

  void setExpiry(String key, Integer sec);

  boolean check();

  Long zAdd(String key, long score, String member);

  Double zUpdate(String key, double score, String member);

  Long zReverseRank(String key, String member);

  Set<String> zRange(String key, long start, long stop);

  void deleteAllWithSpecificKey(String key);
}
