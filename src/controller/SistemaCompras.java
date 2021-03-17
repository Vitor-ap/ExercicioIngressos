package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SistemaCompras extends Thread{
	private int idThread;
	private boolean validacao;
	private int totalIngressos = 100;
	private Semaphore semaforo;
	
	public SistemaCompras(int i, Semaphore semaforo) {
		this.idThread = i;
		this.semaforo = semaforo;
		
		
	}

	@Override
	public void run() {
		loginSistema();
		if (validacao = true) {	
			processoCompra();
				if (validacao = true){
					try {
						semaforo.acquire();
						validacaoCompra();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						semaforo.release();
						}
					}
					
				}
		}
	
		
	private void validacaoCompra() {
		int qtdComprada = new Random().nextInt(5);
		totalIngressos -=  qtdComprada;
		if (totalIngressos >= 0) {
		System.out.println("#"+idThread+":  comprou "+qtdComprada+" ingressos e sobraram"+ totalIngressos +" ingressos." );
	}
		else {
		System.out.println("Não tinha ingressos suficientes para o que a #"+idThread+" queria, então não fez a compra");
		}
}
		
		
	
	private void processoCompra() {
		idThread++;
		double tempoCompra = Math.random() * (3 - 1) + 1;
			if (tempoCompra > 2.5) {
				validacao = false;
				System.out.println("#"+idThread+" : timeout - ultrapassou o tempo de seção, não poderá realizar a compra");
			}
			else{
				validacao = true;
				System.out.println("Processo de compra : "+tempoCompra+" segundos");
			}
			try {
				sleep((long) tempoCompra);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}

	private void loginSistema() {
		double tempoLogin = Math.random() * (2 - 0.05) + 0.05;
		if (tempoLogin > 1) {
			validacao = false;
			System.out.println("tempo de login excedido, não poderá fazer a compra");
		}
		else {
			validacao = true;
			System.out.println("Tempo de login de " +tempoLogin+" segundos.");
		}
			try {
				sleep((long) tempoLogin);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	
	
	
	
			
}


	