package com.demoautomobilefactory.restcontroller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoautomobilefactory.entity.Factura;
import com.demoautomobilefactory.entity.Garantia;
import com.demoautomobilefactory.entity.Modelo;
import com.demoautomobilefactory.services.FacturaServicio;
import com.demoautomobilefactory.services.GarantiaServices;
import com.demoautomobilefactory.services.ModeloServices;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class ControllerFabrica {

	@Autowired
	public ModeloServices modeloServices;
	
	@Autowired
	public GarantiaServices garantiaServices;
	
	@Autowired
	public FacturaServicio facturaServicio;
	
	
    public ControllerFabrica(ModeloServices modeloServices, GarantiaServices garantiaServices,
			FacturaServicio facturaServicio) {
		this.modeloServices = modeloServices;
		this.garantiaServices = garantiaServices;
		this.facturaServicio = facturaServicio;
	}

    
	@PostMapping("addmodelo")
    public ResponseEntity<Mono<?>> AddModelo(@RequestBody Modelo modelo) {
            return new ResponseEntity<>(modeloServices.add(modelo), HttpStatus.OK);
    }	
	
	@GetMapping("addmodelo/{id}")
    public ResponseEntity<Mono<?>> Modeloget(@PathVariable("id") String id)  {
		
				 try {
					return new ResponseEntity<>(modeloServices.get(id), HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<>(Mono.just(e.getMessage()), HttpStatus.NOT_FOUND);
				}
    }	

	@GetMapping("addmodelo")
    public ResponseEntity<Mono<?>> ModeloAll()  {
            return new ResponseEntity<>(modeloServices.getAll(), HttpStatus.OK);
    }		
	
	@DeleteMapping("delmodelo/{id}")
    public ResponseEntity<Mono<?>> DeleteModelo(@PathVariable("id") String id)  {

    	try {
    		modeloServices.delete(id);
        	return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
        	return new ResponseEntity<>(Mono.just(e.getMessage()), HttpStatus.NOT_FOUND);
		}
    }	
	
	/**/
    
    @PostMapping("addgarantia")
    public ResponseEntity<Mono<?>> AddGarantia(@RequestBody Garantia modelo) {
            return new ResponseEntity<>(garantiaServices.add(modelo), HttpStatus.OK);
    }	
	
    
	@GetMapping("addgarantia/{id}")
    public ResponseEntity<Mono<?>> GarantiaGet(@PathVariable("id") String id)  {
            try {
				return new ResponseEntity<>(garantiaServices.get(id), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(Mono.just(e.getMessage()), HttpStatus.OK);
			}
    }	
	
	@GetMapping("addgarantia")
    public ResponseEntity<Mono<?>> GarantiaAll()  {
            return new ResponseEntity<>(garantiaServices.getAll(), HttpStatus.OK);
    }		
	
    @DeleteMapping("delgarantia/{id}")
    public ResponseEntity<Mono<?>> DeleteGarantia(@PathVariable("id") String id)  {

    	try {
			garantiaServices.delete(id);
	    	return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

    }		
    
    /*factura*/
    
    @PostMapping("addfactura")
    public ResponseEntity<Mono<?>> AddFactura(@RequestBody Factura modelo) {
           
    	try {
				return new ResponseEntity<>(facturaServicio.add(modelo), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(Mono.just(e.getMessage()) , HttpStatus.NOT_FOUND);
			}
    }	
	
    
	@GetMapping("addfactura/{id}")
    public ResponseEntity<Mono<?>> FacturaGet(@PathVariable("id") String id)  {
            try {
				return new ResponseEntity<>(facturaServicio.get(id), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(Mono.just(e.getMessage()), HttpStatus.OK);
			}
    }	
	
	@GetMapping("addfactura")
    public ResponseEntity<Mono<?>> FacturaAll()  {
            return new ResponseEntity<>(facturaServicio.getAll(), HttpStatus.OK);
    }		
	
    @DeleteMapping("delfactura/{id}")
    public ResponseEntity<Mono<?>> DeleteFactura(@PathVariable("id") String id)  {
    	try {
			facturaServicio.delete(id);
	    	return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }		    
    
}
