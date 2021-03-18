package com.demoautomobilefactory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoautomobilefactory.entity.Garantia;
import com.demoautomobilefactory.repository.GarantiaRepo;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import reactor.core.publisher.Mono;

@Service
public class GarantiaServicesImpl implements GarantiaServices{
	
	@Autowired
	public GarantiaRepo garantiaRepo;
	
	@Autowired
	private Firestore firestore;	

	@Autowired
	GarantiaServices garantiaServices;

    private ApiFuture<WriteResult> futurewrite;


	public GarantiaServicesImpl(GarantiaRepo garantiaRepo, Firestore firestore) {
		this.garantiaRepo = garantiaRepo;
		this.firestore = firestore;
	}

	
	public void setGarantiaServices(GarantiaServices garantiaServices) {
		this.garantiaServices = garantiaServices;
	}


	public void setFuturewrite(ApiFuture<WriteResult> futurewrite) {
		this.futurewrite = futurewrite;
	}


	@Override
	public Mono<Garantia> add(Garantia model)  {
		return garantiaRepo.save(model);
	}

	@Override
	public Mono<Garantia> get(String id) throws Exception {
		
		Garantia garantia = garantiaRepo.findById(id).block();
		
		if (garantia == null)
			throw new Exception("Error en Garantia");
		else
			return Mono.just(garantia);
		
	}

	@Override
	public void delete(String id) throws Exception {
		
		garantiaServices.get(id);
		
		futurewrite = firestore.collection("Garantia").document(id).delete();
	}

	@Override
	public Mono<List<Garantia>> getAll() {
		return garantiaRepo.findAll().collectList();
	}
}
