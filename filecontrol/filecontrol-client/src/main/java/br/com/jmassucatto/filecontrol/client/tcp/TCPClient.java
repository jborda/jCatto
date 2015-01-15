package br.com.jmassucatto.filecontrol.client.tcp;

import java.io.FileOutputStream;
import java.util.List;

import br.com.jmassucatto.filecontrol.client.tcp.comando.ComandoTCPGetArquivo;
import br.com.jmassucatto.filecontrol.client.tcp.comando.ComandoTCPGetArquivos;

public class TCPClient {
	
	public List<String> getArquivos() {
		ComandoTCPGetArquivos comando = new ComandoTCPGetArquivos();
		comando.executa();
		return comando.getArquivos();
	}

	public void buscaArquivo(String nomeArquivoOrigem, FileOutputStream arquivoDestino) {
		ComandoTCPGetArquivo comando = new ComandoTCPGetArquivo(nomeArquivoOrigem, arquivoDestino);
		comando.executa();
	}

}