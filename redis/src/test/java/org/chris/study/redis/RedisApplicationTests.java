package org.chris.study.redis;

import org.chris.study.redis.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private HelloService helloService;

	@Test
	public void testHello() {
		helloService.hello();
	}

	@Test
	public void testHello2() {
		helloService.hello2();
	}

}
