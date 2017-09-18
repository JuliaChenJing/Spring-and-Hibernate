package cs544.spring.bank.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import cs544.spring.bank.logging.ILogger;

@Aspect
public class JmsLoggingAdvice {
	private ILogger logger;
	
	public JmsLoggingAdvice(ILogger logger) {
		this.logger = logger;
	}
	// Log every JMS message that is sent (using the Logger)
	@After("execution(* cs544.spring.bank.jms.JMSSender.sendJMSMessage(..)) && args (message))")
	public void log(JoinPoint joinpoint, String message) {
		logger.log("---JMS Message: "+message);
	}

}
