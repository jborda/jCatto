package br.com.jmassucatto.filecontrol.client.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TCPClient {

	public static void main(String argv[]) throws Exception {
		System.out.println(new TCPClient().getArquivos());
		new TCPClient().getArquivo("teste1.txt");
	}
	
	public List<String> getArquivos() {
		List<String> arquivos = new ArrayList<String>();
		
		try {
			Socket cliente = new Socket("localhost", 6789);
			DataOutputStream saida = new DataOutputStream(cliente.getOutputStream());
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			
			fazRequisicao(saida, "getArquivos");
			arquivos = obtemListaArquivos(entrada);
			
			cliente.close();
			return arquivos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arquivos;
	}

	public String getArquivo(String nomeArquivo) {

		try {
			Socket cliente = new Socket("localhost", 6789);
			DataOutputStream saida = new DataOutputStream(cliente.getOutputStream());
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			BufferedWriter destino = new BufferedWriter(new OutputStreamWriter(System.out));
			
			fazRequisicao(saida, "arquivo:"+nomeArquivo);
			obtemArquivo(entrada, destino);
			
			cliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private void fazRequisicao(DataOutputStream saida, String fala) throws IOException {
		saida.writeBytes(fala + '\n');
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
		String[] arrayNomes = retorno.split(",");
		return Arrays.asList(arrayNomes);
	}
}