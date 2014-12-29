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

import javax.swing.JOptionPane;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;
import br.com.jmassucatto.filecontrol.common.FileUtils;
import br.com.jmassucatto.filecontrol.server.Server;

public class TCPServer extends Thread {
	
	private boolean isExecutando = true;
	private ServerSocket servidor;
	
	public TCPServer() {
		try {
			servidor = new ServerSocket(6789);
		} catch (IOException e) {
			throw new FileControlException(Excecao.ERRO_AO_SUBIR_SERVIDOR);
		}
	}

	public static void main(String argv[]) throws Exception {
		new TCPServer().start();
	}

	public void run() {	
		try {
			while (isExecutando) {
				Socket conexaoCliente = servidor.accept();
				new Thread(new Conversa(conexaoCliente)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Servidor parou!");
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

	public void para() {
		isExecutando = false;
		try {
			servidor.close();
		} catch (IOException e) {
			throw new FileControlException(Excecao.ERRO_AO_PARAR_SERVIDOR);
		}
	}
}