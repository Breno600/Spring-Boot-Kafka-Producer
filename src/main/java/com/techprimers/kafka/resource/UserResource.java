package com.techprimers.kafka.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.techprimers.kafka.model.User;

@RestController
@RequestMapping("kafka")
public class UserResource {

	@Autowired
	private KafkaTemplate<String, User> kafkaTeamplate;
	
	private static final String TOPIC = "Kafka_Example";
	
	@GetMapping("/publish/{name}/{dept}/{salary}")
	public String post(@PathVariable("name") final String name, @PathVariable("dept") final String dept,  @PathVariable("salary") final Long salary) {
		
		
		kafkaTeamplate.send(TOPIC, new User(name, dept, salary));
		
		return "Published Sucessfully";
	}
	
}
