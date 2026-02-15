package com.elisaparejo.mivelocidad.patrones.state;

public class EstadoMarchando implements Estado {
    private static final double VEL_MIN = 4;
    private static final double VEL_MAX = 6;
    private static final long BANDA_MUERTA_SUBIR = 1000;
    private static final long BANDA_MUERTA_BAJAR = 500;
    private static final double HISTERESIS = 0.3;

    @Override
    public String getNombre() {
        return "MARCHANDO 🚶‍♂️";
    }

    @Override
    public String getColor() {
        return "#ADFF2F";
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
                return new EstadoCaminando();
            }
        }

        if (velocidad > VEL_MAX + HISTERESIS) {
            if (tiempoEnEstado >= BANDA_MUERTA_SUBIR) {
                return new EstadoCorriendo();
            }
        }

        return this;
    }

    @Override
    public void alEntrar() {
        System.out.println("🚶‍♂️ Estado: MARCHANDO");
    }

    @Override
    public void alSalir() {
        System.out.println("🏃 Saliendo de MARCHANDO");
    }
}