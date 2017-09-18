package customers;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;


@Aspect
public class LogAdvice {
	@After("execution(* customers.EmailSender.sendEmail(..)) && args(email, message)")
	public void log(JoinPoint joinpoint, String email, String message) {
		System.out.println("---LogAdvice: "+ new Date() 
				+ "\n method= "+ joinpoint.getSignature().getName() 
				+ "\n email address= "+ email 
				+ "\n message= " + message);
		IEmailSender emailSender = (IEmailSender) joinpoint.getTarget();
		System.out.println(" outgoing mail server = "+emailSender.getOutgoingMailServer());
	}

}
