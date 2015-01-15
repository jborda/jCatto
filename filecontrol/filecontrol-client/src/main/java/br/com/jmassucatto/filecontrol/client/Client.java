package br.com.jmassucatto.filecontrol.client;

import java.io.FileOutputStream;
import java.util.List;

import br.com.jmassucatto.filecontrol.client.tcp.TCPClient;

public class Client {

	TCPClient tcpClient;
	
	public Client() {
		tcpClient = new TCPClient();
	}

	public void sincroniza() throws Exception {
		System.out.println("Client:sincroniza");
		List<String> nomesArquivos = tcpClient.getArquivos();
		for (String nomeArquivo : nomesArquivos)
			tcpClient.buscaArquivo(nomeArquivo, getDestino(nomeArquivo));
	}

	FileOutputStream getDestino(String nomeArquivo) throws Exception {
		String destino = "/tmp/filecontrol/client/" + nomeArquivo;
		return new FileOutputStream(destino);
	}
	
}
