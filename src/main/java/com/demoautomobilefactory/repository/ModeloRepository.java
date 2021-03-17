package com.demoautomobilefactory.repository;

import java.util.concurrent.ExecutionException;


import com.demoautomobilefactory.entity.Modelo;


public interface ModeloRepository {

	Modelo add(Modelo modelo) throws InterruptedException, ExecutionException;
}
