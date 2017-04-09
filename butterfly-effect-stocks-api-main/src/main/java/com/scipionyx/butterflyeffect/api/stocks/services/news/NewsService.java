package com.scipionyx.butterflyeffect.api.stocks.services.news;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.social.twitter.api.SavedSearch;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.stocks.model.valuable.Stock;

@Component()
public class NewsService {

	@Autowired
	Environment env;

	@Value("${spring.social.twitter.appId}")
	private String twitterAppId;

	@Value("${spring.social.twitter.appSecret}")
	private String twitterAppSecret;

	/**
	 * 
	 * @param stock
	 */
	public void getNews(Stock stock) {

		Twitter twitter = new TwitterTemplate(twitterAppId, twitterAppSecret);

		SavedSearch savedSearch = twitter.searchOperations().createSavedSearch("#EPAY");
		@SuppressWarnings("unused")
		Map<String, Object> extraData = savedSearch.getExtraData();

	}

}
