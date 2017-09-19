package cs544.spring.customers;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LogAdvice {
	@After("execution(* cs544.spring.customers.EmailSender.sendEmail(..)) && args(email, message)")
	public void log(JoinPoint joinpoint, String email, String message) {
		System.out.println("---LogAdvice: "+ new Date() //now 
				+ "\n method= "+ joinpoint.getSignature().getName() //method name
				+ "\n email address= "+ email //args of the method
				+ "\n message= " + message);//args of the method
		IEmailSender emailSender = (IEmailSender) joinpoint.getTarget();//get the object
		System.out.println("outgoing mail server = "+emailSender.getOutgoingMailServer());
	}

}
