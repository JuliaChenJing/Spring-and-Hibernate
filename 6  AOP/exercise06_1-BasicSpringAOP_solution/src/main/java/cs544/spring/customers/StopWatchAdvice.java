package cs544.spring.customers;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.util.StopWatch;
@Aspect
public class StopWatchAdvice {

	@Around("execution(* cs544.spring.customers.CustomerDAO.*(..))")
	public Object invoke(ProceedingJoinPoint call) throws Throwable {
		StopWatch sw = new StopWatch();
		sw.start(call.getSignature().getName());
		Object retVal = call.proceed();
		sw.stop();

		long totaltime=sw.getLastTaskTimeMillis();
        System.out.println("-----------StopWatchAdvice:  "+"Time to execute "
		+call.getSignature().getName()+" = "+totaltime+" ms");

		return retVal;

	}

}
