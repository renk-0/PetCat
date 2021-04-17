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
    SensorLuz luminancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = new Pantalla(this);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorThread = new Thread(this::running);
        acelerometro = new SensorAcelerometro(this);
        luminancia = new SensorLuz(this);
        runOnUiThread(screen);
        acelerometro.reg();
        luminancia.reg();
        sensorThread.start();
    }

    public void running() {
        while (true) {
            if(!acelerometro.run()) continue;
            if(!luminancia.run()) continue;
        }
    }

    @Override
    protected void onPause() {
        acelerometro.unreg();
        luminancia.unreg();
        super.onPause();
    }

    @Override
    protected void onResume() {
        acelerometro.reg();
        luminancia.reg();
        super.onResume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                screen.current_color = Color.GREEN;
                runOnUiThread(screen);
                break;

            case MotionEvent.ACTION_UP:
                screen.current_color = Color.BLUE;
                runOnUiThread(screen);
                break;
        }
        return true;
    }
}
