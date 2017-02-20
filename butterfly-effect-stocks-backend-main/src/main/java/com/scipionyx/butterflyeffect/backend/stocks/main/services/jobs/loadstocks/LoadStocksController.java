package com.scipionyx.butterflyeffect.backend.stocks.main.services.jobs.loadstocks;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.api.stocks.model.Exchange;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@RestController
public class LoadStocksController {

	@Autowired(required = true)
	private JobLauncher jobLauncher;

	@Autowired
	private LoadStocksJob job;

	/**
	 * 
	 * @param exchange
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = { RequestMethod.PUT }, path = "/launchJob")
	public String run(Exchange exchange) throws Exception {
		//
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addLong("time", System.currentTimeMillis());
		jobParametersBuilder.addString("exchange", exchange.toString());

		//
		JobParameters jobParameters = jobParametersBuilder.toJobParameters();
		JobExecution run = jobLauncher.run(job.importUserJob(), jobParameters);

		return run.getStepExecutions().toString();
	}

}
