package cotapro.dev;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class SensorProximidad {
	Sensor sensor;
	SensorEventListener listener;

	public SensorProximidad(SensorManager manager, AppCompatActivity activity) {
		sensor = manager.getDefaultSensor(sensor.TYPE_PROXIMITY);
		if (sensor == null) {
			activity.finish();
			throw new RuntimeException("Error al conseguir el sensor");
		}
		listener = new SensorEventListener() {
			@Override
			public void onSensorChanged(SensorEvent event) {
				if (event.values[0] < sensor.getMaximumRange()) {
					activity.getWindow().getDecorView().setBackgroundColor(Color.BLACK);
					//Hola
				} else {
					activity.getWindow().getDecorView().setBackgroundColor(Color.WHITE);
				}
			}
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {}
		};
	}
	//hola carl jsjs

//HOla soy denzel xd
	//hola carl jsjs
  
	public void start(SensorManager manager) {
		if(!manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)) {
			Log.d("Mal", "Muy mal");
		}
	}

	public void stop(SensorManager manager) {
		manager.unregisterListener(listener);
	}
}
