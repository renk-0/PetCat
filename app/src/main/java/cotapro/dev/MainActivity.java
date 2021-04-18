package cotapro.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Thread sensorThread;
    Pantalla screen;
    SensorAcelerometro acelerometro;
    SensorProximidad proximidad;
    SensorLuz luminancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = new Pantalla(this);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorThread = new Thread(this::running);
        acelerometro = new SensorAcelerometro(this);
        proximidad = new SensorProximidad(this);
        proximidad.reg();
        luminancia = new SensorLuz(this);
        luminancia.reg();
        sensorThread.start();
    }

    public void running() {
        while (true) {
            if(!acelerometro.run()) continue;
            if(!proximidad.run()) continue;
            if(!luminancia.run()) continue;
        }
    }

    @Override
    protected void onPause() {
        proximidad.unreg();
        acelerometro.unreg();
        luminancia.unreg();
        super.onPause();
    }

    @Override
    protected void onResume() {
        proximidad.reg();
        acelerometro.reg();
        luminancia.reg();
        super.onResume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                screen.ronroneo();
                runOnUiThread(screen);
                break;

            case MotionEvent.ACTION_UP:
                screen.enojo();
                runOnUiThread(screen);
                break;
        }
        return true;
    }
}
