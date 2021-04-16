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
    public SensorLuz(SensorManager manager, AppCompatActivity activity)
    {
        sensor=manager.getDefaultSensor(sensor.TYPE_LIGHT);
        if(sensor==null)
        {
            activity.finish();
            throw new RuntimeException("Error al conseguir el sensor");
        }
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.values[0]<sensor.getMaximumRange()){
                    activity.getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }else
                {
                    activity.getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) { }
        };

    }
    public void start(SensorManager manager) {
        manager.registerListener(listener, sensor, 2000*1000);
    }

    public void stop(SensorManager manager) {
        manager.unregisterListener(listener);
    }
}
