package cotapro.dev;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class SensorLuz {
    Sensor sensor;
    SensorEventListener listener;
    MainActivity activity;
    SensorManager manager;
    float lumenes = 0;

    public SensorLuz(MainActivity activity) {
        this.activity = activity;
        this.manager = activity.sensorManager;
        sensor = manager.getDefaultSensor(sensor.TYPE_LIGHT);
        if(sensor == null) {
            activity.finish();
            throw new RuntimeException("Error al conseguir el sensor");
        }
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                lumenes = event.values[0];
			}
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) { }
        };
    }

    public boolean run() {
        if(lumenes > 200) {
            activity.screen.current_color = Color.GREEN;
            activity.runOnUiThread(activity.screen);
            return false;
        }
        return true;
    }

    public void reg() { manager.registerListener(listener, sensor, 2000*1000); }

    public void unreg() {
        manager.unregisterListener(listener);
    }
}
