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
			prato(0.6, 0.6); // manda os parametros pra fazer um random de 0.6 ate 1.2 faz um random de *0.6 ( numeros entre o.6 a 1.6) e soma 0.6 pra iniciar do 0,6
			entregar();

		} else {// sopaCebola
			prato(0.3, 0.5); //manda os parametros pra fazer um random de 0.5 ate 0.8 faz um random de *0.3 ( numeros entre o.5 a 0.8) e soma 0.6 ´pra iniciar do 0,5 
			entregar();
		}
	}

	private void prato(double a, double b) {
		double tempo = ((Math.random() * a) + b); // a = distancia do max e min do periodo de cozimento/ b = somar para iniciar do min de coximento 
		long tempoMili = (long) (tempo * 1000);   // transforma double pra long/ tem que or o tempo * 1000 tendtro de () pq se não trnasfora so o tempo e perde as  
		try {                                     // casas depos da virgula
			long tempoPercorrido = 0;
			while (tempoPercorrido < tempoMili) {                                 // enqianto tempo parcorrida menor que tempo total
				sleep(100);                                                       // espera 0.1seg pra atualizar a %  
				tempoPercorrido += 100;                                           // add 0.1seg no contador // o contador é o tempoPercorrido
				int tempocozimento = (int) ((tempoPercorrido * 100) / tempoMili); // calculo pra porcentagem // tem im (int) po o tenpope.. e o tempoMili é long
				if (tempocozimento > 100) {                                       //(informação sleep so usa long por isso fiz a conta em long e depois passei pra int
					tempocozimento = 100;  // if basico pra não mostrar a % 
				}                          // maior que 100 fica paia
				System.out.println("Prato-" + idPrato + " preparando..." + tempocozimento + "%"); // linha que mostra a msg no console com a % subindo a cada linha
			}
			System.out.println("Prato-" + idPrato + " Pronto"); // linha que mostra prato pronto no console

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
