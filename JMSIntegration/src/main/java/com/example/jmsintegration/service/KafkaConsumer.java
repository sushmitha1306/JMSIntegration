package com.example.jmsintegration.service;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.jmsintegration.entity.Exam;

@Service
public class KafkaConsumer {

	private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	private static Exam ex;
    @KafkaListener(topics="exam")
    public void consume(Exam exam)
    {
        Logger log=LoggerFactory.getLogger(KafkaConsumer.class);
        ex= exam;
        log.info("Consumed Message---->"+ex.toString());
        latch.countDown();
            
    }
    public Exam getConsumedmessage() {
        return ex;
    }
}
