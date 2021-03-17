package com.demoautomobilefactory.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

@Configuration
public class ConfigFirestore {

	
    @Value("${spring.cloud.gcp.firestore.project-id}")
    public String fire;

    @Bean
    public Firestore firestore() throws IOException {

    	FileInputStream serviceAccount =
    			  new FileInputStream("src/main/resources/fir-testing-626a8-firebase-adminsdk-p84d2-2a61ce69b9.json");
    	
        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId(fire)
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();
        return firestoreOptions.getService();
        
        
        
        
        
    }	
	
}
