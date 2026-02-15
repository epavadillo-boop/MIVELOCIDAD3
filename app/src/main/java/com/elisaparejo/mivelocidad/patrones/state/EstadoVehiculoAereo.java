package com.elisaparejo.mivelocidad.patrones.state;

public class EstadoVehiculoAereo implements Estado {
    private static final double VEL_MIN = 170;
    
    @Override
    public String getNombre() {
        return "VEHÍCULO AÉREO ✈️";
    }
    
    @Override
    public String getColor() {
        return "#808080";
    }
    
    @Override
    public double getVelocidadMinima() {
        return VEL_MIN;
    }
    
    @Override
    public double getVelocidadMaxima() {
        return Double.MAX_VALUE;
    }
    
    @Override
    public Estado procesarVelocidad(double velocidad, long tiempoEnEstado) {
        if (velocidad < VEL_MIN) {
            return new EstadoVehiculoTerrestre();
        }
        return this;
    }
    
    @Override
    public void alEntrar() {
        System.out.println("✈️ Estado: VEHÍCULO AÉREO");
    }
    
    @Override
    public void alSalir() {
        System.out.println("⬇️ Saliendo de VEHÍCULO AÉREO");
    }
}