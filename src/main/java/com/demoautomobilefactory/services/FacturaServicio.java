package com.demoautomobilefactory.services;

import java.util.List;

import com.demoautomobilefactory.entity.Factura;
import com.demoautomobilefactory.entity.FacturaResul;

import reactor.core.publisher.Mono;

public interface FacturaServicio {
	Mono<FacturaResul> add(Factura model) throws Exception;
	Mono<FacturaResul> get(String id) throws Exception;
	void delete(String id) throws Exception;
	Mono<List<FacturaResul>> getAll();
}
