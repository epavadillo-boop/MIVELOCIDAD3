package com.elisaparejo.mivelocidad.patrones.strategy;

public class FiltroKalman implements EstrategiaFiltro {
    private double Q = 0.01;
    private double R = 0.1;
    private double P = 1;
    private double K;
    private double X = 0;
    
    @Override
    public double[] filtrar(double[] datos) {
        double[] resultado = new double[datos.length];
        
        for (int i = 0; i < datos.length; i++) {
            P = P + Q;
            K = P / (P + R);
            X = X + K * (datos[i] - X);
            P = (1 - K) * P;
            resultado[i] = X;
        }
        return resultado;
    }
    
    @Override
    public String getNombre() {
        return "Filtro de Kalman";
    }
}