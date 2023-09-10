package view;

import controller.ThreadPing;

public class Principal {

	public static void main(String[] args) {

		String vet[] = new String[3];
		vet[0] = "uol";
		vet[1] = "google";
		vet[2] = "terra";

		for (int i = 0; i < vet.length; i++) {
			Thread t = new ThreadPing(vet[i]);
			t.start();
		}

	}

}
