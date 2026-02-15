package com.elisaparejo.mivelocidad;

import android.content.Context;
import com.elisaparejo.mivelocidad.patrones.observer.Observable;
import com.elisaparejo.mivelocidad.patrones.state.Estado;
import com.elisaparejo.mivelocidad.patrones.state.EstadoParado;
import com.elisaparejo.mivelocidad.patrones.strategy.EstrategiaFiltro;
import com.elisaparejo.mivelocidad.patrones.strategy.FiltroKalman;

public class MIVELOCIDAD extends Observable {
    private Estado estadoActual;
    private SensorAndroid sensor;  // ← DIRECTO, sin interfaces ni fábricas
    private EstrategiaFiltro filtro;
    private double velocidad = 0;
    private long tiempoEnEstado = 0;
    
    public MIVELOCIDAD(Context context) {
        this.sensor = new SensorAndroid(context);  // ← DIRECTO
        this.filtro = new FiltroKalman();
        this.estadoActual = new EstadoParado();
        this.estadoActual.alEntrar();
    }
    
    public void iniciar() {
        sensor.iniciar();
        notificarObservadores("INICIO", "Sensor listo");
    }
    
    public void detener() {
        sensor.detener();
        notificarObservadores("FIN", "Sensor detenido");
    }
    
    public void actualizar() {
        double[] datosCrudos = {
            sensor.leerEjeX(),
            sensor.leerEjeY(),
            sensor.leerEjeZ()
        };
        
        double[] datosFiltrados = filtro.filtrar(datosCrudos);
        
        double aceleracion = Math.sqrt(
            datosFiltrados[0] * datosFiltrados[0] +
            datosFiltrados[1] * datosFiltrados[1] +
            datosFiltrados[2] * datosFiltrados[2]
        );
        
        aceleracion = Math.abs(aceleracion - 9.81);
        velocidad = velocidad + (aceleracion * 0.1);
        velocidad = Math.max(0, velocidad);
        
        double velocidadKmh = velocidad * 3.6;
        notificarObservadores("VELOCIDAD", velocidadKmh);
        
        tiempoEnEstado += 100;
        Estado nuevoEstado = estadoActual.procesarVelocidad(velocidadKmh, tiempoEnEstado);
        
        if (nuevoEstado != estadoActual) {
            estadoActual.alSalir();
            estadoActual = nuevoEstado;
            estadoActual.alEntrar();
            tiempoEnEstado = 0;
            notificarObservadores("CAMBIO_ESTADO", estadoActual);
        }
    }
    
    public void cambiarFiltro(EstrategiaFiltro nuevoFiltro) {
        this.filtro = nuevoFiltro;
    }
}