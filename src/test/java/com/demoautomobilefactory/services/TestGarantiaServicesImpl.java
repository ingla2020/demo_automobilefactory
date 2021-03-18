package com.demoautomobilefactory.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.demoautomobilefactory.entity.Garantia;
import com.demoautomobilefactory.entity.Modelo;
import com.demoautomobilefactory.repository.FacturaRepo;
import com.demoautomobilefactory.repository.GarantiaRepo;
import com.demoautomobilefactory.repository.ModelRepo;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class TestGarantiaServicesImpl {

	
	public GarantiaRepo garantiaRepo;
	
	public ModelRepo modeloRepository;
	
	private Firestore firestore;
	
    private DocumentReference documentReference;
    
	
	ApiFuture<WriteResult> writeResult;	

	GarantiaServicesImpl garantiaServicesImpl;
	
	GarantiaServices garantiaServices;
	
    @BeforeEach
    private void beforeEach() {
    	
    	garantiaRepo = Mockito.mock(GarantiaRepo.class);		
    	firestore = Mockito.mock(Firestore.class);
    	writeResult = Mockito.mock(ApiFuture.class);
    	garantiaServices = Mockito.mock(GarantiaServices.class);
    	
        documentReference = Mockito.mock(DocumentReference.class);

        garantiaServicesImpl = new GarantiaServicesImpl(garantiaRepo, firestore);
    	
        garantiaServicesImpl.setFuturewrite(writeResult);
        garantiaServicesImpl.setGarantiaServices(garantiaServices);
    	
    }

    
    @Test
    public void Testadd() throws Exception {
    	
    	Garantia garantia = new Garantia();
    	garantia.setDescripcion("T");
    	garantia.setIdgarantia("1");
    	
    	
    	Mono<Garantia> monomodelo = Mono.just(garantia);

    	Mockito.doReturn(monomodelo).when(garantiaRepo).save(garantia);
    	
    	Mono<Garantia> flujo = garantiaServicesImpl.add(garantia);
    	
    	
        StepVerifier.create(flujo)
        .consumeNextWith(
                response -> assertEquals("1", response.getIdgarantia()))
        .verifyComplete();
    	
    	
    }
    
    
    @Test
    public void Testget() throws Exception {
    	
    	
    	Garantia garantia = new Garantia();
    	garantia.setDescripcion("T");
    	garantia.setIdgarantia("1");
    	
    	
    	Mono<Garantia> monomodelo = Mono.just(garantia);

	
    	
    	Mockito.doReturn(monomodelo).when(garantiaRepo).findById("1");
    	
    	
    	Mono<Garantia> flujo = garantiaServicesImpl.get("1");
    	
        StepVerifier.create(flujo)
        .consumeNextWith(
                response -> assertEquals("1", response.getIdgarantia()))
        .verifyComplete();
    }
    
    
    @Test
    public void Testdelete() throws Exception {
    
    	Garantia garantia = new Garantia();
    	garantia.setDescripcion("T");
    	garantia.setIdgarantia("1");   
    	
    	Mono<Garantia> monomodelo = Mono.just(garantia);
    	
        CollectionReference collectionReference =
                Mockito.mock(CollectionReference.class);

        Mockito.doReturn(collectionReference).when(firestore)
                .collection("Garantia");

        Mockito.doReturn(documentReference).when(collectionReference)
                .document("1");

        Mockito.doReturn(writeResult).when(documentReference).delete();
        
        Mockito.doReturn(monomodelo).when(garantiaServices).get("1");
        
//        modeloServices.get("1");
        
        //documentReference.delete();
        
        garantiaServicesImpl.delete("1");
        
        Mockito.verify(documentReference).delete();
        
        Mockito.verify(garantiaServices).get("1");
    }    
    
    @Test
    public void TestgetAll() {

    	
    	Garantia model = new Garantia();
    	model.setDescripcion("T");
    	model.setIdgarantia("1"); 
    	
    	List<Garantia> listmodelo = new ArrayList<Garantia>();
    	listmodelo.add(model);
    	
    	Flux<List<Garantia>> monofactura = Flux.just(listmodelo);
    	
    	
    	Mono<Garantia> monomodelo = Mono.just(model);


    	
    	Mockito.doReturn(monofactura).when(garantiaRepo).findAll();
    	

    	garantiaRepo.findAll();
    	

    	
    	Mono<List<Garantia>> flujo = garantiaServicesImpl.getAll();
    	
    	
        StepVerifier.create(flujo)
        .consumeNextWith(
                response -> assertEquals(1, response.size()))
        .verifyComplete();
    	
    }	
	
}
