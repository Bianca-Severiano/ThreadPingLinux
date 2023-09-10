package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThreadPing extends Thread {

	private String nome;
	private String endereco;

	public ThreadPing(String nome) {
		this.nome = nome;
	}

	public void run() {
		ping();
	}

	private void ping() {
		endereco = "www." + nome + ".com.br";
		String c = "ping -4 -c 10 " + endereco;
		try {
			Process processo = Runtime.getRuntime().exec(c);
			InputStream fluxo = processo.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();

			while (linha != null) {
				if (linha.contains("time=")) {
					String[] l = linha.split("time=");
					System.out.println(nome + ": " + l[1]);

				}

				try {
					sleep(100);
					if (linha.contains("rtt ")) {
						String[] l = linha.split("/");
						System.err.println("Tempo medio " + nome + ":" + l[4] + " ms");
					}
				} catch (Exception e) {

				}

				linha = buffer.readLine();
			}

			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (Exception e) {
			String msg = e.getMessage();
			System.err.println(msg);
		}
	}
}
