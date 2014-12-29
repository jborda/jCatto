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

	public TCPServer() {
		try {
			servidor = new ServerSocket(6789);
		} catch (IOException e) {
			throw new FileControlException(Excecao.ERRO_AO_SUBIR_SERVIDOR);
		}
	}

	public void run() {
		try {
			while (isExecutando) {
				Socket conexaoCliente = servidor.accept();
				new Requisicao(conexaoCliente).start();
			}
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

}