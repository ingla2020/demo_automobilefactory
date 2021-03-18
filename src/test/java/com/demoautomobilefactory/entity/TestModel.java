package com.demoautomobilefactory.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestModel {

	@Test
	public void TestFactura() {
		Factura factura = new Factura();
		
		factura.setApellido("T");
		factura.setIdfactura("T");
		factura.setIdgarantia("T");
		factura.setIdmodelo("T");
		factura.setNombre("T");
		factura.setPrecio("T");
		
		assertEquals("T", factura.getApellido());
		assertEquals("T", factura.getIdfactura());
		assertEquals("T", factura.getIdgarantia());
		assertEquals("T", factura.getIdmodelo());
		assertEquals("T", factura.getNombre());
		assertEquals("T", factura.getPrecio()); 
	
		
		assertEquals("T", factura.getApellido());
		assertEquals("T", factura.getIdfactura());
		assertEquals("T", factura.getIdgarantia());
		assertEquals("T", factura.getIdmodelo());
		assertEquals("T", factura.getNombre());
		assertEquals("T", factura.getPrecio());		
		
		
	}
	
	
	@Test
	public void TestFacturaResul() {
		Modelo modelo = new Modelo(); 
		Garantia garantia = new Garantia();
		Factura factura = new Factura();
		factura.setApellido("T");
		
		FacturaResul facturaResul = new FacturaResul();
		facturaResul.setApellido("T");
		facturaResul.setDescripcionGarantia("T");
		facturaResul.setDescripcionModelo("T");
		facturaResul.setNombre("T");
		facturaResul.setPrecio("T");
		
		
		FacturaResul facturaResulConstruc = new FacturaResul(factura, modelo, garantia);
		
		
		assertEquals("T" , facturaResulConstruc.getApellido() );
		
		assertEquals("T", facturaResul.getApellido());
		assertEquals("T" ,facturaResul.getDescripcionGarantia() );
		assertEquals("T" , facturaResul.getDescripcionModelo());
		assertEquals("T" , facturaResul.getNombre());
		assertEquals("T" ,facturaResul.getPrecio());
	}
	
	
	@Test
	public void TestGarantia() {
		Garantia garantia = new Garantia();
		
		garantia.setDescripcion("T");
		garantia.setIdgarantia("T");
		
		assertEquals("T", garantia.getDescripcion());
		assertEquals("T", garantia.getIdgarantia());
	}
	
	
	@Test
	public void TestModelo() {
		Modelo modelo = new Modelo();
		
		modelo.setDescripcion("T");
		modelo.setFecha("T");
		modelo.setIdmodelo("T");
		modelo.setIdmodelo("T");
		modelo.setPrecio1("T");
		modelo.setPrecio2("T");
		

		assertEquals("T", modelo.getDescripcion());
		assertEquals("T", modelo.getFecha());
		assertEquals("T", modelo.getIdmodelo());
		assertEquals("T", modelo.getPrecio1());
		assertEquals("T", modelo.getPrecio2());

	}
	
}
