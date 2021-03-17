package com.demoautomobilefactory.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

@Configuration
public class ConfigFirestore {

	
    @Value("${spring.cloud.gcp.firestore.project-id}")
    public String fire;

    @Bean
    public Firestore firestore() {
        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId(fire).build();
        return firestoreOptions.getService();
    }	
	
}
