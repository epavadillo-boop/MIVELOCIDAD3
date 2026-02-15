package com.elisaparejo.mivelocidad.patrones.state;

public class EstadoCorriendo implements Estado {
    private static final double VEL_MIN = 6;
    private static final double VEL_MAX = 12;
    private static final long BANDA_MUERTA_SPRINT = 1500;
    private static final long BANDA_MUERTA_MARCHANDO = 500;
    private static final double HISTERESIS = 0.5;

    @Override
    public String getNombre() {
        return "CORRIENDO 🏃";
    }

    @Override
    public String getColor() {
        return "#00FF00";
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
        if (velocidad > VEL_MAX + HISTERESIS) {
            if (tiempoEnEstado >= BANDA_MUERTA_SPRINT) {
                return new EstadoSprint();
            }
        }

        if (velocidad < VEL_MIN - HISTERESIS) {
            if (tiempoEnEstado >= BANDA_MUERTA_MARCHANDO) {
                return new EstadoMarchando();
            }
        }

        return this;
    }

    @Override
    public void alEntrar() {
        System.out.println("🏃 Estado: CORRIENDO");
    }

    @Override
    public void alSalir() {
        System.out.println("💨 Saliendo de CORRIENDO");
    }
}