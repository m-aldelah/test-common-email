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
	public void testAddCc() throws Exception 
	{
	
		email.addCc(TEST_EMAILS); //testing with test email
		assertEquals(3, email.getCcAddresses().size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addHeaderTest() //testing addHeader function
	{
		email.addHeader(null, null); //creating header with null parameters
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addHeaderTest2() 
	{
		email.addHeader("abcde@abc.com", ""); //creating header test with one empty parameter
	}
	
	@Test
	public void addHeaderTestValid() //valid header test
	{
		email.addHeader("abcde@abc.com", "one");
	}
}
