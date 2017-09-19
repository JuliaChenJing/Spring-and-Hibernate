package cs544.spring.bank.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

@Aspect
public class ServiceTimerAdvice {

	/*
	 * Use the Spring StopWatch functionality to measure the duration of all
	 * service level methods (any method in the bank.service package) and output
	 * the results to the console.
	 */
	@Around("execution(* cs544.spring.bank.service.*.*(..))")
	public Object time(ProceedingJoinPoint call) throws Throwable {
		StopWatch sw = new StopWatch();
		sw.start(call.getSignature().getName());
		Object retVal = call.proceed();
		sw.stop();

		long totaltime = sw.getLastTaskTimeMillis();
		System.out.println("Time to execute " + call.getSignature().getName() + " = " + totaltime + " ms");

		return retVal;
	}

}
