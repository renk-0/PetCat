package cotapro.dev;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class SensorProximidad {
	SensorManager manager;
	MainActivity activity;
	Sensor sensor;
	MediaPlayer mp;
	SensorEventListener listener;
	float distancia;

	public SensorProximidad(MainActivity activity) {
		this.activity = activity;
		this.manager = activity.sensorManager;
		sensor = manager.getDefaultSensor(sensor.TYPE_PROXIMITY);
		mp = MediaPlayer.create(activity, R.raw.gatoasustado);
		listener = new SensorEventListener() {
			@Override
			public void onSensorChanged(SensorEvent event) {
				distancia = event.values[0];
			}
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {}
			};
		}
	public boolean run(){
		if (distancia < sensor.getMaximumRange()) {
			activity.screen.current_color = Color.BLACK;
			activity.runOnUiThread(activity.screen);
			mp.start();
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
