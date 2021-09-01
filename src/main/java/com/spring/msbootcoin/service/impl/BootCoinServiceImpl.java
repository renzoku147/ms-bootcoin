package com.spring.msbootcoin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.msbootcoin.entity.BootCoin;
import com.spring.msbootcoin.repository.BootCoinRepository;
import com.spring.msbootcoin.service.BootCoinService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BootCoinServiceImpl implements BootCoinService{

	@Autowired
	BootCoinRepository bootCoinRepository;
		
	@Override
	public Mono<BootCoin> create(BootCoin t) {
		return bootCoinRepository.save(t);
	}

	@Override
	public Flux<BootCoin> findAll() {
		// TODO Auto-generated method stub
		return bootCoinRepository.findAll();
	}

	@Override
	public Mono<BootCoin> findById(String id) {
		// TODO Auto-generated method stub
		return bootCoinRepository.findById(id);
	}

	@Override
	public Mono<BootCoin> update(BootCoin t) {
		// TODO Auto-generated method stub
		return bootCoinRepository.save(t);
	}

	@Override
	public Mono<Boolean> delete(String t) {
		return bootCoinRepository.findById(t)
		        .flatMap(dc -> bootCoinRepository.delete(dc).then(Mono.just(Boolean.TRUE)))
		        .defaultIfEmpty(Boolean.FALSE);
	}

	@Override
	public Mono<BootCoin> findByPhoneNumber(Integer i) {
		// TODO Auto-generated method stub
		return bootCoinRepository.findByPhoneNumber(i);
	}

}
