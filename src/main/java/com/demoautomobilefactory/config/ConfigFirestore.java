package com.demoautomobilefactory.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Configuration
public class ConfigFirestore {

	
    @Value("${spring.cloud.gcp.firestore.project-id}")
    public String fire;

    @Bean
    public Firestore firestore() throws IOException {

    	
        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId(fire)
                        .build();
        return firestoreOptions.getService();
        

    	
/*    	
    	// Use a service account
    	InputStream serviceAccount = new FileInputStream("src/main/resources/fir-testing-626a8-firebase-adminsdk-p84d2-2a61ce69b9.json");
    	GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
    	FirebaseOptions options = new FirebaseOptions.Builder()
    	    .setCredentials(credentials)
    	    .build();
    	FirebaseApp.initializeApp(options);

    	Firestore db = FirestoreClient.getFirestore();    	
----	
    	FileInputStream serviceAccount =
    			  new FileInputStream("src/main/resources/fir-testing-626a8-firebase-adminsdk-p84d2-2a61ce69b9.json");

    			FirebaseOptions options = new FirebaseOptions.Builder()
    			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
    			  .setDatabaseUrl("https://fir-testing-626a8.firebaseio.com")
    			  .build();

    			FirebaseApp.initializeApp(options);
    			
    			Firestore db = FirestoreClient.getFirestore();
    			
    			return db;
        */    
        
        
    }	
	
}
