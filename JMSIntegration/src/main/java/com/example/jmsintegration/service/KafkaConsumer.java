package com.example.jmsintegration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.jmsintegration.entity.Exam;

@Service
public class KafkaConsumer {

	private static Exam ex;
    @KafkaListener(topics="exam")
    public void consume(Exam exam)
    {
        Logger log=LoggerFactory.getLogger(KafkaConsumer.class);
        ex= exam;
        log.info("Consumed Message---->"+ex.toString());
            
    }
    public Exam getConsumedmessage() {
        return ex;
    }
}
