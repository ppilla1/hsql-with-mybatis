package org.jpmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HsqlWithMybatisApplication {

	static {
		System.setProperty("java.awt.headless", "false");
	}

	public static void main(String[] args) {
		SpringApplication.run(HsqlWithMybatisApplication.class, args);
	}

}
