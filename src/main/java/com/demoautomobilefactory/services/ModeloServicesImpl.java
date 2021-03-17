package com.demoautomobilefactory.services;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoautomobilefactory.entity.Modelo;
import com.demoautomobilefactory.repository.ModeloRepository;

@Service
public class ModeloServicesImpl implements ModeloServices{

	@Autowired
	public ModeloRepository modeloRepository;

	@Override
	public Modelo add(Modelo modelo) throws InterruptedException, ExecutionException {
		return modeloRepository.add(modelo);
	}

	
	
}
