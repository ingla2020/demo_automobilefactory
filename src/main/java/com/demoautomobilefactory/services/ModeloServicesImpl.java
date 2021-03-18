package com.demoautomobilefactory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoautomobilefactory.entity.Modelo;
import com.demoautomobilefactory.repository.ModelRepo;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import reactor.core.publisher.Mono;


@Service
public class ModeloServicesImpl implements ModeloServices{

	@Autowired
	public ModelRepo modeloRepository;
	
	@Autowired
	private Firestore firestore;

	@Override
	public Mono<Modelo> add(Modelo modelo)  {
		return modeloRepository.save(modelo);
	}

	@Override
	public Mono<Modelo> get(String id) throws Exception {
		
		Modelo modelo = modeloRepository.findById(id).block();
		
		if (modelo == null)
			throw new Exception("Error en Modelo");
		else
			return Mono.just(modelo);
	}

	@Override
	public void delete(String id) throws Exception {
		
		get(id);
		
		//modeloRepository.deleteById(id);
		
		ApiFuture<WriteResult> writeResult = firestore.collection("Modelo").document(id).delete();
		
	}

	@Override
	public Mono<List<Modelo>> getAll() {
		return modeloRepository.findAll().collectList();
	}

	
	
}
