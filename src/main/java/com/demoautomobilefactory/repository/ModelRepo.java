package com.demoautomobilefactory.repository;

import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;

import com.demoautomobilefactory.entity.Modelo;

public interface ModelRepo extends FirestoreReactiveRepository<Modelo>{

}
