package br.com.jmassucatto.filecontrol.server.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;

public class TCPServer extends Thread {
	
	public static void main(String argv[]) throws Exception {
		new TCPServer().start();
	}

	private boolean isExecutando = true;
	private ServerSocket servidor;

	public void run() {
		try {
			sobeServidor();
			executa();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Servidor parou!");
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

	private void sobeServidor() throws IOException {
		servidor = new ServerSocket(6789);
	}

	private void executa() throws IOException {
		while (isExecutando) {
			Socket conexaoCliente = esperaRequisicao();
			executaRequisicao(conexaoCliente);
		}
	}

	private Socket esperaRequisicao() throws IOException {
		Socket conexaoCliente = servidor.accept();
		return conexaoCliente;
	}

	private void executaRequisicao(Socket conexaoCliente) throws IOException {
		new Requisicao(conexaoCliente).start();
	}

}