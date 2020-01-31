package com.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.springboot.model.Student;

@Service
public class KafkaReciever {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReciever.class);

	@KafkaListener(topics = "${kafka.topic.name}", group = "${kafka.consumer.group.id}", containerFactory = "kafkaListenerContainerFactory")
	public void recieveData(Student student) {
		System.out.println("student received"+student);
		
		int age = student.getAge();
	
		System.out.println("student received"+age);

		LOGGER.info("Data - " + student.toString() + " recieved");
	}
	
	@KafkaListener(topics = "myexptopic3", group = "mygroupId", containerFactory = "kafkaListenerContainerFactory1")
	public void recieveNormalData(String msg) {
		System.out.println("student received"+msg);

		LOGGER.info("Data - " + msg + " recieved");
	}
}
