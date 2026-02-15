package com.elisaparejo.mivelocidad;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.elisaparejo.mivelocidad.patrones.observer.Observador;
import com.elisaparejo.mivelocidad.patrones.state.Estado;

public class MainActivity extends AppCompatActivity {
    
    private MIVELOCIDAD sensor;
    private TextView textoEstado;
    private TextView textoVelocidad;
    private Button botonReiniciar;
    private Handler handler = new Handler();
    private Runnable runnable;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textoEstado = findViewById(R.id.textoEstado);
        textoVelocidad = findViewById(R.id.textoVelocidad);
        botonReiniciar = findViewById(R.id.botonReiniciar);
        
        inicializarSensor();
        
        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarSensor();
            }
        });
    }
    
    private void inicializarSensor() {
        sensor = new MIVELOCIDAD(this);  // ← AHORA PASA EL CONTEXTO
        
        Observador ui = new Observador() {
            @Override
            public void actualizar(String evento, Object dato) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (evento.equals("VELOCIDAD")) {
                            double v = (double) dato;
                            textoVelocidad.setText(String.format("%.2f km/h", v));
                        } else if (evento.equals("CAMBIO_ESTADO")) {
                            Estado e = (Estado) dato;
                            textoEstado.setText(e.getNombre());
                            try {
                                int color = Color.parseColor(e.getColor());
                                textoEstado.setBackgroundColor(color);
                            } catch (Exception ex) {
                                textoEstado.setBackgroundColor(Color.RED);
                            }
                        }
                    }
                });
            }
        };
        
        sensor.agregarObservador(ui);
        sensor.iniciar();
        
        runnable = new Runnable() {
            @Override
            public void run() {
                sensor.actualizar();
                handler.postDelayed(this, 100);
            }
        };
        handler.post(runnable);
    }
    
    private void reiniciarSensor() {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
        if (sensor != null) {
            sensor.detener();
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inicializarSensor();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
        if (sensor != null) {
            sensor.detener();
        }
    }
}