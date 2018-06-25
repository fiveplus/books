package com.ace.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class StartListener implements ApplicationListener<ContextRefreshedEvent>{
	private Logger logger = LoggerFactory.getLogger(StartListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("==ApplicationStartedEventListener==");
		String dir = System.getProperty("user.dir");
		System.out.println("project run folder: " + dir);
	}

	
}
