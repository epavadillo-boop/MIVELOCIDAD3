package com.elisaparejo.mivelocidad;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorAndroid implements SensorEventListener {
    private SensorManager sensorManager;
    private android.hardware.Sensor accelerometer;
    private float[] ultimasLecturas = new float[3];
    private boolean activo = false;
    
    public SensorAndroid(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER);
    }
    
    public void iniciar() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        activo = true;
    }
    
    public void detener() {
        sensorManager.unregisterListener(this);
        activo = false;
    }
    
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == android.hardware.Sensor.TYPE_ACCELEROMETER) {
            ultimasLecturas[0] = event.values[0];
            ultimasLecturas[1] = event.values[1];
            ultimasLecturas[2] = event.values[2];
        }
    }
    
    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {
        // No necesario
    }
    
    public double leerEjeX() { return ultimasLecturas[0]; }
    public double leerEjeY() { return ultimasLecturas[1]; }
    public double leerEjeZ() { return ultimasLecturas[2]; }
    public boolean estaDisponible() { return activo && accelerometer != null; }
    public void calibrar() { ultimasLecturas = new float[3]; }
}