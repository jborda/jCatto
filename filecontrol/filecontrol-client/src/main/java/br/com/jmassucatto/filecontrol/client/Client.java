package br.com.jmassucatto.filecontrol.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.jmassucatto.filecontrol.server.Server;

public class Client {

	public void getArquivos() throws Exception {
		Server server = new Server();
		for (String nomeArquivo : server.getNomeArquivos())
			baixa(server.getArquivo(nomeArquivo));
	}

	private void baixa(File arquivoOrigem) throws Exception {
		FileInputStream origem = new FileInputStream(arquivoOrigem);
		String arquivoDestino = "/tmp/filecontrol/client/" + arquivoOrigem.getName();
		FileOutputStream destino = new FileOutputStream(arquivoDestino );
		
		baixa(origem, destino);
		
	}

	private void baixa(FileInputStream origem, FileOutputStream destino) throws IOException {
		byte[] buffer = new byte[1024];
		int tamanho;
		while((tamanho = origem.read(buffer)) > 0)
			destino.write(buffer, 0, tamanho);
		
		origem.close();
		destino.close();
		
	}
	
	public static void main(String[] args) throws Exception {
		new Client().getArquivos();
	}
}
