package practica7;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Fregador implements Runnable {
	private Bandeja bandeja;
	

	public Fregador(Bandeja bandeja) {
		this.bandeja = bandeja;
	}

	@Override
	public void run() {

		while (true) {

			try {

				nuevoPlatoLimpio(fregar(cogerPlatoSucio()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return;
			}

		}

	}

	private Plato cogerPlatoSucio() throws InterruptedException {
		// TODO Auto-generated method stub
		return bandeja.cogerPlatoSucio();
	}

	private void nuevoPlatoLimpio(Plato plato) throws InterruptedException {
		bandeja.nuevoPlatoLimpio(plato);

	}

	private Plato fregar(Plato plato) throws InterruptedException {
		LocalTime hora = LocalTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
		TimeUnit.SECONDS.sleep((new Random().nextInt(8) + 4));
		System.out.printf("el hilo %s ha limpiado el plato: %d a las %s \n", this.getClass().getSimpleName(),
				plato.getNumSerie(), formato.format(hora));

		return plato;
	}

}
