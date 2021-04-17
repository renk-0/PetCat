package cotapro.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    SensorProximidad proximidad;
    SensorAcelerometro acelerometro;
    SensorLuz luz;
    private boolean isTouch = false;

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
    public boolean onTouchEvent(MotionEvent event){
        int X = (int) event.getX();
        int Y = (int) event.getY();
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                this.getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                isTouch = true;
                break;

            case MotionEvent.ACTION_UP:
                this.getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                isTouch = false;
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        proximidad.start(sensorManager);
        acelerometro.start(sensorManager);
        luz.start(sensorManager);
        super.onResume();
    }
}
