package com.scipionyx.butterflyeffect.backend.stocks.main.services.jobs;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author rmendes
 *
 */
@RestController
public class LoadStocksController {

	@Autowired(required = true)
	private JobLauncher jobLauncher;

	@Autowired
	private LoadStocksJob job;

	@RequestMapping("/launchjob")
	public String run() throws Exception {
		System.out.println("RUN !");

		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.toJobParameters();
		JobExecution run = jobLauncher.run(job.importUserJob(), jobParameters);

		return run.getStepExecutions().toString();
	}

}
