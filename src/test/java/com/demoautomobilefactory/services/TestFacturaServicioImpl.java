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

public class TestFacturaServicioImpl {

	public FacturaRepo facturaRepo;
	
	public GarantiaRepo garantiaRepo;
	
	public ModelRepo modelRepo;
	
	private Firestore firestore;
	
    private DocumentReference documentReference;
    
	
	ApiFuture<WriteResult> writeResult;	

	FacturaServicioImpl facturaServicioImpl;
	
	FacturaServicio facturaServicio;
	
    @BeforeEach
    private void beforeEach() {
    	
    	facturaRepo = Mockito.mock(FacturaRepo.class);
    	garantiaRepo = Mockito.mock(GarantiaRepo.class);
    	modelRepo = Mockito.mock(ModelRepo.class);		
    	firestore = Mockito.mock(Firestore.class);
    	writeResult = Mockito.mock(ApiFuture.class);
    	facturaServicio = Mockito.mock(FacturaServicio.class);
    	
        documentReference = Mockito.mock(DocumentReference.class);

    	facturaServicioImpl = new FacturaServicioImpl(facturaRepo, garantiaRepo, modelRepo, firestore);
    	
    	facturaServicioImpl.setWriteResult(writeResult);
    	facturaServicioImpl.setFacturaServicio(facturaServicio);
    	
    }

    
    @Test
    public void Testadd() throws Exception {
    	
    	Factura factura = new Factura();
    	factura.setApellido("T");
    	factura.setIdfactura("1");
    	factura.setIdgarantia("1");
    	factura.setIdmodelo("1");
    	factura.setNombre("T");
    	factura.setPrecio("1");
    	
    	Modelo model = new Modelo();
    	model.setDescripcion("T");
    	model.setFecha("T");
    	model.setIdmodelo("1");
    	model.setPrecio1("1");
    	model.setPrecio2("2");
    	
    	Garantia garantia = new Garantia();
    	garantia.setDescripcion("T");
    	garantia.setIdgarantia("1");
    	
    	Mono<Modelo> monomodelo = Mono.just(model);

    	Mono<Garantia> monogarantia = Mono.just(garantia);

    	Mono<Factura> monofactura = Mono.just(factura);
    	
    	FacturaResul facr = new FacturaResul(factura, model, garantia);
    	
    	Mono<FacturaResul> monofacr = Mono.just(facr);
    	
    	Mockito.doReturn(monomodelo).when(modelRepo).findById("1");
    	
    	Mockito.doReturn(monogarantia).when(garantiaRepo).findById("1");
    	
    	Mockito.doReturn(monofactura).when(facturaRepo).save(factura);
    	
    	Mono<FacturaResul> flujo = facturaServicioImpl.add(factura);
    	
    	
        StepVerifier.create(flujo)
        .consumeNextWith(
                response -> assertEquals("1", response.getPrecio()))
        .verifyComplete();
    	
    	
    }
    
    
    @Test
    public void Testget() throws Exception {
    	
    	Factura factura = new Factura();
    	factura.setApellido("T");
    	factura.setIdfactura("1");
    	factura.setIdgarantia("1");
    	factura.setIdmodelo("1");
    	factura.setNombre("T");
    	factura.setPrecio("1");
    	
    	Modelo model = new Modelo();
    	model.setDescripcion("T");
    	model.setFecha("T");
    	model.setIdmodelo("1");
    	model.setPrecio1("1");
    	model.setPrecio2("2");
    	
    	Garantia garantia = new Garantia();
    	garantia.setDescripcion("T");
    	garantia.setIdgarantia("1");
    	
    	Mono<Modelo> monomodelo = Mono.just(model);

    	Mono<Garantia> monogarantia = Mono.just(garantia);

    	Mono<Factura> monofactura = Mono.just(factura);    	
    	
    	Mockito.doReturn(monomodelo).when(modelRepo).findById("1");
    	
    	Mockito.doReturn(monogarantia).when(garantiaRepo).findById("1");
    	
    	Mockito.doReturn(monofactura).when(facturaRepo).findById("1");    	
    	
    	Mono<FacturaResul> flujo = facturaServicioImpl.get("1");
    	
        StepVerifier.create(flujo)
        .consumeNextWith(
                response -> assertEquals("1", response.getPrecio()))
        .verifyComplete();
    }
    
    
    @Test
    public void Testdelete() throws Exception {
    
    	Factura factura = new Factura();
    	factura.setApellido("T");
    	factura.setIdfactura("1");
    	factura.setIdgarantia("1");
    	factura.setIdmodelo("1");
    	factura.setNombre("T");
    	factura.setPrecio("1");    	
    	
    	Mono<Factura> monofactura = Mono.just(factura);     	
    	
        CollectionReference collectionReference =
                Mockito.mock(CollectionReference.class);

        Mockito.doReturn(collectionReference).when(firestore)
                .collection("Factura");

        Mockito.doReturn(documentReference).when(collectionReference)
                .document("1");

        Mockito.doReturn(writeResult).when(documentReference).delete();
        
        Mockito.doReturn(monofactura).when(facturaServicio).get("1");
        
        facturaServicioImpl.delete("1");
        
        Mockito.verify(documentReference).delete();
    }    
    
    @Test
    public void TestgetAll() {
    	
    	Factura factura = new Factura();
    	factura.setApellido("T");
    	factura.setIdfactura("1");
    	factura.setIdgarantia("1");
    	factura.setIdmodelo("1");
    	factura.setNombre("T");
    	factura.setPrecio("1");
    	
    	Modelo model = new Modelo();
    	model.setDescripcion("T");
    	model.setFecha("T");
    	model.setIdmodelo("1");
    	model.setPrecio1("1");
    	model.setPrecio2("2");
    	
    	Garantia garantia = new Garantia();
    	garantia.setDescripcion("T");
    	garantia.setIdgarantia("1");    	
    	
    	
    	List<Factura> listfactura = new ArrayList<Factura>();
    	listfactura.add(factura);
    	
    	Flux<List<Factura>> monofactura = Flux.just(listfactura);
    	
    	
    	Mono<Modelo> monomodelo = Mono.just(model);

    	Mono<Garantia> monogarantia = Mono.just(garantia);
    	
    	Mockito.doReturn(monomodelo).when(modelRepo).findById("1");
    	
    	Mockito.doReturn(monogarantia).when(garantiaRepo).findById("1");
    	
    	Mockito.doReturn(monofactura).when(facturaRepo).findAll();
    	
    	modelRepo.findById("1");
    	
    	garantiaRepo.findById("1");
    	
    	facturaRepo.findAll();
    	
    	Mono<List<FacturaResul>> flujo = facturaServicioImpl.getAll();
    	
    	
        Exception exception = assertThrows(RuntimeException.class, () -> {
        	flujo.block();
        });
    	
        
        String expectedMessage = "com.demoautomobilefactory.entity.Factura";
        String actualMessage = exception.getMessage();

        
        assertTrue(actualMessage.contains(expectedMessage));  
    	
    }
    
}
