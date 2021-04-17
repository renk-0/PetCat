package cotapro.dev;

import android.graphics.Color;

public class Pantalla implements Runnable {
	MainActivity activity;
	int current_color;

	public Pantalla(MainActivity activity) {
		this.activity = activity;
		current_color = Color.WHITE;
	}

	@Override
	public void run() {
		activity.getWindow().getDecorView().setBackgroundColor(current_color);
	}

}
