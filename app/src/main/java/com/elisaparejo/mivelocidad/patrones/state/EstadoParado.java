package com.elisaparejo.mivelocidad.patrones.state;

public class EstadoParado implements Estado {
    private static final double VEL_MIN = 0;
    private static final double VEL_MAX = 1;
    
    @Override
    public String getNombre() {
        return "PARADO 🚶";
    }
    
    @Override
    public String getColor() {
        return "#FF0000";
    }
    
    @Override
    public double getVelocidadMinima() {
        return VEL_MIN;
    }
    
    @Override
    public double getVelocidadMaxima() {
        return VEL_MAX;
    }
    
    @Override
    public Estado procesarVelocidad(double velocidad, long tiempoEnEstado) {
        if (velocidad > VEL_MAX) {
            return new EstadoCaminando();
        }
        return this;
    }
    
    @Override
    public void alEntrar() {
        System.out.println("🛑 Estado: PARADO");
    }
    
    @Override
    public void alSalir() {
        System.out.println("👟 Saliendo de PARADO");
    }
}