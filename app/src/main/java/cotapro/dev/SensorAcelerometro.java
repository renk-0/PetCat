package cotapro.dev;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SensorAcelerometro {
	Sensor sensor;
	SensorEventListener listener;
	float maxVel =  28.5f;
	boolean active = false;


	public SensorAcelerometro(SensorManager manager, AppCompatActivity activity) {
		sensor = manager.getDefaultSensor(sensor.TYPE_ACCELEROMETER);
		if(sensor == null) {
			activity.finish();
			throw new RuntimeException("Error al conseguir el sensor de acelerometro");
		}
		listener = new SensorEventListener() {
			@Override
			public void onSensorChanged(SensorEvent event) {
				float factor = event.values[0] +
						event.values[1] +
						event.values[2];
				if(factor > maxVel) {
					boolean active = true;
					activity.getWindow().getDecorView().setBackgroundColor(Color.RED);
				} else {
					boolean active = false;
					activity.getWindow().getDecorView().setBackgroundColor(Color.WHITE);
				}
			}
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {

			}
		};
	}
	public void start(SensorManager manager) {
		manager.registerListener(listener, sensor, 2000*1000);
	}

	public void stop(SensorManager manager) {
		manager.unregisterListener(listener);
	}
}
