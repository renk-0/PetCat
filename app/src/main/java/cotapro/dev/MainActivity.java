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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = new Pantalla(this);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorThread = new Thread(this::running);
        acelerometro = new SensorAcelerometro(this);
        proximidad = new SensorProximidad(this);
        runOnUiThread(screen);
        acelerometro.reg();
        proximidad.reg();
        sensorThread.start();
    }

    public void running() {
        while (true) {
            if(!acelerometro.run()) continue;
            if(!proximidad.run()) continue;
        }

    }

    @Override
    protected void onPause() {
        proximidad.unreg();
        acelerometro.unreg();
        super.onPause();
    }

    @Override
    protected void onResume() {
        proximidad.reg();
        acelerometro.reg();
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
