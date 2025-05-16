package com.jinu.bitool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // ✅ 스케줄링 기능을 전체 프로젝트에 활성화
@SpringBootApplication
@EnableCaching // ✅ 캐싱 기능을 전체 프로젝트에 활성화
public class BitoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitoolApplication.class, args);
	}

}
