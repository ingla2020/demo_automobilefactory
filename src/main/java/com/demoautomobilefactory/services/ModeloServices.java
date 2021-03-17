package com.demoautomobilefactory.services;

import java.util.concurrent.ExecutionException;

import com.demoautomobilefactory.entity.Modelo;


public interface ModeloServices {
    Modelo add(Modelo modelo) throws InterruptedException, ExecutionException;
}
