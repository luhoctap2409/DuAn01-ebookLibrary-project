package com.ebooks.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ebook.mainFrame.AccoutAdmainDiaLog;
import com.ebook.mainFrame.LogInDiaLog;
import com.ebooks.model.TaiKhoan;

public class testAccountManagement {

	static AccoutAdmainDiaLog accManage;
	private String usernameAd;
	
	private String passwordAd;
	
	@BeforeClass
	public static void setUp() {
		accManage = new AccoutAdmainDiaLog();
	}
	
	@Before
	public void valueDefault() {
		usernameAd = "PhuongNC";
		passwordAd = "123456798";
//		usernameUser = "luuser";
//		passwordUser = "123456798";
		
	}
	
	//chỉnh sửa quyền từ người dùng sang admin
	@Test
	public void EditRoleFromUserToAdmin() {
		TaiKhoan taiKhoan = new TaiKhoan();
		taiKhoan.setTenDangNhap("PhuongNC");
		taiKhoan.setMatKhau("123456798");
		taiKhoan.setTrangThai(true);
		taiKhoan.setVaiTro(true);
		String message = accManage.testUptateTaiKhoan(taiKhoan);
		assertEquals(message, "Lưu thông tin thành công");
	}
	
	//chỉnh sửa trạng thái đang hoạt động sang ngưng hoạt động 
	@Test
	public void EditRoleFromActiveToInactive() {
		TaiKhoan taiKhoan = new TaiKhoan();
		taiKhoan.setTenDangNhap("PhuongNC");
		taiKhoan.setMatKhau("123456798");
		taiKhoan.setTrangThai(false);
		taiKhoan.setVaiTro(true);
		String message = accManage.testUptateTaiKhoan(taiKhoan);
		assertEquals(message, "Lưu thông tin thành công");
	}
	
	//check xóa tài khoản người dùng thất bại khi còn hoạt động
	@Test
	public void DeleteFailWithAccountActive() {
		String message = accManage.testDeleteTaiKhoan("HoangNH");
		assertEquals(message, "Tài khoản đang hoạt động");
	}
	
	//xóa tài khoản người dùng thành công
	@Test
	public void DeleteSuccess() {
		String message = accManage.testDeleteTaiKhoan("luuser");
		assertEquals(message, "Xóa thành công");
	}

	
}
