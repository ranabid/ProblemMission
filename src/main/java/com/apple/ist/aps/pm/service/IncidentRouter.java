package com.apple.ist.aps.pm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class IncidentRouter {
	private static Logger LOGGER = LoggerFactory.getLogger(IncidentRouter.class);
	@Bean
	public RouterFunction<ServerResponse> route(PMServiceHandler pmServiceHandler) {
		LOGGER.info("Routing the request.");
		return RouterFunctions.route(
				RequestPredicates.GET("/api/incidents").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
				pmServiceHandler::incidents);
	}
}
