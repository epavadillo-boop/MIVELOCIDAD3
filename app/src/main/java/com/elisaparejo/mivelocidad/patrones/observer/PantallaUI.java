package com.elisaparejo.mivelocidad.patrones.observer;

import com.elisaparejo.mivelocidad.patrones.state.Estado;

public class PantallaUI implements Observador {
    private String nombre;

    public PantallaUI(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(String evento, Object dato) {
        if (evento.equals("VELOCIDAD")) {
            double velocidad = (double) dato;
            System.out.println("🖥️ [" + nombre + "] Velocidad: " + velocidad + " km/h");
        } else if (evento.equals("CAMBIO_ESTADO")) {
            Estado estado = (Estado) dato;
            System.out.println("🖥️ [" + nombre + "] Estado: " + estado.getNombre());
        } else if (evento.equals("INICIO")) {
            System.out.println("🖥️ [" + nombre + "] Sensor iniciado");
        } else if (evento.equals("FIN")) {
            System.out.println("🖥️ [" + nombre + "] Sensor detenido");
        }
    }
}