package com.elisaparejo.mivelocidad.patrones.state;

public class EstadoSprint implements Estado {
    private static final double VEL_MIN = 12;
    private static final double VEL_MAX = 25;
    private static final long BANDA_MUERTA_CORRIENDO = 500;
    private static final double HISTERESIS = 0.5;

    @Override
    public String getNombre() {
        return "SPRINT 💨";
    }

    @Override
    public String getColor() {
        return "#FFA500";
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
            if (tiempoEnEstado >= BANDA_MUERTA_CORRIENDO) {
                return new EstadoCorriendo();
            }
        }

        if (velocidad > VEL_MAX) {
            return new EstadoVehiculoTerrestre();
        }

        return this;
    }

    @Override
    public void alEntrar() {
        System.out.println("💨 Estado: SPRINT");
    }

    @Override
    public void alSalir() {
        System.out.println("😮‍💨 Saliendo de SPRINT");
    }
}