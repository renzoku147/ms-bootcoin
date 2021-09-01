package com.spring.msbootcoin.service;

import com.spring.msbootcoin.entity.BootCoin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootCoinService {
	Mono<BootCoin> create(BootCoin t);

    Flux<BootCoin> findAll();

    Mono<BootCoin> findById(String id);
    
    Mono<BootCoin> update(BootCoin t);
    
    Mono<Boolean> delete(String t);
    
    Mono<BootCoin> findByPhoneNumber(Integer i);
}
