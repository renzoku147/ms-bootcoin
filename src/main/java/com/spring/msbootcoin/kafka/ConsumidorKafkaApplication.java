package com.spring.msbootcoin.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.msbootcoin.entity.BootCoinTransfer;
import com.spring.msbootcoin.service.BootCoinService;

import reactor.core.publisher.Flux;

@Configuration
public class ConsumidorKafkaApplication {
	@Autowired
	BootCoinService bootCoinService;  
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Bean
    public NewTopic topic(){
        return TopicBuilder.name("topico-everis3")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @KafkaListener(id="myId", topics = "topico-everis10")
    public void listen(String message) throws Exception{
    	System.out.println(">>>>> topico-everis10 @KafkaListener <<<<<");
    	BootCoinTransfer bct = objectMapper.readValue(message, BootCoinTransfer.class);
    	System.out.println("buyer > " + bct.getBuyer().getBootCoin().getPhoneNumber());
    	System.out.println("seller > " + bct.getSeller().getId());
    	System.out.println("seller > " + bct.getSeller().getPhoneNumber());
    	
    	bootCoinService.findByPhoneNumber(bct.getBuyer().getBootCoin().getPhoneNumber())
    	 	.flatMap(buyer -> {
    	 		buyer.setBalance(buyer.getBalance() + bct.getBuyer().getAmount());
    	 		return bootCoinService.update(buyer)
	    	 			.flatMap(bu -> bootCoinService.findByPhoneNumber(bct.getSeller().getPhoneNumber())
		 							.flatMap(seller -> {
		 								seller.setBalance(seller.getBalance() - bct.getBuyer().getAmount());
		 				    	 		return bootCoinService.update(seller);
		 							})
	    	 					);
    	 	}).subscribe();
    	
    }
}
