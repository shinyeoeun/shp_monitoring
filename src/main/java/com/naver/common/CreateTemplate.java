package com.naver.common;

import org.testng.annotations.Test;
import com.nts.gt.auto.common.Formatter;
import com.nts.gt.support.api.TestRailAPI;

public class CreateTemplate extends Formatter{
	
	public static TestRailAPI tr;
	
	public String projectName = "[공통댓글]서스테이닝";
	public String suiteName = "[Regression TC] 공통댓글";

	
	@Test
	public void createTemplate() {
		tr = new TestRailAPI();
		tr.setProject(projectName);
		tr.setSuite(suiteName);
	}
	

}