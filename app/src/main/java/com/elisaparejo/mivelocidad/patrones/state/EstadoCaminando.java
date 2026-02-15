package com.elisaparejo.mivelocidad.patrones.state;

public class EstadoCaminando implements Estado {
    private static final double VEL_MIN = 1;
    private static final double VEL_MAX = 4;
    private static final long BANDA_MUERTA_SUBIR = 1000; // 1 segundo
    private static final long BANDA_MUERTA_BAJAR = 500;  // 0.5 segundos
    private static final double HISTERESIS = 0.3;

    @Override
    public String getNombre() {
        return "CAMINANDO 🚶";
    }

    @Override
    public String getColor() {
        return "#FFFF00";
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
        // A CAMINANDO → PARADO (con banda muerta)
        if (velocidad < VEL_MIN - HISTERESIS) {
            if (tiempoEnEstado >= BANDA_MUERTA_BAJAR) {
                return new EstadoParado();
            }
        }

        // A CAMINANDO → MARCHANDO (con banda muerta)
        if (velocidad > VEL_MAX + HISTERESIS) {
            if (tiempoEnEstado >= BANDA_MUERTA_SUBIR) {
                return new EstadoMarchando();
            }
        }

        return this;
    }

    @Override
    public void alEntrar() {
        System.out.println("🚶 Estado: CAMINANDO");
    }

    @Override
    public void alSalir() {
        System.out.println("🏃 Saliendo de CAMINANDO");
    }
}