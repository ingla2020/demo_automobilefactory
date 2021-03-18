package com.demoautomobilefactory.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.demoautomobilefactory.entity.Factura;
import com.demoautomobilefactory.entity.FacturaResul;
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

public class TestModeloServicesImpl {
	
	public FacturaRepo facturaRepo;
	
	public GarantiaRepo garantiaRepo;
	
	public ModelRepo modeloRepository;
	
	private Firestore firestore;
	
    private DocumentReference documentReference;
    
	
	ApiFuture<WriteResult> writeResult;	

	ModeloServicesImpl modeloServicesImpl;
	
	ModeloServices modeloServices;
	
    @BeforeEach
    private void beforeEach() {
    	
    	modeloRepository = Mockito.mock(ModelRepo.class);		
    	firestore = Mockito.mock(Firestore.class);
    	writeResult = Mockito.mock(ApiFuture.class);
    	modeloServices = Mockito.mock(ModeloServices.class);
    	
        documentReference = Mockito.mock(DocumentReference.class);

        modeloServicesImpl = new ModeloServicesImpl(modeloRepository, firestore);
    	
        modeloServicesImpl.setWriteResult(writeResult);
        modeloServicesImpl.setModeloServices(modeloServices);
    	
    }

    
    @Test
    public void Testadd() throws Exception {
    	
    	Modelo model = new Modelo();
    	model.setDescripcion("T");
    	model.setFecha("T");
    	model.setIdmodelo("1");
    	model.setPrecio1("1");
    	model.setPrecio2("2");
    	
    	
    	Mono<Modelo> monomodelo = Mono.just(model);

    	Mockito.doReturn(monomodelo).when(modeloRepository).save(model);
    	
    	Mono<Modelo> flujo = modeloServicesImpl.add(model);
    	
    	
        StepVerifier.create(flujo)
        .consumeNextWith(
                response -> assertEquals("1", response.getIdmodelo()))
        .verifyComplete();
    	
    	
    }
    
    
    @Test
    public void Testget() throws Exception {
    	
    	
    	Modelo model = new Modelo();
    	model.setDescripcion("T");
    	model.setFecha("T");
    	model.setIdmodelo("1");
    	model.setPrecio1("1");
    	model.setPrecio2("2");
    	
    	
    	Mono<Modelo> monomodelo = Mono.just(model);

	
    	
    	Mockito.doReturn(monomodelo).when(modeloRepository).findById("1");
    	
    	
    	Mono<Modelo> flujo = modeloServicesImpl.get("1");
    	
        StepVerifier.create(flujo)
        .consumeNextWith(
                response -> assertEquals("1", response.getIdmodelo()))
        .verifyComplete();
    }
    
    
    @Test
    public void Testdelete() throws Exception {
    
    	Modelo model = new Modelo();
    	model.setDescripcion("T");
    	model.setFecha("T");
    	model.setIdmodelo("1");
    	model.setPrecio1("1");
    	model.setPrecio2("2");    
    	
    	Mono<Modelo> monomodelo = Mono.just(model);
    	
        CollectionReference collectionReference =
                Mockito.mock(CollectionReference.class);

        Mockito.doReturn(collectionReference).when(firestore)
                .collection("Modelo");

        Mockito.doReturn(documentReference).when(collectionReference)
                .document("1");

        Mockito.doReturn(writeResult).when(documentReference).delete();
        
        Mockito.doReturn(monomodelo).when(modeloServices).get("1");
        
//        modeloServices.get("1");
        
        //documentReference.delete();
        
        modeloServicesImpl.delete("1");
        
        Mockito.verify(documentReference).delete();
        
        Mockito.verify(modeloServices).get("1");
    }    
    
    @Test
    public void TestgetAll() {

    	
    	Modelo model = new Modelo();
    	model.setDescripcion("T");
    	model.setFecha("T");
    	model.setIdmodelo("1");
    	model.setPrecio1("1");
    	model.setPrecio2("2");
    	
    	List<Modelo> listmodelo = new ArrayList<Modelo>();
    	listmodelo.add(model);
    	
    	Flux<List<Modelo>> monofactura = Flux.just(listmodelo);
    	
    	
    	Mono<Modelo> monomodelo = Mono.just(model);


    	
    	Mockito.doReturn(monofactura).when(modeloRepository).findAll();
    	

    	modeloRepository.findAll();
    	

    	
    	Mono<List<Modelo>> flujo = modeloServicesImpl.getAll();
    	
    	
        StepVerifier.create(flujo)
        .consumeNextWith(
                response -> assertEquals(1, response.size()))
        .verifyComplete();
    	
    }
}
