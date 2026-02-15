package com.elisaparejo.mivelocidad.patrones.observer;

public interface Observador {
    void actualizar(String evento, Object dato);
}