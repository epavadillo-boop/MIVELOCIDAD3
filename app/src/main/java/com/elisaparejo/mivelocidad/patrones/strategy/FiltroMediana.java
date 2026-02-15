package com.elisaparejo.mivelocidad.patrones.strategy;

import java.util.Arrays;

public class FiltroMediana implements EstrategiaFiltro {
    private int ventana = 3;
    
    @Override
    public double[] filtrar(double[] datos) {
        double[] resultado = new double[datos.length];
        
        for (int i = 0; i < datos.length; i++) {
            int inicio = Math.max(0, i - ventana/2);
            int fin = Math.min(datos.length, i + ventana/2 + 1);
            double[] ventanaValores = Arrays.copyOfRange(datos, inicio, fin);
            Arrays.sort(ventanaValores);
            resultado[i] = ventanaValores[ventanaValores.length / 2];
        }
        return resultado;
    }
    
    @Override
    public String getNombre() {
        return "Filtro de Mediana";
    }
}