package cs544.spring.customers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
 /*
   The ApplicationContext is the central interface within a Spring application for providing configuration information to the application. It is read-only at run time, but can be reloaded if necessary and supported by the application. A number of classes implement the ApplicationContext interface, allowing for a variety of configuration options and types of applications.
   The ApplicationContext provides:
  ● Bean factory methods for accessing application components.
  ● The ability to load file resources in a generic fashion.
  ● The ability to publish events to registered listeners.
  ● The ability to resolve messages to support internationalization.
  ● Inheritance from a parent context.
  */
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"springconfig.xml");
		ICustomerService customerService = context.getBean("customerService",
				ICustomerService.class);

		customerService.addCustomer("Julia Chen", "juliachenjing@gmail.com",
				"mainstreet 5", "San Leandro", "94579");
	}
}
