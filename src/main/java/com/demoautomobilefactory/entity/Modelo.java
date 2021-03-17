package com.demoautomobilefactory.entity;

import org.springframework.cloud.gcp.data.firestore.Document;

import com.google.cloud.Date;
import com.google.cloud.firestore.annotation.DocumentId;

@Document(collectionName = "Modelo")
public class Modelo {

    @DocumentId
    private String idmodelo;
    
    private String descripcion;
    private String fecha;
    private Number precio1;
    private Number precio2;
    
    
	public String getIdmodelo() {
		return idmodelo;
	}
	public void setIdmodelo(String idmodelo) {
		this.idmodelo = idmodelo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Number getPrecio1() {
		return precio1;
	}
	public void setPrecio1(Number precio1) {
		this.precio1 = precio1;
	}
	public Number getPrecio2() {
		return precio2;
	}
	public void setPrecio2(Number precio2) {
		this.precio2 = precio2;
	}
	@Override
	public String toString() {
		return "Modelo [idmodelo=" + idmodelo + ", descripcion=" + descripcion + ", fecha=" + fecha + ", precio1="
				+ precio1 + ", precio2=" + precio2 + "]";
	}
    
    
    
    
    
    
    
	
}
