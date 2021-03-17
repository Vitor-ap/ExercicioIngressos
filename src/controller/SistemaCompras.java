package controller;

import java.util.concurrent.Semaphore;

public class SistemaCompras extends Thread{
	private int idThread;
	private boolean validacao;
	private static int totalIngressos = 100;
	private Semaphore semaforo;
	private int quantidade;
	
	public SistemaCompras(int i, Semaphore semaforo, int quantidade) {
		this.idThread = i;
		this.semaforo = semaforo;
		this.quantidade = quantidade;
		
	}

	@Override
	public void run() {
		loginSistema();
		if (validacao) {	
			processoCompra();
		}
		if (validacao){
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
	
	
		
	private void validacaoCompra() {
		totalIngressos -= quantidade;
		if (totalIngressos >= 0) {
		System.out.println("#"+idThread+":  comprou "+quantidade+" ingressos e sobraram "+ totalIngressos +" ingressos." );
	}
		else {
		System.err.println("Não tinha ingressos suficientes para o que a #"+idThread+" queria, então não fez a compra");
		}
		
}
		
		
	
	private void processoCompra() {
		idThread++;
		double tempoCompra = Math.random() * (3 - 1) + 1;
		try {
			sleep((long) tempoCompra);
			if (tempoCompra > 2.5) {
				validacao = false;
				System.err.println("#"+idThread+" : timeout - ultrapassou o tempo de seção, não poderá realizar a compra");
			}
			else{
				validacao = true;
				System.out.println("#"+idThread+": Está comprando");
			}
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}

	private void loginSistema() {
		double tempoLogin = Math.random() * (2 - 0.05) + 0.05;
		try {
			sleep((long) tempoLogin);
		if (tempoLogin > 1) {
			validacao = false;
			System.err.println("#"+idThread+": tempo de login excedido, não poderá fazer a compra");
		}
		else {
			validacao = true;
			System.out.println("#"+idThread+": Login realizado com êxito.");
		}
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	
	
	
	
			
}


	