package com.elisaparejo.mivelocidad.patrones.state;

public class EstadoVehiculoTerrestre implements Estado {
    private static final double VEL_MIN = 25;
    private static final double VEL_MAX = 170;
    private static final long BANDA_MUERTA_BAJAR = 2000;
    private static final double HISTERESIS = 1.0;

    @Override
    public String getNombre() {
        return "VEHÍCULO TERRESTRE 🚗";
    }

    @Override
    public String getColor() {
        return "#800080";
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
        if (velocidad < VEL_MIN - HISTERESIS) {
            if (tiempoEnEstado >= BANDA_MUERTA_BAJAR) {
                return new EstadoSprint();
            }
        }

        if (velocidad > VEL_MAX) {
            return new EstadoVehiculoAereo();
        }

        return this;
    }

    @Override
    public void alEntrar() {
        System.out.println("🚗 Estado: VEHÍCULO TERRESTRE");
    }

    @Override
    public void alSalir() {
        System.out.println("✈️ Saliendo de VEHÍCULO TERRESTRE");
    }
}