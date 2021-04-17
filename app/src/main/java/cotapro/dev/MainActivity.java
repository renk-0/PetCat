package cotapro.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Thread sensorThread;
    SensorAcelerometro acelerometro;
    boolean ocupado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorThread = new Thread(this::running);
        acelerometro = new SensorAcelerometro(sensorManager, this);
        acelerometro.reg();
        sensorThread.start();
    }

    public void running() {
        while (true) {
            if(!acelerometro.run()) continue;
        }
    }

    @Override
    protected void onPause() {
        acelerometro.unreg();
        super.onPause();
    }

    @Override
    protected void onResume() {
        acelerometro.reg();
        super.onResume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                this.getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                break;

            case MotionEvent.ACTION_UP:
                this.getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                break;
        }
        return true;
    }
}
