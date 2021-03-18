package com.demoautomobilefactory.repository;

import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;

import com.demoautomobilefactory.entity.Factura;


public interface FacturaRepo extends FirestoreReactiveRepository<Factura>{

}
