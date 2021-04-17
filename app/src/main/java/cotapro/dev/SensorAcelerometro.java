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
	SensorManager manager;
	Sensor sensor;
	MainActivity activity;
	SensorEventListener listener;
	float maxVel =  28.5f;
	float velX, velY, velZ;

	public SensorAcelerometro(SensorManager manager, MainActivity activity) {
		this.activity = activity;
		this.manager = manager;
		sensor = manager.getDefaultSensor(sensor.TYPE_ACCELEROMETER);
		velX = velY = velZ = 0;
		listener = new SensorEventListener() {
			@Override
			public void onSensorChanged(SensorEvent event) {
				velX = event.values[0];
				velY = event.values[1];
				velZ = event.values[2];
			}
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {}
		};
	}

	public boolean run() {
		float factor = velX + velY + velZ;
		if(factor > maxVel) {
			activity.getWindow().getDecorView().setBackgroundColor(Color.RED);
			return false;
		}
		return true;
	}

	public void reg() {
		manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	public void unreg() {
		manager.unregisterListener(listener);
	}
}
