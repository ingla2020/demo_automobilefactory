package com.demoautomobilefactory.restcontroller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoautomobilefactory.entity.Modelo;
import com.demoautomobilefactory.services.ModeloServices;


@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class ControllerFabrica {

	@Autowired
	public ModeloServices modeloServices;
	
    @GetMapping("test")
    public ResponseEntity<?> Test() throws InterruptedException, ExecutionException {

    	Modelo modelo = new Modelo();
    	modelo.setIdmodelo("1000");
    	modelo.setDescripcion("prueba");
    	modelo.setFecha("16/03/2021");
    	modelo.setPrecio1(25000);
    	modelo.setPrecio2(30000);
    	
    	modeloServices.add(modelo);
    	
            return new ResponseEntity<>("paso", HttpStatus.OK);
    }	
	
    
    @PostMapping("addmodelo")
    public String AddModelo(@RequestBody Modelo modelo) throws InterruptedException, ExecutionException {

            return modeloServices.add(modelo).toString();
    }	
	
}
