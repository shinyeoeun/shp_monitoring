package com.naver.shopping;

import com.ntscorp.auto_client.verity.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nts.gt.auto.common.Automation;
import com.nts.gt.auto.common.Formatter;
import com.nts.gt.auto.selenium.ChromeUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.datatransfer.StringSelection;
import org.openqa.selenium.interactions.Actions;
import java.util.List;


public class Suite_02_shopping_my extends Formatter {
	
	public ChromeUtil chrome = null;
	
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
	public void s02_쇼핑MY_관심스토어_진입() {
		
		chrome.get("https://shopping.naver.com/my/keep-stores");
		chrome.waitForPageToLoaded();
		chrome.sleep(3);
		// Verify.verifyTrue(chrome.isBrokenLinkExist());

		String url = chrome.getCurrentUrl();
		Assert.assertTrue(url.contains("my/keep-stores"));
	}
	
	@Test
	public void s03_관심스토어_클릭() {
		
		List<WebElement> elements = chrome.findElements(By.className("KeepStoreListItem_store_item__eP3WQ"));
		
        if (!elements.isEmpty()) { // 리스트가 비어 있지 않은지 확인
            elements.get(0).click(); // 첫 번째 요소 클릭
            chrome.sleep(3);
        } else {
            System.out.println("관심스토어 없음");
        }
		

		Assert.assertTrue(chrome.isElementPresent(By.cssSelector("a[data-shp-area-id='shopping']")));
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