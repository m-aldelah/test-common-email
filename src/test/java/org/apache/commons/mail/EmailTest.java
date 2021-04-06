package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.List;

public class EmailTest {
	private static final String[] TEST_EMAILS = { "ab@bc.com", "a.b@c.org", "abcdefghijklmnopqrst@abcdefghijklmnopqrst.com.bd"};
	private static final Authenticator Authenticator = null;
	private static final Session Session = null;
	private String[] testValidChars= {" ", "a", "A", "\uc5ec", "0123456789", "12345678901234567890"};
	
	private EmailConcrete email;
	
	@Before //SETUP METHOD
	public void setUpEmailTest() throws Exception{
		email = new EmailConcrete(); //creating an instance
		
	}
	
	
	@After //TEARDOWN METHOD
	public void tearDownEmailTest() throws Exception{
		
	}
	
	@Test
	public void testAddBcc() throws Exception //testing AddBcc
	{
		email.addBcc(TEST_EMAILS); //tetsing with test email
		assertEquals(3, email.getBccAddresses().size());
	}
	
	
	@Test 
	public void addReplyToTest() throws Exception
	{
		email.addReplyTo("abcd@abc.com", "abc"); //tetsing replytotest
		assertEquals("abcd@abc.com", "");
	}
	
	@Test
	public void buildMimeMessageTest() throws Exception
	{
		ArrayList<InternetAddress> replyList2 = new ArrayList<InternetAddress>();
	
		//tetsing functions from class
		email.setHostName("localhost");
		email.setSmtpPort(1234);
		email.setFrom("abc@abc.com");
		email.addTo("c@d.com");
		email.setSubject("test mail");
		email.setCharset("ISO-8859-1");
		email.setContent("test content", "text");
		email.addCc("email@test.com");
		email.addBcc("email@test.com");
		email.addHeader("test", "abc");
		email.addReplyTo("abc@abc.com");
		
		MimeMultipart mim = new MimeMultipart(); //tests getMailSession()
		email.emailBody = mim;
		email.setContent(mim);
		email.setSmtpPort(123);
		
		
		email.replyList = replyList2;
	
	
		
		email.buildMimeMessage(); //running buildMimeMessage, deafult test
	}
	
	@Test
	public void getHostNameTest() //tetsing getHostName
	{
		email.setHostName("sampleName");
		String name = email.getHostName();
		
		assertEquals("sampleName", name); //checking if theyre equal
	}
	
	@Test
	public void getHostNamePropTest()
	{
		Properties properties = new Properties();
		Session session = Session.getDefaultInstance(properties, null);
		properties.put(EmailConstants.MAIL_HOST, "abc@abc.com");
		email.setMailSession(session);
		assertEquals("abc@abc.com", email.getHostName() );
	
	}
	
	@Test
	public void sentFromTest() //tetsing getHostName with null
	{
		email.setHostName(null);
		String name = email.getHostName();
		
		assertEquals(null, name);
	}
	
	@Test
	public void testGetSentDate()
	{
		
		Date date = new Date(123123); //tetsing with a date
		email.setSentDate(date);
		email.getSentDate();
		
	}
	
	@Test
	public void testGetMailSession() throws EmailException 
	{
		Session sess = email.getMailSession();
		//Session session = email.getMailSession();
		email.setMailSession(sess); //tetsing Session
	}
	
	@Test
	public void TestGetSocketConnectionTimeOut()
	{
		int temp = email.getSocketConnectionTimeout(); //tetsing the function
	}
	
	@Test
	public void testAddCc() throws Exception 
	{
	
		email.addCc(TEST_EMAILS); //testing with test email
		assertEquals(3, email.getCcAddresses().size());
	}
	
	
}
