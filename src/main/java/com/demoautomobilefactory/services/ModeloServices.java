package com.demoautomobilefactory.services;

import java.util.List;


import com.demoautomobilefactory.entity.Modelo;

import reactor.core.publisher.Mono;



public interface ModeloServices {
    Mono<Modelo> add(Modelo modelo) ;
    Mono<Modelo> get(String id) throws Exception;
	void delete(String id) throws Exception;
	Mono<List<Modelo>> getAll();
}
