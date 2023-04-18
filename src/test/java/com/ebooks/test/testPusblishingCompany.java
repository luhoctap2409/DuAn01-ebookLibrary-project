package com.ebooks.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ebook.mainFrame.LogInDiaLog;
import com.ebook.mainFrame.PublishingCompanyDiaLog;
import com.ebooks.model.NhaXuatBan;

public class testPusblishingCompany {

	static PublishingCompanyDiaLog nxb;
	String nameNXB;
	String address;
	String moTa;
	
	@BeforeClass
	public static void setUp() {
		nxb = new PublishingCompanyDiaLog();
	}
	
	@Before
	public void valueDefault() {
		nameNXB ="Fahasa";
		address = "Cần Thơ";
		moTa= "";
	}
	
	//Thêm nhà xuất bản mới thành công
	@Test
	public void createNXBSuccess() {
		NhaXuatBan nXB = new NhaXuatBan();
		nXB.setTenNXB(nameNXB);
		nXB.setDiaChiNXB(address);
		nXB.setMota(moTa);
		String message = nxb.testCreateNXB(nXB);
		assertEquals(message, "Thêm mới thành công");
	}

	//Thêm nhà xuất bản Thất bại với tên nhà xuất bản trống
		@Test
		public void createNXBFailWithNullNameNXB() {
			NhaXuatBan nXB = new NhaXuatBan();
			nXB.setTenNXB("");
			nXB.setDiaChiNXB(address);
			nXB.setMota(moTa);
			String message = nxb.testCreateNXB(nXB);
			assertEquals(message, "Không được để trống Tên Nhà xuất bản");
		}
}
