package br.com.jmassucatto.filecontrol.client;

import java.io.FileOutputStream;
import java.util.List;

import br.com.jmassucatto.filecontrol.client.tcp.TCPClient;

public class Client {

	public void sincroniza() throws Exception {
		System.out.println("Client:sincroniza");
		TCPClient tcpClient = new TCPClient();
		List<String> nomesArquivos = tcpClient.getArquivos();
		for (String nomeArquivo : nomesArquivos)
			tcpClient.getArquivo(nomeArquivo, getDestino(nomeArquivo));
	}

	private FileOutputStream getDestino(String nomeArquivo) throws Exception {
		String destino = "/tmp/filecontrol/client/" + nomeArquivo;
		return new FileOutputStream(destino);
	}

	public static void main(String[] args) throws Exception {
		new Client().sincroniza();
	}
}
