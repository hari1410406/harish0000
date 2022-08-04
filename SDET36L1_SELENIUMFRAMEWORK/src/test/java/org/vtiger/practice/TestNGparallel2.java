package org.vtiger.practice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestNGparallel2 {
	
	@Test
	public void movieTollyTest() {
		Reporter.log("major postponed ",true);
	}
	
	@Test
	public void movieKollyTest() {
		Reporter.log("Beast postponed  ",true);
	}
	
	@Test
	public void movieSandalTest() {
		Reporter.log("charlie postponed",true);
	}



}
