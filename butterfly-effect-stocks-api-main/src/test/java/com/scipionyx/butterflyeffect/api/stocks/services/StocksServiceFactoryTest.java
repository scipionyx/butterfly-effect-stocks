package com.scipionyx.butterflyeffect.api.stocks.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.scipionyx.butterflyeffect.api.stocks.services.news.NewsService;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {})
public class StocksServiceFactoryTest {

	@Autowired
	private NewsService newsservice;

	/**
	 * 
	 */
	@Test
	public void ping() {
		newsservice.getNews(null);
	}

}
