package com.spring.msbootcoin.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.msbootcoin.handler.BootCoinHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterFunctionConfig {

	@Bean
	public RouterFunction<ServerResponse> routes (BootCoinHandler handler) {
		return RouterFunctions.route(GET("/list"), handler::findAll)
				.andRoute(GET("/findById/{id}"), handler::findById)
				.andRoute(POST("/create"), handler::create)
				.andRoute(DELETE("/delete/{id}"), handler::delete);
	}
}
