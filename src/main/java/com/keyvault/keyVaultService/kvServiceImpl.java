package com.keyvault.keyVaultService;

import org.springframework.stereotype.Component;

import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.azure.security.keyvault.secrets.models.SecretProperties;

public class kvServiceImpl {
	
	SecretClient secretClient;
	/**
	 * The default credential first checks environment variables for configuration.
	 * If environment configuration is incomplete, it will try managed identity.
	 */
	public void createDefaultAzureCredential() {
	    DefaultAzureCredential defaultCredential = new DefaultAzureCredentialBuilder().build();

	    // Azure SDK client builders accept the credential as a parameter
	    SecretClient client = new SecretClientBuilder()
	        .vaultUrl("https://triagetestkeyvault.vault.azure.net/")
	        .credential(defaultCredential)
	        .buildClient();
	    
	    secretClient = client;
	}
	
	public void getSecret(String secretName) {
		
		KeyVaultSecret secret = secretClient.getSecret(secretName);
		System.out.printf("Retrieved secret with name \"%s\" and value \"%s\"%n", secret.getName(), secret.getValue());
		
	}
	
	public void setSecret(String secretName, String secretValue) {
		
		KeyVaultSecret secret = secretClient.setSecret(secretName,secretValue);
		System.out.printf("Secret created with name \"%s\" and value \"%s\"%n", secret.getName(), secret.getValue());
		
	}
	
	public void listAllSecrets() {
		// List operations don't return the secrets with value information. So, for each returned secret we call getSecret to
		// get the secret with its value information.
		for (SecretProperties secretProperties : secretClient.listPropertiesOfSecrets()) {
		    KeyVaultSecret secretWithValue = secretClient.getSecret(secretProperties.getName(), secretProperties.getVersion());
		    System.out.printf("Retrieved secret with name \"%s\" and value \"%s\"%n", secretWithValue.getName(),
		        secretWithValue.getValue());
		}
		
	}
	
		
}
