package com.demoautomobilefactory.entity;

import org.springframework.cloud.gcp.data.firestore.Document;

import com.google.cloud.firestore.annotation.DocumentId;

@Document(collectionName = "Garantia")
public class Garantia {

    @DocumentId
    private String idgarantia;
    private String descripcion;
    
    
	public String getIdgarantia() {
		return idgarantia;
	}
	public void setIdgarantia(String idgarantia) {
		this.idgarantia = idgarantia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    
    
	
}
