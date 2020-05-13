package com.spring.boot.log.springbootlog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class SpringBootLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLogApplication.class, args);
	}

	/**
	 * localhost:8070/echo
	 *
	 * @return
	 */
	@RequestMapping("/echo")
	public String echo() {
		log.debug("this is log app echo");
		return "this is log app";
	}
}
