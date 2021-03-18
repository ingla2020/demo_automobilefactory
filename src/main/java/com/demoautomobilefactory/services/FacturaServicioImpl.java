package com.demoautomobilefactory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoautomobilefactory.entity.Factura;
import com.demoautomobilefactory.entity.FacturaResul;
import com.demoautomobilefactory.entity.Garantia;
import com.demoautomobilefactory.entity.Modelo;
import com.demoautomobilefactory.repository.FacturaRepo;
import com.demoautomobilefactory.repository.GarantiaRepo;
import com.demoautomobilefactory.repository.ModelRepo;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import reactor.core.publisher.Mono;

@Service
public class FacturaServicioImpl implements FacturaServicio{

	@Autowired
	public FacturaRepo facturaRepo;
	
	@Autowired 
	public GarantiaRepo garantiaRepo;
	
	@Autowired
	public ModelRepo modelRepo;
	
	@Autowired
	private Firestore firestore;
	

	ApiFuture<WriteResult> writeResult;
	
	
	@Autowired
	FacturaServicio facturaServicio;
	
	public FacturaServicioImpl(FacturaRepo facturaRepo, GarantiaRepo garantiaRepo, ModelRepo modelRepo,
			Firestore firestore) {
		this.facturaRepo = facturaRepo;
		this.garantiaRepo = garantiaRepo;
		this.modelRepo = modelRepo;
		this.firestore = firestore;
		this.writeResult = writeResult;
	}


	public void setFacturaServicio(FacturaServicio facturaServicio) {
		this.facturaServicio = facturaServicio;
	}

	public void setWriteResult(ApiFuture<WriteResult> writeResult) {
		this.writeResult = writeResult;
	}


	@Override
	public Mono<FacturaResul> add(Factura model) throws Exception  {
		
		Modelo modelo =  modelRepo.findById(model.getIdmodelo()).block();
		
		if (modelo == null)
			throw new Exception("No Found Modelo");
		
		Garantia garantia = garantiaRepo.findById(model.getIdgarantia()).block();
		
		if (garantia == null)
			throw new Exception("No Found Garantia");		
		

		if (Integer.parseInt(model.getPrecio()) >= Integer.parseInt(modelo.getPrecio1()) 
					&& Integer.parseInt(model.getPrecio()) <= Integer.parseInt(modelo.getPrecio2()))

			return facturaRepo.save(model)
							.map(map -> {
								System.out.println("paso1");
								return new FacturaResul(map, modelo, garantia);
							});
		else
			throw new Exception("Monto fuera de rango");		
			
	
	}

	@Override
	public Mono<FacturaResul> get(String id) throws Exception {
		
		Factura factura = facturaRepo.findById(id).block();

		Modelo modelo =  modelRepo.findById(factura.getIdmodelo()).block();
		
		Garantia garantia = garantiaRepo.findById(factura.getIdgarantia()).block();		
		
		
		
		if (factura == null)
			throw new Exception("Error en factura");
		else
			return Mono.just(factura).map(map -> {
				return new FacturaResul(map, modelo, garantia);
			});
	}

	@Override
	public void delete(String id) throws Exception {

		facturaServicio.get(id);
		
		writeResult = firestore.collection("Factura").document(id).delete();
		
	}

	@Override
	public Mono<List<FacturaResul>> getAll() {

		return facturaRepo.findAll().collectList().map(map -> {
			
			List<FacturaResul> list = new ArrayList<>();
			for (Factura st : map)
			{
				list.add(new FacturaResul(st, modelRepo.findById(st.getIdmodelo()).block(), garantiaRepo.findById(st.getIdgarantia()).block()));
			}
			return list;
		});
		
		
	}	
	
}
