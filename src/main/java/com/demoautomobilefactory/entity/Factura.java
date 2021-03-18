package com.demoautomobilefactory.entity;

import org.springframework.cloud.gcp.data.firestore.Document;

import com.google.cloud.firestore.annotation.DocumentId;

@Document(collectionName = "Factura")
public class Factura {

    @DocumentId
    private String idfactura;
    private String nombre;
    private String apellido;
    private String idmodelo;
    private String idgarantia;
    private String precio;
    
    
	public String getIdfactura() {
		return idfactura;
	}
	public void setIdfactura(String idfactura) {
		this.idfactura = idfactura;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getIdmodelo() {
		return idmodelo;
	}
	public void setIdmodelo(String idmodelo) {
		this.idmodelo = idmodelo;
	}
	public String getIdgarantia() {
		return idgarantia;
	}
	public void setIdgarantia(String idgarantia) {
		this.idgarantia = idgarantia;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
    
    
    
    
}
