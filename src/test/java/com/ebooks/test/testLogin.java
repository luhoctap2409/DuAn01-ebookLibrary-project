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
		usernameAd = "PhuongNC";
		passwordAd = "123456798";
		usernameUser = "luuser";
		passwordUser = "123456798";
		
	}
	
	//check null tên đăng nhập và mật khẩu vơi tài khoản admin
	@Test
	public void loginFailWithNullAll() {
		String message = login.testDangNhap("", "");
		assertEquals(message ,"Trống tên đăng nhập");
		
	}
	
	//check sai password
	@Test
	public void loginFailWithPassFalse() {
		String message = login.testDangNhap(usernameAd, "123");
		assertEquals(message ,"Đăng nhập thất bại");
	}
	
	
	//check đăng nhâp thành công với quyền admin
	@Test
	public void loginSuccessWithAdmin() {
		String message = login.testDangNhap(usernameAd, passwordAd);
		assertEquals(message ,"Đăng nhập quyền quản trị thành công");
	}
	
	//check đăng nhập thành công với quyền user
	@Test
	public void loginSuccessWithUser() {
		String message = login.testDangNhap(usernameAd, passwordAd);
		assertEquals(message ,"Đăng nhập thành công");
	}
	
	
}
