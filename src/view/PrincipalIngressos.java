package view;

import java.util.concurrent.Semaphore;

import controller.SistemaCompras;

public class PrincipalIngressos {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo  = new Semaphore(permissoes);
		
		for (int i = 0; i <= 300; i++) {
			Thread tSisCompra = new SistemaCompras(i, semaforo);
			tSisCompra.start();

		}
	}
}


