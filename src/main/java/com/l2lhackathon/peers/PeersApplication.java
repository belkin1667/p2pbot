package com.l2lhackathon.peers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.l2lhackathon.peers")
public class PeersApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeersApplication.class, args);
	}

}
