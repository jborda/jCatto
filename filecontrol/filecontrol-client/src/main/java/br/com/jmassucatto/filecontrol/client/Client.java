package br.com.jmassucatto.filecontrol.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import br.com.jmassucatto.filecontrol.client.tcp.TCPClient;
import br.com.jmassucatto.filecontrol.common.FileUtils;
import br.com.jmassucatto.filecontrol.server.Server;

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

	public void getArquivos() throws Exception {
		Server server = new Server();
		for (String nomeArquivo : server.getNomeArquivos())
			baixa(server.getArquivo(nomeArquivo));
	}

	private void baixa(File arquivoOrigem) throws Exception {
		FileInputStream origem = new FileInputStream(arquivoOrigem);
		String arquivoDestino = "/tmp/filecontrol/client/" + arquivoOrigem.getName();
		FileOutputStream destino = new FileOutputStream(arquivoDestino);

		FileUtils.copy(origem, destino);
	}

	public static void main(String[] args) throws Exception {
		new Client().sincroniza();
	}
}
