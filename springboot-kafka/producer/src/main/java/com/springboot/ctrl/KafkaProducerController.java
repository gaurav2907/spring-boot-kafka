package com.springboot.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Student;
import com.springboot.service.KafkaSender;

@RestController
@RequestMapping("/kafkaProducer")
public class KafkaProducerController {

	@Autowired
	private KafkaSender sender;

	@PostMapping
	public ResponseEntity<String> sendData(@RequestBody Student student) {
		
		Integer age = student.getAge();
		if (age.compareTo(new Integer(80)) >= 0 ) {
			
			System.out.println("over aged");
			return new ResponseEntity<>("Mentioned age"+" "+age+" is more than 80", HttpStatus.BAD_REQUEST);

		}
		else if(age.compareTo(new Integer (18)) >= 0) {
	
		sender.sendData(student);
		return new ResponseEntity<>("Data sent to Kafka", HttpStatus.OK);

		}	else {
			System.out.println("Minor");
			return new ResponseEntity<>("Mentioned age"+" "+age+" is less than 80", HttpStatus.BAD_REQUEST);

			
		}	
	}		
}
