package com.demoautomobilefactory.repository;

import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;

import com.demoautomobilefactory.entity.Garantia;


public interface GarantiaRepo extends FirestoreReactiveRepository<Garantia> {

}
