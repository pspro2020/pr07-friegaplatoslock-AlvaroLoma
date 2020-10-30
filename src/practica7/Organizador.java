package practica7;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Organizador implements Runnable {
	private Bandeja bandeja;

	public Organizador(Bandeja bandeja) {

		this.bandeja = bandeja;
	}

	@Override
	public void run() {
		while (true) {

			try {
				
				colocarPlato(organizarPlato(cogerPlatoSeco()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return;
			}

		}

	}

	private void colocarPlato(Plato organizarPlato) {
		bandeja.colocarAlacena(organizarPlato);
		
	}

	private Plato organizarPlato(Plato plato) throws InterruptedException {
		TimeUnit.SECONDS.sleep((new Random().nextInt(2) + 1));
	
		LocalTime hora = LocalTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
		System.out.printf("el hilo %s ha colocado el plato: %d en la alacena a las %s \n",
				this.getClass().getSimpleName(), plato.getNumSerie(), formato.format(hora));
		return plato;

	}

	private Plato cogerPlatoSeco() throws InterruptedException {
		return bandeja.cogerPlatoSeco();

	}

}
