package cs544.spring.customers;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LogAdvice {
	/*
	      In aspect-oriented programming a set of join points is called a pointcut.
              A join point is a specification of when, in the corresponding main program, the aspect code should be executed. 
              The join point is a point of execution in the base code where the advice specified in a corresponding pointcut is applied.
       */
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
