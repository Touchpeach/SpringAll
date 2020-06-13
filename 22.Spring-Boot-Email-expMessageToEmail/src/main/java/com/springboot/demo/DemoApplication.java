package com.springboot.demo;

import com.springboot.demo.model.MailConfigBean;
import com.springboot.demo.model.SendMessageConfigBean;
import com.springboot.demo.model.SendMessageMongoConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties({MailConfigBean.class, SendMessageMongoConfigBean.class, SendMessageConfigBean.class})
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setAddCommandLineProperties(true);
		app.run(args);
	}
}
