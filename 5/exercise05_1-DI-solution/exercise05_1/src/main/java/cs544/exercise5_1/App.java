package cs544.exercise5_1;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		// IProductService productService = new ProductService();
		try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext( "application-context.xml")) {

			IProductService productService = context.getBean("productService", IProductService.class);

			Product product1 = productService.getProduct(423);
			if (product1 != null) {
				System.out.println(product1.toString());
			}
			Product product2 = productService.getProduct(239);
			if (product2 != null) {
				System.out.println(product2.toString());
			}
			
			// Extra item
			Product product3 = productService.getProduct(100);
			if (product3 != null) {
				System.out.println(product3.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
