package com.elisaparejo.mivelocidad.patrones.strategy;

public interface EstrategiaFiltro {
    double[] filtrar(double[] datos);
    String getNombre();
}