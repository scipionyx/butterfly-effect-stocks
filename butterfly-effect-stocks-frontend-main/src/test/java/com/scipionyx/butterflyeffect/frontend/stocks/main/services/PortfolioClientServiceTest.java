package com.scipionyx.butterflyeffect.frontend.stocks.main.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.scipionyx.butterflyeffect.api.infrastructure.services.client.CrudParameter;
import com.scipionyx.butterflyeffect.api.stocks.model.Portfolio;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {})
public class PortfolioClientServiceTest {

	@Autowired
	private PortfolioClientService service;

	@Test
	public void test() throws Exception {
		List<CrudParameter> paramters = new ArrayList<>();
		paramters.add(new CrudParameter("user", "admin"));
		List<Portfolio> findAllByOrderBy = service.findAllByOrderBy(paramters, "name");
		findAllByOrderBy.size();
	}

}
