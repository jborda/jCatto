package br.com.jmassucatto.filecontrol.client.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.jmassucatto.filecontrol.server.comando.ComandoTipo;

public class TCPClient {
	
	public List<String> getArquivos() {
		List<String> arquivos = new ArrayList<String>();
		
		try {
			Socket cliente = new Socket("localhost", 6789);
			DataOutputStream saida = new DataOutputStream(cliente.getOutputStream());
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			
			fazRequisicaoGetArquivos(saida);
			arquivos = obtemListaArquivos(entrada);
			
			cliente.close();
			return arquivos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arquivos;
	}

	public String getArquivo(String nomeArquivoOrigem, FileOutputStream arquivoDestino) {

		try {
			Socket cliente = new Socket("localhost", 6789);
			DataOutputStream saida = new DataOutputStream(cliente.getOutputStream());
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			
			fazRequisicaoGetArquivo(saida, nomeArquivoOrigem);
			
			BufferedWriter destino = new BufferedWriter(new OutputStreamWriter(arquivoDestino));
			obtemArquivo(entrada, destino);
			
			cliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private void fazRequisicaoGetArquivos(DataOutputStream saida) throws IOException {
		saida.writeBytes(ComandoTipo.GET_ARQUIVOS.name() + '\n');
	}
	
	private void fazRequisicaoGetArquivo(DataOutputStream saida, String nomeArquivo) throws IOException {
		saida.writeBytes(ComandoTipo.GET_ARQUIVO.name() + '\n' + nomeArquivo + '\n');
	}

	private void obtemArquivo(BufferedReader entrada, BufferedWriter destino) throws IOException {
		String linha;
		while ((linha = entrada.readLine()) != null)
			destino.write(linha);
		destino.flush();
		destino.close();
	}

	private List<String> obtemListaArquivos(BufferedReader entrada) throws IOException {
		String retorno = entrada.readLine();
		return convertParaList(retorno);
	}

	private List<String> convertParaList(String retorno) {
		retorno = retorno.substring(1, retorno.length() - 1);
		String[] arrayNomes = retorno.split(", ");
		return Arrays.asList(arrayNomes);
	}
}