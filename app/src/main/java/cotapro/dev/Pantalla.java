package cotapro.dev;

import android.graphics.Color;

import pl.droidsonroids.gif.GifImageView;

public class Pantalla implements Runnable {
	MainActivity activity;
	GifImageView gato;
	boolean transicionando = false;
	int current_color;

	public Pantalla(MainActivity activity) {
		this.activity = activity;
		current_color = Color.WHITE;
		gato = activity.findViewById(R.id.gato);
	}

	@Override
	public void run() {
		activity.getWindow().getDecorView().setBackgroundColor(current_color);
	}

}
