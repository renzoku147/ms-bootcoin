package com.spring.msbootcoin.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.spring.msbootcoin.entity.BootCoin;

import reactor.core.publisher.Mono;

public interface BootCoinRepository extends ReactiveMongoRepository<BootCoin, String> {
	
	Mono<BootCoin> findByPhoneNumber(Integer i);
}
