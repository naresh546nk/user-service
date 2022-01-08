package com.user.fault;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/circuitBreaker")
public class CircuitBrakerController {
	Logger logger= LoggerFactory.getLogger(CircuitBrakerController.class);
	
	@GetMapping("/sample")
	//@Retry(name="sample-api",fallbackMethod = "errorHandler")
	//@CircuitBreaker(name="default",fallbackMethod = "errorHandler")
	@RateLimiter(name="default")  // request per second
	//@Bulkhead(name="sample-api") //for conncurrent call 
	public String  sampleApi() {
		logger.info("hitting the cirecutBreaker controller");
		String body="sample-api call is success full ...";
		// body = new RestTemplate().getForEntity("localhost:8888", String.class).getBody();
		
		return body;
	}

	
	public String errorHandler(Exception ex) {
		return "error occured while accessing the resources ... pleaes try latter .";
	}
}
