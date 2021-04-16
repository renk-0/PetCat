package cotapro.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    SensorProximidad proximidad;
    SensorAcelerometro acelerometro;
    SensorLuz luz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximidad = new SensorProximidad(sensorManager, this);
        acelerometro = new SensorAcelerometro(sensorManager, this);
        luz= new SensorLuz(sensorManager, this);
        luz.start(sensorManager);
        proximidad.start(sensorManager);
        acelerometro.start(sensorManager);
    }

    @Override
    protected void onPause() {
        proximidad.stop(sensorManager);
        acelerometro.stop(sensorManager);
        luz.stop(sensorManager);
        super.onPause();
    }

    @Override
    protected void onResume() {
        proximidad.start(sensorManager);
        acelerometro.start(sensorManager);
        luz.start(sensorManager);
        super.onResume();
    }
}