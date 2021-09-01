package com.spring.msbootcoin.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Document("BootCoin")
@AllArgsConstructor
@NoArgsConstructor
public class BootCoin {
	@Id
	private String id;
	
	@NotNull
	private String dni;
	
	@NotNull
	private Integer phoneNumber;
	
	@NotNull
	private String email;
	
	@NotNull
	private Double balance;
}
