package com.spring.boot.httpcomponents;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class SpringBootHttpcomponentsApplicationTests {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	void contextLoads() {
		String url = "https://localhost:9527/get/test?name=rock";
		String result = restTemplate.getForObject(url, String.class);
		System.out.println(result);
		Assertions.assertThat(result).isNotNull(); // 正确
//		Assertions.assertThat(result).isNull(); // 错误
	}

}
