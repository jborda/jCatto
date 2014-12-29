package br.com.jmassucatto.filecontrol.server.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import br.com.jmassucatto.filecontrol.server.comando.Comando;
import br.com.jmassucatto.filecontrol.server.comando.ComandoFactory;
import br.com.jmassucatto.filecontrol.server.comando.ComandoTipo;

public class Requisicao extends Thread {

	private BufferedReader entrada;
	private DataOutputStream saida;

	public Requisicao(Socket conexaoCliente) throws IOException {
		entrada = new BufferedReader(new InputStreamReader(
				conexaoCliente.getInputStream()));
		saida = new DataOutputStream(conexaoCliente.getOutputStream());
	}

	public void run() {
		try {
			executa();
			finaliza();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void executa() throws IOException {
		Comando comando = getComando(entrada.readLine());
		comando.executa(entrada, saida);
	}

	private void finaliza() throws IOException {
		entrada.close();
		saida.close();
	}

	private Comando getComando(String requisicao) {
		ComandoTipo tipo = ComandoTipo.valueOf(requisicao);
		return new ComandoFactory(tipo).getComando();
	}
}