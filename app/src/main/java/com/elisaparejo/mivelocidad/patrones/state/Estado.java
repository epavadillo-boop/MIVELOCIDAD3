package com.elisaparejo.mivelocidad.patrones.state;

public interface Estado {
    String getNombre();
    String getColor();
    double getVelocidadMinima();
    double getVelocidadMaxima();
    Estado procesarVelocidad(double velocidad, long tiempoEnEstado);
    void alEntrar();
    void alSalir();
}