package com.company.star.application;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan("com.company.star.*")
@EntityScan("com.company.star.db.model")
@EnableJpaRepositories("com.company.star.db.repository")
@EnableAspectJAutoProxy
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class StartBeShipmentServiceApplication {
	private static final Logger l = LoggerFactory.getLogger(StartBeShipmentServiceApplication.class);
	private static final String PRODUCTION_KEY = "DESKTOP-9J9DH1L";
	
	public static void main(String[] args) {
		l.info("--------------------- Initialization Starting ---------------------");
		String envStr = PRODUCTION_KEY;
		try {
			InetAddress ip = InetAddress.getLocalHost();
			String hostname = ip.getHostName();
			l.debug("hostname: " + hostname);
			if (hostname.toLowerCase().contains("staging-virtual-machine")) {
				envStr = "dev-local";
			} else if (hostname.contains("DESKTOP-9J9DH1L")) {
				envStr = "local-fawzy";
			}
			l.info("envStr: " + envStr);
		} catch (Exception e) {
			l.error(e.getMessage(), e);
		}

		SpringApplication app = new SpringApplication(StartBeShipmentServiceApplication.class);
		app.setAdditionalProfiles(envStr);
		app.run(args);
		l.info("--------------------- Initialization Successfull ---------------------");
	}

	@Bean
	public HttpTraceRepository httpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}
}
