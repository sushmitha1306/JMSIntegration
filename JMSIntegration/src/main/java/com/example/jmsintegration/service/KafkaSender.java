package com.example.jmsintegration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.jmsintegration.entity.Exam;
@Service
public class KafkaSender {

	@Autowired
    private KafkaTemplate<String, Exam> kafkaTemplate;
	String kafkaTopic = "exam";
    Logger log=LoggerFactory.getLogger(KafkaSender.class);
    public void send(Exam exam) {
    	kafkaTemplate.send(kafkaTopic, exam);
    }
}
