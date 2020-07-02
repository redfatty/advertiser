package com.hj.advertiser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hj.advertiser.dao")
public class LedAdvertiserApplication {

	public static void main(String[] args) {
		SpringApplication.run(LedAdvertiserApplication.class, args);
	}

}
