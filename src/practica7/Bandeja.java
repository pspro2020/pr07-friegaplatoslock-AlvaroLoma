package practica7;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Bandeja {
	ArrayList<Plato> platosSucios = new ArrayList<Plato>();
	ArrayList<Plato> platosLimpios = new ArrayList<Plato>();
	ArrayList<Plato> platosSecos = new ArrayList<Plato>();
	ArrayList<Plato> alacena = new ArrayList<Plato>();
	private int num = 1;
	ReentrantLock cerrojo = new ReentrantLock(true);
	Condition sinLlenar = cerrojo.newCondition();

	public Bandeja() {

		cargarPlatosSucios();
	}

	private void cargarPlatosSucios() {
		for (int i = 0; i < 10; i++) {
			platosSucios.add(new Plato(num++));
		}

	}

	public void nuevoPlatoLimpio(Plato plato) throws InterruptedException {
		cerrojo.lock();
		try {
			platosLimpios.add(plato);
			sinLlenar.signal();
		} finally {
			cerrojo.unlock();
		}

	}

	public Plato cogerPlatoLimpio() throws InterruptedException {
		cerrojo.lock();
		try {
			while (platosLimpios.isEmpty()) {
				sinLlenar.await();
			}
			return platosLimpios.remove(0);
		} finally {
			cerrojo.unlock();
		}

		
	}

	public void nuevoPlatoSeco(Plato platoSecado) throws InterruptedException {
		cerrojo.lock();
		try {
			platosSecos.add(platoSecado);
			sinLlenar.signal();
		} finally {
			cerrojo.unlock();
		}

	}

	public Plato cogerPlatoSeco() throws InterruptedException {
		cerrojo.lock();
		try {
			while (platosSecos.isEmpty()) {
				sinLlenar.await();
			}
			return platosSecos.remove(0);
		} finally {
			cerrojo.unlock();
		}

		
	}

	public Plato cogerPlatoSucio() throws InterruptedException {
		cerrojo.lock();
		try {
			while (platosSucios.isEmpty()) {
				sinLlenar.await();
			}
			return platosSucios.remove(0);
		} finally {
			cerrojo.unlock();
		}

		
	}

	public void colocarAlacena(Plato plato) {
		alacena.add(plato);

	}

}
