package cs544.spring.bank.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import cs544.spring.bank.logging.ILogger;

@Aspect
public class DaoLoggingAdvice {
	private ILogger logger;
	
	public DaoLoggingAdvice(ILogger logger) {
		this.logger = logger;
	}

	//Log every call to any method in the bank.dao package (using the Logger).
	@After("execution(* cs544.spring.bank.dao.*.*(..))")
	public void log(JoinPoint joinpoint) {
		logger.log("-----Call was made to:" + joinpoint.getSignature().getName()
				+ " on " + joinpoint.getTarget().getClass());
	}
}
