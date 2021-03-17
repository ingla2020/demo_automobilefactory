package com.demoautomobilefactory.repository;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demoautomobilefactory.entity.Modelo;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

@Repository
public class ModeloRepositoryImpl implements ModeloRepository{

	@Autowired
	private Firestore firestore;

	@Override
	public Modelo add(Modelo modelo) throws InterruptedException, ExecutionException {
		// coleccion/idDocumento
		WriteResult writeResult = this.firestore.document("modelo/" + modelo.getIdmodelo()).set(modelo).get();
		System.out.println("Update time: " + writeResult.getUpdateTime());
		return modelo;
	}	
	
}
