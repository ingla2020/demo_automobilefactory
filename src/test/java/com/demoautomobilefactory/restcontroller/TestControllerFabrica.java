package com.demoautomobilefactory.restcontroller;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.http.ResponseEntity;

import com.demoautomobilefactory.entity.Modelo;
import com.demoautomobilefactory.services.FacturaServicio;
import com.demoautomobilefactory.services.GarantiaServices;
import com.demoautomobilefactory.services.ModeloServices;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class TestControllerFabrica {

	public ModeloServices modeloServices;
	
	public GarantiaServices garantiaServices;
	
	public FacturaServicio facturaServicio;	
	
	ControllerFabrica controllerFabrica;
	
    @BeforeEach
    private void beforeEach() {

    	modeloServices = Mockito.mock(ModeloServices.class);
    	garantiaServices = Mockito.mock(GarantiaServices.class);
    	facturaServicio = Mockito.mock(FacturaServicio.class);
    	
    	controllerFabrica = new ControllerFabrica(modeloServices, garantiaServices, facturaServicio);
    }
    
    @Test
    public void TestAddModelo() {
    	
    	Modelo modelo = new Modelo();
    	modelo.setDescripcion("T");
    	modelo.setFecha("T");
    	modelo.setIdmodelo("T");
    	modelo.setPrecio1("1");
    	modelo.setPrecio2("1");
    	
    	Mono<Modelo> monomodelo = Mono.just(modelo);
    	
    	Mockito.doReturn(monomodelo).when(modeloServices).add(modelo);
    	
    	Mono<?> flujo = controllerFabrica.AddModelo(modelo).getBody();
    	

    	
        StepVerifier.create(flujo)
        .consumeNextWith(
                response -> assertEquals(modelo, response))
        .verifyComplete();
    	
    }
    
   
    @Test
    public void TestModeloget() throws Exception {
    	Modelo modelo = new Modelo();
    	modelo.setDescripcion("T");
    	modelo.setFecha("T");
    	modelo.setIdmodelo("T");
    	modelo.setPrecio1("1");
    	modelo.setPrecio2("1");
    	
    	Mono<Modelo> monomodelo = Mono.just(modelo);
    	
    	Mockito.doReturn(monomodelo).when(modeloServices).get("1");
    	
    	Mono<?> flujo = controllerFabrica.Modeloget("1").getBody();
    	

    	
        StepVerifier.create(flujo)
        .consumeNextWith(
                response -> assertEquals(modelo, response))
        .verifyComplete();
    		
        
    	Mockito.doThrow(new Exception()).when(modeloServices).get("1");
        
    	

    	
        Exception exception = assertThrows(RuntimeException.class, () -> {
        	controllerFabrica.Modeloget("1");
        });
        
        
        String expectedMessage = "value";
        String actualMessage = exception.getMessage();

        
        assertTrue(actualMessage.contains(expectedMessage));        
        
    }    
    
    
    
    @Test
    public void TestModeloAll() {
    	Modelo modelo = new Modelo();
    	modelo.setDescripcion("T");
    	modelo.setFecha("T");
    	modelo.setIdmodelo("T");
    	modelo.setPrecio1("1");
    	modelo.setPrecio2("1");
    	
    	Mono<Modelo> monomodelo = Mono.just(modelo);
    	
    	Mockito.doReturn(monomodelo).when(modeloServices).getAll();
    	
    	Mono<?> flujo = controllerFabrica.ModeloAll().getBody();
    	

    	
        StepVerifier.create(flujo)
        .consumeNextWith(
                response -> assertEquals(modelo, response))
        .verifyComplete();   	
    }    
    
    
    @Test
    public void TestDeleteModelo() throws Exception {
    	Modelo modelo = new Modelo();
    	modelo.setDescripcion("T");
    	modelo.setFecha("T");
    	modelo.setIdmodelo("T");
    	modelo.setPrecio1("1");
    	modelo.setPrecio2("1");
    	
    	Mono<Modelo> monomodelo = Mono.just(modelo);
    	
    	Mockito.doNothing().when(modeloServices).delete("1");
    	
    	controllerFabrica.DeleteModelo("1");
    	
        Mockito.verify(modeloServices, Mockito.times(1)).delete("1");
    	
  	    /**/	
        
    	Mockito.doThrow(new Exception()).when(modeloServices).delete("1");
    	
        Exception exception = assertThrows(RuntimeException.class, () -> {
        	controllerFabrica.DeleteModelo("1");
        });
        
        
        String expectedMessage = "value";
        String actualMessage = exception.getMessage();

        
        assertTrue(actualMessage.contains(expectedMessage));            
        
    }    
    
}
