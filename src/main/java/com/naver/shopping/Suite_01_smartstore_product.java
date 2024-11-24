package com.naver.shopping;

import com.ntscorp.auto_client.verity.Verify;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nts.gt.auto.common.Automation;
import com.nts.gt.auto.common.Formatter;
import com.nts.gt.auto.selenium.ChromeUtil;

import java.awt.datatransfer.StringSelection;
import java.awt.*;
import java.awt.event.KeyEvent;
import org.openqa.selenium.interactions.Actions;

public class Suite_01_smartstore_product extends Formatter {
	
	public ChromeUtil chrome = null;
	public String smartstoreProductUrl = "https://smartstore.naver.com/roomingshop/products/6016105792";
	
	@BeforeClass
	public void setUp() {
		chrome = (ChromeUtil) new Automation()
				.pc()
				.chrome()				
				.mainUrl("https://www.naver.com")
				.start();
	}
	
	@Test
	public void s01_네이버로그인() throws InterruptedException, AWTException{
		
		chrome.windowMaximize();	
		chrome.get("https://nid.naver.com/nidlogin.login?mode=form&url=https://www.naver.com/");
		
        String naverId = "shin_test";
        String naverPw = "qatest123";
		
		chrome.click(By.id("id"));
		setClipboardData(naverId); // 클립보드에 userId 복사
		pasteClipboard(); // 클립보드 붙여넣기
        chrome.sleep(1);
		
        Actions actions = new Actions(chrome);
        actions.sendKeys("\uE004").perform();

		setClipboardData(naverPw); // 클립보드에 userId 복사
		pasteClipboard(); // 클립보드 붙여넣기
		chrome.sleep(1);
		
		Verify.verifyTrue(chrome.isElementPresent(By.id("id_clear")));
		Verify.verifyTrue(chrome.isElementPresent(By.id("pw_clear")));
		
		chrome.click(By.className("btn_login"));
		Assert.assertTrue(chrome.isElementPresent(By.className("MyView-module__btn_logout___bsTOJ")));
	
	}
	
	
	@Test
	public void s02_스스_상품상세_진입() {
		
		chrome.get(smartstoreProductUrl);
		// Verify.verifyTrue(chrome.isBrokenLinkExist());

		String url = chrome.getCurrentUrl();
		Assert.assertTrue(url.contains("smartstore.naver.com"));
	
	}
	
	@Test
	public void s03_스스_구매하기() {
		chrome.click(By.cssSelector("a[data-shp-area='pcs.buy']"));
		chrome.sleep(2);
		
		String url = chrome.getCurrentUrl();
		Assert.assertTrue(url.contains("orders.pay.naver.com"));
    
	}
	
	
	
	@AfterClass
	public void tearDown() {
		chrome.quit();
	}
	

    // 클립보드에 데이터 복사
    private static void setClipboardData(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    // 클립보드 붙여넣기
    private static void pasteClipboard() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

}