package com.naver.common;


import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.nts.gt.auto.common.Formatter;
import com.nts.gt.support.api.TestRailAPI;

public class SetUp extends Formatter{
	
	public static TestRailAPI tr;
	public String trUser= "dl_pay_sqa@nts-corp.com";
	public String trPasswd = "test123";
	
	public String projectName = "[교육용] 자동화연동";
	public String suiteName = "정기배포체크리스트";
	public String runName = null;
	
	@BeforeSuite
	public void init() {
		runName = "[2024년 04월 2주차]-Regression Test (신여은)";
		
		tr = new TestRailAPI(trUser, trPasswd);
		tr.setTestRailRun(projectName, suiteName, runName);
	}
	
	@AfterMethod
	public void afterMethod(Method caller, ITestResult result) {
		tr.setResult(result);
	}
	
	public String getRunDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 MM월 W주차");
		
		return sdf.format(d);
		
				
	}

}