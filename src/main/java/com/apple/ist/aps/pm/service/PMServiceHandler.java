package com.apple.ist.aps.pm.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class PMServiceHandler {
	private static Logger LOGGER = LoggerFactory.getLogger(PMServiceHandler.class);
	public Mono<ServerResponse> incidents(ServerRequest request) {
		LOGGER.info("Received the request for the list of incidents");
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(this.getIncidents()));
	}
	
	public List<Incident> getIncidents() {
		final AtomicLong counter = new AtomicLong();
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			//LocalDateTime date = LocalDateTime.parse("2018-12-15T06:30:00");
			//String formatDateTime = date.format(formatter);
			//LocalDate parsedDate = LocalDate.parse(formatDateTime, formatter);
			//LOGGER.info(formatDateTime);
			List<Incident> incidents = new ArrayList<Incident>();
			incidents.add(new Incident(counter.incrementAndGet(), 1, "INC123456", LocalDateTime.parse("2018-12-15T06:30:00").format(formatter), "P1S", 2));
			incidents.add(new Incident(counter.incrementAndGet(), 4, "INC345666", LocalDateTime.parse("2018-12-16T16:10:40").format(formatter), "P2H", 1));
			return incidents;
			
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			return null;
		}
		
	}
}
