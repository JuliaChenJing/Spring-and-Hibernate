package edu.mum.cs544.task;

import org.springframework.scheduling.annotation.Scheduled;


public class SimpleHelloWorld {
	
	@Scheduled(cron="*/3 * * * * *")
	public void sayHello() {
		System.out.println("Hello World!");
	}

}
