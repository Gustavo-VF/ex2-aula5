package view;
import java.util.concurrent.Semaphore;

import controller.cozinha;
public class cozinheiro {

	public static void main(String[] args) {
		 Semaphore semaforo = new Semaphore(1);
		for (int i = 1; i < 6; i++) {
			Thread cz = new cozinha(i,semaforo);
			cz.start();

		}

	}

}
