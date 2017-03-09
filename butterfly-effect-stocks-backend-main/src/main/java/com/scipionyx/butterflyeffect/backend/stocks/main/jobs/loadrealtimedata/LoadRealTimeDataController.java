package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.loadrealtimedata;

import java.util.Calendar;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@RestController
public class LoadRealTimeDataController {

	@Autowired(required = true)
	private JobLauncher jobLauncher;

	@Autowired(required = true)
	@Qualifier(value = "jobLoadRealTimeData")
	private Job job;

	/**
	 * 
	 * @param exchange
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = { RequestMethod.PUT }, path = "/stock/jobLoadRealTimeData")
	public String run(@RequestParam(name = "symbol", required = false) String symbol,
			@RequestParam(name = "exchange", required = false) String exchange) throws Exception {
		//
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addLong("timestamp", Calendar.getInstance().getTimeInMillis());
		jobParametersBuilder.addString("symbol", symbol);
		jobParametersBuilder.addString("exchange", exchange);

		//
		JobParameters jobParameters = jobParametersBuilder.toJobParameters();
		JobExecution run = jobLauncher.run(job, jobParameters);

		return run.getStepExecutions().toString();
	}

}
