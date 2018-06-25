package com.ace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ace.listener.StartListener;

@Configuration
public class ListenerConfig {
	@Bean
	public StartListener startListener(){
		return new StartListener();
	}
}
