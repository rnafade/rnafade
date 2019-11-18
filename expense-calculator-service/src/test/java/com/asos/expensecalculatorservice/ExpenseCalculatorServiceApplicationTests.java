package com.asos.expensecalculatorservice;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(JUnitPlatform.class)
@SelectPackages({ "com.asos.expensecalculatorservice.resource", "com.asos.expensecalculatorservice.services" })
class ExpenseCalculatorServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
