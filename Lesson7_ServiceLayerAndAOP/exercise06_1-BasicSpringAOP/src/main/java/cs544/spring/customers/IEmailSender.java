package cs544.spring.customers;

public interface IEmailSender {
	public void sendEmail(String email, String message);
	public String getOutgoingMailServer();
}