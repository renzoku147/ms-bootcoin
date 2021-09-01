package com.spring.msbootcoin.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.msbootcoin.entity.BootCoin;
import com.spring.msbootcoin.service.BootCoinService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class BootCoinHandler {
	
	@Autowired
	BootCoinService bootCoinService;
	
	public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(bootCoinService.findAll(), BootCoin.class);
    }
	
	public Mono<ServerResponse> findById(ServerRequest request) {
		String id = request.pathVariable("id");
		
        return ServerResponse.ok()
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(bootCoinService.findById(id), BootCoin.class);
    }
	
	public Mono<ServerResponse> create(ServerRequest request) {
		Mono<BootCoin> monoBootCoint = request.bodyToMono(BootCoin.class);
		
		return monoBootCoint.flatMap(bootcoint -> bootCoinService.create(bootcoint)
													.flatMap(bootcoinCreate -> ServerResponse
					        													.status(HttpStatus.CREATED)
					        													.contentType(MediaType.APPLICATION_JSON)
					        													.body(fromValue(bootcoinCreate))));
	}
	
	public Mono<ServerResponse> delete(ServerRequest request) {
		String id = request.pathVariable("id");
		System.out.println("Esta llegando al DELETE");
        return bootCoinService.delete(id)
        		.flatMap(purse -> ServerResponse.status(HttpStatus.OK)
								.bodyValue("BootCoin Eliminado"));
    }
}
