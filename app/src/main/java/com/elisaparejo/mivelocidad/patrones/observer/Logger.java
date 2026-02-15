package com.elisaparejo.mivelocidad.patrones.observer;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Logger implements Observador {

    @Override
    public void actualizar(String evento, Object dato) {
        String timestamp = LocalDateTime.now().toString();
        String linea = timestamp + " | " + evento + " | " + dato;
        System.out.println("📝 LOG: " + linea);

        try (PrintWriter out = new PrintWriter(new FileWriter("/sdcard/mivelocidad_log.txt", true))) {
            out.println(linea);
        } catch (Exception e) {
            System.err.println("Error guardando log: " + e.getMessage());
        }
    }
}