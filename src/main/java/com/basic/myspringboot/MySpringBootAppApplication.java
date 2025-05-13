package com.basic.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MySpringBootAppApplication {

	public static void main(String[] args) {
		//SpringApplication.run(MySpringBootAppApplication.class, args);

		//직접 생성으로
		SpringApplication application = new SpringApplication(MySpringBootAppApplication.class);
		//기본적으로 WebApplicationType : 웹어플리케이션 (NONE)
		// SERVLET으로 변경 (tomcat) -> 계속 살아있음
		application.setWebApplicationType(WebApplicationType.SERVLET);
		application.run(args);
	}

}
