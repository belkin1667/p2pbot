package com.l2lhackathon.peers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/*@ComponentScan(basePackages = {
	"com.l2lhackathon.peers.bot",
	"com.l2lhackathon.peers.configuration",
	"com.l2lhackathon.peers.controller",
	"com.l2lhackathon.peers.domain",
	"com.l2lhackathon.peers.exception",
	"com.l2lhackathon.peers.metrics",
	"com.l2lhackathon.peers.service",
})*/
@ConfigurationPropertiesScan("com.l2lhackathon.peers")
public class PeersApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeersApplication.class, args);
	}

}
