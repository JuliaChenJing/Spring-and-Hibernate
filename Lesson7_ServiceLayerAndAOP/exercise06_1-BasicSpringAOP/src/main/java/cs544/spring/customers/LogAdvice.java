package cs544.spring.customers;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LogAdvice {
	@After("execution(* cs544.spring.customers.EmailSender.sendEmail(..)) && args(email, message)")
	public void log(JoinPoint joinpoint, String email, String message) {
		System.out.println(new Date() + " method= "
				+ joinpoint.getSignature().getName() + " email address= "
				+ email + " message= " + message);
		IEmailSender emailSender = (IEmailSender) joinpoint.getTarget();
		System.out.println("outgoing mail server = "+emailSender.getOutgoingMailServer());
	}

}
