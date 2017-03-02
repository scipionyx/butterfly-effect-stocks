package com.scipionyx.butterflyeffect.frontend.stocks.main.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {})
public class PortfolioClientServiceTest {

	@Autowired
	private PortfolioClientService service;

	@Test
	public void test() throws Exception {

	}

}
