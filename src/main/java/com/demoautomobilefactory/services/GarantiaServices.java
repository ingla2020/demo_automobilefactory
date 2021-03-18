package com.demoautomobilefactory.services;

import java.util.List;

import com.demoautomobilefactory.entity.Garantia;


import reactor.core.publisher.Mono;

public interface GarantiaServices {
    Mono<Garantia> add(Garantia model) ;
    Mono<Garantia> get(String id) throws Exception;
	void delete(String id) throws Exception;
	Mono<List<Garantia>> getAll();
}
