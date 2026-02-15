package com.elisaparejo.mivelocidad.patrones.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador obs) {
        observadores.add(obs);
    }

    public void eliminarObservador(Observador obs) {
        observadores.remove(obs);
    }

    public void notificarObservadores(String evento, Object dato) {
        for (Observador obs : observadores) {
            obs.actualizar(evento, dato);
        }
    }
}