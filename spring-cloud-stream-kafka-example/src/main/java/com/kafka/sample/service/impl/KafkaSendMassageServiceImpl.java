package com.kafka.sample.service.impl;

import com.kafka.sample.domain.KafkaMassageRequest;
import com.kafka.sample.service.KafkaSendMassageService;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaSendMassageServiceImpl implements KafkaSendMassageService {

  private Producer producer;

  public KafkaSendMassageServiceImpl(Producer producer) {

    super();
    this.producer = producer;
  }


  @Override
  public void consume(Object o) {

  }

  @Override
  public void produce(KafkaMassageRequest payload) {

    payload.setTime(System.currentTimeMillis());
    producer.getMysource()
        .output()
        .send(MessageBuilder.withPayload(payload)
            .setHeader("type", "http")
            .build());

  }
}
