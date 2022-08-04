package org.vtiger.practice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class PracticeTestNgforParallel {
	
	@Test
	public void movieTelugu() {
		Reporter.log("RRR released", true);
	}
	@Test
	public void movieTamil() {
		Reporter.log("Vikram released", true);
	}
	
	@Test
	public void movieSandalwood() {
		Reporter.log(" KGF released", true);
	}



}
