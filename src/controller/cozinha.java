package controller;

import java.util.concurrent.Semaphore;

public class cozinha extends Thread {
	private int idPrato;
	private Semaphore semaforo = new Semaphore(1);

	public cozinha(int _idPrato, Semaphore _semaforo) {
		this.idPrato = _idPrato;
		this.semaforo = _semaforo;

	}

	public void run() {
		verificaPrato();
	}

	private void verificaPrato() {
		if ((idPrato % 2) == 0) {// lasanhaBolonhesa
			prato(0.6, 0.6);
			entregar();

		} else {// sopaCebola
			prato(0.3, 0.5);
			entregar();
		}
	}

	private void prato(double a, double b) {
		double tempo = ((Math.random() * a) + b);
		long tempoMili = (long) (tempo * 1000);
		try {
			long tempoPercorrido = 0;
			while (tempoPercorrido < tempoMili) {
				sleep(100);
				tempoPercorrido += 100;
				int tempocozimento = (int) ((tempoPercorrido * 100) / tempoMili);
				if (tempocozimento > 100) {
					tempocozimento = 100;
				}
				System.out.println("Prato-" + idPrato + " preparando..." + tempocozimento + "%");
			}
			System.out.println("Prato-" + idPrato + " Pronto");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void entregar() {
		try {
			semaforo.acquire();
			sleep(500);
			System.out.println("prato-" + idPrato + " entregue");

		} catch (Exception e) {
			String msg = e.getMessage();
			System.err.println(msg);

		} finally {
			semaforo.release();

		}

	}

}
