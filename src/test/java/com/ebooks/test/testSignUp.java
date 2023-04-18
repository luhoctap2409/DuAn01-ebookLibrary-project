package com.ebooks.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ebook.mainFrame.SignUpDiaLog;

public class testSignUp {

	static SignUpDiaLog signUp;
	
	private String usernameUser;
	
	private String passwordUser;
	
	@BeforeClass
	public static void setUp() {
		signUp = new SignUpDiaLog();
	}
	
	@Before
	public void valueDefault() {
		usernameUser = "";
		passwordUser = "";
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
