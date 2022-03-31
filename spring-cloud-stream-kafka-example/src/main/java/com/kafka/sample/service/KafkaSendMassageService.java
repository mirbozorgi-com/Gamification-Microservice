package com.kafka.sample.service;

import com.kafka.sample.domain.KafkaMassageRequest;

public interface KafkaSendMassageService {

  void consume(Object o);

  void produce(KafkaMassageRequest payload);


}
