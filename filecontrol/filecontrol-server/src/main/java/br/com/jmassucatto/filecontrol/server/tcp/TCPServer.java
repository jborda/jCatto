package br.com.jmassucatto.filecontrol.server.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import br.com.jmassucatto.filecontrol.common.FileUtils;
import br.com.jmassucatto.filecontrol.server.Server;

public class TCPServer {

	public static void main(String argv[]) throws Exception {
		new TCPServer().roda();
	}

	private void roda() throws Exception {	
		ServerSocket servidor = new ServerSocket(6789);

		while (true) {
			Socket conexaoCliente = servidor.accept();
			new Thread(new Conversa(conexaoCliente)).start();
		}
	}
	
	class Conversa implements Runnable {

		private BufferedReader entrada;
		private DataOutputStream saida;

		public Conversa(Socket conexaoCliente) throws IOException {
			entrada = new BufferedReader(new InputStreamReader(conexaoCliente.getInputStream()));
			saida = new DataOutputStream(conexaoCliente.getOutputStream());
		}

		public void run() {
			try {
				String requisicao = entrada.readLine();
				retorna(requisicao);
				entrada.close();
				saida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void retorna(String requisicao) throws IOException {
			if (requisicao.equals("getArquivos"))
				retornaListaArquivos();
			
			if (requisicao.startsWith("arquivo:"))
				retornaArquivo(requisicao);
		}

		private void retornaListaArquivos() throws IOException {
			List<String> arquivos = new Server().getNomeArquivos();
			saida.writeBytes(arquivos.toString());
		}
		
		private void retornaArquivo(String requisicao) throws IOException {
			String nomeArquivo = requisicao.split(":")[1];
			File arquivo = new Server().getArquivo(nomeArquivo);
			FileUtils.copy(new FileInputStream(arquivo), saida);
		}
	}
}