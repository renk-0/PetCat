package cotapro.dev;

import android.graphics.Color;
import android.media.MediaPlayer;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class Pantalla implements Runnable {
	MainActivity activity;
	GifImageView gato;
	GifDrawable drawable;
	boolean transicionando = false;
	int current_color;
	int gato_sprite;

	public Pantalla(MainActivity activity) {
		this.activity = activity;
		current_color = Color.WHITE;
		gato = activity.findViewById(R.id.gato);
		drawable = (GifDrawable) gato.getDrawable();
		gato_sprite = R.drawable.parpadeo;
	}

	public void ronroneo() {
		transicionando = true;
		gato_sprite = R.drawable.touch;
		//current_color = Color.GREEN;
	}

	public void enojo() {
		transicionando = true;
		gato_sprite = R.drawable.mentira;
		//current_color = Color.BLUE;
	}

	public void mareo() {
		transicionando = true;
		gato_sprite = R.drawable.agitar;
		//current_color = Color.RED;
	}
	public void esconder(){
		transicionando = true;
		gato_sprite = R.drawable.proxim;
	}
	public void luz(){
		transicionando = true;
		gato_sprite = R.drawable.luz;
	}

	private void transicionar() {
		gato.setImageResource(gato_sprite);
		drawable = (GifDrawable) gato.getDrawable();
		transicionando = false;
	}

	@Override
	public void run() {
		if(!transicionando) return;
		transicionar();
		//activity.getWindow().getDecorView().setBackgroundColor(current_color);
	}
}
