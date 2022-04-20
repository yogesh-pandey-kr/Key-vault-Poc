package com.keyvault.keyvaultdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.keyvault.keyVaultService.kvServiceImpl;

@SpringBootApplication
public class KeyvaultdemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KeyvaultdemoApplication.class, args);
		
		kvServiceImpl kv = new kvServiceImpl();
		kv.createDefaultAzureCredential();
		
		kv.getSecret("mySecret");
	}

}
