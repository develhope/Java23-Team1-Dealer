package com.develhope.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("test")
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
