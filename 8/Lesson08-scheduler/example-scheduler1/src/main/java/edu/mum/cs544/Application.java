package edu.mum.cs544;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	public static void main(String args[]) throws InterruptedException{
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        
    }

}
