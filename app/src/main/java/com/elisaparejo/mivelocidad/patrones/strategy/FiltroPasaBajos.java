package com.elisaparejo.mivelocidad.patrones.strategy;

public class FiltroPasaBajos implements EstrategiaFiltro {
    private double alpha = 0.8;
    private double valorAnterior = 0;
    
    public FiltroPasaBajos() {
        // Constructor sin parámetros
    }
    
    public FiltroPasaBajos(double alpha) {
        this.alpha = alpha;
    }
    
    @Override
    public double[] filtrar(double[] datos) {
        double[] resultado = new double[datos.length];
        
        for (int i = 0; i < datos.length; i++) {
            if (i == 0) {
                resultado[i] = datos[i];
                valorAnterior = datos[i];
            } else {
                resultado[i] = alpha * valorAnterior + (1 - alpha) * datos[i];
                valorAnterior = resultado[i];
            }
        }
        return resultado;
    }
    
    @Override
    public String getNombre() {
        return "Filtro Pasa Bajos (α=" + alpha + ")";
    }
}