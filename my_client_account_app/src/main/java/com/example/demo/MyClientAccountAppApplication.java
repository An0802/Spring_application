package com.example.demo;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.example.demo.model.Account;
import com.example.demo.repo.AccountRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootApplication
@EnableDiscoveryClient
public class MyClientAccountAppApplication{

	private final AccountRepository accountRepository;
	public static void main(String[] args) {
		SpringApplication.run(MyClientAccountAppApplication.class, args);
	}

	@PostConstruct
	public void init() throws Exception{
		List<Account> result = Stream.of(new Account(UUID.randomUUID().toString(),"Sachin","Mumbai","sachin@gmail.com"),
				new Account(UUID.randomUUID().toString(),"Rahul","Bangalore","rahul@gmail.com"),
				new Account(UUID.randomUUID().toString(),"Sourav","Kolkata","sourav@gmail.com")).collect(Collectors.toList());
		
		for (Account a: result) {
			accountRepository.createAccount(a);
		}
	}
}
