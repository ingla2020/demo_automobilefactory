package com.demoautomobilefactory.entity;

public class FacturaResul {

    private String nombre;
    private String apellido;
    private String DescripcionModelo;
    private String DescripcionGarantia;
    private String precio;
    
    
    
	public FacturaResul() {
	}
	
	public FacturaResul(Factura map, Modelo modelo, Garantia garantia) {
		this.nombre = map.getNombre();
		this.apellido = map.getApellido();
		this.precio = map.getPrecio();
		this.DescripcionModelo = modelo.getDescripcion();
		this.DescripcionGarantia = garantia.getDescripcion();
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
	public String getDescripcionModelo() {
		return DescripcionModelo;
	}
	public void setDescripcionModelo(String descripcionModelo) {
		DescripcionModelo = descripcionModelo;
	}
	public String getDescripcionGarantia() {
		return DescripcionGarantia;
	}
	public void setDescripcionGarantia(String descripcionGarantia) {
		DescripcionGarantia = descripcionGarantia;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
    
    
    
	
}
