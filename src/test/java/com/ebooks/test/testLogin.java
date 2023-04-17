package com.ebooks.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ebook.mainFrame.LogInDiaLog;

public class testLogin {

	static LogInDiaLog login;
	private String usernameAd;
	private String usernameUser;
	private String passwordAd;
	private String passwordUser;
	
	@BeforeClass
	public static void setUp() {
		login = new LogInDiaLog();
	}
	
	@Before
	public void valueDefault() {
		usernameAd = "luntpc";
		passwordAd = "12345";
		
	}
	
	@Test
	public void loginFailWithNullAll() {
		String message = login.testDangNhap(usernameAd, passwordAd);
		assertEquals(message ,"Đăng nhập thành công");
		
	}

}
